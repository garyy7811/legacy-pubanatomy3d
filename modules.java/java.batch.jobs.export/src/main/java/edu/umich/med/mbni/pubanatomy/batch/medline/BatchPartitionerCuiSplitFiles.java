package edu.umich.med.mbni.pubanatomy.batch.medline;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * User: flashflexpro@gmail.com
 * Date: 7/23/13
 * Time: 11:07 AM
 */
@Component
public class BatchPartitionerCuiSplitFiles extends MultiResourcePartitioner{

    protected Log logger = LogFactory.getLog( getClass() );

    @javax.annotation.Resource
    private MedlineAutoUpdateJobContext jobContext;

    @Override
    public Map<String, ExecutionContext> partition( int gridSize ){
        File[] files;
        try{
            files = jobContext.getCuiSplitFile().getFile().listFiles();
        }
        catch( IOException e ){
            e.printStackTrace();
            throw new Error( e.getMessage() );
        }

        ArrayList<Resource> lst = new ArrayList<>();
        for( int i = 0; i < files.length; i++ ){
            lst.add( new FileSystemResource( files[ i ] ) );
        }

        setResources( lst.toArray( new Resource[ lst.size() ] ) );

        logger.info( "Total " + lst.size() + "files into" + gridSize + " partitions" );
        return super.partition( gridSize );
    }
}
