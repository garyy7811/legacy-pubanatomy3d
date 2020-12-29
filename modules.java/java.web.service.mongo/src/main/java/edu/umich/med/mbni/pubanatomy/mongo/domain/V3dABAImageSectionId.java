package edu.umich.med.mbni.pubanatomy.mongo.domain;

import java.io.Serializable;

/**
 * @author flashflexpro@gmail.com
 *         Date: 1/18/13
 *         Time: 5:30 PM
 */
public class V3dABAImageSectionId implements Serializable{

    public V3dABAImageSectionId( long abaSectionImageId, V3dModelId modelId ){
        this.abaSectionImageId = abaSectionImageId;
        this.modelId = modelId;
    }

    private long abaSectionImageId;

    private V3dModelId modelId;

    public long getAbaSectionImageId(){
        return abaSectionImageId;
    }

    public V3dModelId getModelId(){
        return modelId;
    }


}
