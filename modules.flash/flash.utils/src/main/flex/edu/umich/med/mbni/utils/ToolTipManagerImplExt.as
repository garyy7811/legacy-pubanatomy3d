/**
 * @author flashflexpro@gmail.com
 * Date: 3/27/13
 * Time: 9:35 AM
 */
package edu.umich.med.mbni.utils {
import flash.display.DisplayObject;
import flash.events.MouseEvent;
import flash.geom.Point;

import mx.core.IToolTip;
import mx.core.IUIComponent;

import mx.core.mx_internal;

use namespace mx_internal;

import mx.managers.ToolTipManagerImpl;

public class ToolTipManagerImplExt extends ToolTipManagerImpl {

    private static var _singleInst:ToolTipManagerImplExt;

    public static function getInstance():ToolTipManagerImplExt{
        if( !_singleInst ){
            _singleInst = new ToolTipManagerImplExt();
        }
        return _singleInst;
    }

    override mx_internal function checkIfTargetChanged( displayObject:DisplayObject ):void{
        if( !enabled )
            return;

        if( _currentToolTip is ToolTipEx && ( _currentToolTip as ToolTipEx ).mouseEnabled
                && _currentToolTip.getBounds( _currentToolTip ).contains( _currentToolTip.mouseX, _currentToolTip.mouseY ) ){
            return;
        }

        findTarget( displayObject );

        if( currentTarget != previousTarget || currentToolTip == null ){
            targetChanged();
            previousTarget = currentTarget;
        }
    }

    override mx_internal function toolTipMouseOutHandler( event:MouseEvent ):void{
        if( currentToolTip == null || !( currentToolTip as ToolTipEx ).mouseEnabled ){
            super.toolTipMouseOutHandler( event );
        }
    }

    override mx_internal function systemManager_mouseDownHandler( event:MouseEvent ):void{
        if( event.target == currentToolTip ){
            return;
        }
        super.mx_internal::systemManager_mouseDownHandler( event );
    }

    private var _mouseRollOverTip:ToolTipEx;
    public function get mouseRollOverTip():ToolTipEx{
        return _mouseRollOverTip;
    }

    public function set mouseRollOverTip( value:ToolTipEx ):void{
        _mouseRollOverTip = value;
        if( value == null ){
            hideTimer.start();
        }
        else{
            hideTimer.stop();
        }
    }

    CONFIG::debugging{
        override public function createToolTip( text:String, x:Number, y:Number, errorTipBorderStyle:String = null, context:IUIComponent = null ):IToolTip{
            var rt:IToolTip = super.createToolTip( text, x, y, errorTipBorderStyle, context );
            if( rt is ToolTipEx ){
                return rt;
            }
            //this is a Flex4.6 bug, this method should never be called!
            throw new Error();
        }
    }


    override mx_internal function positionTip():void{
        if( _currentToolTip == null ){
            return;
        }
        super.mx_internal::positionTip();
        var ct:ToolTipEx = _currentToolTip as ToolTipEx;
        if( ct != null && ct.getBounds( ct ).contains( ct.mouseX, ct.mouseY ) ){
            var ctcnr:Point = currentTarget.localToGlobal( new Point( currentTarget.mouseX, currentTarget.mouseY ) );
            ct.move( ctcnr.x - ct.width - 10, ctcnr.y - ct.height - 10 );
        }
    }
}
}
