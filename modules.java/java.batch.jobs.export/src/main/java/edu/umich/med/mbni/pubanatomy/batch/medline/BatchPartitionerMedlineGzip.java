package edu.umich.med.mbni.pubanatomy.batch.medline;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * User: flashflexpro@gmail.com
 * Date: 7/19/13
 * Time: 11:52 AM
 */
@Component
public class BatchPartitionerMedlineGzip implements Partitioner{

    public static final String EXE_CTX_GZIP_FILE_LST = "EXE_CTX_GZIP_FILE_LST";

    @Resource
    private MedlineAutoUpdateJobContext jobContext;

    @Override
    public Map<String, ExecutionContext> partition( int gridSize ){
        if( jobContext.getMedlineGzipsContextHashMap() == null ){
            HashMap<String, ExecutionContext> contextHashMap = new HashMap<>();

            ExecutionContext ctx = null;
            ArrayList<File> eachFileLst = null;

            for( int i = 0; i < jobContext.getChangedMedlineGzips().length; i++ ){
                File eachChangedGzip = jobContext.getChangedMedlineGzips()[ i ];

                int aa = i%gridSize;
                String key = "partition_" + aa ;

                ctx = contextHashMap.get( key );
                if( ctx == null ){
                    ctx = new ExecutionContext(  );
                    contextHashMap.put( key, ctx );

                    ctx.put( EXE_CTX_GZIP_FILE_LST, new ArrayList<File>(  ) );
                }
                eachFileLst = ( ArrayList<File> ) ctx.get( EXE_CTX_GZIP_FILE_LST );
                eachFileLst.add( eachChangedGzip );
            }
            jobContext.setMedlineGzipsContextHashMap( contextHashMap );

        }

        logger.info( "Total " + jobContext.getChangedMedlineGzips().length + " gzip files divided into " + jobContext.getMedlineGzipsContextHashMap().size() + "partitions." );

        return jobContext.getMedlineGzipsContextHashMap();
    }

    protected Log logger = LogFactory.getLog( getClass() );
}
