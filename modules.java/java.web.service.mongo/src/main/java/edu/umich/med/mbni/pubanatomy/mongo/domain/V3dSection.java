package edu.umich.med.mbni.pubanatomy.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/19/12
 *         Time: 10:00 AM
 */
@Document
public class V3dSection{

    public V3dSection( V3dSectionId id, byte[] edges ){
        this.id = id;
        this.edges = edges;
    }

    @Id
    private V3dSectionId id;

    private byte[] edges;


    public V3dSectionId getId(){
        return id;
    }

    public void setId( V3dSectionId id ){
        this.id = id;
    }

    public byte[] getEdges(){
        return edges;
    }

    public void setEdges( byte[] edges ){
        this.edges = edges;
    }
}
