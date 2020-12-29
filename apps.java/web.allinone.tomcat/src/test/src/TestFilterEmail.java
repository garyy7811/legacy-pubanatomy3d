import edu.umich.med.mbni.pubanatomy.solr.FacetSearching;
import edu.umich.med.mbni.pubanatomy.solr.medline.FacetSearchingMedline;
import edu.umich.med.mbni.pubanatomy.solr.medline.SolrBeanMedlineCitation;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: flashflexpro@gmail.com
 * Date: 11/7/13
 * Time: 2:59 PM
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = "/test-web.xml" )
public class TestFilterEmail{

    private static String jnlQstr = " (\"Nature Reviews Neuroscience" +
                                    "\") OR (\"Behavioral and Brain Sciences" +
                                    "\") OR (\"Molecular Psychiatry" +
                                    "\") OR (\"Nature Neuroscience" +
                                    "\") OR (\"Neuron" +
                                    "\") OR (\"PLoS Biology" +
                                    "\") OR (\"Annals of Neurology" +
                                    "\") OR (\"Brain" +
                                    "\") OR (\"Neuroscience & Biobehavioral Reviews" +
                                    "\") OR (\"Brain Research Reviews" +
                                    "\") OR (\"Biological Psychiatry" +
                                    "\") OR (\"Current Opinion in Neurobiology" +
                                    "\") OR (\"Journal of Neuroscience" +
                                    "\") OR (\"Cortex" +
                                    "\") OR (\"Cerebral Cortex" +
                                    "\") OR (\"Neuropsychopharmacology" +
                                    "\") OR (\"NeuroImage" +
                                    "\") OR (\"PLoS Computational Biology" +
                                    "\") OR (\"Journal of Cognitive Neuroscience" +
                                    "\") OR (\"Human Brain Mapping" +
                                    "\") OR (\"Current Alzheimer Research" +
                                    "\") OR (\"Neuropharmacology" +
                                    "\") OR (\"Hippocampus" +
                                    "\") OR (\"Journal of Cerebral Blood Flow & Metabolism" +
                                    "\") OR (\"Journal of Neurochemistry" +
                                    "\") OR (\"International Journal of Neural Systems" +
                                    "\") OR (\"Genes, Brain and Behavior" +
                                    "\") OR (\"Neuropsychologia" +
                                    "\") OR (\"Molecular and Cellular Neuroscience" +
                                    "\") OR (\"ASN Neuro" +
                                    "\") OR (\"Psychopharmacology" +
                                    "\") OR (\"Journal of Psychopharmacology" +
                                    "\") OR (\"European Journal of Neuroscience" +
                                    "\") OR (\"Neurogenetics" +
                                    "\") OR (\"Neural Development" +
                                    "\") OR (\"Behavioural Brain Research" +
                                    "\") OR (\"Neurosurgery" +
                                    "\") OR (\"The Cerebellum" +
                                    "\") OR (\"Neuroscience" +
                                    "\") OR (\"Journal of Neurophysiology" +
                                    "\") OR (\"BMC Neuroscience" +
                                    "\") OR (\"Neuroinformatics" +
                                    "\") OR (\"Behavior Genetics" +
                                    "\") OR (\"Journal of Neuroscience Research" +
                                    "\") OR (\"Synapse" +
                                    "\") OR (\"Journal of Molecular Neuroscience" +
                                    "\") OR (\"Physiology & Behavior" +
                                    "\") OR (\"Brain and Cognition" +
                                    "\") OR (\"Journal of Vision" +
                                    "\") OR (\"Social Neuroscience" +
                                    "\") OR (\"Journal of Neural Engineering" +
                                    "\") OR (\"Brain Research" +
                                    "\") OR (\"Frontiers in Computational Neuroscience" +
                                    "\") OR (\"Neuropsychobiology" +
                                    "\") OR (\"Brain Research Bulletin" +
                                    "\") OR (\"Vision Research" +
                                    "\") OR (\"Journal of Computational Neuroscience" +
                                    "\") OR (\"Behavioral and Brain Functions" +
                                    "\") OR (\"Neural Computation" +
                                    "\") OR (\"Journal of NeuroVirology" +
                                    "\") OR (\"Psychiatric Genetics" +
                                    "\") OR (\"Neuroscience Letters" +
                                    "\") OR (\"Neural Networks" +
                                    "\") OR (\"Journal of Neurogenetics" +
                                    "\") OR (\"Intl Journal of Developmental Neuroscience" +
                                    "\") OR (\"NeuroReport" +
                                    "\") OR (\"Proceedings of the National Academy of Sciences" +
                                    "\") OR (\"Physiological genomics" +
                                    "\") ";

    private static final String q = "query_fullText:(" +
                                    " ( \"brain\" or \"cns\" OR \"neuron\" OR \"neuronal\" OR \"glioma\"  OR ( " +
                                    "\"glial\" ) ) " +
                                    " AND ( \"array\" or \"chip\" or \"genome\" or \"imaging\" or \"molecule\" or " +
                                    "\"metabolite\" )" +
                                    ")".toLowerCase() +

                                    " AND articleJournalPubDateYear:([ 2008 TO * ])" +

                                    " AND articleJournalTitle:(" + jnlQstr.toLowerCase() +
                                    ")";


    private static final String yy = "(query_fullText:(((((((((\"sequencing\")) OR ((\"expression profile\"))) OR (" +
                                     "(\"genome\"))) OR ((\"genomics\"))) OR ((\"microarray\"))) OR ((\"array\"))) OR" +
                                     " " +
                                     "((\"informatics\"))) OR (((((((\"mutations\")) OR ((\"metabolite\"))) OR (" +
                                     "(\"metabolites\"))) OR (((\"mutation\")) OR ((\"microrna\")))) OR ((" +
                                     "(\"neuroinformatics\")) OR ((\"micrornas\")))) OR ((\"bioinformatics\")))) AND " +
                                     "(" +
                                     "query_fullText:((\"brain\") OR ( \"cns\" )  OR \"neuron\" OR \"neuronal\" OR " +
                                     "\"glioma\"  OR ( \" +\n" +
                                     "                                    \"\"glial\" ) ) " +
                                     ")" +
                                     ") AND " +
                                     " articleJournalPubDateYear:([ 2008 TO * ])" +
                                     " AND articleAffiliation:(" +
                                     "(\"brain\") " +
                                     "OR ( \"neurology\" ) " +
                                     "OR ( \"mental\" )  " +
                                     "OR ( \"psychiatry\" ) " +
                                     "OR ( \"neurobiology\" )" +
                                     "OR ( \"neuroscience\" )" +
                                     "OR ( \"psychology\" )" +
                                     "OR ( \"disorder\" )" +
                                     "OR ( \"tumor\" )" +
                                     "OR ( \"cancer\" )" +
                                     "OR ( \"radiology\" )" +
                                     ") ";


    @Resource
    private FacetSearchingMedline fsMedline;

    @Test
    public void tstAccess() throws IllegalAccessException, ParserConfigurationException, IntrospectionException,
            IOException, SAXException, InvocationTargetException, SolrServerException{
        testFilterEmails( yy );
        testFilterEmails( q );

        List umishlist = new ArrayList();
        for( int i = 0; i < emailList.size(); i++ ){
            String s = emailList.get( i );
            System.out.println( s );
            if( s.indexOf( "umich." ) > 0 ){
                umishlist.add( s );
            }
        }

        System.out.println( emailList.size() );


        System.out.println( "******************************" );

        for( int u = 0; u < umishlist.size(); u++ ){
            String s = ( String ) umishlist.get( u );
            System.out.println( s );
        }
        System.out.println( umishlist.size() );
    }

    private void testFilterEmails( String qStr ) throws SAXException, ParserConfigurationException,
            SolrServerException, IOException, IntrospectionException, InvocationTargetException, IllegalAccessException{

        BeanInfo solrBeanInfo = Introspector.getBeanInfo( SolrBeanMedlineCitation.class );

        PropertyDescriptor[] solrPrpts = solrBeanInfo.getPropertyDescriptors();

        FacetSearching.FacetSearchResult solrSearchRslt = null;
        int startRow = 0;
        int recordNumPerPage = 3000;
        while( true ){
            solrSearchRslt = fsMedline.searchWithFacets( qStr, startRow, recordNumPerPage, new HashMap<String,
                    Object[]>(), "pmid", true );

            System.out.println( startRow + solrSearchRslt.getrQueryRecords().length + "/" + solrSearchRslt
                    .getrNumFound() );

            for( int i = 0; i < solrSearchRslt.getrQueryRecords().length; i++ ){
                SolrBeanMedlineCitation c = ( SolrBeanMedlineCitation ) solrSearchRslt.getrQueryRecords()[ i ];


                for( int j = 0; j < solrPrpts.length; j++ ){
                    PropertyDescriptor solrPrpt = solrPrpts[ j ];

                    if( !solrPrpt.getName().toLowerCase().startsWith( "query_" ) && ( String.class.equals( solrPrpt
                            .getPropertyType() ) || solrPrpt.getPropertyType().isArray()

                    ) ){

                        PropertyDescriptor destPrpDesc = new PropertyDescriptor( solrPrpt.getName(),
                                SolrBeanMedlineCitation.class );
                        Object tmpRt = destPrpDesc.getReadMethod().invoke( c );
                        if( tmpRt instanceof String ){
                            ripEmail( ( String ) tmpRt );
                        }
                        else if( tmpRt instanceof String[] ){
                            String[] ttrr = ( String[] ) tmpRt;
                            for( int p = 0; p < ttrr.length; p++ ){
                                ripEmail( ttrr[ p ] );
                            }
                        }
                    }
                }
            }

            if( solrSearchRslt.getrQueryRecords().length < recordNumPerPage ){
                break;
            }
            startRow += recordNumPerPage;
        }


    }

    private static final String EMAIL_PATTERN = "([\\w-\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})";

    private Pattern p = Pattern.compile( EMAIL_PATTERN );

    private ArrayList<String> emailList = new ArrayList<>();

    private void ripEmail( String tmpRt ){
        Matcher mc = p.matcher( tmpRt );
        while( mc.find() ){
            String rr = mc.group();
            if( emailList.indexOf( rr ) < 0 ){
                emailList.add( rr );
            }
        }
    }

    @Test
    public void testReg(){
        p = Pattern.compile( "" );
        Matcher mc = p.matcher( "asdfsdf yanggang@umich.edu yang.gang.med@umich.edu" );
        while( mc.find() ){
            System.out.println( mc.group() );
        }
    }

}
