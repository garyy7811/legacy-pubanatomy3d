package edu.umich.med.mbni.pubanatomy.batch.aba;

import edu.umich.med.mbni.pubanatomy.batch.meta.DownGridFileMeta;

/**
 * User: flashflexpro@gmail.com
 * Date: 4/30/13
 * Time: 4:29 PM
 */
public class DownGridFileMetaAnnotation extends DownGridFileMeta{

    private int abaOntologyId;

    private int resolutionUm;

    public int getAbaOntologyId(){
        return abaOntologyId;
    }

    public void setAbaOntologyId( int abaOntologyId ){
        this.abaOntologyId = abaOntologyId;
    }

    public int getResolutionUm(){
        return resolutionUm;
    }

    public void setResolutionUm( int resolutionUm ){
        this.resolutionUm = resolutionUm;
    }

    @Override
    public String getFileName(){
        return toString();
    }

    @Override
    public String toString(){
        return "DownGridFileMetaAnnotation{" +
               "abaOntologyId=" + abaOntologyId +
               ", resolutionUm=" + resolutionUm +
               '}';
    }
}
