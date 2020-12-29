package edu.umich.med.mbni.pubanatomy.batch.job;

import org.springframework.batch.item.ItemWriter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 * User: flashflexpro@gmail.com
 * Date: 4/26/13
 * Time: 3:40 PM
 */
public class BatchWriterDomainObj<S extends MongoRepository> implements ItemWriter{

    private S service;

    public S getService(){
        return service;
    }

    public void setService( S service ){
        this.service = service;
    }

    @Override
    public void write( List items ) throws Exception{
        List rslt = service.save( items );
    }
}
