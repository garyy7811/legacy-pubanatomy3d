package edu.umich.med.mbni.pubanatomy.batch.aba;

import edu.umich.med.mbni.pubanatomy.batch.domain.AbaSectionDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/2/13
 * Time: 9:43 AM
 */
public class BatchReaderAbaDownloadJsonDataSet extends BatchReaderAbaDownloadJson<AbaSectionDataSet>{
    @Override
    List<AbaSectionDataSet> jsonToDomain( HashMap jsResult ){

        ArrayList<HashMap> structures = ( ArrayList<HashMap> ) jsResult.get( "msg" );

        ArrayList<AbaSectionDataSet> rt = new ArrayList<AbaSectionDataSet>();

        for( int i = 0; i < structures.size(); i++ ){
            HashMap hm = structures.get( i );

            AbaSectionDataSet vs = new AbaSectionDataSet();
            vs.setSectionDataSetId( ( Integer ) hm.get( "id" ) );
            rt.add( vs );
        }
        return rt;
    }
}
