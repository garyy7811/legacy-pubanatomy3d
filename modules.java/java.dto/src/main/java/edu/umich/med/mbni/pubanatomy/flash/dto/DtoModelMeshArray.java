package edu.umich.med.mbni.pubanatomy.flash.dto;

import java.io.Serializable;

public class DtoModelMeshArray implements Serializable{

    private DtoModel model;

    private DtoModelMesh[] flashMeshes;


    public DtoModel getModel(){
        return model;
    }

    public void setModel( DtoModel model ){
        this.model = model;
    }

    public DtoModelMesh[] getFlashMeshes() {
        return flashMeshes;
    }

    public void setFlashMeshes( DtoModelMesh[] flashMeshes ) {
        this.flashMeshes = flashMeshes;
    }
}
