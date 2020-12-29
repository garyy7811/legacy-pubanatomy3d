package edu.umich.med.mbni.pubanatomy.mongo;

import edu.umich.med.mbni.pubanatomy.flash.dto.*;
import edu.umich.med.mbni.pubanatomy.mongo.domain.*;
import edu.umich.med.mbni.pubanatomy.mongo.service.StructureService;
import edu.umich.med.mbni.pubanatomy.utils.Persist;
import flex.messaging.io.amf.ASObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author flashflexpro@gmail.com
 *         Date: 3/5/12
 *         Time: 12:20 PM
 */
@Component
public class DtoConverter{
    public static DtoStructure structure2dto( V3dStructure structure, StructureService service ){

        DtoStructure rt = new DtoStructure();
        rt.setStructureId( structure.getStructureId() );
        rt.setName( structure.getName() );
        rt.setAcronym( structure.getAcronym() );

        rt.setAtlasId( structure.getAtlasId() );
        rt.setOntologyId( structure.getOntologyId() );
        rt.setHemisphereId( structure.getHemisphereId() );
        rt.setWeight( structure.getWeight() );
        rt.setGraphId( structure.getGraphId() );
        rt.setColor( structure.getColor() );
        rt.setMin( new int[]{ structure.getMinX(), structure.getMinY(), structure.getMinZ() } );
        rt.setMax( new int[]{ structure.getMaxX(), structure.getMaxY(), structure.getMaxZ() } );
        rt.setPointsNum( structure.getPointsNum() );
        rt.setDepth( structure.getDepth() );

        if( structure.getVisualModel() != null ){
            rt.setVisualModel( model2dto( structure.getVisualModel() ) );
        }
        if( structure.getSectionModel() != null ){
            rt.setSectionModel( model2dto( structure.getSectionModel() ) );
        }

        List<V3dStructure> sc = service.findV3dStructureByParentStructureId( structure.getStructureId() );

        if( sc != null && sc.size() > 0 ){
            for( V3dStructure child : sc ){
                DtoStructure dtoChild = structure2dto( child, service );
                dtoChild.setParent( rt );
                rt.getChildren().add( dtoChild );
            }
        }

        return rt;
    }

    public static DtoModel model2dto( V3dModelId model ){
        DtoModel rt = new DtoModel();
        rt.setStructureId( model.getStructureId() );
        rt.setDividedBy( model.getDividedBy() );
        rt.setProcessId( model.getProcessId() );
        return rt;
    }

    public static DtoSection section2dto( V3dSection section ){
        DtoSection rt = new DtoSection();
        rt.setModel( model2dto( section.getId().getModelId() ) );

        DtoPlane plane = new DtoPlane();
        plane.setDirection( section.getId().getDirection() );
        plane.setValue( section.getId().getValue() );
        rt.setPlane( plane );

        if( section.getEdges() != null ){
            rt.setEdges( new Persist<ArrayList<ArrayList<double[]>>>().uncompressJavaObj( section.getEdges() ) );
        }

        return rt;
    }

    public static DtoModelMesh mesh2dto( FlashMesh flashMesh ){
        DtoModelMesh rt = new DtoModelMesh();
        rt.setIndexes( flashMesh.getIndexes() );
        rt.setVertices( flashMesh.getVertices() );
        rt.setNormals( flashMesh.getNormals() );
        return rt;
    }

/*
    public static DtoVoxel voxel2dto( V3dVoxel found ){
        DtoVoxel rt = new DtoVoxel();
        rt.setX( found.getId().getX() );
        rt.setY( found.getId().getY() );
        rt.setZ( found.getId().getZ() );
        rt.setStructureId( found.getStructureId() );
        return rt;
    }
    public static DtoSearchHighValueTerms hightValTerms2Dto( StatsByReIndex.Result input ){

        DtoSearchHighValueTerms rt = new DtoSearchHighValueTerms();
        rt.setGlobalDocNum( input.getGlobalDocNum() );
        rt.setSubDocNum( input.getSubDocNum() );
        List<DtoSearchHighValueTermNum> tms = new ArrayList<DtoSearchHighValueTermNum>();
        if( input.getTerms() != null && input.getTerms().size() > 0 ){
            for( StatsByReIndex.TermNumFound t : input.getTerms() ){
                tms.add( termNumFound2dto( t ) );
            }
        }
        rt.setTerms( tms );
        return rt;
    }

    private static DtoSearchHighValueTermNum termNumFound2dto( StatsByReIndex.TermNumFound t ){
        DtoSearchHighValueTermNum rt = new DtoSearchHighValueTermNum();
        rt.setGlobalFound( t.getGlobalFound() );
        rt.setSubFound( t.getSubFound() );
        rt.setTerm( t.getTerm() );
        return rt;
    }*/

    public static String solrFieldTerms2QueryStr( ASObject fields2Terms ){
        String rt = null;
        for( Object e : fields2Terms.entrySet() ){
            Map.Entry entry = ( Map.Entry )e;
            if( entry.getValue() == null || entry.getValue() == "" ){
                continue;
            }
            if( rt == null ){
                rt = "";
            }
            else{
                rt += " AND ";
            }

            rt += ( entry.getKey() + ":(" + entry.getValue() + ")" );
        }
        return rt;
    }

    public static DtoGeneExprLevel geneExpr2Dto( FlashGeneExprLevel flge ){
        DtoGeneExprLevel rt = new DtoGeneExprLevel();
        rt.setSectionDataSetId( flge.getSectionDataSetId() );
        rt.setType( flge.getType() );
        rt.setUnitLen( flge.getUnitLen() );
        DtoVoxelLevel[] levels = new DtoVoxelLevel[ flge.getLevel().length ];

        for( int i = 0; i < levels.length; i++ ){
            DtoVoxelLevel cl = new DtoVoxelLevel();
            DtoVoxel v = new DtoVoxel();

            v.setX( flge.getX()[ i ] );
            v.setY( flge.getY()[ i ] );
            v.setZ( flge.getZ()[ i ] );

            cl.setVoxel( v );
            cl.setLevel( flge.getLevel()[ i ] );
            levels[ i ] = cl;
        }

        rt.setVoxelLevels( levels );
        return rt;
    }

    public static DtoImageSection imgSection2dto( V3dABAImageSection section ){
        DtoImageSection dtoImageSection = new DtoImageSection();
        dtoImageSection.setModel(  model2dto(  section.getId().getModelId() ) ) ;
        dtoImageSection.setImageId( section.getId().getAbaSectionImageId() );
        dtoImageSection.setEdges( new Persist<ArrayList<ArrayList<double[]>>>().uncompressJavaObj( section.getEdges() ) );
        return dtoImageSection;
    }
}
