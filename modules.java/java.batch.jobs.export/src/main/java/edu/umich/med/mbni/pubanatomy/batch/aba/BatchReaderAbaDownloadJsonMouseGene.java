package edu.umich.med.mbni.pubanatomy.batch.aba;

import edu.umich.med.mbni.pubanatomy.batch.domain.AbaGene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/2/13
 * Time: 9:43 AM
 */
public class BatchReaderAbaDownloadJsonMouseGene extends BatchReaderAbaDownloadJson<AbaGene>{
    @Override
    List<AbaGene> jsonToDomain( HashMap jsResult ){

        ArrayList<HashMap> structures = ( ArrayList<HashMap> ) jsResult.get( "msg" );

        ArrayList<AbaGene> rt = new ArrayList<>();

        for( int i = 0; i < structures.size(); i++ ){
            HashMap hg = structures.get( i );

            AbaGene g = new AbaGene();
            g.setGeneId( ( Integer ) hg.get( "id" ) );
            g.setName( ( String ) hg.get( "name" ) );
            g.setEntrezId( ( Integer ) hg.get( "entrez_id" ) );
            g.setAcronym( ( String ) hg.get( "acronym" ) );
            g.setChromosomeId( ( Integer ) hg.get( "chromosome_id" ) );
            g.setHomoloGeneId( ( Integer ) hg.get( "homologene_id" ) );
            g.setType( ( String ) hg.get( "type" ) );
            g.setLegacyEnsemblGeneId( ( Integer ) hg.get( "legacy_ensembl_gene_id" ) );
            g.setEnsemblId( ( Integer ) hg.get( "ensembl_id" ) );

            g.setAliasTags( ( String ) hg.get( "alias_tags" ) );
            g.setSphinxId( ( Integer ) hg.get( "sphinx_id" ) );

            rt.add( g );
        }
        return rt;
    }
}
