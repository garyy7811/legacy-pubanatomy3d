package edu.umich.med.mbni.pubanatomy.mongo;

import edu.umich.med.mbni.pubanatomy.flash.dto.*;
import flex.messaging.util.ExceptionUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * User: flashflexpro@gmail.com
 * Date: 8/5/13
 * Time: 3:17 PM
 */
@Controller
@RequestMapping("/allen-inst")
public class MongoServiceProxyAllenInst{

    @Resource
    MongoService mongoService;

    @RequestMapping(value = "getAppInitArgs", method = RequestMethod.GET)
    public
    @ResponseBody
    Object[] getAppInitArgs() throws IOException, ClassNotFoundException{
        return mongoService.getAppInitArgs();
    }

    @RequestMapping(value = "getStructure3dModels", method = RequestMethod.GET)
    public
    @ResponseBody
    DtoModel[] getStructure3dModels( @RequestParam( "param0" ) int id ){
        return mongoService.getStructure3dModels( id );
    }

    @RequestMapping(value = "getModelMeshArr", method = RequestMethod.GET)
    public
    @ResponseBody
    DtoModelMeshArray getModelMeshArr( @RequestParam( "param0" ) int structureId,
                                       @RequestParam( "param1" ) int dividedBy ) throws ClassNotFoundException,
            IOException{
        return mongoService.getModelMeshArr( structureId, dividedBy );
    }

    @RequestMapping(value = "getSectionsByPlane", method = RequestMethod.GET)
    public
    @ResponseBody
    List<DtoSection> getSectionsByPlane( @RequestParam( "param0" ) int dir, @RequestParam("param1") int val ) throws
            IOException, ClassNotFoundException{
        DtoPlane plane = new DtoPlane();
        plane.setDirection( dir );
        plane.setValue( val );
        return mongoService.getSectionsByPlane( plane );
    }

    @RequestMapping(value = "getImageSectionsByImgId", method = RequestMethod.GET)
    public
    @ResponseBody
    List<DtoImageSection> getImageSectionsByImgId( @RequestParam("param0") int id ) throws IOException,
            ClassNotFoundException{
        return mongoService.getImageSectionsByImgId( id );
    }

    @RequestMapping(value = "getExpressionLevel", method = RequestMethod.GET)
    public
    @ResponseBody
    DtoGeneExprLevel getExpressionLevel( @RequestParam( "param0" ) int sectionDsId,
                                         @RequestParam("param1") String type ) throws IOException,
            ClassNotFoundException{
        return mongoService.getExpressionLevel( sectionDsId, type );
    }

    @RequestMapping( value = "isExprLevelAvail", method = RequestMethod.GET )
    @Cacheable( "isExprLevelAvail" )
    public
    @ResponseBody
    Object[] isExprLevelAvail( @RequestParam( "param0" ) int sectionDsId, @RequestParam( "param1" ) String t1,
                               @RequestParam( "param2" ) String t2, @RequestParam( "param3" ) String t3 ){
        return new Object[]{ mongoService.isExprLevelAvail( sectionDsId, t1 ),
                             mongoService.isExprLevelAvail( sectionDsId, t2 ),
                             mongoService.isExprLevelAvail( sectionDsId, t3 ) };
    }

    @ExceptionHandler
    @ResponseBody
    public DtoException handleException( Exception ex ){
        return new DtoException( 0, ExceptionUtil.exceptionToString( ex ) );
    }
}