package edu.umich.med.mbni.pubanatomy.batch.medline;

import edu.umich.med.mbni.pubanatomy.batch.domain.MedlineGzipLastMd5;
import edu.umich.med.mbni.pubanatomy.batch.repo.MedlineGzipLastMd5Service;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import javax.annotation.Resource;
import java.io.*;
import java.util.Calendar;
import java.util.zip.GZIPInputStream;

/**
 * User: flashflexpro@gmail.com
 * Date: 8/6/13
 * Time: 2:49 PM
 */
public class BatchTaskletPrepareConceptFtpGene extends BatchTaskletPrepareConcept{

    protected Log logger = LogFactory.getLog( getClass() );

    @Resource
    private MedlineGzipLastMd5Service medlineGzipLastMd5Service;

    public BatchTaskletPrepareConceptFtpGene( PMID2ConceptMeta meta ){
        super( meta );
    }

    @Override
    public RepeatStatus execute( StepContribution contribution, ChunkContext chunkContext ) throws Exception{
        try{
            FTPClient fc = new FTPClient();
            fc.connect( "ftp.ncbi.nlm.nih.gov" );
            fc.login( "anonymous", "" );
            fc.setFileType( FTP.BINARY_FILE_TYPE );
            fc.enterLocalPassiveMode();

            String gene2pmidPath = "/gene/DATA/gene2pubmed.gz";
            FTPFile fFile = fc.listFiles( gene2pmidPath )[ 0 ];

            Calendar timestamp = fFile.getTimestamp();
            logger.info( "NCBI gene to pmid file time stamp:" + timestamp.getTime() );

            long remoteFileLastModified = timestamp.getTimeInMillis();

            String currentLastModified = MedlineAutoUpdateJobContext.LAST_MODIFIED_PREFIX + remoteFileLastModified;

            MedlineGzipLastMd5 g2m = medlineGzipLastMd5Service.findOne( meta.getFile().getFilename() );

            if( g2m == null || !currentLastModified.equals( g2m.getMD5() ) ){
                logger.info( ">>>>NCBI gene to pmid file start downloading" );

                byte[] buffer = new byte[ 10240 ];

                GZIPInputStream gzis = new GZIPInputStream( fc.retrieveFileStream( gene2pmidPath ) );
                FileOutputStream out = new FileOutputStream( meta.getFile().getFile() );

                int len;
                while( ( len = gzis.read( buffer ) ) > 0 ){
                    out.write( buffer, 0, len );
                }

                gzis.close();
                out.close();

                logger.info( "<<<<NCBI gene to pmid file downloaded " );
                if( !meta.getFile().getFile().setLastModified( remoteFileLastModified ) ){
                    throw new RuntimeException( "Can't set Gene Meta File's last Modified time" );
                }
            }
            fc.logout();
            fc.disconnect();
            logger.info( "NCBI ftp disconnected." );
        }
        catch( Exception e ){
            e.printStackTrace();
            logger.info( "NCBI gene to pmid file update error:" );
            logger.info( ExceptionUtils.getMessage( e ) );
        }

        return super.execute( contribution, chunkContext );
    }
}
