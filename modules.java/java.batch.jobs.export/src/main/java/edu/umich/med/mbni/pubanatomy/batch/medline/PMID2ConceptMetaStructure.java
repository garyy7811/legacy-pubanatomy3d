package edu.umich.med.mbni.pubanatomy.batch.medline;

import edu.umich.med.mbni.pubanatomy.batch.domain.AbaStructure;
import edu.umich.med.mbni.pubanatomy.solr.medline.SolrBeanMedlineCitation;
import edu.umich.med.mbni.pubanatomy.solr.pmid2concept.SolrBeanPmid2Concept;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: flashflexpro@gmail.com
 * Date: 9/12/13
 * Time: 10:53 AM
 */
public class PMID2ConceptMetaStructure extends PMID2ConceptMeta{

    @Override
    protected boolean injectMetaIntoBean( SolrBeanPmid2Concept m, SolrBeanMedlineCitation rt ) throws InvocationTargetException, IllegalAccessException{
        Assert.isTrue( !super.injectMetaIntoBean( m, rt ) );

        rt.setBrainStructureIds( m.getConceptIds() );

        List<String> symbLst = new ArrayList<>();
        List<String> nameLst = new ArrayList<>();
        for( int i = 0; i < m.getConceptStrings().length; i++ ){
            String s = m.getConceptStrings()[ i ];
            if( i % 2 == 0 ){
                symbLst.add( s );
            }
            else{
                nameLst.add( s );
            }
        }

        rt.setBrainStructureSymbols( symbLst.toArray( new String[ nameLst.size() ] ) );
        rt.setBrainStructureNames( nameLst.toArray( new String[ nameLst.size() ] ) );

        return true;
    }

    @Override
    public SolrBeanPmid2Concept fields2bean( List<String> strLst, SolrBeanPmid2Concept bean ){
        List<String> nameLst = new ArrayList<>();
        List<Long> idLst = new ArrayList<>();
        for( int i = 0; i < strLst.size(); i++ ){
            Long strId = Long.parseLong( strLst.get( i ) );

            AbaStructure dto = structureService.findOne( strId.intValue() );
            if( dto == null ){
                logger.error( "cant' find brain structure: " + strId );
                continue;
            }

            nameLst.add( dto.getAcronym() );
            nameLst.add( dto.getName() );
            idLst.add( strId );
        }
        if( nameLst.size() > 0 ){
            bean.setConceptIds( idLst.toArray( new Long[ idLst.size() ] ) );
            bean.setConceptStrings( nameLst.toArray( new String[ nameLst.size() ] ) );
            return bean;
        }
        return null;
    }

}
