package edu.umich.med.mbni.pubanatomy.batch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/16/12
 *         Time: 2:15 PM
 */
@Document
public class AbaGene{

    @Id
    private int geneId = -1;

    @Indexed
    private String name;

    private String acronym;
    /**
     * organism_id
     */
    private Integer organismId;

    /**
     * chromosome_id
     */
    private Integer chromosomeId;

    /**
     * homologene_id
     */
    private Integer homoloGeneId;

    @Indexed
    private String type;

    /**
     * legacy_ensembl_gene_id
     */
    private Integer legacyEnsemblGeneId;

    /**
     * ensembl_id
     */
    @Indexed
    private Integer ensemblId;


    /**
     * entrez_id
     */
    @Indexed
    private Integer entrezId;


    /**
     * alias-tags
     */
    private String aliasTags;

    /**
     *sphinx_id
     */
    private Integer sphinxId;


    public int getGeneId(){
        return geneId;
    }

    public void setGeneId( int geneId ){
        this.geneId = geneId;
    }

    public String getName(){
        return name;
    }

    public void setName( String name ){
        this.name = name;
    }

    public String getAcronym(){
        return acronym;
    }

    public void setAcronym( String acronym ){
        this.acronym = acronym;
    }

    public Integer getOrganismId(){
        return organismId;
    }

    public void setOrganismId( Integer organismId ){
        this.organismId = organismId;
    }

    public Integer getChromosomeId(){
        return chromosomeId;
    }

    public void setChromosomeId( Integer chromosomeId ){
        this.chromosomeId = chromosomeId;
    }

    public Integer getHomoloGeneId(){
        return homoloGeneId;
    }

    public void setHomoloGeneId( Integer homoloGeneId ){
        this.homoloGeneId = homoloGeneId;
    }

    public String getType(){
        return type;
    }

    public void setType( String type ){
        this.type = type;
    }

    public Integer getLegacyEnsemblGeneId(){
        return legacyEnsemblGeneId;
    }

    public void setLegacyEnsemblGeneId( Integer legacyEnsemblGeneId ){
        this.legacyEnsemblGeneId = legacyEnsemblGeneId;
    }

    public Integer getEnsemblId(){
        return ensemblId;
    }

    public void setEnsemblId( Integer ensemblId ){
        this.ensemblId = ensemblId;
    }

    public Integer getEntrezId(){
        return entrezId;
    }

    public void setEntrezId( Integer entrezId ){
        this.entrezId = entrezId;
    }

    public String getAliasTags(){
        return aliasTags;
    }

    public void setAliasTags( String aliasTags ){
        this.aliasTags = aliasTags;
    }

    public Integer getSphinxId(){
        return sphinxId;
    }

    public void setSphinxId( Integer sphinxId ){
        this.sphinxId = sphinxId;
    }
}
