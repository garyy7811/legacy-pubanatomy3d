package edu.umich.med.mbni.pubanatomy.batch.meta;

/**
 * User: flashflexpro@gmail.com
 * Date: 4/30/13
 * Time: 4:29 PM
 */
public abstract class DownGridFileMeta extends GridFileMeta{

    private String fromUrl;

    public String getFromUrl(){
        return fromUrl;
    }

    public void setFromUrl( String fromUrl ){
        this.fromUrl = fromUrl;
    }

}
