package edu.umich.med.mbni.pubanatomy.flash.dto;

import java.util.ArrayList;


public class DtoStructure{

    private int structureId;

    private String acronym;
    private String name;

    private int atlasId = -1;
    private int ontologyId = -1;
    private int hemisphereId = -1;
    private int weight = -1;
    private int graphId = -1;
    private String color;

    private DtoModel visualModel;
    private DtoModel sectionModel;

    private DtoStructure parent;
    private ArrayList<DtoStructure> children = new ArrayList<DtoStructure>(  );

    private int[] min;
    private int[] max;
    private long pointsNum;

    private int depth;

    public int getStructureId(){
        return structureId;
    }

    public void setStructureId( int structureId ){
        this.structureId = structureId;
    }

    public String getAcronym(){
        return acronym;
    }

    public void setAcronym( String acronym ){
        this.acronym = acronym;
    }

    public String getName(){
        return name;
    }

    public void setName( String name ){
        this.name = name;
    }

    public int getAtlasId(){
        return atlasId;
    }

    public void setAtlasId( int atlasId ){
        this.atlasId = atlasId;
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

    public int getGraphId(){
        return graphId;
    }

    public void setGraphId( int graphId ){
        this.graphId = graphId;
    }

    public String getColor(){
        return color;
    }

    public void setColor( String color ){
        this.color = color;
    }

    public DtoModel getVisualModel(){
        return visualModel;
    }

    public void setVisualModel( DtoModel visualModel ){
        this.visualModel = visualModel;
    }

    public DtoModel getSectionModel(){
        return sectionModel;
    }

    public void setSectionModel( DtoModel sectionModel ){
        this.sectionModel = sectionModel;
    }

    public DtoStructure getParent(){
        return parent;
    }

    public void setParent( DtoStructure parent ){
        this.parent = parent;
    }

    public ArrayList<DtoStructure> getChildren(){
        return children;
    }

    public void setChildren( ArrayList<DtoStructure> children ){
        this.children = children;
    }

    public int[] getMin(){
        return min;
    }

    public void setMin( int[] min ){
        this.min = min;
    }

    public int[] getMax(){
        return max;
    }

    public void setMax( int[] max ){
        this.max = max;
    }

    public long getPointsNum(){
        return pointsNum;
    }

    public void setPointsNum( long pointsNum ){
        this.pointsNum = pointsNum;
    }

    public int getDepth(){
        return depth;
    }

    public void setDepth( int depth ){
        this.depth = depth;
    }
}
