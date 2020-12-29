package edu.umich.med.mbni.pubanatomy.service.solr;

import edu.umich.med.mbni.pubanatomy.flash.dto.DtoFacetResultMedline;
import edu.umich.med.mbni.pubanatomy.flash.dto.DtoResultRecordMedline;
import edu.umich.med.mbni.pubanatomy.solr.medline.FacetSearchingMedline;
import edu.umich.med.mbni.pubanatomy.solr.medline.SolrBeanMedlineCitation;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/2/13
 * Time: 2:50 PM
 */
@Controller
@RequestMapping("/search")
public class SolrQueryMedline extends SolrQueryCommon<DtoFacetResultMedline, DtoResultRecordMedline,
        SolrBeanMedlineCitation>{

    @Autowired
    protected SolrQueryMedline( FacetSearchingMedline facetSearching ){
        super( facetSearching );
    }

    @RequestMapping(value = "query", method = RequestMethod.POST)
    public
    @ResponseBody
    DtoFacetResultMedline facetQuery( @RequestBody Object[] args ) throws SAXException, ParserConfigurationException,
            SolrServerException, IOException{
        return super.facetQuery( args );
    }

    @RequestMapping(value = "suggest", method = RequestMethod.GET)
    public
    @ResponseBody
    HashMap suggest( @RequestParam( "param0" ) String fieldName, @RequestParam( "param1" ) String prefixStr,
                     @RequestParam( "param2" ) int lenLimit ) throws SAXException, ParserConfigurationException,
            SolrServerException, IOException{
        return termsSuggesting.getTopTermsByPrefix( fieldName, prefixStr, lenLimit );
    }

    @RequestMapping(value = "count", method = RequestMethod.GET)
    public
    @ResponseBody
    long count( @RequestParam( "param0" ) String qStr ) throws SAXException, ParserConfigurationException,
            SolrServerException, IOException{
        return termsSuggesting.getQuickCount( qStr );
    }


    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    @Override
    protected DtoFacetResultMedline getNewFacetDto(){
        return new DtoFacetResultMedline();
    }

    @Override
    protected DtoResultRecordMedline solrBeanToDto( SolrBeanMedlineCitation s ){
        DtoResultRecordMedline rt = new DtoResultRecordMedline();
        rt.setPmid( s.getPmid() );
        rt.setJournalCountry( s.getJournalCountry() );
        rt.setJournalNlmUniqueId( s.getJournalNlmUniqueId() );
        rt.setJournalMedlineTa( s.getJournalMedlineTa() );
        rt.setArticleTitle( s.getArticleTitle() );
        rt.setArticleLangs( s.getArticleLangs() );
        rt.setArticleJournalTitle( s.getArticleJournalTitle() );
        rt.setArticleJournalPubDateYear( s.getArticleJournalPubDateYear() );
        rt.setArticleJournalPubDateMedlineDate( s.getArticleJournalPubDateMedlineDate() );
        rt.setArticleJournalIsoAbbrev( s.getArticleJournalIsoAbbrev() );
        rt.setArticleJournalIssn( s.getArticleJournalIssn() );
        rt.setArticleAffiliation( s.getArticleAffiliation() );
        rt.setArticlePaginationMedlinePgn( s.getArticlePaginationMedlinePgn() );
        rt.setArticleAuthorListLastNames( s.getArticleAuthorListLastNames() );
        rt.setArticleAuthorListForeNames( s.getArticleAuthorListForeNames() );
        rt.setArticleAuthorListInitials( s.getArticleAuthorListInitials() );
        rt.setArticleAuthorListSuffixs( s.getArticleAuthorListSuffixs() );
        rt.setArticleAuthorListCollectives( s.getArticleAuthorListCollectives() );
        rt.setArticleJournalIssueVolume( s.getArticleJournalIssueVolume() );
        rt.setArticleJournalIssueIssue( s.getArticleJournalIssueIssue() );
        rt.setArticlePublicationTypeList( s.getArticlePublicationTypeList() );
        rt.setMeshHeadingDescNames_majorTopic_n( s.getMeshHeadingDescNames_majorTopic_n() );
        rt.setMeshHeadingDescNames_majorTopic_y( s.getMeshHeadingDescNames_majorTopic_y() );
        rt.setMeshHeadingQualifierNames_majorTopic_n( s.getMeshHeadingQualifierNames_majorTopic_n() );
        rt.setMeshHeadingQualifierNames_majorTopic_y( s.getMeshHeadingQualifierNames_majorTopic_y() );
        rt.setAbstractTexts( s.getAbstractTexts() );
        rt.setOtherAbstractTexts( s.getOtherAbstractTexts() );
        rt.setKeywordList_majorTopic_n( s.getKeywordList_majorTopic_n() );
        rt.setKeywordList_majorTopic_y( s.getKeywordList_majorTopic_y() );
        rt.setGrantIds( s.getGrantIds() );
        rt.setGrantAcronyms( s.getGrantAcronyms() );
        rt.setGrantAgencis( s.getGrantAgencis() );
        rt.setGrantCountries( s.getGrantCountries() );
        rt.setChemicalRegistryNumbers( s.getChemicalRegistryNumbers() );
        rt.setChemicalNameOfSubstances( s.getChemicalNameOfSubstances() );
        rt.setGeneSymbols( s.getGeneSymbols() );
        rt.setInvestigatorList( s.getInvestigatorList() );
        rt.setDiseases( s.getDiseases() );
        rt.setProtocols( s.getProtocols() );
        rt.setArticleAuthorFullNames( s.getArticleAuthorFullNames() );
        rt.setMeshHeadingDescNames( s.getMeshHeadingDescNames() );
        rt.setMeshHeadingQualifierNames( s.getMeshHeadingQualifierNames() );
        rt.setKeywords( s.getKeywords() );

        rt.setFunctions( s.getFunctions() );

        rt.setGeneIds( s.getGeneIds() );
        rt.setGeneSymbols( s.getGeneSymbols() );
        rt.setGeneNames( s.getGeneNames() );


        rt.setBrainStructureIds( s.getBrainStructureIds() );
        rt.setBrainStructureSymbols( s.getBrainStructureSymbols() );
        rt.setBrainStructureNames( s.getBrainStructureNames() );

        return rt;
    }
}