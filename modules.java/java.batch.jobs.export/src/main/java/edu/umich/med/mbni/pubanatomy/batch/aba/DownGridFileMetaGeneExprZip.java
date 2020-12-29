package edu.umich.med.mbni.pubanatomy.batch.aba;

import edu.umich.med.mbni.pubanatomy.batch.meta.DownGridFileMeta;

/**
 * User: flashflexpro@gmail.com
 * Date: 4/30/13
 * Time: 4:29 PM
 */
public class DownGridFileMetaGeneExprZip extends DownGridFileMeta{

    private int abaDataSetId;

    public int getAbaDataSetId(){
        return abaDataSetId;
    }

    public void setAbaDataSetId( int abaDataSetId ){
        this.abaDataSetId = abaDataSetId;
    }

    @Override
    public String getFileName(){
        return toString();
    }

    @Override
    public String toString(){
        return "DownGridFileMetaGeneExprZip{" +
               "abaDataSetId=" + abaDataSetId +
               '}';
    }
}
