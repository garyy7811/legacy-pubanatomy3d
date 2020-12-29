/**
 * @author flashflexpro@gmail.com
 * Date: 2/11/13
 * Time: 6:47 PM
 */
package edu.umich.med.mbni.pubanatomy.voxel3d.model {
import edu.umich.med.mbni.treedatagrid.TreeGridDataRow;
import edu.umich.med.mbni.utils.ModelOperationMenuItem;

public class Voxel3dOperation3dModelVisibility extends ModelOperationMenuItem {
    public function Voxel3dOperation3dModelVisibility( rows:Vector.<Voxel3dStructureTreeGridDataRow>, fromSelectedRows:Vector.<Object>, v:Boolean ){
        super();
        _rows = rows;
        _visible = v;
        _fromSelectedRows = fromSelectedRows;
        if( _rows != null ){
            if( _visible ){
                label = "Make Selected visible";
                toolTip = "#t(makeSelectedVisible)p#";
            }
            else{
                label = "Make Selected invisible";
                toolTip = "#t(makeSelectedInVisible)p#";
            }
        }
        else if( _fromSelectedRows != null ){
            if( _visible ){
                label = "Make Selected visible Only";
                toolTip = "#t(makeSelectedVisibleOnly)p#";
            }
            else{
                label = "Make Selected invisible Only";
                toolTip = "#t(makeSelectedInVisibleOnly)p#";
            }
        }
        else{
            throw new Error();
        }
    }

    private var _fromSelectedRows:Vector.<Object>;
    private var _visible:Boolean;
    private var _rows:Vector.<Voxel3dStructureTreeGridDataRow>;

    override protected  function onExecute():void{
        if( _rows != null ){
            for( var i:int = _rows.length - 1; i >= 0; i-- ){
                ( _rows[i] as Voxel3dStructureTreeGridDataRow ).v3dStructure.structure3dModel.visible = _visible;
            }
        }
        else if( _fromSelectedRows != null ){
            var rowLst:Vector.<TreeGridDataRow> = ( _fromSelectedRows[ 0 ] as Voxel3dStructureTreeGridDataRow ).v3dTreeGridData.inputRowDataList;
            for( var j:int = rowLst.length - 1; j >= 0; j-- ){
                var row:Voxel3dStructureTreeGridDataRow = rowLst[j] as Voxel3dStructureTreeGridDataRow;
                row.v3dStructure.structure3dModel.visible = ( _fromSelectedRows.indexOf( row ) >= 0 ) ? _visible : !_visible;
            }
        }
        else{
            throw new Error();
        }
    }

}
}
