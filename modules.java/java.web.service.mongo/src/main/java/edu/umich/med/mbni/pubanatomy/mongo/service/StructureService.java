package edu.umich.med.mbni.pubanatomy.mongo.service;

import edu.umich.med.mbni.pubanatomy.mongo.domain.V3dStructure;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/18/12
 *         Time: 6:08 PM
 */
public interface StructureService extends MongoRepository<V3dStructure, Integer>{
    List<V3dStructure> findV3dStructureByDepth( int depth );

    List<V3dStructure> findV3dStructureByParentStructureId( int parentStructureId );
}
