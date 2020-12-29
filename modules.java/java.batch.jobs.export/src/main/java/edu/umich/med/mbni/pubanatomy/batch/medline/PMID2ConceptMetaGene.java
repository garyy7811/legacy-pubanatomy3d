package edu.umich.med.mbni.pubanatomy.batch.medline;

import edu.umich.med.mbni.pubanatomy.batch.domain.AbaGene;
import edu.umich.med.mbni.pubanatomy.solr.medline.SolrBeanMedlineCitation;
import edu.umich.med.mbni.pubanatomy.solr.pmid2concept.SolrBeanPmid2Concept;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: flashflexpro@gmail.com
 * Date: 9/12/13
 * Time: 10:54 AM
 */
public class PMID2ConceptMetaGene extends PMID2ConceptMeta{


    @Override
    protected boolean injectMetaIntoBean( SolrBeanPmid2Concept m, SolrBeanMedlineCitation rt ) throws
            InvocationTargetException, IllegalAccessException{
        Assert.isTrue( !super.injectMetaIntoBean( m, rt ) );

        rt.setGeneIds( m.getConceptIds() );

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

        rt.setGeneSymbols( symbLst.toArray( new String[ nameLst.size() ] ) );
        rt.setGeneNames( nameLst.toArray( new String[ nameLst.size() ] ) );

        return true;
    }

    @Override
    public SolrBeanPmid2Concept fields2bean( List<String> strLst, SolrBeanPmid2Concept bean ){
        List<String> nameLst = new ArrayList<>();
        List<Long> idLst = new ArrayList<>();
        for( int i = 0; i < strLst.size(); i++ ){
            Long geneId = Long.parseLong( strLst.get( i ) );

            List<AbaGene> foundGenes = geneService.findAbaGeneByEntrezId( geneId.intValue() );
            if( foundGenes == null || foundGenes.size() == 0 ){
                logger.error( "cant' find brain gene: " + geneId );
                continue;
            }

            AbaGene dto = foundGenes.get( 0 );
            nameLst.add( dto.getAcronym() );
            nameLst.add( dto.getName() );
            idLst.add( geneId );
        }
        if( nameLst.size() > 0 ){
            bean.setConceptIds( idLst.toArray( new Long[ idLst.size() ] ) );
            bean.setConceptStrings( nameLst.toArray( new String[ nameLst.size() ] ) );
            return bean;
        }
        return null;
    }

    @Override
    public boolean validate( String[] lineStrArr ){
        return "10090".equals( lineStrArr[ 0 ] );
    }
}
