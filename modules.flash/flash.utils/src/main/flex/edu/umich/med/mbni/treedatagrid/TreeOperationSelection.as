/**
 * @author flashflexpro@gmail.com
 * Date: 1/30/13
 * Time: 11:34 AM
 */
package edu.umich.med.mbni.treedatagrid {
import edu.umich.med.mbni.treemodel.TreeBranchItem;
import edu.umich.med.mbni.utils.ModelOperationMenuItem;

import flash.ui.Keyboard;


public class TreeOperationSelection extends ModelOperationMenuItem {

    public static const SELECT_PARENTS_ALL_LEVEL:uint = 0;
    public static const SELECT_CHILDREN_LEVEL:uint = 1;

    public function TreeOperationSelection( row:TreeGridDataRow, type:uint = SELECT_PARENTS_ALL_LEVEL, level:uint = 0 ){
        var tmp:String = "select all level parents";
        if( type == SELECT_CHILDREN_LEVEL ){
            if( row.depth + level > row.treeGridData.maxDepth ){
                tmp = "select all children ";
            }
            else{
                tmp = "select children " + level + " levels down";
            }
        }
        super( tmp );
        _type = type;
        _row = row;
        _level = level;
        toolTip = "hold Ctrl to add new selection, \n hit ESC to unselect all";
    }

    private var _row:TreeGridDataRow;
    private var _type:uint;
    private var _level:uint;

    override protected function onExecute():void{
        var newSelection:Vector.<Object> =
                ( _row.treeGridData.rootModel.pressedKeys.getItemIndex( Keyboard.CONTROL ) >= 0 && _row.treeGridData.selectedItems != null )
                        ? _row.treeGridData.selectedItems.concat( new Vector.<Object>() ) : new Vector.<Object>();
        if( _type == SELECT_PARENTS_ALL_LEVEL ){
            _row.parentRow.setOpenOrClose( true );
            var prts:Vector.<TreeBranchItem> = _row.getAllParents();
            for( var i:int = prts.length - 1; i >= 0; i-- ){
                newSelection.push( prts[i] );
            }
        }
        else if( _type == SELECT_CHILDREN_LEVEL ){
            for( var j:int = _row.allChildren.length - 1; j >= 0; j-- ){
                var cr:TreeGridDataRow = _row.allChildren.getItemAt( j ) as TreeGridDataRow;
                if( cr.depth - _row.depth <= _level ){
                    cr.setOpenOrClose( true );
                    newSelection.push( cr );
                }
            }
        }
        _row.treeGridData.selectedItems = newSelection;
    }

}
}
