/**
 * @author flashflexpro@gmail.com
 * Date: 2/11/13
 * Time: 6:47 PM
 */
package edu.umich.med.mbni.pubanatomy.voxel3d.model {
import edu.umich.med.mbni.utils.ModelOperationMenuItem;

public class Voxel3dOperationGeneExprOnOff3dStage extends ModelOperationMenuItem {
    public function Voxel3dOperationGeneExprOnOff3dStage( dsTypesOn3ds:Vector.<Voxel3dStructureGeneExprType>, fromSelectedRows:Vector.<Object>, onOff3d:Boolean ){
        super();
        _v3dDsTypes = dsTypesOn3ds;
        _onOff3d = onOff3d;
        _fromSelectedRows = fromSelectedRows;
        var dt:Voxel3dStructureGeneExprType = dsTypesOn3ds[ 0 ];
        if( _onOff3d ){
            label = "show " + dt.v3dStructureGeneExpr.structureGene.gene.dtoGene.acronym + "'s expression levels in 3D";
        }
        else{
            label = "remove " + dt.v3dStructureGeneExpr.structureGene.gene.dtoGene.acronym + "'s expression levels from 3D";

        }
        toolTip = "total " + _v3dDsTypes.length + " structure's expression";
    }

    private var _fromSelectedRows:Vector.<Object>;
    private var _onOff3d:Boolean;
    private var _v3dDsTypes:Vector.<Voxel3dStructureGeneExprType>;

    override protected  function onExecute():void{
        for( var i:int = _v3dDsTypes.length - 1; i >= 0; i-- ){
            ( _v3dDsTypes[i] as Voxel3dStructureGeneExprType ).on3dStage = _onOff3d;
        }
    }

}
}
