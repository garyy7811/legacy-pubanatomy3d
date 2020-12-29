package edu.umich.med.mbni.pubanatomy.batch;

import edu.umich.med.mbni.pubanatomy.batch.domain.AbaAtlasSectionImgToStructure;
import edu.umich.med.mbni.pubanatomy.batch.domain.AbaStructure;
import edu.umich.med.mbni.pubanatomy.batch.meta.BatchItemFileAndMeta;
import edu.umich.med.mbni.pubanatomy.batch.repo.AbaAtlasSectionImgToStructureService;
import edu.umich.med.mbni.pubanatomy.batch.repo.AbaStructureService;
import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Output;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * User: flashflexpro@gmail.com
 * Date: 4/26/13
 * Time: 2:43 PM
 */
public class BatchReaderAtlasImgToStructureList extends
        AbstractPagingItemReader<BatchItemFileAndMeta<AmfGridFileMetaStructureIdToAtlasImgIdLst>>{

    @Resource
    private MongoOperations operations;

    @Resource
    private AbaAtlasSectionImgToStructureService abaAtlasSectionImgToStructureService;

    @Resource
    private AbaStructureService abaStructureService;

    @Override
    protected void doReadPage(){
        if( results == null ){
            results = new CopyOnWriteArrayList<>();
        }
        else{
            results.clear();
        }
        if( getPage() >= 1 ){
            return;
        }

        MapReduceResults<StructureId2AtlasImgListSize> grpRslt = operations.mapReduce(
                "abaAtlasSectionImgToStructure", "classpath:findAllAtlasImgForStructureMap.js",
                "classpath:findAllAtlasImgForStructureReduce.js", StructureId2AtlasImgListSize.class );
        Iterator<StructureId2AtlasImgListSize> iterator = grpRslt.iterator();
        while( iterator.hasNext() ){
            StructureId2AtlasImgListSize next = iterator.next();
            if( next.getValue() > 0 ){
                List<AbaAtlasSectionImgToStructure> tmp = abaAtlasSectionImgToStructureService
                        .findAbaAtlasSectionImgToStructureByIdStructureId( next.getId() );
                if( tmp.size() > 0 ){
                    for( int i = 0; i < tmp.size(); i++ ){
                        addAtlasSecIdToStructure( tmp.get( i ).getId().getAtlasSectionImgId(), next.getId() );
                    }
                }
            }
        }

        HashMap<Integer, Long[]> str2secIdArr = new HashMap<>(  );

        for( Map.Entry<Integer, ArrayList<Long>> entry :str2secIdLst.entrySet() ){
            str2secIdArr.put( entry.getKey(), entry.getValue().toArray(  new Long[ entry.getValue().size()] ) );
        }

        Amf3Output amf3Output = new Amf3Output( SerializationContext.getSerializationContext() );

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        amf3Output.setOutputStream( out );
        try{
            amf3Output.writeObject( str2secIdArr );
        }
        catch( IOException e1 ){
            e1.printStackTrace();
        }

        BatchItemFileAndMeta<AmfGridFileMetaStructureIdToAtlasImgIdLst> rslt = new BatchItemFileAndMeta<>();
        rslt.setFileMeta( new AmfGridFileMetaStructureIdToAtlasImgIdLst() );
        rslt.setInputStream( new ByteArrayInputStream( out.toByteArray() ) );
        results.add( rslt );
    }

    HashMap<Integer, ArrayList<Long>> str2secIdLst = new HashMap<>();

    private void addAtlasSecIdToStructure( Long imgId, int structureId ){
        AbaStructure c = abaStructureService.findOne( structureId );
        if( c == null ){
            return;
        }
        ArrayList<Long> lst = str2secIdLst.get( structureId );
        if( lst == null ){
            lst = new ArrayList<>(  );
            str2secIdLst.put( structureId, lst );
        }

        if( lst.indexOf( imgId ) < 0 ){
            lst.add( imgId );
        }

        if( c.getParentStructureId() > 0 ){
            addAtlasSecIdToStructure( imgId, c.getParentStructureId() );
        }
    }

    @Override
    protected void doJumpToPage( int itemIndex ){
    }

    @Override
    public void afterPropertiesSet() throws Exception{
        super.afterPropertiesSet();
        Assert.isTrue( getPageSize() == 1 );
    }

    private class StructureId2AtlasImgListSize{
        private int id;

        private int value;

        private int getId(){
            return id;
        }

        private void setId( int id ){
            this.id = id;
        }

        private int getValue(){
            return value;
        }

        private void setValue( int value ){
            this.value = value;
        }
    }

}
