package edu.umich.med.mbni.pubanatomy.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.TermsResponse;
import org.springframework.cache.annotation.Cacheable;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author flashflexpro@gmail.com
 *         Date: 4/30/12
 *         Time: 3:55 PM
 */
public class TermsSuggesting{

    protected TermsSuggesting( HttpSolrServer solrServer ){
        this.solrServer = solrServer;
    }

    private SolrServer solrServer;

    @Cacheable( "suggest" )
    public HashMap<String, Long> getTopTermsByPrefix( String fieldName, String prefixStr,
                                                      int termsLimit ) throws IOException, SAXException,
            ParserConfigurationException, SolrServerException{
        SolrQuery params = new SolrQuery();
        params.setQueryType( "/terms" );
        params.setTermsPrefix( prefixStr );
        params.set( "terms.fl", fieldName );
        params.setTermsLimit( termsLimit );

        QueryResponse resp = solrServer.query( params );
        List<TermsResponse.Term> m = resp.getTermsResponse().getTerms( fieldName );
        HashMap<String, Long> rt = new HashMap<>( m.size() );
        for( TermsResponse.Term t : m ){
            rt.put( t.getTerm(), t.getFrequency() );
        }
        return rt;
    }


    @Cacheable( "count" )
    public long getQuickCount( String queryStr ) throws IOException, SAXException, ParserConfigurationException,
            SolrServerException{
        SolrQuery quickCountQuery = new SolrQuery( queryStr );
        quickCountQuery.setRows( 0 );
        QueryResponse resp = solrServer.query( quickCountQuery );
        return resp.getResults().getNumFound();
    }

}
