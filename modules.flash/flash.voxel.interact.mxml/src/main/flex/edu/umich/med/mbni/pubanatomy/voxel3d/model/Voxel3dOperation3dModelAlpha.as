/**
 * @author flashflexpro@gmail.com
 * Date: 2/11/13
 * Time: 6:47 PM
 */
package edu.umich.med.mbni.pubanatomy.voxel3d.model {

import edu.umich.med.mbni.utils.ModelOperationMenuItem;

public class Voxel3dOperation3dModelAlpha extends ModelOperationMenuItem {
    public function Voxel3dOperation3dModelAlpha( targetTree:Voxel3dStructureOn3dTreeGridData ){
        super();
        _targetTree = targetTree;
        label = "Adjust Opacity";
        toolTip = "#t(v3dOprtnAjstOpct)p#";
    }

    private var _targetTree:Voxel3dStructureOn3dTreeGridData;

    override protected  function onExecute():void{
        _targetTree.isAjustingSelectedItemsAlpha = true;
    }

}
}
