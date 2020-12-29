package edu.umich.med.mbni.pubanatomy.batch.repo;

import edu.umich.med.mbni.pubanatomy.batch.domain.AbaAtlasSectionImgToStructure;
import edu.umich.med.mbni.pubanatomy.batch.domain.AbaAtlasSectionImgToStructureID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface AbaAtlasSectionImgToStructureService extends MongoRepository<AbaAtlasSectionImgToStructure, AbaAtlasSectionImgToStructureID>{

    List<AbaAtlasSectionImgToStructure> findAbaAtlasSectionImgToStructureByIdStructureId( int structureId );

}
