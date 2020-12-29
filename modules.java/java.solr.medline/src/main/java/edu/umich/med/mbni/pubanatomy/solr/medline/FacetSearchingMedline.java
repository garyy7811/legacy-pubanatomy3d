package edu.umich.med.mbni.pubanatomy.solr.medline;

import edu.umich.med.mbni.pubanatomy.solr.FacetSearching;
import org.apache.solr.client.solrj.SolrServer;

/**
 * @author flashflexpro@gmail.com
 *         Date: 4/30/12
 *         Time: 3:55 PM
 */
public class FacetSearchingMedline extends FacetSearching<SolrBeanMedlineCitation>{

    protected FacetSearchingMedline( SolrServer solrServer ){
        super( solrServer );
    }

    @Override
    protected Class getBeanClass(){
        return SolrBeanMedlineCitation.class;
    }
}
