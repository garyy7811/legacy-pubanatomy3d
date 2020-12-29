package edu.umich.med.mbni.pubanatomy.solr.medline;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @author flashflexpro@gmail.com
 *         Date: 4/23/12
 *         Time: 2:05 PM
 */
public class SolrBeanMedlineCitation{

    public static final String[] prpLongs = new String[]{ "pmid", "geneIds", "brainStructureIds", "articleJournalPubDateYear" };
    public static final String[] prpSentcs = new String[]{ "articleTitle", "articleJournalTitle", "articleJournalPubDateMedlineDate",
                                                           "articleAffiliation", "abstractTexts", "otherAbstractTexts" };

    @Field
    private long pmid = -1L;

    @Field
    private String journalCountry;

    @Field
    private String journalNlmUniqueId;

    @Field
    private String journalMedlineTa;

    @Field
    private String articleTitle;

    @Field
    private String articleJournalTitle;

    @Field
    private long articleJournalPubDateYear;

    @Field
    private String articleJournalPubDateMedlineDate;

    @Field
    private String articleJournalIsoAbbrev;

    @Field
    private String articleJournalIssn;

    @Field
    private String articleAffiliation;

    @Field
    private String articleJournalIssueVolume;

    @Field
    private String articleJournalIssueIssue;

    @Field
    private String[] articleLangs;

    @Field
    private String[] articlePaginationMedlinePgn;

    @Field
    private String[] articleAuthorListLastNames;

    @Field
    private String[] articleAuthorListForeNames;

    @Field
    private String[] articleAuthorListInitials;

    @Field
    private String[] articleAuthorListSuffixs;

    @Field
    private String[] articleAuthorListCollectives;

    @Field
    private String[] articlePublicationTypeList;

    @Field
    private String[] meshHeadingDescNames_majorTopic_n;

    @Field
    private String[] meshHeadingDescNames_majorTopic_y;

    @Field
    private String[] meshHeadingQualifierNames_majorTopic_n;

    @Field
    private String[] meshHeadingQualifierNames_majorTopic_y;

    @Field
    private String[] abstractTexts;

    @Field
    private String[] otherAbstractTexts;

    @Field
    private String[] keywordList_majorTopic_n;

    @Field
    private String[] keywordList_majorTopic_y;

    @Field
    private String[] grantIds;

    @Field
    private String[] grantAcronyms;

    @Field
    private String[] grantAgencis;

    @Field
    private String[] grantCountries;

    @Field
    private String[] chemicalRegistryNumbers;

    @Field
    private String[] chemicalNameOfSubstances;

    @Field
    private String[] investigatorList;

    @Field
    private String[] diseases;

    @Field
    private String[] protocols;

    @Field
    private String[] articleAuthorFullNames;

    @Field
    private String[] meshHeadingDescNames;

    @Field
    private String[] meshHeadingQualifierNames;

    @Field
    private String[] keywords;


    /*--------------------------------------*/


    @Field
    private Long[] geneIds;

    @Field
    private String[] geneSymbols;

    @Field
    private String[] geneNames;

    @Field
    private Long[] brainStructureIds;

    @Field
    private String[] brainStructureSymbols;

    @Field
    private String[] brainStructureNames;

    @Field
    private String[] functions;


    @Field
    private String[] query_chemicalNameOfSubstances;

    @Field
    private String[] query_geneNames;

    @Field
    private String[] query_diseases;

    @Field
    private String[] query_brainStructureNames;

    @Field
    private String[] query_functions;

    @Field
    private String[] query_meshHeadingDescNames;

    @Field
    private String[] query_keywords;

    @Field
    private String[] query_fullText;


    public long getPmid(){
        return pmid;
    }

    public void setPmid( long pmid ){
        this.pmid = pmid;
    }

    public String getJournalCountry(){
        return journalCountry;
    }

    public void setJournalCountry( String journalCountry ){
        this.journalCountry = journalCountry;
    }

    public String getJournalNlmUniqueId(){
        return journalNlmUniqueId;
    }

    public void setJournalNlmUniqueId( String journalNlmUniqueId ){
        this.journalNlmUniqueId = journalNlmUniqueId;
    }

    public String getJournalMedlineTa(){
        return journalMedlineTa;
    }

    public void setJournalMedlineTa( String journalMedlineTa ){
        this.journalMedlineTa = journalMedlineTa;
    }

    public String getArticleTitle(){
        return articleTitle;
    }

    public void setArticleTitle( String articleTitle ){
        this.articleTitle = articleTitle;
    }

    public String getArticleJournalTitle(){
        return articleJournalTitle;
    }

    public void setArticleJournalTitle( String articleJournalTitle ){
        this.articleJournalTitle = articleJournalTitle;
    }

    public long getArticleJournalPubDateYear(){
        return articleJournalPubDateYear;
    }

    public void setArticleJournalPubDateYear( long articleJournalPubDateYear ){
        this.articleJournalPubDateYear = articleJournalPubDateYear;
    }

    public String getArticleJournalPubDateMedlineDate(){
        return articleJournalPubDateMedlineDate;
    }

    public void setArticleJournalPubDateMedlineDate( String articleJournalPubDateMedlineDate ){
        this.articleJournalPubDateMedlineDate = articleJournalPubDateMedlineDate;
    }

    public String getArticleJournalIsoAbbrev(){
        return articleJournalIsoAbbrev;
    }

    public void setArticleJournalIsoAbbrev( String articleJournalIsoAbbrev ){
        this.articleJournalIsoAbbrev = articleJournalIsoAbbrev;
    }

    public String getArticleJournalIssn(){
        return articleJournalIssn;
    }

    public void setArticleJournalIssn( String articleJournalIssn ){
        this.articleJournalIssn = articleJournalIssn;
    }

    public String getArticleAffiliation(){
        return articleAffiliation;
    }

    public void setArticleAffiliation( String articleAffiliation ){
        this.articleAffiliation = articleAffiliation;
    }

    public String getArticleJournalIssueVolume(){
        return articleJournalIssueVolume;
    }

    public void setArticleJournalIssueVolume( String articleJournalIssueVolume ){
        this.articleJournalIssueVolume = articleJournalIssueVolume;
    }

    public String getArticleJournalIssueIssue(){
        return articleJournalIssueIssue;
    }

    public void setArticleJournalIssueIssue( String articleJournalIssueIssue ){
        this.articleJournalIssueIssue = articleJournalIssueIssue;
    }

    public String[] getArticleLangs(){
        return articleLangs;
    }

    public void setArticleLangs( String[] articleLangs ){
        this.articleLangs = articleLangs;
    }

    public String[] getArticlePaginationMedlinePgn(){
        return articlePaginationMedlinePgn;
    }

    public void setArticlePaginationMedlinePgn( String[] articlePaginationMedlinePgn ){
        this.articlePaginationMedlinePgn = articlePaginationMedlinePgn;
    }

    public String[] getArticleAuthorListLastNames(){
        return articleAuthorListLastNames;
    }

    public void setArticleAuthorListLastNames( String[] articleAuthorListLastNames ){
        this.articleAuthorListLastNames = articleAuthorListLastNames;
    }

    public String[] getArticleAuthorListForeNames(){
        return articleAuthorListForeNames;
    }

    public void setArticleAuthorListForeNames( String[] articleAuthorListForeNames ){
        this.articleAuthorListForeNames = articleAuthorListForeNames;
    }

    public String[] getArticleAuthorListInitials(){
        return articleAuthorListInitials;
    }

    public void setArticleAuthorListInitials( String[] articleAuthorListInitials ){
        this.articleAuthorListInitials = articleAuthorListInitials;
    }

    public String[] getArticleAuthorListSuffixs(){
        return articleAuthorListSuffixs;
    }

    public void setArticleAuthorListSuffixs( String[] articleAuthorListSuffixs ){
        this.articleAuthorListSuffixs = articleAuthorListSuffixs;
    }

    public String[] getArticleAuthorListCollectives(){
        return articleAuthorListCollectives;
    }

    public void setArticleAuthorListCollectives( String[] articleAuthorListCollectives ){
        this.articleAuthorListCollectives = articleAuthorListCollectives;
    }

    public String[] getArticlePublicationTypeList(){
        return articlePublicationTypeList;
    }

    public void setArticlePublicationTypeList( String[] articlePublicationTypeList ){
        this.articlePublicationTypeList = articlePublicationTypeList;
    }

    public String[] getMeshHeadingDescNames_majorTopic_n(){
        return meshHeadingDescNames_majorTopic_n;
    }

    public void setMeshHeadingDescNames_majorTopic_n( String[] meshHeadingDescNames_majorTopic_n ){
        this.meshHeadingDescNames_majorTopic_n = meshHeadingDescNames_majorTopic_n;
    }

    public String[] getMeshHeadingDescNames_majorTopic_y(){
        return meshHeadingDescNames_majorTopic_y;
    }

    public void setMeshHeadingDescNames_majorTopic_y( String[] meshHeadingDescNames_majorTopic_y ){
        this.meshHeadingDescNames_majorTopic_y = meshHeadingDescNames_majorTopic_y;
    }

    public String[] getMeshHeadingQualifierNames_majorTopic_n(){
        return meshHeadingQualifierNames_majorTopic_n;
    }

    public void setMeshHeadingQualifierNames_majorTopic_n( String[] meshHeadingQualifierNames_majorTopic_n ){
        this.meshHeadingQualifierNames_majorTopic_n = meshHeadingQualifierNames_majorTopic_n;
    }

    public String[] getMeshHeadingQualifierNames_majorTopic_y(){
        return meshHeadingQualifierNames_majorTopic_y;
    }

    public void setMeshHeadingQualifierNames_majorTopic_y( String[] meshHeadingQualifierNames_majorTopic_y ){
        this.meshHeadingQualifierNames_majorTopic_y = meshHeadingQualifierNames_majorTopic_y;
    }

    public String[] getAbstractTexts(){
        return abstractTexts;
    }

    public void setAbstractTexts( String[] abstractTexts ){
        this.abstractTexts = abstractTexts;
    }

    public String[] getOtherAbstractTexts(){
        return otherAbstractTexts;
    }

    public void setOtherAbstractTexts( String[] otherAbstractTexts ){
        this.otherAbstractTexts = otherAbstractTexts;
    }

    public String[] getKeywordList_majorTopic_n(){
        return keywordList_majorTopic_n;
    }

    public void setKeywordList_majorTopic_n( String[] keywordList_majorTopic_n ){
        this.keywordList_majorTopic_n = keywordList_majorTopic_n;
    }

    public String[] getKeywordList_majorTopic_y(){
        return keywordList_majorTopic_y;
    }

    public void setKeywordList_majorTopic_y( String[] keywordList_majorTopic_y ){
        this.keywordList_majorTopic_y = keywordList_majorTopic_y;
    }

    public String[] getGrantIds(){
        return grantIds;
    }

    public void setGrantIds( String[] grantIds ){
        this.grantIds = grantIds;
    }

    public String[] getGrantAcronyms(){
        return grantAcronyms;
    }

    public void setGrantAcronyms( String[] grantAcronyms ){
        this.grantAcronyms = grantAcronyms;
    }

    public String[] getGrantAgencis(){
        return grantAgencis;
    }

    public void setGrantAgencis( String[] grantAgencis ){
        this.grantAgencis = grantAgencis;
    }

    public String[] getGrantCountries(){
        return grantCountries;
    }

    public void setGrantCountries( String[] grantCountries ){
        this.grantCountries = grantCountries;
    }

    public String[] getChemicalRegistryNumbers(){
        return chemicalRegistryNumbers;
    }

    public void setChemicalRegistryNumbers( String[] chemicalRegistryNumbers ){
        this.chemicalRegistryNumbers = chemicalRegistryNumbers;
    }

    public String[] getChemicalNameOfSubstances(){
        return chemicalNameOfSubstances;
    }

    public void setChemicalNameOfSubstances( String[] chemicalNameOfSubstances ){
        this.chemicalNameOfSubstances = chemicalNameOfSubstances;
    }

    public String[] getInvestigatorList(){
        return investigatorList;
    }

    public void setInvestigatorList( String[] investigatorList ){
        this.investigatorList = investigatorList;
    }

    public String[] getDiseases(){
        return diseases;
    }

    public void setDiseases( String[] diseases ){
        this.diseases = diseases;
    }

    public String[] getProtocols(){
        return protocols;
    }

    public void setProtocols( String[] protocols ){
        this.protocols = protocols;
    }

    public String[] getArticleAuthorFullNames(){
        return articleAuthorFullNames;
    }

    public void setArticleAuthorFullNames( String[] articleAuthorFullNames ){
        this.articleAuthorFullNames = articleAuthorFullNames;
    }

    public String[] getMeshHeadingDescNames(){
        return meshHeadingDescNames;
    }

    public void setMeshHeadingDescNames( String[] meshHeadingDescNames ){
        this.meshHeadingDescNames = meshHeadingDescNames;
    }

    public String[] getMeshHeadingQualifierNames(){
        return meshHeadingQualifierNames;
    }

    public void setMeshHeadingQualifierNames( String[] meshHeadingQualifierNames ){
        this.meshHeadingQualifierNames = meshHeadingQualifierNames;
    }

    public String[] getKeywords(){
        return keywords;
    }

    public void setKeywords( String[] keywords ){
        this.keywords = keywords;
    }

    public Long[] getGeneIds(){
        return geneIds;
    }

    public void setGeneIds( Long[] geneIds ){
        this.geneIds = geneIds;
    }

    public String[] getGeneSymbols(){
        return geneSymbols;
    }

    public void setGeneSymbols( String[] geneSymbols ){
        this.geneSymbols = geneSymbols;
    }

    public String[] getGeneNames(){
        return geneNames;
    }

    public void setGeneNames( String[] geneNames ){
        this.geneNames = geneNames;
    }

    public Long[] getBrainStructureIds(){
        return brainStructureIds;
    }

    public void setBrainStructureIds( Long[] brainStructureIds ){
        this.brainStructureIds = brainStructureIds;
    }

    public String[] getBrainStructureSymbols(){
        return brainStructureSymbols;
    }

    public void setBrainStructureSymbols( String[] brainStructureSymbols ){
        this.brainStructureSymbols = brainStructureSymbols;
    }

    public String[] getBrainStructureNames(){
        return brainStructureNames;
    }

    public void setBrainStructureNames( String[] brainStructureNames ){
        this.brainStructureNames = brainStructureNames;
    }

    public String[] getFunctions(){
        return functions;
    }

    public void setFunctions( String[] functions ){
        this.functions = functions;
    }

    public String[] getQuery_chemicalNameOfSubstances(){
        return query_chemicalNameOfSubstances;
    }

    public void setQuery_chemicalNameOfSubstances( String[] query_chemicalNameOfSubstances ){
        this.query_chemicalNameOfSubstances = query_chemicalNameOfSubstances;
    }

    public String[] getQuery_geneNames(){
        return query_geneNames;
    }

    public void setQuery_geneNames( String[] query_geneNames ){
        this.query_geneNames = query_geneNames;
    }

    public String[] getQuery_diseases(){
        return query_diseases;
    }

    public void setQuery_diseases( String[] query_diseases ){
        this.query_diseases = query_diseases;
    }

    public String[] getQuery_brainStructureNames(){
        return query_brainStructureNames;
    }

    public void setQuery_brainStructureNames( String[] query_brainStructureNames ){
        this.query_brainStructureNames = query_brainStructureNames;
    }

    public String[] getQuery_functions(){
        return query_functions;
    }

    public void setQuery_functions( String[] query_functions ){
        this.query_functions = query_functions;
    }

    public String[] getQuery_meshHeadingDescNames(){
        return query_meshHeadingDescNames;
    }

    public void setQuery_meshHeadingDescNames( String[] query_meshHeadingDescNames ){
        this.query_meshHeadingDescNames = query_meshHeadingDescNames;
    }

    public String[] getQuery_keywords(){
        return query_keywords;
    }

    public void setQuery_keywords( String[] query_keywords ){
        this.query_keywords = query_keywords;
    }

    public String[] getQuery_fullText(){
        return query_fullText;
    }

    public void setQuery_fullText( String[] query_fullText ){
        this.query_fullText = query_fullText;
    }
}
