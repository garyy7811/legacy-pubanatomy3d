package edu.umich.med.mbni.pubanatomy.mongo.domain;

import java.io.Serializable;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/16/12
 *         Time: 2:59 PM
 */
public class V3dVoxelId implements Serializable{

    public V3dVoxelId( int x, int y, int z ){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private int x;

    private int y;

    private int z;

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


    @Override
    public boolean equals( Object o ){
        if( this == o ){
            return true;
        }
        if( ! ( o instanceof V3dVoxelId ) ){
            return false;
        }

        V3dVoxelId that = ( V3dVoxelId )o;

        if( x != that.x ){
            return false;
        }
        if( y != that.y ){
            return false;
        }
        if( z != that.z ){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }
}
