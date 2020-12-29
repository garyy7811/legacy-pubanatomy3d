/**
 * @author flashflexpro@gmail.com
 * Date: 2/23/13
 * Time: 11:34 AM
 */
package edu.umich.med.mbni.pubanatomy.integrate.model {
import edu.umich.med.mbni.utils.ModelOperationMenuItem;

public class CMDDividableWidget extends ModelOperationMenuItem {

    public function CMDDividableWidget( toDo:uint, model:MDLDividableWidget ){
        _toDo = toDo;
        _model = model;
        if( _toDo == CLOSE ){
            label = "Close this view";
        }
        else if( _toDo == POPUP ){
            label = "Popup to window";
        }
        else{
            if( _toDo == HDiv ){
                label = "Add 3D view Horizontally";
                toolTip = "#t(split3dviewH)p#"
            }
            else if( _toDo == VDiv ){
                label = "Add 3D view Vertically";
                toolTip = "#t(split3dviewV)p#"
            }
        }
    }

    public static const CLOSE:uint = 0;
    public static const HDiv:uint = 1;
    public static const VDiv:uint = 2;
    public static const POPUP:uint = 3;

    private var _toDo:uint;

    private var _model:MDLDividableWidget;

    override protected function onExecute():void{
        if( _toDo == CLOSE || _toDo == POPUP ){
            if( _model.modelCamera.scene.focusedCamera == _model.modelCamera ){
                _model.modelCamera.scene.focusedCamera = null;
            }

            if( _toDo == POPUP ){
                CONFIG::debugging{
                    if( _model.sectionPlane == null ){
                        throw new Error( this + ".onExecute " );
                    }
                }
                _model.rootApp.webWinModels.addItem( _model.sectionPlane );
            }
            else if( _model.sectionPlane != null && _model.sectionPlane.v3dPlane != null ){
                _model.sectionPlane.rootModelSection2d.rootModelVoxel3d.planeLst.removeItem( _model.sectionPlane.v3dPlane );
            }
            _model.parent.removeChild( _model );
        }
        else if( _toDo == HDiv || _toDo == VDiv ){
            _model.duplicate( _toDo == VDiv );
        }
    }
}
}
