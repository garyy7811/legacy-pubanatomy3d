package edu.umich.med.mbni.pubanatomy.batch.medline;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.core.CoreContainer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: flashflexpro@gmail.com
 * Date: 7/19/13
 * Time: 12:22 PM
 */
public class MedlineAutoUpdateJobContext{

    private int solrCommitPer;

    public MedlineAutoUpdateJobContext( String mergeAllSolrHomePath, String mergAllSolrCoreName,
                                        Resource cuiSplitFile ) throws
            FileNotFoundException{
        System.setProperty( "solr.solr.home", mergeAllSolrHomePath );
        CoreContainer coreContainer = new CoreContainer.Initializer().initialize();
        solrServerAllMerge = new EmbeddedSolrServer( coreContainer, mergAllSolrCoreName );

        this.cuiSplitFile = cuiSplitFile;
    }

    public MedlineAutoUpdateJobContext( SolrServer solrServerAllMerge,
                                        Resource cuiSplitFile ){
        this.solrServerAllMerge = solrServerAllMerge;

        this.cuiSplitFile = cuiSplitFile;
    }

    private boolean initializingMedlineFrom0;

    private File[] changedMedlineGzips;

    private HashMap<String, ExecutionContext> medlineGzipsContextHashMap;

    private SolrServer solrServerAllMerge;

    private Resource cuiSplitFile;

    private List<PMID2ConceptMeta> allMetas;

    private PMID2ConceptMeta currentMeta;

    private boolean deleteGzipFile = false;

    public void destroy(){
        for( int i = 0; i < allMetas.size(); i++ ){
            PMID2ConceptMeta pmid2ConceptMeta = allMetas.get( i );
            pmid2ConceptMeta.destroy();
            solrServerAllMerge.shutdown();
        }
        changedMedlineGzips = null;
    }

    public boolean isInitializingMedlineFrom0(){
        return initializingMedlineFrom0;
    }

    public void setInitializingMedlineFrom0( boolean initializingMedlineFrom0 ){
        this.initializingMedlineFrom0 = initializingMedlineFrom0;
    }

    public File[] getChangedMedlineGzips(){
        return changedMedlineGzips;
    }

    public void setChangedMedlineGzips( File[] changedMedlineGzips ){
        this.changedMedlineGzips = changedMedlineGzips;
    }

    public HashMap<String, ExecutionContext> getMedlineGzipsContextHashMap(){
        return medlineGzipsContextHashMap;
    }

    public void setMedlineGzipsContextHashMap( HashMap<String, ExecutionContext> medlineGzipsContextHashMap ){
        this.medlineGzipsContextHashMap = medlineGzipsContextHashMap;
    }

    public SolrServer getSolrServerAllMerge(){
        return solrServerAllMerge;
    }


    public Resource getCuiSplitFile(){
        return cuiSplitFile;
    }

    public void setCurrentMeta( PMID2ConceptMeta currentMeta ){
        this.currentMeta = currentMeta;
    }

    public PMID2ConceptMeta getCurrentMeta(){
        return currentMeta;
    }

    public void setAllMetas( List<PMID2ConceptMeta> allMetas ){
        this.allMetas = allMetas;
    }

    public List<PMID2ConceptMeta> getAllMetas(){
        return allMetas;
    }

    public boolean isDeleteGzipFile(){
        return deleteGzipFile;
    }

    public void setDeleteGzipFile( boolean deleteGzipFile ){
        this.deleteGzipFile = deleteGzipFile;
    }

    public int getAllMergeSolrCommitPer(){
        return solrCommitPer;
    }

    public void setAllMergeSolrCommitPer( int solrCommitPer ){
        this.solrCommitPer = solrCommitPer;
    }

    private static Pattern md5Reg = Pattern.compile( " = .{32,}" );
    public static String getGzipMd5( File gzip ) throws IOException{
        String md5AbsolutePath = gzip.getAbsolutePath() + ".md5";
        if( !new File( md5AbsolutePath ).exists() ){
            return MedlineAutoUpdateJobContext.LAST_MODIFIED_PREFIX + gzip.lastModified();
        }

        InputStream fis;
        BufferedReader br;
        String line = null;
        fis = new FileInputStream( md5AbsolutePath );
        br = new BufferedReader( new InputStreamReader( fis ) );

        String tmp;
        while( ( tmp = br.readLine() ) != null ){
            line = tmp;
        }

        br.close();
        Matcher matcher = md5Reg.matcher( line );
        matcher.find();
        return matcher.group().substring( 3 );
    }

    public static final String LAST_MODIFIED_PREFIX = "lastModified:";
}