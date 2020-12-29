package edu.umich.med.mbni.pubanatomy.batch.medline;

import edu.umich.med.mbni.pubanatomy.solr.medline.SolrBeanMedlineCitation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.apache.lucene.analysis.standard.ClassicTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.springframework.batch.item.ItemProcessor;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/30/13
 * Time: 1:42 PM
 */
public class BatchProcessorMedlineQueryField implements ItemProcessor<Object[], Object[]>{

    public BatchProcessorMedlineQueryField() throws IntrospectionException{
        solrBeanInfo = Introspector.getBeanInfo( SolrBeanMedlineCitation.class );
        solrPrpts = solrBeanInfo.getPropertyDescriptors();
    }

    private static PropertyDescriptor[] solrPrpts;
    private static BeanInfo solrBeanInfo;
    protected Log logger = LogFactory.getLog( getClass() );

    @Override
    public Object[] process( Object[] arr ) throws Exception{
        ArrayList lst = ( ArrayList ) arr[ 0 ];
        logger.debug( " processing:" + lst.size() + " citations"  );
        for( int i = 0; i < lst.size(); i++ ){
            processEachBean( ( SolrBeanMedlineCitation ) lst.get( i ) );
        }
        return arr;
    }

    private SolrBeanMedlineCitation processEachBean( SolrBeanMedlineCitation b) throws Exception{
        b.setQuery_fullText( null );
        for( int i = 0; i < solrPrpts.length; i++ ){
            PropertyDescriptor solrPrpt = solrPrpts[ i ];
            if( solrPrpt.getWriteMethod() == null || "query_fullText".equalsIgnoreCase( solrPrpt.getName() )){
                continue;
            }

            if( solrPrpt.getName().toLowerCase().startsWith( "query_" ) ){
                copyField( b, solrPrpt.getName().substring( 6 ), solrPrpt.getName(), false );
            }
            copyField( b, solrPrpt.getName(), "query_fullText", true );
        }

        return b;
    }

    private void copyField( SolrBeanMedlineCitation c, String src, String dest, boolean append ) throws IntrospectionException,
            InvocationTargetException, IllegalAccessException, IOException{

        PropertyDescriptor srcPrpDesc = new PropertyDescriptor( src, SolrBeanMedlineCitation.class );
        Method srcRead = srcPrpDesc.getReadMethod();
        Object fv = srcRead.invoke( c );

        String[] rt = null;
        boolean isKeyWord = ( ArrayUtils.indexOf( SolrBeanMedlineCitation.prpSentcs, src ) < 0 );
        if( ArrayUtils.indexOf( SolrBeanMedlineCitation.prpLongs, src ) >= 0 ){
            if( fv instanceof Long ){
                rt = tokenizeStringArray( new String[]{ ( ( Long ) fv ).toString() }, isKeyWord );
            }
            else{
                if( fv != null ){
                    rt = tokenizeStringArray( new String[]{ fv.toString() }, isKeyWord );
                }
            }
        }
        else if( String.class.equals(  srcPrpDesc.getPropertyType() ) ){
            rt = tokenizeStringArray( new String[]{ ( String ) fv }, isKeyWord );
        }
        else if( srcPrpDesc.getPropertyType().isArray() ){
            rt = tokenizeStringArray( ( String[] ) fv, isKeyWord );
        }
        else{
            throw new RuntimeException( "unknow property" );
        }
        if( rt != null ){
            PropertyDescriptor destPrpDesc = new PropertyDescriptor( dest, SolrBeanMedlineCitation.class );
            if( append ){
                String[] existing = ( String[] ) destPrpDesc.getReadMethod().invoke( c );
                if( existing != null ){
                    rt = ArrayUtils.addAll( rt, existing );
                }
            }
            destPrpDesc.getWriteMethod().invoke( c, new Object[]{ rt } );
        }
    }

    public String[] tokenizeStringArray( String[] inputStrArr, boolean isKeyWord ) throws IOException{
        ArrayList<String> lst = new ArrayList<>();
        if( inputStrArr != null ){
            for( int i = inputStrArr.length - 1; i >= 0; i-- ){
                ArrayList<String> tokens = sentenceToTokens( inputStrArr[ i ], isKeyWord );
                if( tokens != null ){
                    lst.addAll( tokens );
                }
            }
        }
        return lst.toArray( new String[ lst.size() ] );
    }

    private static ThreadLocal<Analyzer> classicTokenizer = new ThreadLocal<Analyzer>(){
        @Override
        protected Analyzer initialValue(){
            return new Analyzer(){
                @Override
                protected TokenStreamComponents createComponents( String is, Reader reader ){
                    return new TokenStreamComponents( new ClassicTokenizer( Version.LUCENE_43, reader ) );
                }
            };
        }
    };

    private static ThreadLocal<Analyzer> keywordTokenizer = new ThreadLocal<Analyzer>(){
        @Override
        protected Analyzer initialValue(){
            return new Analyzer(){
                @Override
                protected TokenStreamComponents createComponents( String fieldName, Reader reader ){
                    return new TokenStreamComponents( new KeywordTokenizer( reader ) );
                }
            };
        }
    };

    public ArrayList<String> sentenceToTokens( String s, boolean isKeyword ) throws IOException{
        ArrayList<String> rt = new ArrayList<>();
        if( s == null || s.length() == 0 ){
            return rt;
        }

        TokenStream ts = ( isKeyword ? keywordTokenizer : classicTokenizer ).get().tokenStream( null, new StringReader( s ) );

        CharTermAttribute termAtt = ts.addAttribute( CharTermAttribute.class );
        try{
            ts.reset();
            while( ts.incrementToken() ){
                rt.add( termAtt.toString() );
            }
            ts.end();
        }
        finally{
            ts.close();
        }
        return rt;
    }
}
