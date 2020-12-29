package edu.umich.med.mbni.pubanatomy.batch.medline;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: flashflexpro@gmail.com
 * Date: 8/6/13
 * Time: 2:49 PM
 */
@Component
public class BatchDeciderProcessOrSkipMedlineGzip implements JobExecutionDecider{

    @Resource
    private MedlineAutoUpdateJobContext jobContext;

    @Override
    public FlowExecutionStatus decide( JobExecution jobExecution, StepExecution stepExecution ){
        if( jobContext.isInitializingMedlineFrom0()
            || ( jobContext.getChangedMedlineGzips() != null && jobContext.getChangedMedlineGzips().length > 0 )){
            return new FlowExecutionStatus( "process" );
        }

        return new FlowExecutionStatus( "n" );
    }
}
