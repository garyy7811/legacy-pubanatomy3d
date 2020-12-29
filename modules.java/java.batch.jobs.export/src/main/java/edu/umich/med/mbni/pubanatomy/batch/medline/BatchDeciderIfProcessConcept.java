package edu.umich.med.mbni.pubanatomy.batch.medline;

import edu.umich.med.mbni.pubanatomy.batch.domain.MedlineGzipLastMd5;
import edu.umich.med.mbni.pubanatomy.batch.repo.MedlineGzipLastMd5Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * User: flashflexpro@gmail.com
 * Date: 9/12/13
 * Time: 12:25 PM
 */
public class BatchDeciderIfProcessConcept implements JobExecutionDecider{

    protected Log logger = LogFactory.getLog( getClass() );

    @Resource
    private MedlineAutoUpdateJobContext jobContext;

    @Resource
    private MedlineGzipLastMd5Service medlineGzipLastMd5Service;

    @Override
    public FlowExecutionStatus decide( JobExecution jobExecution, StepExecution stepExecution ){
        try{
            PMID2ConceptMeta meta = jobContext.getCurrentMeta();
            File conceptFile = meta.getFile().getFile();
            logger.info( "handling file:" + conceptFile.getAbsolutePath() );

            String currentLastModified = MedlineAutoUpdateJobContext.LAST_MODIFIED_PREFIX + conceptFile.lastModified();

            MedlineGzipLastMd5 g2m = medlineGzipLastMd5Service.findOne( meta.getFile().getFilename() );

            if( g2m != null && currentLastModified.equals( g2m.getMD5() ) ){
                logger.info( "ignore processed concept: " + conceptFile.getAbsolutePath() + "; mark:" + currentLastModified  );
                return new FlowExecutionStatus( "nope" );
            }

            logger.info( "start processing: " + conceptFile.getAbsolutePath() + currentLastModified  );

            //for future save
            if( g2m == null ){
                g2m = new MedlineGzipLastMd5();
                g2m.setGzipFileName( meta.getFile().getFilename() );
            }
            g2m.setMD5( currentLastModified );

            meta.setLastModifiedMark( g2m );
        }
        catch( IOException e ){
            e.printStackTrace();
            throw new Error( e.getMessage() );
        }

        return new FlowExecutionStatus( "process" );
    }
}
