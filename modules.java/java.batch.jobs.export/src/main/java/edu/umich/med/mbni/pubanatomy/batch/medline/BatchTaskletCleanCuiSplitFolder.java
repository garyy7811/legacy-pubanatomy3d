package edu.umich.med.mbni.pubanatomy.batch.medline;


import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/7/13
 * Time: 2:39 PM
 */
@Component
public class BatchTaskletCleanCuiSplitFolder implements Tasklet{

    @javax.annotation.Resource
    private MedlineAutoUpdateJobContext jobContext;

    @Override
    public RepeatStatus execute( StepContribution contribution, ChunkContext chunkContext ) throws Exception{

        if( jobContext.getCuiSplitFile().exists() ){
            Assert.isTrue( jobContext.getCuiSplitFile().getFile().isDirectory() );

            File[] lsttodel = jobContext.getCuiSplitFile().getFile().listFiles();
            for( int i = 0; i < lsttodel.length; i++ ){
                File file = lsttodel[ i ];
                Assert.isTrue( file.delete() );
            }
        }
        else{
            Assert.isTrue( jobContext.getCuiSplitFile().getFile().mkdirs() );
        }
        return RepeatStatus.FINISHED;
    }
}
