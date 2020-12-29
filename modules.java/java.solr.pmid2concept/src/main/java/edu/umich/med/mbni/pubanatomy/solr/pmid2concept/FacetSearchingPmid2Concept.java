package edu.umich.med.mbni.pubanatomy.solr.pmid2concept;

import edu.umich.med.mbni.pubanatomy.solr.FacetSearching;
import org.apache.solr.client.solrj.SolrServer;

/**
 * @author flashflexpro@gmail.com
 *         Date: 4/30/12
 *         Time: 3:55 PM
 */
public class FacetSearchingPmid2Concept extends FacetSearching<SolrBeanPmid2Concept>{

    protected FacetSearchingPmid2Concept( SolrServer solrServer ){
        super( solrServer );
    }

    @Override
    protected Class getBeanClass(){
        return SolrBeanPmid2Concept.class;
    }
}
