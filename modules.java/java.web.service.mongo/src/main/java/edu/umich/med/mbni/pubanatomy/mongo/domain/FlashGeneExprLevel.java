package edu.umich.med.mbni.pubanatomy.mongo.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/18/12
 *         Time: 5:37 PM
 */
public class FlashGeneExprLevel implements Serializable{

    public static final long serialVersionUID = 774893726349286857L;

    public static final String geneExprGridFsNamePrefix = "SectionDataSetId";

    public static String getGeneExprGridFsName( long sectionDataSetId, String type ){
        return geneExprGridFsNamePrefix + sectionDataSetId + "." + type;
    }

    public static final String   EXPR_TYPE_energy    = "energy";
    public static final String   EXPR_TYPE_intensity = "intensity";
    public static final String   EXPR_TYPE_density   = "density";
    public static final String[] EXPR_TYPES          =
            new String[]{ EXPR_TYPE_energy, EXPR_TYPE_intensity, EXPR_TYPE_density };

    private long sectionDataSetId;

    private int unitLen = 200;

    private String type;

    private Integer[] x;
    private Integer[] y;
    private Integer[] z;


    private BigDecimal[] level;

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

    public BigDecimal[] getLevel(){
        return level;
    }

    public void setLevel( BigDecimal[] level ){
        this.level = level;
    }
}
