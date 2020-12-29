package edu.umich.med.mbni.pubanatomy.mongo;

import com.mongodb.gridfs.GridFSDBFile;
import edu.umich.med.mbni.pubanatomy.flash.dto.*;
import edu.umich.med.mbni.pubanatomy.mongo.domain.*;
import edu.umich.med.mbni.pubanatomy.mongo.service.*;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * User: flashflexpro@gmail.com
 * Date: 8/5/13
 * Time: 3:17 PM
 */
@Component
public class MongoService{

    @Resource
    Dto3DConfig dtoConfig;

    @Resource
    GridFsTemplate gridFsTemplate;

    @Resource
    StructureService structureService;

    @Resource
    ModelService threeDModelService;

    @Resource
    private SectionService sectionService;

    @Resource
    private ABAImgSectionService abaImgSectionService;

    @Cacheable( "getAppInitArgs" )
    public Object[] getAppInitArgs() throws IOException, ClassNotFoundException{

        GridFSDBFile f = gridFsTemplate.findOne( Query.query( GridFsCriteria.whereFilename().is( FlashGeneExprAtlas
                                                                                                         .MONGO_FILE_NAME + "200" ) ) );
        FlashGeneExprAtlas fl = ( FlashGeneExprAtlas ) new ObjectInputStream( f.getInputStream() ).readObject();
        DtoGeneExprAnnotation exprAnnotation = new DtoGeneExprAnnotation();
        exprAnnotation.setStructureId( fl.getStructureId() );
        exprAnnotation.setUnitlen( fl.getUnitlen() );
        exprAnnotation.setX( fl.getX() );
        exprAnnotation.setY( fl.getY() );
        exprAnnotation.setZ( fl.getZ() );

        return new Object[]{ dtoConfig, DtoConverter.structure2dto( structureService.findOne( dtoConfig
                .getRoot3dstructure() ), structureService ), exprAnnotation

        };
    }

    @Cacheable( "getStructure3dModels" )
    public DtoModel[] getStructure3dModels( int structureId ){
        List<V3dModel> models = threeDModelService.findModelByIdStructureId( structureId );
        List<DtoModel> dtoLst = new ArrayList<DtoModel>( models.size() );
        for( V3dModel model : models ){
            dtoLst.add( DtoConverter.model2dto( model.getId() ) );
        }
        DtoModel[] rt = new DtoModel[ models.size() ];
        return dtoLst.toArray( rt );
    }

    @Cacheable( "getModelMeshArr" )
    public DtoModelMeshArray getModelMeshArr( int structureId, int dividedBy ) throws ClassNotFoundException,
            IOException{
        V3dModel m = threeDModelService.findModelByIdStructureIdAndIdDividedByAndIdProcessId( structureId, dividedBy,
                "" );

        GridFSDBFile f = gridFsTemplate.findOne( Query.query( GridFsCriteria.whereFilename().is( m.getId().toString()
        ) ) );

        FlashMesh[] fm = ( FlashMesh[] ) new ObjectInputStream( f.getInputStream() ).readObject();
        DtoModelMeshArray rt = new DtoModelMeshArray();
        rt.setModel( DtoConverter.model2dto( m.getId() ) );

        DtoModelMesh[] dtoFlashMeshes = new DtoModelMesh[ fm.length ];

        for( int i = 0; i < fm.length; i++ ){
            DtoModelMesh tmp = DtoConverter.mesh2dto( fm[ i ] );
            tmp.setOwner( rt );
            dtoFlashMeshes[ i ] = tmp;
        }
        rt.setFlashMeshes( dtoFlashMeshes );
        return rt;

    }

    @Cacheable( "getSectionsByPlane" )
    public List<DtoSection> getSectionsByPlane( DtoPlane plane ) throws IOException, ClassNotFoundException{
        ArrayList<DtoSection> rt = new ArrayList<DtoSection>();

        List<V3dSection> lstOfSecs = sectionService.findV3dSectionByIdDirectionAndIdValue( plane.getDirection(),
                plane.getValue() );
        if( lstOfSecs != null ){
            for( int i = 0; i < lstOfSecs.size(); i++ ){
                V3dSection v3dSection = lstOfSecs.get( i );
                rt.add( DtoConverter.section2dto( v3dSection ) );
            }
        }
        return rt;
    }

    @Cacheable( "getImageSectionsByImgId" )
    public List<DtoImageSection> getImageSectionsByImgId( long sectionImgId ) throws IOException,
            ClassNotFoundException{

        //        List<V3dABAImageSection> lstOfSecs = abaImgSectionService
        // .findV3dABAImageSectionByIdAbaSectionImageId( sectionImgId );cause duplicate edges!

        List<V3dABAImageSection> lstOfSecs = abaImgSectionService
                .findV3dABAImageSectionByIdAbaSectionImageIdAndIdModelIdDividedBy( sectionImgId, 2 );
        ArrayList<DtoImageSection> rt = new ArrayList<DtoImageSection>();
        if( lstOfSecs != null ){
            for( int i = 0; i < lstOfSecs.size(); i++ ){
                V3dABAImageSection section = lstOfSecs.get( i );
                rt.add( DtoConverter.imgSection2dto( section ) );
            }
        }
        return rt;
    }

    @Cacheable( "getExpressionLevel" )
    public DtoGeneExprLevel getExpressionLevel( long sectionDataSetId, String type ) throws IOException,
            ClassNotFoundException{

        GridFSDBFile f = gridFsTemplate.findOne( Query.query( GridFsCriteria.whereFilename().is( FlashGeneExprLevel
                .getGeneExprGridFsName( sectionDataSetId, type ) ) ) );
        if( f == null ){
            return null;
        }

        FlashGeneExprLevel tmp = ( FlashGeneExprLevel ) new ObjectInputStream( f.getInputStream() ).readObject();
        if( tmp.getUnitLen() != 200 ){
            System.out.println( "getExpressionLevel---wrong unit len:" + tmp.getUnitLen() );
            return null;
        }
        return DtoConverter.geneExpr2Dto( tmp );
    }

    public boolean isExprLevelAvail( long sectionDataSetId, String type ){
        GridFSDBFile f = gridFsTemplate.findOne( Query.query( GridFsCriteria.whereFilename().is( FlashGeneExprLevel
                .getGeneExprGridFsName( sectionDataSetId, type ) ) ) );
        return ( f != null );
    }


    @Resource
    private TooltipHelpService helpService;

    public String getTooltipHelp( String uid ){
        ToolTipHelp rt = helpService.findOne( uid );
        if( rt == null ){
            return null;
        }
        return rt.getHtmlText();
    }

    @Resource
    private TooltipService tooltipService;

    public String getTooltip( String uid ){
        ToolTip tip = tooltipService.findOne( uid );
        if( tip == null ){
            return null;
        }
        return tip.getShortTipTxt();
    }

    public Boolean saveTooltipHelp( String uid, String shortTip, String htmlTxt ){
        if( uid == null || uid.length() == 0 || shortTip == null || shortTip.length() < 3 || shortTip.length() > 200 ){
            return false;
        }

        if( htmlTxt != null ){
            if( htmlTxt.length() < 5 ){
                htmlTxt = null;
            }
            else if( htmlTxt.length() > 5000 ){
                return false;
            }
        }

        ToolTip tip = tooltipService.findOne( uid );
        if( tip == null ){
            tip = new ToolTip();
            tip.setUid( uid );
        }
        tip.setShortTipTxt( shortTip );
        tooltipService.save( tip );

        ToolTipHelp toolTipHelp = new ToolTipHelp();
        toolTipHelp.setUid( uid );
        toolTipHelp.setHtmlText( htmlTxt );
        helpService.save( toolTipHelp );
        return true;
    }
}