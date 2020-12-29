package edu.umich.med.mbni.pubanatomy.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/16/12
 *         Time: 2:15 PM
 */
@Document
public class ToolTip{
    @Id
    private String uid;

    private String shortTipTxt;

    public String getShortTipTxt(){
        return shortTipTxt;
    }

    public void setShortTipTxt( String shortTipTxt ){
        this.shortTipTxt = shortTipTxt;
    }

    public String getUid(){
        return uid;
    }

    public void setUid( String uid ){
        this.uid = uid;
    }
}
