package edu.umich.med.mbni.pubanatomy.mongo.domain;

import java.io.Serializable;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/18/12
 *         Time: 5:42 PM
 */
public class FlashMesh implements Serializable{

    private Double[]  vertices;
    private Double[]  normals;
    private Integer[] indexes;

    public Double[] getVertices(){
        return vertices;
    }

    public void setVertices( Double[] vertices ){
        this.vertices = vertices;
    }

    public Double[] getNormals(){
        return normals;
    }

    public void setNormals( Double[] normals ){
        this.normals = normals;
    }

    public Integer[] getIndexes(){
        return indexes;
    }

    public void setIndexes( Integer[] indexes ){
        this.indexes = indexes;
    }
}
