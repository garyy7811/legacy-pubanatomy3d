package edu.umich.med.mbni.pubanatomy.batch.domain;

import java.io.Serializable;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/16/12
 *         Time: 2:25 PM
 */
public class AbaStructure3DModel implements Serializable{

    public AbaStructure3DModel( int structureId, int dividedBy, float isoLevel, String processId ){
        this.structureId = structureId;
        this.dividedBy = dividedBy;
        this.isoLevel = isoLevel;
        this.processDesc = processId;
    }

    private int structureId;

    private float isoLevel;

    private int dividedBy;

    private String processDesc;

    public int getStructureId(){
        return structureId;
    }

    public void setStructureId( int structureId ){
        this.structureId = structureId;
    }

    public float getIsoLevel(){
        return isoLevel;
    }

    public void setIsoLevel( float isoLevel ){
        this.isoLevel = isoLevel;
    }

    public int getDividedBy(){
        return dividedBy;
    }

    public void setDividedBy( int dividedBy ){
        this.dividedBy = dividedBy;
    }

    public String getProcessDesc(){
        return processDesc;
    }

    public void setProcessDesc( String processDesc ){
        this.processDesc = processDesc;
    }

    @Override
    public String toString(){
        return "AbaStructure3DModel{" +
               "structureId=" + structureId +
               ", isoLevel=" + isoLevel +
               ", dividedBy=" + dividedBy +
               ", processDesc='" + processDesc + '\'' +
               '}';
    }

    @Override
    public boolean equals( Object o ){
        if( this == o ){
            return true;
        }
        if( !( o instanceof AbaStructure3DModel ) ){
            return false;
        }

        AbaStructure3DModel that = ( AbaStructure3DModel ) o;

        if( dividedBy != that.dividedBy ){
            return false;
        }
        if( Float.compare( that.isoLevel, isoLevel ) != 0 ){
            return false;
        }
        if( structureId != that.structureId ){
            return false;
        }
        if( !processDesc.equals( that.processDesc ) ){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        int result = structureId;
        result = 31 * result + ( isoLevel != +0.0f ? Float.floatToIntBits( isoLevel ) : 0 );
        result = 31 * result + dividedBy;
        result = 31 * result + processDesc.hashCode();
        return result;
    }
}
