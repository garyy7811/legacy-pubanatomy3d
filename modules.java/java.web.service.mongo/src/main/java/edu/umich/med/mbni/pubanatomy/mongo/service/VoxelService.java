package edu.umich.med.mbni.pubanatomy.mongo.service;

import edu.umich.med.mbni.pubanatomy.mongo.domain.V3dVoxel;
import edu.umich.med.mbni.pubanatomy.mongo.domain.V3dVoxelId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/18/12
 *         Time: 6:28 PM
 */
public interface VoxelService extends MongoRepository<V3dVoxel, V3dVoxelId>{

}
