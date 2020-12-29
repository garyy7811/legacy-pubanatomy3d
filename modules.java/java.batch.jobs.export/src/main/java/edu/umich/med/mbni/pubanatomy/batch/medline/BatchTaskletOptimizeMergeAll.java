package edu.umich.med.mbni.pubanatomy.batch.medline;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: flashflexpro@gmail.com
 * Date: 9/25/13
 * Time: 7:53 PM
 */
@Component
public class BatchTaskletOptimizeMergeAll implements Tasklet{

    @Resource
    private MedlineAutoUpdateJobContext jobContext;

    protected Log logger = LogFactory.getLog( getClass() );

    @Override
    public RepeatStatus execute( StepContribution contribution, ChunkContext chunkContext ) throws Exception{
        logger.info( "merge all server start optimizing ..." );
        jobContext.getSolrServerAllMerge().optimize();
        logger.info( "merge all server optimization finished" );
        return RepeatStatus.FINISHED;
    }
}
