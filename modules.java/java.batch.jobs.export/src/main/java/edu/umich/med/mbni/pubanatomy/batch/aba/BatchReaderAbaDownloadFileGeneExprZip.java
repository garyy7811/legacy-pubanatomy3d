package edu.umich.med.mbni.pubanatomy.batch.aba;

import edu.umich.med.mbni.pubanatomy.batch.domain.AbaSectionDataSet;
import edu.umich.med.mbni.pubanatomy.batch.meta.BatchItemFileAndMeta;
import edu.umich.med.mbni.pubanatomy.batch.repo.AbaSectionDataSetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/2/13
 * Time: 9:30 AM
 */
public class BatchReaderAbaDownloadFileGeneExprZip extends
        BatchReaderAbaDownload<BatchItemFileAndMeta<DownGridFileMetaGeneExprZip>>{

    private int currentSectionDataSetId;

    @Resource
    private AbaSectionDataSetService sectionDataSetService;


    @Override
    protected List<BatchItemFileAndMeta<DownGridFileMetaGeneExprZip>> convertPage( InputStream is ){

        BatchItemFileAndMeta<DownGridFileMetaGeneExprZip> batchItemFileAndMeta = new
                BatchItemFileAndMeta<DownGridFileMetaGeneExprZip>();
        batchItemFileAndMeta.setInputStream( is );
        DownGridFileMetaGeneExprZip zip = new DownGridFileMetaGeneExprZip();
        zip.setAbaDataSetId( currentSectionDataSetId );
        zip.setFromUrl( getCurrentPageUrl() );
        batchItemFileAndMeta.setFileMeta( zip );
        return new CopyOnWriteArrayList<BatchItemFileAndMeta<DownGridFileMetaGeneExprZip>>( new BatchItemFileAndMeta[]{ batchItemFileAndMeta } );
    }

    @Override
    protected void updateUrl(){

        Page<AbaSectionDataSet> ds = sectionDataSetService.findAll( new PageRequest( getPage(), getPageSize()) );
        if( ds.getContent() == null || ds.getContent().size() == 0 ){
            currentPageUrl = null;
        }
        else{
            currentSectionDataSetId = ds.getContent().get( 0 ).getSectionDataSetId();
            currentPageUrl = (  "http://api.brain-map.org/grid_data/download/" + currentSectionDataSetId +
                                "?include=intensity,density,energy" );
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception{
    }
}
