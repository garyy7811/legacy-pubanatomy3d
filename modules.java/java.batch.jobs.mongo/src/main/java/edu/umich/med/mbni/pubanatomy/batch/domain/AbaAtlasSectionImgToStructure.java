package edu.umich.med.mbni.pubanatomy.batch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/7/13
 * Time: 11:58 AM
 */
@Document
public class AbaAtlasSectionImgToStructure{

    public AbaAtlasSectionImgToStructure( AbaAtlasSectionImgToStructureID id, String description ){
        this.id = id;
        this.description = description;
    }

    @Id
    private AbaAtlasSectionImgToStructureID id;

    private String description;

    public AbaAtlasSectionImgToStructureID getId(){
        return id;
    }

    public void setId( AbaAtlasSectionImgToStructureID id ){
        this.id = id;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription( String description ){
        this.description = description;
    }

    @Override
    public boolean equals( Object o ){
        if( this == o ){
            return true;
        }
        if( !( o instanceof AbaAtlasSectionImgToStructure ) ){
            return false;
        }

        AbaAtlasSectionImgToStructure that = ( AbaAtlasSectionImgToStructure ) o;

        if( description != null ? !description.equals( that.description ) : that.description != null ){
            return false;
        }
        if( id != null ? !id.equals( that.id ) : that.id != null ){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + ( description != null ? description.hashCode() : 0 );
        return result;
    }
}
