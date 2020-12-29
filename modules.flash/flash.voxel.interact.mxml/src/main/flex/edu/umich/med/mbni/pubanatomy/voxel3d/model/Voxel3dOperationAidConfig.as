/**
 * Created with IntelliJ IDEA.
 * User: garyyang
 * Date: 4/23/13
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
package edu.umich.med.mbni.pubanatomy.voxel3d.model {
import edu.umich.med.mbni.pubanatomy.datadriven3dwindow.Dd3dModelCamera;
import edu.umich.med.mbni.utils.ModelOperationMenuItem;
import edu.umich.med.mbni.utils.ModelOperationMenuType;


public class Voxel3dOperationAidConfig extends ModelOperationMenuItem {

    public static const AXISES:String = "Show Axes";
    public static const CENTER:String = "Show Camera Looking At";
    public static const CAMERA:String = "Show Active Camera Ray";
    public static const MOUSE:String = "Show 3D Mouse Position";
    public static const TIP:String = "Show Tip On 3D structure";

    public function Voxel3dOperationAidConfig( camera:Dd3dModelCamera, label:String ){
        _camera = camera;
        type = ModelOperationMenuType.TYPE_CHECK;
        this.label = label;

        if( label == AXISES ){
            toggled = _camera.aidAxis;
            toolTip = "#t(3dviewShowAxes)p#";
        }
        else if( label == CENTER ){
            toggled = _camera.aidCenter;
            toolTip = "#t(3dviewShowCamLookingAt)p#";
        }
        else if( label == CAMERA ){
            toggled = _camera.aidCamera;
            toolTip = "#t(3dviewShowCamRay)p#";
        }
        else if( label == MOUSE ){
            toggled = _camera.aidMouse;
            toolTip = "#t(3dviewShowCurorPosition)p#";
        }
        else if( label == TIP ){
            toggled = _camera.aidTip;
            toolTip = "#t(3dviewShowTipOn3dModel)p#";
        }
    }

    private var _camera:Dd3dModelCamera;

    override protected function onExecute():void{
        if( label == AXISES ){
            _camera.aidAxis = toggled;
        }
        else if( label == CENTER ){
            _camera.aidCenter = toggled;
        }
        else if( label == CAMERA ){
            _camera.aidCamera = toggled;
        }
        else if( label == MOUSE ){
            _camera.aidMouse = toggled;
        }
        else if( label == TIP ){
            _camera.aidTip = toggled;
        }
    }

}
}
