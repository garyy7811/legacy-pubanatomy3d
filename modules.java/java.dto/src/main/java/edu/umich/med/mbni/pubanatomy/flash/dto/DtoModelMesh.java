package edu.umich.med.mbni.pubanatomy.flash.dto;


import java.io.Serializable;

public class DtoModelMesh implements Serializable{

    private DtoModelMeshArray owner;

    private Double[]  vertices;
    private Integer[] indexes;
    private Double[]  normals;


    public DtoModelMeshArray getOwner(){
        return owner;
    }

    public void setOwner( DtoModelMeshArray owner ){
        this.owner = owner;
    }

    public Double[] getVertices(){
        return vertices;
    }

    public void setVertices( Double[] vertices ){
        this.vertices = vertices;
    }

    public Integer[] getIndexes(){
        return indexes;
    }

    public void setIndexes( Integer[] indexes ){
        this.indexes = indexes;
    }

    public Double[] getNormals(){
        return normals;
    }

    public void setNormals( Double[] normals ){
        this.normals = normals;
    }
}
