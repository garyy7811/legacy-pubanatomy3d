package edu.umich.med.mbni.pubanatomy.voxel3d.model {

import edu.umich.med.mbni.pubanatomy.datadriven3dwindow.Dd3dModelCamera;
import edu.umich.med.mbni.utils.ModelOperationMenuItem;
import edu.umich.med.mbni.utils.ModelOperationMenuType;

public class Voxel3dOperationCameraSync extends ModelOperationMenuItem {

    public static const SYNC_None:uint = 0;
    public static const SYNC_DIV:uint = 1;
    public static const SYNC_VAL:uint = 2;

    public function Voxel3dOperationCameraSync( camera:Dd3dModelCamera, target:uint ){
        type = ModelOperationMenuType.TYPE_RADIO;
        _camera = camera;
        _target = target;
        if( _target == SYNC_None ){
            label = "Not at all";
            toolTip = "#t(3dviewSynNone)p#";
        }
        else if( _target == SYNC_DIV ){
            label = "Rotation changes";
            toolTip = "#t(3dviewSynChange)p#";
        }
        else if( _target == SYNC_VAL ){
            label = "Rotation values";
            toolTip = "#t(3dviewSynAll)p#";
        }
        toggled = ( _camera.syncToFocused == _target );
    }

    private var _target:uint;

    private var _camera:Dd3dModelCamera;

    override protected function onExecute():void{
        _camera.syncToFocused = _target;
    }

}
}
