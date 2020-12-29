package edu.umich.med.mbni.pubanatomy.flash.dto;

/**
 * @author flashflexpro@gmail.com
 *         Date: 6/25/12
 *         Time: 7:24 PM
 */
public class DtoGeneExprLevel{

    private long sectionDataSetId;

    private int unitLen = 200;

    private String type;

    private DtoVoxelLevel[] voxelLevels;

    public long getSectionDataSetId(){
        return sectionDataSetId;
    }

    public void setSectionDataSetId( long sectionDataSetId ){
        this.sectionDataSetId = sectionDataSetId;
    }

    public int getUnitLen(){
        return unitLen;
    }

    public void setUnitLen( int unitLen ){
        this.unitLen = unitLen;
    }

    public String getType(){
        return type;
    }

    public void setType( String type ){
        this.type = type;
    }

    public DtoVoxelLevel[] getVoxelLevels(){
        return voxelLevels;
    }

    public void setVoxelLevels( DtoVoxelLevel[] voxelLevels ){
        this.voxelLevels = voxelLevels;
    }
}
