package edu.umich.med.mbni.pubanatomy.flash.dto;

/**
 * @author flashflexpro@gmail.com
 *         Date: 12/10/12
 *         Time: 1:04 PM
 */
public class DtoGeneExprAnnotation{

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

    public int getUnitlen(){
        return unitlen;
    }

    public void setUnitlen( int unitlen ){
        this.unitlen = unitlen;
    }
}
