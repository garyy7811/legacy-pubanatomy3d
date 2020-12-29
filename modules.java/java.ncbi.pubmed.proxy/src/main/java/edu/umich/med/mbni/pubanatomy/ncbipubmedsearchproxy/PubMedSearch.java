package edu.umich.med.mbni.pubanatomy.ncbipubmedsearchproxy;

import gov.nih.nlm.ncbi.www.soap.eutils.EFetchPubmedServiceStub;
import gov.nih.nlm.ncbi.www.soap.eutils.EUtilsServiceStub;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.rmi.RemoteException;

/**
 * User: flashflexpro@gmail.com
 * Date: 7/3/13
 * Time: 11:13 AM
 */
@Component
public class PubMedSearch{

    private static EUtilsServiceStub eUtilsService;
    private static EFetchPubmedServiceStub eFetchPubmedService;

    @Cacheable( "pubMedCount" )
    public String[] count( @PathVariable String q ) throws RemoteException{
        EUtilsServiceStub.ESearchRequest req = new EUtilsServiceStub.ESearchRequest();
        req.setDb( "pubmed" );
        req.setTerm( q );
        req.setUsehistory( "y" );
        req.setSort( "PublicationDate" );

        if( eUtilsService == null ){
            eUtilsService = new EUtilsServiceStub();
        }
        EUtilsServiceStub.ESearchResult res = eUtilsService.run_eSearch( req );

        return new String[]{ q, res.getCount(), res.getWebEnv(), res.getQueryKey() };
    }

    @Cacheable( "pubMedList" )
    public String listPmidStr( String w, String k, int c ) throws RemoteException{
        EFetchPubmedServiceStub.EFetchRequest req = new EFetchPubmedServiceStub.EFetchRequest();
        req.setWebEnv( w );
        req.setQuery_key( k );
        req.setRetstart( "0" );
        req.setRetmax( c + "" );
        if( eFetchPubmedService == null ){
            eFetchPubmedService = new EFetchPubmedServiceStub();
        }
        EFetchPubmedServiceStub.EFetchResult res = eFetchPubmedService.run_eFetch( req );
        StringBuffer stringBuffer = new StringBuffer( "pmid:( " );
        for( int i = 0; i < res.getPubmedArticleSet().getPubmedArticleSetChoice().length; i++ ){
            EFetchPubmedServiceStub.PubmedArticleType art = res.getPubmedArticleSet().getPubmedArticleSetChoice()[ i
                    ].getPubmedArticle();

            if( i > 0 ){
                stringBuffer.append( " OR " );
            }
            stringBuffer.append( art.getMedlineCitation().getPMID().getString() );
        }
        stringBuffer.append( " )" );
        return stringBuffer.toString();
    }
}
