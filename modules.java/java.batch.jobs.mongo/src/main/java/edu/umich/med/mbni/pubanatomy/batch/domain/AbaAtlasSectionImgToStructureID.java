package edu.umich.med.mbni.pubanatomy.batch.domain;

import java.io.Serializable;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/7/13
 * Time: 11:58 AM
 */
public class AbaAtlasSectionImgToStructureID implements Serializable{
    public AbaAtlasSectionImgToStructureID( long atlasSectionImgId, int structureId ){
        this.atlasSectionImgId = atlasSectionImgId;
        this.structureId = structureId;
    }

    private long atlasSectionImgId;

    private int structureId;


    public long getAtlasSectionImgId(){
        return atlasSectionImgId;
    }

    public void setAtlasSectionImgId( long atlasSectionImgId ){
        this.atlasSectionImgId = atlasSectionImgId;
    }

    public int getStructureId(){
        return structureId;
    }

    public void setStructureId( int structureId ){
        this.structureId = structureId;
    }

    @Override
    public boolean equals( Object o ){
        if( this == o ){
            return true;
        }
        if( !( o instanceof AbaAtlasSectionImgToStructureID ) ){
            return false;
        }

        AbaAtlasSectionImgToStructureID that = ( AbaAtlasSectionImgToStructureID ) o;

        if( atlasSectionImgId != that.atlasSectionImgId ){
            return false;
        }
        if( structureId != that.structureId ){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        int result = ( int ) ( atlasSectionImgId ^ ( atlasSectionImgId >>> 32 ) );
        result = 31 * result + structureId;
        return result;
    }
}
