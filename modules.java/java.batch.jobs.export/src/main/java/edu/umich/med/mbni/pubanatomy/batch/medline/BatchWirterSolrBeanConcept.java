package edu.umich.med.mbni.pubanatomy.batch.medline;


import edu.umich.med.mbni.pubanatomy.solr.pmid2concept.SolrBeanPmid2Concept;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.batch.item.ItemWriter;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: flashflexpro@gmail.com
 * Date: 8/6/13
 * Time: 4:33 PM
 */
public class BatchWirterSolrBeanConcept implements ItemWriter<SolrBeanPmid2Concept>{

    @Resource
    private MedlineAutoUpdateJobContext jobContext;

    private HttpSolrServer solrServer;

    @Override
    public void write( List<? extends SolrBeanPmid2Concept> items ) throws Exception{
        if( solrServer == null ){
            solrServer = jobContext.getCurrentMeta().getSolrServer();
        }
        solrServer.addBeans( items );
        solrServer.commit();
    }

}
