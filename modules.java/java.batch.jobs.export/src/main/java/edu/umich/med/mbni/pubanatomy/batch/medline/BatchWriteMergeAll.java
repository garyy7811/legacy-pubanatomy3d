package edu.umich.med.mbni.pubanatomy.batch.medline;

import edu.umich.med.mbni.pubanatomy.batch.domain.MedlineGzipLastMd5;
import edu.umich.med.mbni.pubanatomy.batch.repo.MedlineGzipLastMd5Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.batch.item.ItemWriter;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: flashflexpro@gmail.com
 * Date: 8/17/13
 * Time: 12:58 PM
 */
public class BatchWriteMergeAll implements ItemWriter<Object[]>{

    protected Log logger = LogFactory.getLog( getClass() );

    @Resource
    private MedlineAutoUpdateJobContext jobContext;

    @Resource
    private MedlineGzipLastMd5Service medlineGzipLastMd5Service;

    private int solrCommitPerNum = 1000;

    private SolrServer solrServer;

    @Override
    public void write( List<? extends Object[]> items ){
        logger.debug( " writing: " + items.size() + " citations" );
        solrServer = jobContext.getSolrServerAllMerge();
        solrCommitPerNum = jobContext.getAllMergeSolrCommitPer();
        for( int i = 0; i < items.size(); i++ ){
            Object[] gzipObjs = null;
            try{
                gzipObjs = items.get( i );
                handleEachGzipObjs( gzipObjs );
            }
            catch( Exception e ){
                e.printStackTrace();
                File f = ( File ) gzipObjs[ 2 ];
                logger.info( f.getAbsolutePath() + " exception:" + e.getMessage() );
            }
        }
    }

    private void handleEachGzipObjs( Object[] gzipObjs ) throws IOException, SolrServerException{
        //save
        ArrayList lstToSave = ( ArrayList ) gzipObjs[ 0 ];
        for( int j = 0; j < lstToSave.size(); j++ ){
            solrServer.addBean( lstToSave.get( j ) );
            if( j % solrCommitPerNum == solrCommitPerNum - 1 ){
                solrServer.commit();
            }
        }

        solrServer.commit();

        //delete
        ArrayList lstToDel = ( ArrayList ) gzipObjs[ 1 ];
        if( lstToDel.size() > 0 ){
            List<String> toDelLst = new ArrayList<>();
            logger.info( " deleting:" + lstToDel.size() + " citations!" );
            for( int j = 0; j < lstToDel.size(); j ++ ){
                toDelLst.add( ( String ) lstToDel.get( j ) );
            }
            solrServer.deleteById( toDelLst );
            solrServer.commit();
        }

        //update
        File eachGzipFile = ( File ) gzipObjs[ 2 ];


        MedlineGzipLastMd5 eachMd5 = medlineGzipLastMd5Service.findOne( eachGzipFile.getName() );
        if( eachMd5 == null ){
            eachMd5 = new MedlineGzipLastMd5();
            eachMd5.setGzipFileName( eachGzipFile.getName() );
        }

        eachMd5.setMD5( ( String ) gzipObjs[ 3 ] );
        medlineGzipLastMd5Service.save( eachMd5 );
        logger.info( eachGzipFile.getAbsolutePath() + " committed." );
    }

}
