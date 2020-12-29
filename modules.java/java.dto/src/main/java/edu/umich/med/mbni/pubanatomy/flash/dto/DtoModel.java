package edu.umich.med.mbni.pubanatomy.flash.dto;

/**
 * @author flashflexpro@gmail.com
 *         Date: 3/5/12
 *         Time: 12:33 PM
 */
public class DtoModel{

    private int dividedBy;
    private String processId;
    protected long structureId;

    public long getStructureId(){
        return structureId;
    }

    public void setStructureId( long structureId ){
        this.structureId = structureId;
    }

    public int getDividedBy(){
        return dividedBy;
    }

    public void setDividedBy( int dividedBy ){
        this.dividedBy = dividedBy;
    }

    public String getProcessId(){
        return processId;
    }

    public void setProcessId( String processId ){
        this.processId = processId;
    }

}
