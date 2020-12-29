package edu.umich.med.mbni.pubanatomy.flash.dto;

/**
 * copy of SolrBeanMedlineCitation
 */
public class DtoResultRecordMedline{


    private long pmid = -1L;


    private String journalCountry;


    private String journalNlmUniqueId;


    private String journalMedlineTa;


    private String articleTitle;


    private String articleJournalTitle;


    private long articleJournalPubDateYear;


    private String articleJournalPubDateMedlineDate;


    private String articleJournalIsoAbbrev;


    private String articleJournalIssn;


    private String articleAffiliation;


    private String articleJournalIssueVolume;


    private String articleJournalIssueIssue;


    private String[] articleLangs;


    private String[] articlePaginationMedlinePgn;


    private String[] articleAuthorListLastNames;


    private String[] articleAuthorListForeNames;


    private String[] articleAuthorListInitials;


    private String[] articleAuthorListSuffixs;


    private String[] articleAuthorListCollectives;


    private String[] articlePublicationTypeList;


    private String[] meshHeadingDescNames_majorTopic_n;


    private String[] meshHeadingDescNames_majorTopic_y;


    private String[] meshHeadingQualifierNames_majorTopic_n;


    private String[] meshHeadingQualifierNames_majorTopic_y;


    private String[] abstractTexts;


    private String[] otherAbstractTexts;


    private String[] keywordList_majorTopic_n;


    private String[] keywordList_majorTopic_y;


    private String[] grantIds;


    private String[] grantAcronyms;


    private String[] grantAgencis;


    private String[] grantCountries;


    private String[] chemicalRegistryNumbers;


    private String[] chemicalNameOfSubstances;


    private String[] investigatorList;


    private String[] diseases;


    private String[] protocols;


    private String[] articleAuthorFullNames;


    private String[] meshHeadingDescNames;


    private String[] meshHeadingQualifierNames;


    private String[] keywords;


    /*--------------------------------------*/


    private Long[] geneIds;


    private String[] geneSymbols;


    private String[] geneNames;


    private Long[] brainStructureIds;


    private String[] brainStructureSymbols;


    private String[] brainStructureNames;


    private String[] functions;

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
}
