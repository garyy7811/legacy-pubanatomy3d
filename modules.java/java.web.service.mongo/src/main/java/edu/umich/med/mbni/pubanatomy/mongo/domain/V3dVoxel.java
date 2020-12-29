package edu.umich.med.mbni.pubanatomy.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/16/12
 *         Time: 2:59 PM
 */
@Document
public class V3dVoxel{

    public V3dVoxel( V3dVoxelId id ){
        this.id = id;
    }

    @Id
    private V3dVoxelId id;

    private int structureId;

    private int structureDepth;

    public V3dVoxelId getId(){
        return id;
    }

    public void setId( V3dVoxelId id ){
        this.id = id;
    }

    public int getStructureId(){
        return structureId;
    }

    public void setStructureId( int structureId ){
        this.structureId = structureId;
    }

    public int getStructureDepth(){
        return structureDepth;
    }

    public void setStructureDepth( int structureDepth ){
        this.structureDepth = structureDepth;
    }

    @Override
    public boolean equals( Object o ){
        if( this == o ){
            return true;
        }
        if( ! ( o instanceof V3dVoxel ) ){
            return false;
        }

        V3dVoxel v3dVoxel = ( V3dVoxel )o;

        if( ! id.equals( v3dVoxel.id ) ){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }
}
