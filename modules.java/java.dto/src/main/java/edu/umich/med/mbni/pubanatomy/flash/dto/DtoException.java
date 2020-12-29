package edu.umich.med.mbni.pubanatomy.flash.dto;

/**
 * User: flashflexpro@gmail.com
 * Date: 10/8/13
 * Time: 9:40 AM
 */
public class DtoException{


    public DtoException(){
    }

    public DtoException( String stack ){
        this.stack = stack;
    }

    public DtoException( int code, String stack ){
        this.code = code;
        this.stack = stack;
    }

    private int code = 0;
    private String stack;

    public int getCode(){
        return code;
    }

    public void setCode( int code ){
        this.code = code;
    }

    public String getStack(){
        return stack;
    }

    public void setStack( String stack ){
        this.stack = stack;
    }
}
