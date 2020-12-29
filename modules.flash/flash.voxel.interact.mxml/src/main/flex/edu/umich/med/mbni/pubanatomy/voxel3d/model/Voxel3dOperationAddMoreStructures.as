/**
 * @author flashflexpro@gmail.com
 * Date: 1/30/13
 * Time: 4:03 PM
 */
package edu.umich.med.mbni.pubanatomy.voxel3d.model {
import edu.umich.med.mbni.pubanatomy.atlas.model.StructureItem;
import edu.umich.med.mbni.utils.ModelOperationMenuItem;

import flash.events.MouseEvent;

public class Voxel3dOperationAddMoreStructures extends ModelOperationMenuItem {
    public function Voxel3dOperationAddMoreStructures( mouseev:MouseEvent, row:Voxel3dStructureTreeGridDataRow ){
        super( "Load Children to 3D Model View", null, null, true, mouseev );
        _v3dStructureRow = row;
        toolTip = "Drag from All Structure on the right and drop into here for flexibility!";
    }

    private var _v3dStructureRow:Voxel3dStructureTreeGridDataRow;

    override protected function onExecute():void{
        var adding:Vector.<StructureItem> = new <StructureItem>[];

        for( var i:int = _v3dStructureRow.structure.directChildren.length - 1; i >= 0; i-- ){
            var a:StructureItem = _v3dStructureRow.structure.directChildren.getItemAt( i ) as StructureItem;
            adding.push( a );
            ( _v3dStructureRow.v3dTreeGridData.rootModelVoxel3d.syncAllStructuresToVoxels.getDestBySource( a ) as Voxel3dStructure ).on3dStage = true;
        }

        _v3dStructureRow.v3dTreeGridData.rootModelVoxel3d.callInNext.putIn( openAndSelectAdded, this, [ adding ], 5 );
    }

    private function openAndSelectAdded( adding:Vector.<StructureItem> ):void{
        var sl:Vector.<Object> = new Vector.<Object>();
        for( var i:int = _v3dStructureRow.v3dTreeGridData.inputRowDataList.length - 1; i >= 0; i-- ){
            var tr:Voxel3dStructureTreeGridDataRow = _v3dStructureRow.v3dTreeGridData.inputRowDataList[i] as Voxel3dStructureTreeGridDataRow;
            if( adding.indexOf( tr.structure ) >= 0 ){
                tr.setOpenOrClose( true );
                sl.push( tr );
            }
        }
        _v3dStructureRow.v3dTreeGridData.selectedItems = sl
    }
}
}
