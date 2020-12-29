package edu.umich.med.mbni.pubanatomy.batch.medline;

import edu.umich.med.mbni.pubanatomy.batch.medline.nihmedline_dtd_nlmmedlinecitationset_130501.MedlineCitationSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.util.ClassUtils;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.*;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/7/13
 * Time: 2:39 PM
 */
public abstract class BatchReaderMedlineGzip<T> extends AbstractItemCountingItemStreamItemReader{

    public BatchReaderMedlineGzip(){
        setName( ClassUtils.getShortName( getClass() ) );
    }

    protected Log logger = LogFactory.getLog( getClass() );

    private List<File> medlineGzipFiles;

    @Override
    protected void doOpen() throws Exception{
    }
    @Override
    protected void doClose() throws Exception{
    }

    @Override
    public void open( ExecutionContext executionContext ) throws ItemStreamException{
        medlineGzipFiles = ( List<File> ) executionContext.get( BatchPartitionerMedlineGzip.EXE_CTX_GZIP_FILE_LST );
        logger.debug( medlineGzipFiles.size() + " gzip files opened!" );
        super.open( executionContext );
    }

    private static ThreadLocal<Unmarshaller> unmarshaller = new ThreadLocal<Unmarshaller>(){
        @Override
        protected Unmarshaller initialValue(){
            String pkgStr = "edu.umich.med.mbni.pubanatomy.batch.medline.nihmedline_dtd_nlmmedlinecitationset_130501";
            try{
                return JAXBContext.newInstance( pkgStr ).createUnmarshaller();
            }
            catch( JAXBException e ){
                e.printStackTrace();
                throw new Error( e.getMessage() );
            }
        }
    };

    private static ThreadLocal<SAXParser> saxParser = new ThreadLocal<SAXParser>(){
        @Override
        protected SAXParser initialValue(){
            try{
                SAXParserFactory spf = SAXParserFactory.newInstance();
                spf.setFeature( "http://apache.org/xml/features/validation/schema", false );
                spf.setFeature( "http://apache.org/xml/features/nonvalidating/load-external-dtd", false );
                return spf.newSAXParser();
            }
            catch( Exception e ){
                e.printStackTrace();
                throw new Error( e.getMessage() );
            }
        }
    };

    @Override
    protected void jumpToItem( int itemIndex ) throws Exception{
        setCurrentItemCount( itemIndex );
    }

    @Override
    protected T doRead() throws Exception{
        if( getCurrentItemCount() > medlineGzipFiles.size() ){
            return null;
        }
        File f = medlineGzipFiles.get( getCurrentItemCount() - 1 );
        logger.debug( "reading gzip: " + f.getAbsolutePath() );

        if( !f.getName().endsWith( ".gz" ) ){
            throw new RuntimeException( "expecting .gz file: " + f.getAbsoluteFile() );
        }

        GZIPInputStream gzipInputStream = new GZIPInputStream( new FileInputStream( f ) );
        SAXSource saxSource = new SAXSource( saxParser.get().getXMLReader(), new InputSource( gzipInputStream ) );
        return processMedlineCitationSet( ( MedlineCitationSet ) unmarshaller.get().unmarshal( saxSource ), f );
    }


    abstract protected T processMedlineCitationSet( MedlineCitationSet mcSet, File gzip );

}
