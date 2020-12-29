package edu.umich.med.mbni.pubanatomy.flash.dto;

/**
 * @author flashflexpro@gmail.com
 *         Date: 3/9/12
 *         Time: 11:37 AM
 */
public class DtoVoxel{

    private int x;
    private int y;
    private int z;

    private int structureId;

    public int getX(){
        return x;
    }

    public void setX( int x ){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY( int y ){
        this.y = y;
    }

    public int getZ(){
        return z;
    }

    public void setZ( int z ){
        this.z = z;
    }

    public int getStructureId(){
        return structureId;
    }

    public void setStructureId( int structureId ){
        this.structureId = structureId;
    }
}
