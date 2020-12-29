package edu.umich.med.mbni.pubanatomy.batch.repo;

import edu.umich.med.mbni.pubanatomy.batch.domain.MedlineGzipLastMd5;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * User: flashflexpro@gmail.com
 * Date: 7/11/13
 * Time: 12:24 PM
 */
public interface MedlineGzipLastMd5Service extends MongoRepository<MedlineGzipLastMd5, String>{ }
