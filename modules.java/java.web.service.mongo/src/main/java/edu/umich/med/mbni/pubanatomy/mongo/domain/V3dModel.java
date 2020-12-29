package edu.umich.med.mbni.pubanatomy.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/16/12
 *         Time: 2:25 PM
 */
@Document
public class V3dModel{

    public V3dModel( V3dModelId id ){
        this.id = id;
    }

    @Id
    private V3dModelId id;

    public V3dModelId getId(){
        return id;
    }

}
