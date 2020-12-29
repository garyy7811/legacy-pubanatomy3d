package edu.umich.med.mbni.pubanatomy.batch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/16/12
 *         Time: 2:15 PM
 */
@Document
public class AbaStructure{
    @Id
    private int structureId = -1;

    private int atlasId;

    @Indexed
    private String name;

    @Indexed
    private String acronym;

    private int stLevel;

    private int ontologyId;

    private int hemisphereId;

    private int weight;

    private int parentStructureId = -1;

    @Indexed
    private int depth = - 1;

    private int graphId;

    private int graphOrder;

    private String structureIdPath;

    private String color;

    private int neuroNameStructureId;

    private String neuroNameStructureIdPath;

    private int sphinxId;

    private AbaStructure3DModel visualModel;

    private AbaStructure3DModel sectionModel;


    @Indexed
    private int minX;

    @Indexed
    private int minY;

    @Indexed
    private int minZ;

    @Indexed
    private int maxX;

    @Indexed
    private int maxY;

    @Indexed
    private int maxZ;


    @Indexed
    private long pointsNum;


    public int getStructureId(){
        return structureId;
    }

    public void setStructureId( int structureId ){
        this.structureId = structureId;
    }

    public int getAtlasId(){
        return atlasId;
    }

    public void setAtlasId( int atlasId ){
        this.atlasId = atlasId;
    }

    public String getName(){
        return name;
    }

    public void setName( String name ){
        this.name = name;
    }

    public String getAcronym(){
        return acronym;
    }

    public void setAcronym( String acronym ){
        this.acronym = acronym;
    }

    public int getStLevel(){
        return stLevel;
    }

    public void setStLevel( int stLevel ){
        this.stLevel = stLevel;
    }

    public int getOntologyId(){
        return ontologyId;
    }

    public void setOntologyId( int ontologyId ){
        this.ontologyId = ontologyId;
    }

    public int getHemisphereId(){
        return hemisphereId;
    }

    public void setHemisphereId( int hemisphereId ){
        this.hemisphereId = hemisphereId;
    }

    public int getWeight(){
        return weight;
    }

    public void setWeight( int weight ){
        this.weight = weight;
    }

    public int getDepth(){
        return depth;
    }

    public void setDepth( int depth ){
        this.depth = depth;
    }

    public int getGraphId(){
        return graphId;
    }

    public void setGraphId( int graphId ){
        this.graphId = graphId;
    }

    public int getGraphOrder(){
        return graphOrder;
    }

    public void setGraphOrder( int graphOrder ){
        this.graphOrder = graphOrder;
    }

    public String getStructureIdPath(){
        return structureIdPath;
    }

    public void setStructureIdPath( String structureIdPath ){
        this.structureIdPath = structureIdPath;
    }

    public String getColor(){
        return color;
    }

    public void setColor( String color ){
        this.color = color;
    }

    public int getNeuroNameStructureId(){
        return neuroNameStructureId;
    }

    public void setNeuroNameStructureId( int neuroNameStructureId ){
        this.neuroNameStructureId = neuroNameStructureId;
    }

    public String getNeuroNameStructureIdPath(){
        return neuroNameStructureIdPath;
    }

    public void setNeuroNameStructureIdPath( String neuroNameStructureIdPath ){
        this.neuroNameStructureIdPath = neuroNameStructureIdPath;
    }

    public int getSphinxId(){
        return sphinxId;
    }

    public void setSphinxId( int sphinxId ){
        this.sphinxId = sphinxId;
    }

    public AbaStructure3DModel getVisualModel(){
        return visualModel;
    }

    public void setVisualModel( AbaStructure3DModel visualModel ){
        this.visualModel = visualModel;
    }

    public AbaStructure3DModel getSectionModel(){
        return sectionModel;
    }

    public void setSectionModel( AbaStructure3DModel sectionModel ){
        this.sectionModel = sectionModel;
    }

    public int getMinX(){
        return minX;
    }

    public void setMinX( int minX ){
        this.minX = minX;
    }

    public int getMinY(){
        return minY;
    }

    public void setMinY( int minY ){
        this.minY = minY;
    }

    public int getMinZ(){
        return minZ;
    }

    public void setMinZ( int minZ ){
        this.minZ = minZ;
    }

    public int getMaxX(){
        return maxX;
    }

    public void setMaxX( int maxX ){
        this.maxX = maxX;
    }

    public int getMaxY(){
        return maxY;
    }

    public void setMaxY( int maxY ){
        this.maxY = maxY;
    }

    public int getMaxZ(){
        return maxZ;
    }

    public void setMaxZ( int maxZ ){
        this.maxZ = maxZ;
    }

    public long getPointsNum(){
        return pointsNum;
    }

    public void setPointsNum( long pointsNum ){
        this.pointsNum = pointsNum;
    }

    public int getParentStructureId(){
        return parentStructureId;
    }

    public void setParentStructureId( int parentStructureId ){
        this.parentStructureId = parentStructureId;
    }

    @Override
    public boolean equals( Object o ){
        if( this == o ){
            return true;
        }
        if( ! ( o instanceof AbaStructure ) ){
            return false;
        }

        AbaStructure that = ( AbaStructure )o;

        if( that.getStructureId() != structureId ){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        return structureId;
    }
}
