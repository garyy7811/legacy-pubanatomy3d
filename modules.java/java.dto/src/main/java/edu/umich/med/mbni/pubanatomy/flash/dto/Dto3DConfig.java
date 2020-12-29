package edu.umich.med.mbni.pubanatomy.flash.dto;

/**
 * User: flashflexpro@gmail.com
 * Date: 8/5/13
 * Time: 3:27 PM
 */
public class Dto3DConfig{


    private int unitLength;

    private int scale3d;

    private int scaleSection;

    private int preload3dModelDepth;

    private int spaceFromX;
    private int spaceFromY;
    private int spaceFromZ;

    private int spaceToX;
    private int spaceToY;
    private int spaceToZ;

    private int root3dstructure;

    public int getUnitLength(){
        return unitLength;
    }

    public void setUnitLength( int unitLength ){
        this.unitLength = unitLength;
    }

    public int getScale3d(){
        return scale3d;
    }

    public void setScale3d( int scale3d ){
        this.scale3d = scale3d;
    }

    public int getScaleSection(){
        return scaleSection;
    }

    public void setScaleSection( int scaleSection ){
        this.scaleSection = scaleSection;
    }

    public int getPreload3dModelDepth(){
        return preload3dModelDepth;
    }

    public void setPreload3dModelDepth( int preload3dModelDepth ){
        this.preload3dModelDepth = preload3dModelDepth;
    }

    public int getSpaceFromX(){
        return spaceFromX;
    }

    public void setSpaceFromX( int spaceFromX ){
        this.spaceFromX = spaceFromX;
    }

    public int getSpaceFromY(){
        return spaceFromY;
    }

    public void setSpaceFromY( int spaceFromY ){
        this.spaceFromY = spaceFromY;
    }

    public int getSpaceFromZ(){
        return spaceFromZ;
    }

    public void setSpaceFromZ( int spaceFromZ ){
        this.spaceFromZ = spaceFromZ;
    }

    public int getSpaceToX(){
        return spaceToX;
    }

    public void setSpaceToX( int spaceToX ){
        this.spaceToX = spaceToX;
    }

    public int getSpaceToY(){
        return spaceToY;
    }

    public void setSpaceToY( int spaceToY ){
        this.spaceToY = spaceToY;
    }

    public int getSpaceToZ(){
        return spaceToZ;
    }

    public void setSpaceToZ( int spaceToZ ){
        this.spaceToZ = spaceToZ;
    }

    public int getRoot3dstructure(){
        return root3dstructure;
    }

    public void setRoot3dstructure( int root3dstructure ){
        this.root3dstructure = root3dstructure;
    }
}
