package edu.umich.med.mbni.pubanatomy.flash.dto;

import java.math.BigDecimal;

/**
 * @author flashflexpro@gmail.com
 *         Date: 6/25/12
 *         Time: 7:24 PM
 */
public class DtoVoxelLevel{

    private DtoVoxel voxel;

    private BigDecimal level;


    public DtoVoxel getVoxel(){
        return voxel;
    }

    public void setVoxel( DtoVoxel voxel ){
        this.voxel = voxel;
    }

    public BigDecimal getLevel(){
        return level;
    }

    public void setLevel( BigDecimal level ){
        this.level = level;
    }
}
