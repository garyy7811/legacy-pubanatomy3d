package edu.umich.med.mbni.pubanatomy.batch.medline;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.core.io.Resource;

/**
 * User: flashflexpro@gmail.com
 * Date: 7/11/13
 * Time: 5:34 PM
 */
public class MedlineAutoUpdateScheduler{

    protected Log logger = LogFactory.getLog( getClass() );

    private static boolean thereIsALockFile = false;

    private Resource lockFile;

    @javax.annotation.Resource
    private JobRepository jobRepository;

    public Resource getLockFile(){
        return lockFile;
    }

    public void setLockFile( Resource lockFile ){
        this.lockFile = lockFile;
    }

    private JobLauncher jobLauncher;

    public JobLauncher getJobLauncher(){
        return jobLauncher;
    }

    public void setJobLauncher( JobLauncher jobLauncher ){
        this.jobLauncher = jobLauncher;
    }

    @javax.annotation.Resource(name = "autoUpdateSolr")
    private Job autoUpdateJob;

    private long counter = 0;

    private JobParameters jobParams;

    public void checkIfLockFileChanged() throws JobParametersInvalidException, JobExecutionAlreadyRunningException{
        boolean wasALockFile = thereIsALockFile;
        thereIsALockFile = lockFile.exists();
        if( wasALockFile && !thereIsALockFile ){
            if( jobParams != null ){
                JobExecution lastJobExe = jobRepository.getLastJobExecution( autoUpdateJob.getName(), jobParams );
                ExitStatus lastJobExeExitSt = lastJobExe.getExitStatus();
                if( lastJobExeExitSt == ExitStatus.UNKNOWN ){
                    logger.info( jobParams + " is still running, skip new job." );
                    return;
                }
            }

            while( true ){
                Exception ex = null;
                try{
                    jobParams = new JobParametersBuilder().addLong( "id", counter ).toJobParameters();
                    jobLauncher.run( autoUpdateJob, jobParams );
                }
                catch( JobRestartException e ){
                    ex = e;
                    logger.info( counter + " already exist, try next" );
                }
                catch( JobInstanceAlreadyCompleteException e ){
                    e.printStackTrace();
                    logger.info( "how can job is running: " + counter );
                    throw new Error( "!!!!" );
                }
                counter++;
                if( ex == null ){
                    return;
                }
            }
        }
    }
}