package edu.umich.med.mbni.pubanatomy.mongo.service;

import edu.umich.med.mbni.pubanatomy.mongo.domain.V3dSection;
import edu.umich.med.mbni.pubanatomy.mongo.domain.V3dSectionId;
import edu.umich.med.mbni.pubanatomy.mongo.domain.V3dSection;
import edu.umich.med.mbni.pubanatomy.mongo.domain.V3dSectionId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/19/12
 *         Time: 10:07 AM
 */
public interface SectionService extends MongoRepository<V3dSection, V3dSectionId>{
    List<V3dSection> findV3dSectionByIdDirectionAndIdValue( int direction, int value );
}
