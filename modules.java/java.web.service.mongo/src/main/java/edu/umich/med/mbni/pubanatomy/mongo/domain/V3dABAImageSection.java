package edu.umich.med.mbni.pubanatomy.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author flashflexpro@gmail.com
 *         Date: 1/18/13
 *         Time: 5:30 PM
 */
@Document
public class V3dABAImageSection{

    public V3dABAImageSection( V3dABAImageSectionId id, byte[] edges ){
        this.id = id;
        this.edges = edges;
    }

    @Id
    private V3dABAImageSectionId id;

    private byte[] edges;

    public V3dABAImageSectionId getId(){
        return id;
    }

    public void setEdges( byte[] edges ){
        this.edges = edges;
    }

    public byte[] getEdges(){
        return edges;
    }
}
