package edu.med.mbni.pubanatomy.mongo.test;

import edu.umich.med.mbni.pubanatomy.service.domain.AbaAtlasSectionImgToStructure;
import edu.umich.med.mbni.pubanatomy.mongo.domain.V3dModel;
import edu.umich.med.mbni.pubanatomy.mongo.domain.V3dModelId;
import edu.umich.med.mbni.pubanatomy.mongo.domain.V3dStructure;
import edu.umich.med.mbni.pubanatomy.mongo.repo.AbaAtlasSectionImgToStructureService;
import edu.umich.med.mbni.pubanatomy.mongo.service.ModelService;
import edu.umich.med.mbni.pubanatomy.mongo.service.StructureService;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author flashflexpro@gmail.com
 *         Date: 11/19/12
 *         Time: 3:38 PM
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = "/test.web.service.mongo.xml" )
public class MongoDbTest{

    @Resource
    private StructureService structureService;
    @Resource
    private ModelService modelService;

    @Before
    public void setUp(){
        V3dModel sectionModel = new V3dModel( new V3dModelId( 2384, 1, "" ) );
        modelService.save( sectionModel );

        V3dStructure structure = new V3dStructure();
        structure.setStructureId( 2384 );
        structure.setSectionModel( sectionModel.getId() );
        structureService.save( structure );


    }

    @Test
    public void ttt(){

        V3dModel mdl = modelService.findOne( new V3dModelId( 2384, 1, "" ) );


        Assert.assertEquals( mdl.getId().getStructureId(), 2384 );
        Assert.assertEquals( mdl.getId().getDividedBy(), 1 );


        V3dStructure str = structureService.findOne( 2384 );
        Assert.assertEquals( str.getStructureId(), 2384 );
        Assert.assertEquals( str.getSectionModel().getStructureId(), 2384 );
        Assert.assertEquals( str.getSectionModel().getDividedBy(), 1 );
    }

    @After
    public void destroy(){
        modelService.delete( new V3dModelId( 2384, 1, "" ) );
        structureService.delete( 2384 );
    }

    @Resource
    AbaAtlasSectionImgToStructureService abaAtlasSectionImgToStructureService;

    @Test
    public void testAbaAtlasSectionImgToStructureService(){
        List<AbaAtlasSectionImgToStructure> rt = abaAtlasSectionImgToStructureService
                .findAbaAtlasSectionImgToStructureByIdStructureId( 17158 );
        Assert.assertEquals( 2, rt.size() );
    }

}
