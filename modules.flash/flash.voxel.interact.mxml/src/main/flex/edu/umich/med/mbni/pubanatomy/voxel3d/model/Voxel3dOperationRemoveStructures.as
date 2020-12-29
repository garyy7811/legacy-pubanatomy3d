/**
 * @author flashflexpro@gmail.com
 * Date: 2/11/13
 * Time: 6:47 PM
 */
package edu.umich.med.mbni.pubanatomy.voxel3d.model {
import edu.umich.med.mbni.utils.ModelOperationMenuItem;

import flash.events.MouseEvent;

import mx.events.CloseEvent;

import spark.components.Alert;

public class Voxel3dOperationRemoveStructures extends ModelOperationMenuItem {
    public function Voxel3dOperationRemoveStructures( structures:Vector.<Voxel3dStructureTreeGridDataRow> ){
        super( ( structures.length > 1 ) ? "Remove Structures" : "Remove this Structure" );
        var nms:Vector.<String> = new <String>[ "remove " ];
        for( var i:int = structures.length - 1; i >= 0; i-- ){
            var s:Voxel3dStructureTreeGridDataRow = structures[i] as Voxel3dStructureTreeGridDataRow;
            nms.push( s.structure.dtoStructure.acronym );
        }
        toolTip = "removing structures' names|^|" + nms.join() + " \r\n#t(v3doprtnRmv)p#";
        _structures = structures;
    }

    private var _structures:Vector.<Voxel3dStructureTreeGridDataRow>;

    override protected function onExecute():void{
        if( _structures.length > 1 ){
            Alert.show( "Are you sure?", "Removing " + _structures.length + " structures from 3D view ... ", Alert.YES | Alert.NO, null, doDel );
        }
        else{
            doDel();
        }
    }

    private function doDel( ev:CloseEvent = null ){
        if( ev != null && ev.detail == Alert.NO ){
            return;
        }
        for( var i:int = _structures.length - 1; i >= 0; i-- ){
            var s:Voxel3dStructureTreeGridDataRow = _structures[i] as Voxel3dStructureTreeGridDataRow;
            s.v3dStructure.on3dStage = false;
        }
    }
}
}
