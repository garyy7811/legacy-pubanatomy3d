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
public class AbaSectionDataSet{
    @Id
    private int sectionDataSetId = -1;

    public int getSectionDataSetId(){
        return sectionDataSetId;
    }

    public void setSectionDataSetId( int sectionDataSetId ){
        this.sectionDataSetId = sectionDataSetId;
    }

}
