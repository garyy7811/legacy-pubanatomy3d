package edu.umich.med.mbni.pubanatomy.batch.medline;

import edu.umich.med.mbni.pubanatomy.batch.domain.MedlineGzipLastMd5;
import edu.umich.med.mbni.pubanatomy.batch.repo.MedlineGzipLastMd5Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/7/13
 * Time: 2:39 PM
 */
public class BatchTaskletChkMedlineGzipMd5 implements Tasklet{

    public BatchTaskletChkMedlineGzipMd5( List<org.springframework.core.io.Resource> medlineGzipFolders ){
        this.medlineGzipFolders = medlineGzipFolders;
    }

    protected Log logger = LogFactory.getLog( getClass() );

    private List<org.springframework.core.io.Resource> medlineGzipFolders;

    @Resource
    private MedlineAutoUpdateJobContext jobContext;

    @Resource
    private MedlineGzipLastMd5Service medlineGzipLastMd5Service;

    @Override
    public RepeatStatus execute( StepContribution contribution, ChunkContext chunkContext ) throws Exception{
        List<File> allMedlineGzipFiles = new ArrayList<>();


        logger.info( "scanning " + medlineGzipFolders.size() + " folders:" );

        for( int i = 0; i < medlineGzipFolders.size(); i++ ){
            org.springframework.core.io.Resource eachMedlineFolder = medlineGzipFolders.get( i );
            if( !eachMedlineFolder.exists() || !eachMedlineFolder.getFile().isDirectory() ){
                throw new Error( eachMedlineFolder.getFile().getAbsolutePath() + " Not " + "Found!" );
            }

            File[] eachGzFiles = eachMedlineFolder.getFile().listFiles( new FilenameFilter(){
                @Override
                public boolean accept( File dir, String name ){
                    return name.toLowerCase().endsWith( ".gz" );
                }
            } );
            logger.info( " adding " + eachGzFiles.length + " .gz files inside:" + eachMedlineFolder.getFile().getAbsolutePath() );
            allMedlineGzipFiles.addAll( Arrays.asList( eachGzFiles ) );
        }
        Collections.sort( allMedlineGzipFiles, new Comparator<File>(){
            @Override
            public int compare( File o1, File o2 ){
                return o1.getName().compareTo( o2.getName() );
            }
        } );

        File[] allChangedGzipFiles;

        Page<MedlineGzipLastMd5> allMedlineLastMd5s = medlineGzipLastMd5Service.findAll( new PageRequest( 0, 1 ) );
        logger.info( allMedlineLastMd5s.getTotalElements() + " records found in DB");
        if( allMedlineLastMd5s.getTotalElements() <= 0 ){
            allChangedGzipFiles = allMedlineGzipFiles.toArray( new File[ allMedlineGzipFiles.size() ] );
        }
        else{

            ArrayList<File> notProcessedGzipFileList = new ArrayList<>();

            for( int i = 0; i < allMedlineGzipFiles.size(); i++ ){
                File gzipFile = allMedlineGzipFiles.get( i );
                if( !isGzipFileProcessed( gzipFile ) ){
                    notProcessedGzipFileList.add( gzipFile );
                }
            }
            allChangedGzipFiles = notProcessedGzipFileList.toArray( new File[ notProcessedGzipFileList.size() ] );
        }

        logger.info( allChangedGzipFiles.length + " .gz files changed ... " );

        jobContext.setChangedMedlineGzips( allChangedGzipFiles );
        jobContext.setInitializingMedlineFrom0( allMedlineLastMd5s.getTotalElements() == 0 );

        return RepeatStatus.FINISHED;
    }

    private boolean isGzipFileProcessed( File gzipFile ) throws IOException{
        MedlineGzipLastMd5 g2m = medlineGzipLastMd5Service.findOne( gzipFile.getName() );
        if( g2m == null ){
            return false;
        }
        return g2m.getMD5().equals( MedlineAutoUpdateJobContext.getGzipMd5( gzipFile ) );
    }
}