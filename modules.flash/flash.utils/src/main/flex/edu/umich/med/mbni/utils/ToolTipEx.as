/**
 * @author flashflexpro@gmail.com
 * Date: 3/27/13
 * Time: 11:32 AM
 */
package edu.umich.med.mbni.utils {

import flash.events.Event;
import flash.events.MouseEvent;

import mx.controls.ToolTip;
import mx.core.mx_internal;

use namespace mx_internal;

public class ToolTipEx extends ToolTip {
    public function ToolTipEx(){
        super();
        addEventListener( Event.REMOVED_FROM_STAGE, onRemoved );
        addEventListener( MouseEvent.ROLL_OVER, onRollOver );
        addEventListener( MouseEvent.ROLL_OUT, onRollOut );
        addEventListener( MouseEvent.CLICK, onClick );
    }


    protected function onRemoved( event:Event ):void{
        removeEventListener( Event.REMOVED_FROM_STAGE, onRemoved );
        removeEventListener( MouseEvent.ROLL_OVER, onRollOver );
        removeEventListener( MouseEvent.ROLL_OUT, onRollOut );
        removeEventListener( MouseEvent.CLICK, onClick );
    }

    protected function onRollOut( event:MouseEvent ):void{
        ToolTipManagerImplExt.getInstance().mouseRollOverTip = null;
    }

    protected function onRollOver( event:MouseEvent ):void{
        ToolTipManagerImplExt.getInstance().mouseRollOverTip = this;
    }

    protected function onClick( event:MouseEvent = null ):void{
        CONFIG::debugging{
            throw new Error( this + ".onClick " );
        }
    }

    override public function validateSize( recursive:Boolean = false ):void{
        super.validateSize( recursive );
        callLater( ToolTipManagerImplExt.getInstance().positionTip );
    }

}
}
