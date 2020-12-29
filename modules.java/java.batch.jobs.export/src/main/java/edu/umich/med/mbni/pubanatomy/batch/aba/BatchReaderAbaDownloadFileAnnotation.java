package edu.umich.med.mbni.pubanatomy.batch.aba;

import edu.umich.med.mbni.pubanatomy.batch.meta.BatchItemFileAndMeta;
import org.springframework.util.Assert;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/2/13
 * Time: 9:30 AM
 */
public class BatchReaderAbaDownloadFileAnnotation extends
        BatchReaderAbaDownload<BatchItemFileAndMeta<DownGridFileMetaAnnotation>>{


    private String url;
    private int resolutionUm = -1;
    private int ontologyId = -1;


    public String getUrl(){
        return url;
    }

    public void setUrl( String url ){
        this.url = url;
    }

    public int getResolutionUm(){
        return resolutionUm;
    }

    public void setResolutionUm( int resolutionUm ){
        this.resolutionUm = resolutionUm;
    }

    public int getOntologyId(){
        return ontologyId;
    }

    public void setOntologyId( int ontologyId ){
        this.ontologyId = ontologyId;
    }

    @Override
    protected List<BatchItemFileAndMeta<DownGridFileMetaAnnotation>> convertPage( InputStream is ){
        BatchItemFileAndMeta<DownGridFileMetaAnnotation> batchItemFileAndMeta = new
                BatchItemFileAndMeta<DownGridFileMetaAnnotation>();
        batchItemFileAndMeta.setInputStream( is );
        DownGridFileMetaAnnotation fileMeta = new DownGridFileMetaAnnotation();
        fileMeta.setFromUrl( getCurrentPageUrl() );
        fileMeta.setAbaOntologyId( ontologyId );
        fileMeta.setResolutionUm( resolutionUm );
        batchItemFileAndMeta.setFileMeta( fileMeta );
        return new CopyOnWriteArrayList<BatchItemFileAndMeta<DownGridFileMetaAnnotation>>( new
                BatchItemFileAndMeta[]{ batchItemFileAndMeta } );
    }

    @Override
    protected void updateUrl(){
        currentPageUrl = ( getPage() == 0 ? url : null );
    }

    @Override
    public void afterPropertiesSet() throws Exception{
        Assert.notNull( url );
        Assert.state( ontologyId > 0 );
        Assert.state( resolutionUm > 0 );
        Assert.state( getPageSize() == 1 );
    }

    @Override
    public int getPageSize(){
        return 1;
    }
}
