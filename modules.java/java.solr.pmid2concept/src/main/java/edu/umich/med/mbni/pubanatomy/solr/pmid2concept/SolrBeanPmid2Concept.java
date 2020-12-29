package edu.umich.med.mbni.pubanatomy.solr.pmid2concept;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.util.Assert;

/**
 * @author flashflexpro@gmail.com
 *         Date: 4/23/12
 *         Time: 2:05 PM
 */
public class SolrBeanPmid2Concept{

    @Field
    private long pmid = -1L;

    @Field
    private Long[] conceptIds;

    @Field
    private String[] conceptStrings;

    public long getPmid(){
        return pmid;
    }

    public void setPmid( long pmid ){
        this.pmid = pmid;
    }

    public Long[] getConceptIds(){
        return conceptIds;
    }

    public void setConceptIds( Long[] conceptIds ){
        this.conceptIds = conceptIds;
    }

    public String[] getConceptStrings(){
        return conceptStrings;
    }

    public void setConceptStrings( String[] conceptStrings ){
        Assert.notNull( conceptStrings );
        Assert.isTrue( conceptStrings.length > 0 );
        this.conceptStrings = conceptStrings;
    }
}
