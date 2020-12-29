package edu.umich.med.mbni.pubanatomy.batch.repo;

import edu.umich.med.mbni.pubanatomy.batch.domain.AbaGene;
import edu.umich.med.mbni.pubanatomy.batch.domain.AbaStructure;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * User: flashflexpro@gmail.com
 * Date: 4/26/13
 * Time: 3:44 PM
 */
public interface AbaGeneService extends MongoRepository<AbaGene, Integer>{

    List<AbaGene> findAbaGeneByEntrezId( int entrezId );

}
