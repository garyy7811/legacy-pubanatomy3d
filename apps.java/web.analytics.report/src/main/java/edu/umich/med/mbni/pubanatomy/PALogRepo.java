package edu.umich.med.mbni.pubanatomy;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/12/2014
 * Time: 3:26 PM
 */
public interface PALogRepo extends MongoRepository<PALogRecord, Long>{

    public List<PALogRecord> findBySessionId( String sessionId );

    public List<PALogRecord> findByIpAddress( String ipAddress );
}
