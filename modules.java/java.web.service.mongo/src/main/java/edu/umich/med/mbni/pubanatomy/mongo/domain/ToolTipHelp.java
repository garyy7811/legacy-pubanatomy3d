package edu.umich.med.mbni.pubanatomy.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/16/12
 *         Time: 2:15 PM
 */
@Document
public class ToolTipHelp{
    @Id
    private String uid;

    private String htmlText;

    public String getHtmlText(){
        return htmlText;
    }

    public void setHtmlText( String htmlText ){
        this.htmlText = htmlText;
    }

    public String getUid(){
        return uid;
    }

    public void setUid( String uid ){
        this.uid = uid;
    }
}
