package edu.umich.med.mbni.pubanatomy.service.solr;

import edu.umich.med.mbni.pubanatomy.flash.dto.DtoException;
import edu.umich.med.mbni.pubanatomy.flash.dto.DtoResultRecordMedline;
import edu.umich.med.mbni.pubanatomy.flash.dto.common.DtoFacetResult;
import edu.umich.med.mbni.pubanatomy.solr.FacetSearching;
import edu.umich.med.mbni.pubanatomy.solr.TermsSuggesting;
import flex.messaging.io.amf.ASObject;
import flex.messaging.util.ExceptionUtil;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/2/13
 * Time: 2:50 PM
 */
abstract public class SolrQueryCommon<FacetResultDto extends DtoFacetResult, RecordDtoT, SolrBeanT>{

    protected SolrQueryCommon( FacetSearching<SolrBeanT> facetSearching ){
        this.facetSearching = facetSearching;
    }

    private FacetSearching<SolrBeanT> facetSearching;

    @Resource
    protected TermsSuggesting termsSuggesting;

    @Cacheable( "facetQuery" )
    protected FacetResultDto facetQuery( Object[] args ) throws SAXException, ParserConfigurationException,
            SolrServerException, IOException{
        String queryStr = ( String ) args[ 0 ];
        Integer startRow = ( Integer ) args[ 1 ];
        Integer rowNum = ( Integer ) args[ 2 ];
        ASObject field2offsetLimitPrefixSort = ( ASObject ) args[ 3 ];
        String sortBy = ( String ) args[ 4 ];
        Boolean desc = ( Boolean ) args[ 5 ];

        FacetSearching.FacetSearchResult facetResult = facetSearching.searchWithFacets( queryStr, startRow, rowNum,
                field2offsetLimitPrefixSort, sortBy, desc );
        return facetResultToDto( facetResult );
    }

    protected FacetResultDto facetResultToDto( FacetSearching.FacetSearchResult searchResult ){
        FacetResultDto dsf = getNewFacetDto();

        dsf.setQueryStr( searchResult.getQueryStr() );
        dsf.setqStartRow( searchResult.getqStartRow() );
        dsf.setqRowNum( searchResult.getqRowNum() );
        dsf.setqFacetFields( searchResult.getqFacetFields() );
        dsf.setqMaxFacetTermNum( searchResult.getqMaxFacetTermNum() );

        dsf.setrNumFound( searchResult.getrNumFound() );
        dsf.setrStartRow( searchResult.getrStartRow() );
        ArrayList<RecordDtoT> lst = new ArrayList<>();
        for( Object r : searchResult.getrQueryRecords() ){
            SolrBeanT s = ( SolrBeanT ) r;
            lst.add( solrBeanToDto( s ) );
        }
        dsf.setrQueryRecords( lst.toArray( new DtoResultRecordMedline[ lst.size() ] ) );

        if( searchResult.getrFacetsRecords() != null ){

            HashMap<String, HashMap<String, Long>> frLst = new HashMap<>();
            for( Object etr : searchResult.getrFacetsRecords().entrySet() ){
                Map.Entry<String, HashMap<String, Long>>  entry = ( Map.Entry<String, HashMap<String, Long>> ) etr;

                HashMap<String, Long> t2m = new HashMap<>();
                for( Map.Entry<String, Long> tnEntry : entry.getValue().entrySet() ){
                    t2m.put( tnEntry.getKey(), tnEntry.getValue() );
                }
                frLst.put( entry.getKey(), t2m );
            }
            dsf.setrFacetsRecords( frLst );
        }
        return dsf;

    }

    abstract protected FacetResultDto getNewFacetDto();

    abstract protected RecordDtoT solrBeanToDto( SolrBeanT solrBean );


    @ExceptionHandler
    @ResponseBody
    public DtoException handleException( Exception ex ){
        return new DtoException( 0, ExceptionUtil.exceptionToString( ex ) );
    }
}
