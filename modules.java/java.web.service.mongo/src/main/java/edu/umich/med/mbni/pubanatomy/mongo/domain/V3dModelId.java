package edu.umich.med.mbni.pubanatomy.mongo.domain;

import java.io.Serializable;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/16/12
 *         Time: 2:25 PM
 */
public class V3dModelId implements Serializable{

    public V3dModelId( int structureId, int dividedBy, String processId ){
        this.structureId = structureId;
        this.dividedBy = dividedBy;
        this.processId = processId;
    }

    private int structureId;

    private int dividedBy;

    private String processId;

    public int getStructureId(){
        return structureId;
    }

    public void setStructureId( int structureId ){
        this.structureId = structureId;
    }

    public String getProcessId(){
        return processId;
    }

    public void setProcessId( String processId ){
        this.processId = processId;
    }

    public int getDividedBy(){
        return dividedBy;
    }

    public void setDividedBy( int dividedBy ){
        this.dividedBy = dividedBy;
    }


    @Override
    public boolean equals( Object o ){
        if( this == o ){
            return true;
        }
        if( ! ( o instanceof V3dModelId ) ){
            return false;
        }

        V3dModelId that = ( V3dModelId )o;

        if( dividedBy != that.dividedBy ){
            return false;
        }
        if( structureId != that.structureId ){
            return false;
        }
        if( ! processId.equals( that.processId ) ){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        int result = structureId;
        result = 31 * result + dividedBy;
        result = 31 * result + processId.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "V3dModelId{" +
                "structureId=" + structureId +
                ", dividedBy=" + dividedBy +
                ", processId='" + processId + '\'' +
                '}';
    }
}
