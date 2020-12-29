package edu.umich.med.mbni.pubanatomy.mongo.domain;

import java.io.Serializable;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/19/12
 *         Time: 10:00 AM
 */
public class V3dSectionId implements Serializable{

    public V3dSectionId( V3dModelId modelId, int direction, int value ){
        this.modelId = modelId;
        this.direction = direction;
        this.value = value;
    }

    private V3dModelId modelId;

    private int direction = - 1;

    private int value = - 1;

    public V3dModelId getModelId(){
        return modelId;
    }

    public void setModelId( V3dModelId modelId ){
        this.modelId = modelId;
    }

    public int getDirection(){
        return direction;
    }

    public void setDirection( int direction ){
        this.direction = direction;
    }

    public int getValue(){
        return value;
    }

    public void setValue( int value ){
        this.value = value;
    }

    @Override
    public boolean equals( Object o ){
        if( this == o ){
            return true;
        }
        if( ! ( o instanceof V3dSectionId ) ){
            return false;
        }

        V3dSectionId that = ( V3dSectionId )o;

        if( direction != that.direction ){
            return false;
        }
        if( value != that.value ){
            return false;
        }
        if( ! modelId.equals( that.modelId ) ){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        int result = modelId.hashCode();
        result = 31 * result + direction;
        result = 31 * result + value;
        return result;
    }
}
