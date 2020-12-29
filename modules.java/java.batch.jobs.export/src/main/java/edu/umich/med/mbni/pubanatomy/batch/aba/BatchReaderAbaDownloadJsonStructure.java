package edu.umich.med.mbni.pubanatomy.batch.aba;

import edu.umich.med.mbni.pubanatomy.batch.domain.AbaStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/2/13
 * Time: 9:43 AM
 */
public class BatchReaderAbaDownloadJsonStructure extends BatchReaderAbaDownloadJson<AbaStructure>{
    @Override
    List<AbaStructure> jsonToDomain( HashMap jsResult ){

        ArrayList<HashMap> structures = ( ArrayList<HashMap> ) jsResult.get( "msg" );

        ArrayList<AbaStructure> rt = new ArrayList<>();

        for( int i = 0; i < structures.size(); i++ ){
            HashMap hm = structures.get( i );

            AbaStructure vs = new AbaStructure();
            vs.setStructureId( ( Integer ) hm.get( "id" ) );
            if( hm.get( "parent_structure_id" ) != null ){
                vs.setParentStructureId( ( Integer ) hm.get( "parent_structure_id" ) );
            }
            if( hm.get( "atlas_id" ) != null ){
                vs.setAtlasId( ( Integer ) hm.get( "atlas_id" ) );
            }
            if( hm.get( "name" ) != null ){
                vs.setName( ( String ) hm.get( "name" ) );
            }
            if( hm.get( "acronym" ) != null ){
                vs.setAcronym( ( String ) hm.get( "acronym" ) );
            }
            if( hm.get( "ontology_id" ) != null ){
                vs.setOntologyId( ( Integer ) hm.get( "ontology_id" ) );
            }
            if( hm.get( "hemisphere_id" ) != null ){
                vs.setHemisphereId( ( Integer ) hm.get( "hemisphere_id" ) );
            }
            if( hm.get( "weight" ) != null ){
                vs.setWeight( ( Integer ) hm.get( "weight" ) );
            }
            if( hm.get( "depth" ) != null ){
                vs.setDepth( ( Integer ) hm.get( "depth" ) );
            }
            if( hm.get( "graph_id" ) != null ){
                vs.setGraphId( ( Integer ) hm.get( "graph_id" ) );
            }
            if( hm.get( "color_hex_triplet" ) != null ){
                vs.setColor( ( String ) hm.get( "color_hex_triplet" ) );
            }
            if( hm.get( "neuro_name_structure_id" ) != null ){
                vs.setNeuroNameStructureId( ( Integer ) hm.get( "neuro_name_structure_id" ) );
            }
            if( hm.get( "neuro_name_structure_id_path" ) != null ){
                vs.setNeuroNameStructureIdPath( ( String ) hm.get( "neuro_name_structure_id_path" ) );
            }
            if( hm.get( "sphinx_id" ) != null ){
                vs.setSphinxId( ( Integer ) hm.get( "sphinx_id" ) );
            }
            rt.add( vs );
        }
        return rt;
    }
}
