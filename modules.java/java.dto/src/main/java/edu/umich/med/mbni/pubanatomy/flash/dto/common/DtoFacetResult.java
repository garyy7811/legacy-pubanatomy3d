package edu.umich.med.mbni.pubanatomy.flash.dto.common;

import java.util.HashMap;

/**
 * @author flashflexpro@gmail.com
 *         Date: 5/1/12
 *         Time: 11:04 AM
 */

public abstract class DtoFacetResult<T extends Object>{

    private String queryStr;
    private long qStartRow;
    private long qRowNum;

    private String[] qFacetFields;

    private int qMaxFacetTermNum;

    private long rNumFound;

    private long rStartRow;

    private T[] rQueryRecords;

    private HashMap<String, HashMap<String, Long>> rFacetsRecords;

    public String getQueryStr(){
        return queryStr;
    }

    public void setQueryStr( String queryStr ){
        this.queryStr = queryStr;
    }

    public long getqStartRow(){
        return qStartRow;
    }

    public void setqStartRow( long qStartRow ){
        this.qStartRow = qStartRow;
    }

    public long getqRowNum(){
        return qRowNum;
    }

    public void setqRowNum( long qRowNum ){
        this.qRowNum = qRowNum;
    }

    public String[] getqFacetFields(){
        return qFacetFields;
    }

    public void setqFacetFields( String[] qFacetFields ){
        this.qFacetFields = qFacetFields;
    }

    public int getqMaxFacetTermNum(){
        return qMaxFacetTermNum;
    }

    public void setqMaxFacetTermNum( int qMaxFacetTermNum ){
        this.qMaxFacetTermNum = qMaxFacetTermNum;
    }

    public long getrNumFound(){
        return rNumFound;
    }

    public void setrNumFound( long rNumFound ){
        this.rNumFound = rNumFound;
    }

    public long getrStartRow(){
        return rStartRow;
    }

    public void setrStartRow( long rStartRow ){
        this.rStartRow = rStartRow;
    }

    public T[] getrQueryRecords(){
        return rQueryRecords;
    }

    public void setrQueryRecords( T[] rQueryRecords ){
        this.rQueryRecords = rQueryRecords;
    }

    public HashMap<String, HashMap<String, Long>> getrFacetsRecords(){
        return rFacetsRecords;
    }

    public void setrFacetsRecords( HashMap<String, HashMap<String, Long>> rFacetsRecords ){
        this.rFacetsRecords = rFacetsRecords;
    }
}
