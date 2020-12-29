package edu.umich.med.mbni.pubanatomy.batch.medline;

import edu.umich.med.mbni.pubanatomy.batch.domain.MedlineGzipLastMd5;
import edu.umich.med.mbni.pubanatomy.batch.repo.AbaGeneService;
import edu.umich.med.mbni.pubanatomy.batch.repo.AbaStructureService;
import edu.umich.med.mbni.pubanatomy.solr.medline.SolrBeanMedlineCitation;
import edu.umich.med.mbni.pubanatomy.solr.pmid2concept.SolrBeanPmid2Concept;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.core.io.Resource;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


/**
 * User: flashflexpro@gmail.com
 * Date: 8/6/13
 * Time: 1:44 PM
 */
public class PMID2ConceptMeta{

    protected Log logger = LogFactory.getLog( getClass() );

    private Resource file;

    private int pmidIndex;

    private int propertyIndex;

    private String propertyName;

    private HttpSolrServer solrServer;

    private boolean overrideExist = false;

    private MedlineGzipLastMd5 lastModifiedMark;

    public Resource getFile(){
        return file;
    }

    public void setFile( Resource file ){
        this.file = file;
    }

    public int getPmidIndex(){
        return pmidIndex;
    }

    public void setPmidIndex( int pmidIndex ){
        this.pmidIndex = pmidIndex;
    }

    public int getPropertyIndex(){
        return propertyIndex;
    }

    public void setPropertyIndex( int propertyIndex ){
        this.propertyIndex = propertyIndex;
    }

    public String getPropertyName(){
        return propertyName;
    }

    public void setPropertyName( String propertyName ){
        this.propertyName = propertyName;
    }

    public HttpSolrServer getSolrServer(){
        return solrServer;
    }

    public void setSolrServer( HttpSolrServer solrServer ){
        this.solrServer = solrServer;
    }

    @javax.annotation.Resource
    protected AbaStructureService structureService;

    @javax.annotation.Resource
    protected AbaGeneService geneService;

    public boolean isOverrideExist(){
        return overrideExist;
    }

    public void setOverrideExist( boolean overrideExist ){
        this.overrideExist = overrideExist;
    }

    public void setLastModifiedMark( MedlineGzipLastMd5 lastModifiedMark ){
        this.lastModifiedMark = lastModifiedMark;
    }

    public MedlineGzipLastMd5 getLastModifiedMark(){
        return lastModifiedMark;
    }

    private Method propertyWriter;

    protected boolean injectMetaIntoBean( SolrBeanPmid2Concept m, SolrBeanMedlineCitation bean ) throws InvocationTargetException, IllegalAccessException{
        if( propertyWriter == null ){
            try{
                propertyWriter = new PropertyDescriptor( propertyName, SolrBeanMedlineCitation.class ).getWriteMethod();
            }
            catch( IntrospectionException e ){
                e.printStackTrace();
                throw new Error( e.getMessage() );
            }
        }

        if( ArrayUtils.indexOf( SolrBeanMedlineCitation.prpLongs, propertyName ) < 0 ){
            propertyWriter.invoke( bean, new Object[]{ m.getConceptStrings() } );
            return true;
        }
        return false;
    }

    public SolrBeanPmid2Concept fields2bean( List<String> strLst, SolrBeanPmid2Concept bean ){
        String[] a = new String[ strLst.size() ];
        strLst.toArray( a );
        bean.setConceptStrings( a );
        return bean;
    }


    public void destroy(){
        solrServer.shutdown();
    }

    public boolean validate( String[] lineStrArr ){
        return true;
    }
}
