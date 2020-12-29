package edu.umich.med.mbni.pubanatomy.mongo.service;

import edu.umich.med.mbni.pubanatomy.mongo.domain.ToolTip;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/18/12
 *         Time: 6:08 PM
 */
public interface TooltipService extends MongoRepository<ToolTip, String>{
}
