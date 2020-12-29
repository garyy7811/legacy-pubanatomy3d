package edu.umich.med.mbni.pubanatomy.batch.medline;


import edu.umich.med.mbni.pubanatomy.batch.medline.nihmedline_dtd_nlmmedlinecitationset_130501.*;
import edu.umich.med.mbni.pubanatomy.solr.medline.SolrBeanMedlineCitation;
import edu.umich.med.mbni.pubanatomy.solr.pmid2concept.SolrBeanPmid2Concept;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.dao.DataRetrievalFailureException;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/7/13
 * Time: 2:39 PM
 */
public class BatchReaderMedlineGzipImport extends BatchReaderMedlineGzip<Object[]>{

    @Resource
    private MedlineAutoUpdateJobContext jobContext;

    @Override
    protected Object[] processMedlineCitationSet( final MedlineCitationSet mcSet, File gzipFile ){
        logger.debug( "processMedlineCitationSet gzip: " + gzipFile.getAbsolutePath() );
        final ArrayList<SolrBeanMedlineCitation> beanLst = new ArrayList<>();
        final ArrayList<String> PmidLstToDelete = new ArrayList<>();
        final String gzipMd5;

        try{
            for( MedlineCitation ci : mcSet.getMedlineCitation() ){
                SolrBeanMedlineCitation sbm = extractBean( ci );

                for( int i = 0; i < jobContext.getAllMetas().size(); i++ ){
                    PMID2ConceptMeta pmid2ConceptMeta = jobContext.getAllMetas().get( i );
                    HttpSolrServer solr = pmid2ConceptMeta.getSolrServer();

                    QueryResponse rslt = solr.query( new SolrQuery( "pmid:" + sbm.getPmid() ) );
                    if( rslt.getResults().getNumFound() > 0 ){
                        pmid2ConceptMeta.injectMetaIntoBean( rslt.getBeans( SolrBeanPmid2Concept.class ).get( 0 ),
                                sbm );
                    }
                }

                if( sbm != null ){
                    beanLst.add( sbm );
                }
            }
            if( mcSet.getDeleteCitation() != null && mcSet.getDeleteCitation().getPMID() != null ){
                for( int i = 0; i < mcSet.getDeleteCitation().getPMID().size(); i++ ){
                    PMID toDelPmid = mcSet.getDeleteCitation().getPMID().get( i );
                    PmidLstToDelete.add( toDelPmid.getvalue() );
                }
            }

            gzipMd5 = MedlineAutoUpdateJobContext.getGzipMd5( gzipFile );
        }
        catch( Exception e ){
            logger.debug( "processMedlineCitationSet exception: " + e.getMessage() );
            e.printStackTrace();
            throw new DataRetrievalFailureException( e.getMessage() );
        }
        return new Object[]{ beanLst, PmidLstToDelete, gzipFile, gzipMd5 };
    }

    private SolrBeanMedlineCitation extractBean( MedlineCitation ci ){
        SolrBeanMedlineCitation bean = new SolrBeanMedlineCitation();

        bean.setPmid( Long.parseLong( ci.getPMID().getvalue() ) );

        bean.setJournalCountry( ci.getMedlineJournalInfo().getCountry() );
        bean.setJournalNlmUniqueId( ci.getMedlineJournalInfo().getNlmUniqueID() );
        bean.setJournalMedlineTa( ci.getMedlineJournalInfo().getMedlineTA() );

        if( ci.getSupplMeshList() != null && ci.getSupplMeshList().getSupplMeshName() != null ){
            ArrayList<String> diseases = new ArrayList<>();
            ArrayList<String> protocols = new ArrayList<>();
            for( SupplMeshName n : ci.getSupplMeshList().getSupplMeshName() ){
                if( "disease".equalsIgnoreCase( n.getType() ) ){
                    diseases.add( n.getvalue() );
                }
                else if( "protocol".equalsIgnoreCase( n.getType() ) ){
                    protocols.add( n.getvalue() );
                }
                else{
                    throw new Error( n.getType() );
                }
            }
            bean.setDiseases( diseases.toArray( new String[ diseases.size() ] ) );
            bean.setProtocols( protocols.toArray( new String[ protocols.size() ] ) );
        }


        if( ci.getArticle() != null ){
            List<Language> languageList = ci.getArticle().getLanguage();
            if( languageList != null && languageList.size() > 0 ){
                ArrayList<String> langlst = new ArrayList<>();
                for( Language l : languageList ){
                    langlst.add( l.getvalue() );
                }
                bean.setArticleLangs( langlst.toArray( new String[ langlst.size() ] ) );
            }

            bean.setArticleTitle( ci.getArticle().getArticleTitle() );
            bean.setArticleAffiliation( ci.getArticle().getAffiliation() );
            if( ci.getArticle().getAbstract() != null ){
                ArrayList<String> abstractTxts = new ArrayList<String>();
                for( AbstractText abstractText : ci.getArticle().getAbstract().getAbstractText() ){
                    abstractTxts.add( abstractText.getvalue() );
                }
                bean.setAbstractTexts( abstractTxts.toArray( new String[ abstractTxts.size() ] ) );
            }

            if( ci.getArticle().getJournal() != null ){
                bean.setArticleJournalTitle( ci.getArticle().getJournal().getTitle() );

                bean.setArticleJournalIsoAbbrev( ci.getArticle().getJournal().getISOAbbreviation() );

                if( ci.getArticle().getJournal().getISSN() != null ){
                    bean.setArticleJournalIssn( ci.getArticle().getJournal().getISSN().getvalue() );
                }

                if( ci.getArticle().getJournal().getJournalIssue() != null ){
                    setPubDate( bean, ci.getArticle().getJournal().getJournalIssue().getPubDate() );

                    bean.setArticleJournalIssueVolume( ci.getArticle().getJournal().getJournalIssue().getVolume() );

                    bean.setArticleJournalIssueIssue( ci.getArticle().getJournal().getJournalIssue().getIssue() );
                }
            }

            if( ci.getArticle().getAuthorList() != null && ci.getArticle().getAuthorList().getAuthor() != null ){
                setAuthorList( bean, ci.getArticle().getAuthorList().getAuthor() );
            }


            setArticlePaginationMedlinePgn( bean, ci.getArticle().getPaginationOrELocationID() );


            if( ci.getArticle().getPublicationTypeList() != null && ci.getArticle().getPublicationTypeList()
                                                                            .getPublicationType() != null ){

                ArrayList<String> lst = new ArrayList<String>();
                for( PublicationType tmpType : ci.getArticle().getPublicationTypeList().getPublicationType() ){
                    lst.add( tmpType.getvalue() );
                }
                bean.setArticlePublicationTypeList( lst.toArray( new String[ lst.size() ] ) );
            }
            if( ci.getArticle().getGrantList() != null ){
                for( Grant grant : ci.getArticle().getGrantList().getGrant() ){
                    List<String> lstId = new ArrayList<String>();
                    List<String> lstAcr = new ArrayList<String>();
                    List<String> lstAge = new ArrayList<String>();
                    List<String> lstCoun = new ArrayList<String>();

                    //todo: split this filed into fields
                    if( grant.getGrantID() != null ){
                        lstId.add( grant.getGrantID() );
                    }
                    if( grant.getAcronym() != null ){
                        lstAcr.add( grant.getAcronym() );
                    }
                    if( grant.getAgency() != null ){
                        lstAge.add( grant.getAgency() );
                    }
                    if( grant.getCountry() != null ){
                        lstCoun.add( grant.getCountry() );
                    }
                    bean.setGrantIds( lstId.toArray( new String[ lstId.size() ] ) );
                    bean.setGrantAcronyms( lstAcr.toArray( new String[ lstAcr.size() ] ) );
                    bean.setGrantAgencis( lstAge.toArray( new String[ lstAge.size() ] ) );
                    bean.setGrantCountries( lstCoun.toArray( new String[ lstCoun.size() ] ) );
                }
            }
        }

        if( ci.getOtherAbstract() != null ){
            ArrayList<String> lst = new ArrayList<String>();
            for( OtherAbstract oa : ci.getOtherAbstract() ){
                for( AbstractText at : oa.getAbstractText() ){
                    lst.add( at.getvalue() );
                }
            }
            bean.setOtherAbstractTexts( lst.toArray( new String[ lst.size() ] ) );
        }

        if( ci.getMeshHeadingList() != null && ci.getMeshHeadingList().getMeshHeading() != null ){
            ArrayList<String> lst_desc_major_n = new ArrayList<String>();
            ArrayList<String> lst_desc_major_y = new ArrayList<String>();
            ArrayList<String> lst_desc = new ArrayList<String>();

            ArrayList<String> lst_qual_major_n = new ArrayList<String>();
            ArrayList<String> lst_qual_major_y = new ArrayList<String>();
            ArrayList<String> lst_qual = new ArrayList<String>();
            for( MeshHeading heading : ci.getMeshHeadingList().getMeshHeading() ){
                if( heading.getDescriptorName().getMajorTopicYN().substring( 0, 1 ).equalsIgnoreCase( "n" ) ){
                    lst_desc_major_n.add( heading.getDescriptorName().getvalue() );
                }
                else{
                    lst_desc_major_y.add( heading.getDescriptorName().getvalue() );
                }
                lst_desc.add( heading.getDescriptorName().getvalue() );
                if( heading.getQualifierName() != null && heading.getQualifierName().size() > 0 ){
                    for( QualifierName qn : heading.getQualifierName() ){
                        if( qn.getMajorTopicYN().substring( 0, 1 ).equalsIgnoreCase( "n" ) ){
                            lst_qual_major_n.add( qn.getvalue() );
                        }
                        else{
                            lst_qual_major_y.add( qn.getvalue() );
                        }
                        lst_qual.add( qn.getvalue() );
                    }
                }
            }
            bean.setMeshHeadingDescNames_majorTopic_n( lst_desc_major_n.toArray( new String[ lst_desc_major_n.size()
                    ] ) );
            bean.setMeshHeadingDescNames_majorTopic_y( lst_desc_major_y.toArray( new String[ lst_desc_major_y.size()
                    ] ) );
            bean.setMeshHeadingDescNames( lst_desc.toArray( new String[ lst_desc.size() ] ) );

            bean.setMeshHeadingQualifierNames_majorTopic_n( lst_qual_major_n.toArray( new String[ lst_qual_major_n
                    .size() ] ) );
            bean.setMeshHeadingQualifierNames_majorTopic_y( lst_qual_major_y.toArray( new String[ lst_qual_major_y
                    .size() ] ) );
            bean.setMeshHeadingQualifierNames( lst_qual.toArray( new String[ lst_qual.size() ] ) );
        }

        if( ci.getKeywordList() != null && ci.getKeywordList().size() > 0 ){
            ArrayList<String> lst_m_n = new ArrayList<String>();
            ArrayList<String> lst_m_y = new ArrayList<String>();

            ArrayList<String> lst = new ArrayList<String>();
            for( KeywordList kl : ci.getKeywordList() ){
                for( Keyword kw : kl.getKeyword() ){
                    if( kw.getMajorTopicYN().substring( 0, 1 ).equalsIgnoreCase( "n" ) ){
                        lst_m_n.add( kw.getvalue() );
                    }
                    else{
                        lst_m_y.add( kw.getvalue() );
                    }
                    lst.add( kw.getvalue() );
                }
            }
            bean.setKeywordList_majorTopic_n( lst_m_n.toArray( new String[ lst_m_n.size() ] ) );
            bean.setKeywordList_majorTopic_y( lst_m_y.toArray( new String[ lst_m_y.size() ] ) );
            bean.setKeywords( lst.toArray( new String[ lst.size() ] ) );
        }
        if( ci.getChemicalList() != null ){
            ArrayList<String> lstReg = new ArrayList<String>();
            ArrayList<String> lstNos = new ArrayList<String>();
            for( Chemical ch : ci.getChemicalList().getChemical() ){
                lstNos.add( ch.getNameOfSubstance() );
                lstReg.add( ch.getRegistryNumber() );
            }
            bean.setChemicalNameOfSubstances( lstNos.toArray( new String[ lstNos.size() ] ) );
            bean.setChemicalRegistryNumbers( lstReg.toArray( new String[ lstReg.size() ] ) );
        }

        if( ci.getInvestigatorList() != null ){
            List<String> lst = new ArrayList<String>();
            for( Investigator ivst : ci.getInvestigatorList().getInvestigator() ){
                if( ivst.getForeName() != null ){
                    lst.add( ivst.getForeName().getvalue() );
                }
                if( ivst.getAffiliation() != null ){
                    lst.add( ivst.getAffiliation() );
                }

                if( ivst.getInitials() != null ){
                    lst.add( ivst.getInitials().getvalue() );
                }
                if( ivst.getLastName() != null ){
                    lst.add( ivst.getLastName().getvalue() );
                }
                if( ivst.getSuffix() != null ){
                    lst.add( ivst.getSuffix().getvalue() );
                }
            }
            bean.setInvestigatorList( lst.toArray( new String[ lst.size() ] ) );

            //            importGenes( bean );
        }
        return bean;
    }

    private void setArticlePaginationMedlinePgn( SolrBeanMedlineCitation bean, List<Object> paginationOrELocationID ){
        ArrayList<String> lst = new ArrayList<String>();
        for( Object o : paginationOrELocationID ){
            if( o instanceof Pagination ){
                for( Object p : ( ( Pagination ) o ).getStartPageOrEndPageOrMedlinePgn() ){
                    if( p instanceof StartPage ){
                        lst.add( ( ( StartPage ) p ).getvalue() );
                    }
                    else if( p instanceof EndPage ){
                        lst.add( ( ( EndPage ) p ).getvalue() );
                    }
                    else if( p instanceof MedlinePgn ){
                        lst.add( ( ( MedlinePgn ) p ).getvalue() );
                    }
                }
            }
            else if( o instanceof ELocationID ){
                //todo:
            }
        }
        bean.setArticlePaginationMedlinePgn( lst.toArray( new String[ lst.size() ] ) );
    }

    private void setAuthorList( SolrBeanMedlineCitation bean, List<Author> authorLst ){
        ArrayList<String> lstLast = new ArrayList<>();
        ArrayList<String> lstFore = new ArrayList<>();
        ArrayList<String> lstInit = new ArrayList<>();
        ArrayList<String> lstSuff = new ArrayList<>();
        ArrayList<String> lstCollec = new ArrayList<>();

        ArrayList<String> lstFull = new ArrayList<>();
        for( Author author : authorLst ){
            String full = "";
            List<Object> authorName_LFISC_list = author.getLastNameOrForeNameOrInitialsOrSuffixOrCollectiveName();
            for( int i = 0, tmpSize = authorName_LFISC_list.size(); i < tmpSize; i++ ){
                if( i > 0 ){
                    full += " ";
                }

                Object o = authorName_LFISC_list.get( i );
                if( o instanceof Suffix ){
                    lstSuff.add( ( ( Suffix ) o ).getvalue() );
                    full += ( ( Suffix ) o ).getvalue();
                }
                else if( o instanceof ForeName ){
                    lstFore.add( ( ( ForeName ) o ).getvalue() );
                    full += ( ( ForeName ) o ).getvalue();
                }
                else if( o instanceof LastName ){
                    lstLast.add( ( ( LastName ) o ).getvalue() );
                    full += ( ( LastName ) o ).getvalue();
                }
                else if( o instanceof Initials ){
                    lstInit.add( ( ( Initials ) o ).getvalue() );
                    full += ( ( Initials ) o ).getvalue();
                }
                else if( o instanceof CollectiveName ){
                    lstCollec.add( ( ( CollectiveName ) o ).getvalue() );
                    full += ( ( CollectiveName ) o ).getvalue();
                }
                else{
                    throw new RuntimeException();
                }
            }
            lstFull.add( full );
        }

        bean.setArticleAuthorListLastNames( lstLast.toArray( new String[ lstLast.size() ] ) );
        bean.setArticleAuthorListForeNames( lstFore.toArray( new String[ lstFore.size() ] ) );
        bean.setArticleAuthorListInitials( lstInit.toArray( new String[ lstInit.size() ] ) );
        bean.setArticleAuthorListSuffixs( lstSuff.toArray( new String[ lstSuff.size() ] ) );
        bean.setArticleAuthorListCollectives( lstSuff.toArray( new String[ lstCollec.size() ] ) );

        bean.setArticleAuthorFullNames( lstFull.toArray( new String[ lstFull.size() ] ) );
    }

    private void setPubDate( SolrBeanMedlineCitation bean, PubDate ci ){
        for( Object o : ci.getYearOrMonthOrDayOrSeasonOrMedlineDate() ){
            if( o instanceof Year ){
                bean.setArticleJournalPubDateYear( Integer.parseInt( ( ( Year ) o ).getvalue() ) );
                return;
            }
        }
        bean.setArticleJournalPubDateMedlineDate( ( ( MedlineDate ) ci.getYearOrMonthOrDayOrSeasonOrMedlineDate().get( 0 ) ).getvalue() );
    }
}
