package edu.umich.med.mbni.pubanatomy.batch.repo;

import edu.umich.med.mbni.pubanatomy.batch.domain.AbaSectionDataSet;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * User: flashflexpro@gmail.com
 * Date: 4/26/13
 * Time: 3:44 PM
 */
public interface AbaSectionDataSetService extends MongoRepository<AbaSectionDataSet, Integer>{

}
