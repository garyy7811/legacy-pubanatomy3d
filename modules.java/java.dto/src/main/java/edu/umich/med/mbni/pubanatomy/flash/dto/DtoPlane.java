package edu.umich.med.mbni.pubanatomy.flash.dto;

/**
 * @author flashflexpro@gmail.com
 *         Date: 3/8/12
 *         Time: 7:59 PM
 */
public class DtoPlane{

    private int direction = - 1;

    private int value = - 1;

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
}
