package edu.umich.med.mbni.pubanatomy.flash.dto;

import java.util.ArrayList;

/**
 * @author flashflexpro@gmail.com
 *         Date: 3/5/12
 *         Time: 1:55 PM
 */
public class DtoImageSection{

    private DtoModel model;

    private Number imageId;

    private ArrayList<ArrayList<double[]>> edges;

    public DtoModel getModel(){
        return model;
    }

    public void setModel( DtoModel model ){
        this.model = model;
    }

    public ArrayList<ArrayList<double[]>> getEdges(){
        return edges;
    }

    public void setEdges( ArrayList<ArrayList<double[]>> edges ){
        this.edges = edges;
    }

    public Number getImageId(){
        return imageId;
    }

    public void setImageId( Number imageId ){
        this.imageId = imageId;
    }
}
