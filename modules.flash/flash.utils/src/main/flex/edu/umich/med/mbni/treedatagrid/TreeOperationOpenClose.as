/**
 * @author flashflexpro@gmail.com
 * Date: 1/30/13
 * Time: 11:34 AM
 */
package edu.umich.med.mbni.treedatagrid {
import edu.umich.med.mbni.utils.ModelOperationMenuItem;

import flash.events.MouseEvent;


public class TreeOperationOpenClose extends ModelOperationMenuItem {

    public static const CLOSE_BROTHERS_ONLY:uint = 1;
    public static const CLOSE_ALL_OTHERS:uint = 2;

    public function TreeOperationOpenClose( row:TreeGridDataRow, openOrClose:Boolean, nextDepthNum:uint, closeOthers:uint = 0 ){
        _openOrClose = openOrClose;
        _nextDepthNum = nextDepthNum;
        _closeOthers = closeOthers;

        _maxOpenDepth = row.depth;
        for( var i:int = row.allChildren.length - 1; i >= 0; i-- ){
            var c:TreeGridDataRow = row.allChildren.getItemAt( i ) as TreeGridDataRow;
            if( c.openOrClose == true && c.isInDataGrid ){
                _maxOpenDepth = Math.max( _maxOpenDepth, c.depth );
            }
        }
        var tmp:String = ( _nextDepthNum > 0 ?
                (( openOrClose ? "Open " : "Close " ) + "next " + nextDepthNum + " level(s)")
                : (( openOrClose ? "Open " : "Close " ) + " all levels" ) );
        if( closeOthers > 0 ){
            tmp += ( ( closeOthers == CLOSE_BROTHERS_ONLY ) ? " and close brothers only" : " and close all others" );
        }
        super( tmp, row );
        toolTip = "#t(TreeOperationOpenClose)p#";
    }

    private var _maxOpenDepth:uint = 0;


    [Bindable(event="targetChanged")]
    public function get treeGridRow():TreeGridDataRow{
        return target as TreeGridDataRow;
    }

    private var _openOrClose:Boolean;

    private var _nextDepthNum:uint;


    private var _closeOthers:uint;

    public function get openOrClose():Boolean{
        return _openOrClose;
    }

    public function get nextDepthNum():uint{
        return _nextDepthNum;
    }


    public function get maxOpenDepth():uint{
        return _maxOpenDepth;
    }

    public function get closeOthers():uint{
        return _closeOthers;
    }

    override protected function onExecute():void{

        if( _closeOthers == CLOSE_ALL_OTHERS ){
            for( var i:int = treeGridRow.treeGridData.inputRowDataList.length - 1; i >= 0; i-- ){
                var r:TreeGridDataRow = treeGridRow.treeGridData.inputRowDataList[i] as TreeGridDataRow;
                r.setOpenOrClose( false );
            }
        }
        else if( _closeOthers == CLOSE_BROTHERS_ONLY ){
            if( treeGridRow.parentRow != null ){
                for( var i:int = treeGridRow.parentRow.directChildren.length - 1; i >= 0; i-- ){
                    var tr:TreeGridDataRow = treeGridRow.parentRow.directChildren.getItemAt( i ) as TreeGridDataRow;
                    if( tr != treeGridRow ){
                        tr.openOrCloseChildren( false, int.MAX_VALUE );
                    }
                }
            }
        }

        if( _openOrClose ){
            treeGridRow.setOpenOrClose( true );
        }

        for( var i:int = treeGridRow.allChildren.length - 1; i >= 0; i-- ){
            var c:TreeGridDataRow = treeGridRow.allChildren.getItemAt( i ) as TreeGridDataRow;
            if( _openOrClose && !c.openOrClose && ( _nextDepthNum == 0 || c.depth <= _nextDepthNum + _maxOpenDepth ) ){
                c.setOpenOrClose( true );
            }
            else if( !_openOrClose && c.openOrClose && ( _nextDepthNum == 0 || c.depth > _maxOpenDepth - _nextDepthNum ) ){
                c.setOpenOrClose( false );
            }
        }
    }
}
}
