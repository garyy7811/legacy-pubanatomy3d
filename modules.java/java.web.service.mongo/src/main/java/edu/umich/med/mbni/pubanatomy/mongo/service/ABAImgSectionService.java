package edu.umich.med.mbni.pubanatomy.mongo.service;

import edu.umich.med.mbni.pubanatomy.mongo.domain.V3dABAImageSection;
import edu.umich.med.mbni.pubanatomy.mongo.domain.V3dABAImageSectionId;
import edu.umich.med.mbni.pubanatomy.mongo.domain.V3dABAImageSection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author flashflexpro@gmail.com
 *         Date: 1/18/13
 *         Time: 5:41 PM
 */
public interface ABAImgSectionService extends MongoRepository<V3dABAImageSection, V3dABAImageSectionId>{
    List<V3dABAImageSection> findV3dABAImageSectionByIdAbaSectionImageId( long abaSectionImageId );
    List<V3dABAImageSection> findV3dABAImageSectionByIdAbaSectionImageIdAndIdModelIdDividedBy( long abaSectionImageId, int dividedBy );
    List<V3dABAImageSection> findV3dABAImageSectionByIdModelIdStructureIdAndIdModelIdDividedBy( int structureId, int dividedBy );
}
