package edu.umich.med.mbni.pubanatomy.batch.medline;

import edu.umich.med.mbni.pubanatomy.solr.pmid2concept.SolrBeanPmid2Concept;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class BatchReaderFlatFileToConceptBeanPage extends AbstractItemCountingItemStreamItemReader<SolrBeanPmid2Concept>{

    public BatchReaderFlatFileToConceptBeanPage(){
        setName( ClassUtils.getShortName( getClass() ) );
    }

    @Resource
    private MedlineAutoUpdateJobContext jobContext;

    private FlatFileItemReader<String[]> flatFileItemReader;

    public FlatFileItemReader<String[]> getFlatFileItemReader(){
        return flatFileItemReader;
    }

    public void setFlatFileItemReader( FlatFileItemReader<String[]> flatFileItemReader ){
        this.flatFileItemReader = flatFileItemReader;
    }

    private PMID2ConceptMeta meta;

    @Override
    public void open( ExecutionContext executionContext ) throws ItemStreamException{
        flatFileItemReader.open( executionContext );
        super.open( executionContext );
    }

    @Override
    public void update( ExecutionContext executionContext ) throws ItemStreamException{
        flatFileItemReader.update( executionContext );
        super.update( executionContext );
    }

    @Override
    public void close() throws ItemStreamException{
        flatFileItemReader.close();
        super.close();
    }

    @Override
    protected void doOpen() throws Exception{
        meta = jobContext.getCurrentMeta();
        solrServer = meta.getSolrServer();
    }
    @Override
    protected void doClose() throws Exception{
        meta = null;
        solrServer = null;
    }

    private HttpSolrServer solrServer;

    private Long lastPmid = null;
    private List<String> lastList = null;

    @Override
    protected SolrBeanPmid2Concept doRead() throws Exception{
        SolrBeanPmid2Concept rt = null;
        while( true ){
            String[] lineStrArr = flatFileItemReader.read();
            if( lineStrArr == null ){
                if( lastPmid != null ){
                    rt = convertListIntoBean();
                }
                return rt;
            }

            rt = addLineToList( lineStrArr );
            if( rt == null ){
                continue;
            }
            return rt;
        }
    }

    protected SolrBeanPmid2Concept addLineToList( String[] lineStrArr ) throws SolrServerException{

        if( !meta.validate( lineStrArr ) ){
            return null;
        }

        SolrBeanPmid2Concept rt = null;
        Long currentLinePmid = Long.parseLong( lineStrArr[ meta.getPmidIndex() ] );
        if( !currentLinePmid.equals( lastPmid ) ){
            if( lastPmid != null ){
                rt = convertListIntoBean();
            }
            lastPmid = currentLinePmid;
            lastList = new ArrayList<>();
        }
        lastList.add( lineStrArr[ meta.getPropertyIndex() ] );
        return rt;
    }

    protected SolrBeanPmid2Concept convertListIntoBean() throws SolrServerException{

        if( !meta.isOverrideExist() ){
            QueryResponse response = solrServer.query( new SolrQuery( "pmid:" + lastPmid ) );
            if( response.getResults().getNumFound() > 0 ){
                return null;
            }
        }

        SolrBeanPmid2Concept bean = new SolrBeanPmid2Concept();
        bean.setPmid( lastPmid );
        bean = meta.fields2bean( lastList, bean );
        return bean;
    }

    @Override
    protected void jumpToItem( int itemIndex ) throws Exception{
        setCurrentItemCount( itemIndex );
    }



}