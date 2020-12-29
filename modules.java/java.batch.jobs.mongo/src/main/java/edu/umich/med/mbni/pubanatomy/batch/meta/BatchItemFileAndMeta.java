package edu.umich.med.mbni.pubanatomy.batch.meta;

import java.io.InputStream;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/1/13
 * Time: 9:27 AM
 */
public class BatchItemFileAndMeta<T extends GridFileMeta>{

    private InputStream inputStream;

    private T fileMeta;

    public InputStream getInputStream(){
        return inputStream;
    }

    public void setInputStream( InputStream inputStream ){
        this.inputStream = inputStream;
    }

    public T getFileMeta(){
        return fileMeta;
    }

    public void setFileMeta( T fileMeta ){
        this.fileMeta = fileMeta;
    }
}
