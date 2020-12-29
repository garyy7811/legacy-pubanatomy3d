/**
 * @author flashflexpro@gmail.com
 * Date: 1/29/13
 * Time: 12:25 PM
 */
package edu.umich.med.mbni.utils {
import flash.events.Event;

[Bindable]
public class ModelOperationMenuItem extends ModelOperationMenuType {

    public function ModelOperationMenuItem( label:String = "", target:Object = null, type:String = null, enabled:Boolean = true, ev:Event = null ){
        super();
        this.type = type;
        _trigerEvent = ev;

        this.target = target;
        this.label = ( label == "" ? ( this + "" ) : label);
        this.enabled = enabled;
    }

    private var _trigerEvent:Event;

    public function get trigerEvent():Event{
        return _trigerEvent;
    }

    public var toggled:Boolean;

    public var enabled:Boolean = true;

    public var label:String;

    public var toolTip:String;


    override public function destroy():void{
        super.destroy();
        _trigerEvent = null;
    }
}
}
