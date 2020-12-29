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


	public class Voxel3dOperationResetCamera extends ModelOperationMenuItem {
		public function Voxel3dOperationResetCamera ( camera:Dd3dModelCamera ) {
			_camera = camera;
			enabled = (
					_camera.cameraCtrlDistance!=
					Dd3dModelCamera.RESET_DIS ||
					_camera.cameraCtrlPanAngle!=
					Dd3dModelCamera.RESET_PAN ||
					_camera.cameraCtrlTiltAngle!=
					Dd3dModelCamera.RESET_TILT ||
					_camera.cameraCutRadius!=
					Dd3dModelCamera.RESET_CUT
					);
			label = "Reset 3D View";
			toolTip = "#t(reset3dView)p#";
		}

		private var _camera:Dd3dModelCamera;

		override protected function onExecute ():void {
			_camera.reset ();
		}

	}
}
