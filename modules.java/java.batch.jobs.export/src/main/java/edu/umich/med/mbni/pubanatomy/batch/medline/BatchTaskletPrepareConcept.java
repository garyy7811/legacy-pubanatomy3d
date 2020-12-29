package edu.umich.med.mbni.pubanatomy.batch.medline;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import javax.annotation.Resource;

/**
 * User: flashflexpro@gmail.com
 * Date: 8/6/13
 * Time: 2:49 PM
 */
public class BatchTaskletPrepareConcept implements Tasklet{

    public static final String currentPmid2ConceptfileName = "currentPmid2ConceptfileName";

    public BatchTaskletPrepareConcept( PMID2ConceptMeta meta ){
        this.meta = meta;
    }

    protected PMID2ConceptMeta meta;

    @Resource
    private MedlineAutoUpdateJobContext jobContext;

    @Override
    public RepeatStatus execute( StepContribution contribution, ChunkContext chunkContext ) throws Exception{
        jobContext.setCurrentMeta( meta );

        chunkContext
                .getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .put( currentPmid2ConceptfileName, meta.getFile().getFile().getAbsolutePath() );
        return RepeatStatus.FINISHED;
    }
}
