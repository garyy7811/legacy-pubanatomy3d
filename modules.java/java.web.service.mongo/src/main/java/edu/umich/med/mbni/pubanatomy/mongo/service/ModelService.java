package edu.umich.med.mbni.pubanatomy.mongo.service;

import edu.umich.med.mbni.pubanatomy.mongo.domain.V3dModel;
import edu.umich.med.mbni.pubanatomy.mongo.domain.V3dModelId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/18/12
 *         Time: 6:28 PM
 */
public interface ModelService extends MongoRepository<V3dModel, V3dModelId>{

    V3dModel findModelByIdStructureIdAndIdDividedByAndIdProcessId( int structureId, int dividedInto, String processId );

    List<V3dModel> findModelByIdStructureId( int structureId );
}
