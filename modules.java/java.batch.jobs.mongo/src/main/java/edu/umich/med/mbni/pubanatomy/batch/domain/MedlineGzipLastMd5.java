package edu.umich.med.mbni.pubanatomy.batch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User: flashflexpro@gmail.com
 * Date: 7/11/13
 * Time: 12:17 PM
 */
@Document
public class MedlineGzipLastMd5{
    @Id
    private String gzipFileName;

    private String MD5;


    public String getGzipFileName(){
        return gzipFileName;
    }

    public void setGzipFileName( String gzipFileName ){
        this.gzipFileName = gzipFileName;
    }

    public String getMD5(){
        return MD5;
    }

    public void setMD5( String MD5 ){
        this.MD5 = MD5;
    }
}
