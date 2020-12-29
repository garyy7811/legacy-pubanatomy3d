package edu.umich.med.mbni.pubanatomy.batch;

import edu.umich.med.mbni.pubanatomy.batch.meta.GridFileMeta;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/9/13
 * Time: 3:24 PM
 */
public class AmfGridFileMetaStructureIdToAtlasImgIdLst extends GridFileMeta{

    public static final String FILE_NAME = "structureIdToAtlasImgIdLst";

    @Override
    public String getFileName(){
        return FILE_NAME;
    }
}
