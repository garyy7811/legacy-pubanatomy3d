package edu.umich.med.mbni.pubanatomy.batch.medline;

import edu.umich.med.mbni.pubanatomy.batch.repo.MedlineGzipLastMd5Service;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import javax.annotation.Resource;

/**
 * User: flashflexpro@gmail.com
 * Date: 9/12/13
 * Time: 2:17 PM
 */
public class BatchTaskletSaveConceptStatus implements Tasklet{

    @Resource
    private MedlineAutoUpdateJobContext jobContext;

    @Resource
    private MedlineGzipLastMd5Service medlineGzipLastMd5Service;

    @Override
    public RepeatStatus execute( StepContribution contribution, ChunkContext chunkContext ) throws Exception{
        medlineGzipLastMd5Service.save( jobContext.getCurrentMeta().getLastModifiedMark() );
        jobContext.getCurrentMeta().getSolrServer().optimize();
        return RepeatStatus.FINISHED;
    }
}
