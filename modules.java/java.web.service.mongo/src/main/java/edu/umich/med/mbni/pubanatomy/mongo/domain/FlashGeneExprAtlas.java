package edu.umich.med.mbni.pubanatomy.mongo.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/18/12
 *         Time: 5:37 PM
 */
@Document
public class FlashGeneExprAtlas implements Serializable{

    public static final String   MONGO_FILE_NAME     = "FlashGeneExprLevel";

    private Integer[] x;
    private Integer[] y;
    private Integer[] z;

    private Integer[] structureId;
    private int unitlen;

    public Integer[] getX(){
        return x;
    }

    public void setX( Integer[] x ){
        this.x = x;
    }

    public Integer[] getY(){
        return y;
    }

    public void setY( Integer[] y ){
        this.y = y;
    }

    public Integer[] getZ(){
        return z;
    }

    public void setZ( Integer[] z ){
        this.z = z;
    }

    public Integer[] getStructureId(){
        return structureId;
    }

    public void setStructureId( Integer[] structureId ){
        this.structureId = structureId;
    }

    public void setUnitlen( int unitlen ){
        this.unitlen = unitlen;
    }

    public int getUnitlen(){
        return unitlen;
    }
}
