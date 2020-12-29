package edu.umich.med.mbni.pubanatomy.batch.job;

import edu.umich.med.mbni.pubanatomy.batch.meta.BatchItemFileAndMeta;
import org.springframework.batch.item.ItemWriter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;


/**
 * User: flashflexpro@gmail.com
 * Date: 4/26/13
 * Time: 3:40 PM
 */
@Component
public class BatchWriterFileAndMeta implements ItemWriter<BatchItemFileAndMeta>{

    @Resource
    protected GridFsTemplate gridFsTemplate;

    @Override
    public void write( List<? extends BatchItemFileAndMeta> items ) throws Exception{
        for( int i = 0; i < items.size(); i++ ){
            BatchItemFileAndMeta fm = items.get( i );

            InputStream tmp = fm.getInputStream();
            if( tmp == null ){
                tmp = new ByteArrayInputStream( new byte[ 0 ] );
            }
            gridFsTemplate.store( tmp, fm.getFileMeta().getFileName(), fm.getFileMeta() );
        }
    }
}
