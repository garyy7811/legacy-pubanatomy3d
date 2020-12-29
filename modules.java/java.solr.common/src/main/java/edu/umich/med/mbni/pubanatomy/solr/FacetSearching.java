package edu.umich.med.mbni.pubanatomy.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author flashflexpro@gmail.com
 *         Date: 4/30/12
 *         Time: 3:55 PM
 */
abstract public class FacetSearching<SolrBeanT extends Object>{

    protected FacetSearching( SolrServer solrServer ){
        this.solrServer = solrServer;
    }

    abstract protected Class<SolrBeanT> getBeanClass();

    private SolrServer solrServer;

    public FacetSearchResult searchWithFacets( final String queryStr, int startRow, int rowNum, HashMap<String,
            Object[]> field2offsetLimitPrefixSort, String sortBy, boolean desc ) throws IOException, SAXException,
            ParserConfigurationException, SolrServerException{

        FacetSearchResult<SolrBeanT> rt = new FacetSearchResult<SolrBeanT>();
        final SolrQuery params = new SolrQuery();
        params.setQuery( queryStr );
        params.setStart( startRow );
        params.setRows( rowNum );

        rt.setQueryStr( queryStr );
        rt.setqStartRow( startRow );
        rt.setqRowNum( rowNum );
        if( field2offsetLimitPrefixSort != null && field2offsetLimitPrefixSort.size() > 0 ){
            params.setFacet( true );
            params.setFacetSort( "count" );
            for( Map.Entry<String, Object[]> entry : field2offsetLimitPrefixSort.entrySet() ){
                params.add( "facet.field", entry.getKey() );
                params.add( "f." + entry.getKey() + ".facet.mincount", "1" );
                params.add( "f." + entry.getKey() + ".facet.offset", entry.getValue()[ 0 ].toString() );
                params.add( "f." + entry.getKey() + ".facet.limit", entry.getValue()[ 1 ].toString() );
                if( entry.getValue().length > 2 && entry.getValue()[ 2 ] != null ){
                    params.add( "f." + entry.getKey() + ".facet.prefix", entry.getValue()[ 2 ].toString() );
                }
                if( entry.getValue().length > 3 && entry.getValue()[ 3 ] != null ){
                    params.add( "f." + entry.getKey() + ".facet.sort", entry.getValue()[ 3 ].toString() );
                }
            }
        }
        if( sortBy != null && sortBy.length() > 0 ){
            params.addSortField( sortBy, desc ? SolrQuery.ORDER.desc : SolrQuery.ORDER.asc );
        }
        QueryResponse solrResp = solrServer.query( params );

        rt.setrNumFound( solrResp.getResults().getNumFound() );
        rt.setrStartRow( solrResp.getResults().getStart() );

        List<SolrBeanT> beans = solrResp.getBeans( getBeanClass() );
        SolrBeanT[] rta = ( SolrBeanT[] ) Array.newInstance( getBeanClass(), beans.size() );
        beans.toArray( rta );
        rt.setrQueryRecords( rta );

        if( field2offsetLimitPrefixSort != null && field2offsetLimitPrefixSort.size() > 0 ){
            HashMap<String, HashMap<String, Long>> ffarr = new HashMap<String, HashMap<String, Long>>();
            for( String fs : field2offsetLimitPrefixSort.keySet() ){
                HashMap<String, Long> fr = new HashMap<String, Long>();
                FacetField lst = solrResp.getFacetField( fs );
                if( lst.getValueCount() > 0 && lst.getValues() != null ){
                    for( FacetField.Count c : lst.getValues() ){
                        fr.put( c.getName(), c.getCount() );
                    }
                }
                ffarr.put( fs, fr );
            }
            rt.setrFacetsRecords( ffarr );
        }
        return rt;
    }


    public static class FacetSearchResult<SolrBeanTT extends Object>{

        private String queryStr;
        private long qStartRow;
        private long qRowNum;

        private String[] qFacetFields;

        private int qMaxFacetTermNum;


        private long rNumFound;

        private long rStartRow;

        private SolrBeanTT[] rQueryRecords;

        private HashMap<String, HashMap<String, Long>> rFacetsRecords;


        public String getQueryStr(){
            return queryStr;
        }

        public void setQueryStr( String queryStr ){
            this.queryStr = queryStr;
        }

        public long getqStartRow(){
            return qStartRow;
        }

        public void setqStartRow( long qStartRow ){
            this.qStartRow = qStartRow;
        }

        public long getqRowNum(){
            return qRowNum;
        }

        public void setqRowNum( long qRowNum ){
            this.qRowNum = qRowNum;
        }

        public String[] getqFacetFields(){
            return qFacetFields;
        }

        public void setqFacetFields( String[] qFacetFields ){
            this.qFacetFields = qFacetFields;
        }

        public int getqMaxFacetTermNum(){
            return qMaxFacetTermNum;
        }

        public void setqMaxFacetTermNum( int qMaxFacetTermNum ){
            this.qMaxFacetTermNum = qMaxFacetTermNum;
        }

        public long getrNumFound(){
            return rNumFound;
        }

        public void setrNumFound( long rNumFound ){
            this.rNumFound = rNumFound;
        }

        public long getrStartRow(){
            return rStartRow;
        }

        public void setrStartRow( long rStartRow ){
            this.rStartRow = rStartRow;
        }

        public SolrBeanTT[] getrQueryRecords(){
            return rQueryRecords;
        }

        public void setrQueryRecords( SolrBeanTT[] rQueryRecords ){
            this.rQueryRecords = rQueryRecords;
        }

        public HashMap<String, HashMap<String, Long>> getrFacetsRecords(){
            return rFacetsRecords;
        }

        public void setrFacetsRecords( HashMap<String, HashMap<String, Long>> rFacetsRecords ){
            this.rFacetsRecords = rFacetsRecords;
        }
    }
}
