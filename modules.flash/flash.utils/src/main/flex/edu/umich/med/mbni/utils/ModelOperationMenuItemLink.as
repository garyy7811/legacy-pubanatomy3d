/**
 * Created with IntelliJ IDEA.
 * User: garyyang
 * Date: 5/15/13
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
package edu.umich.med.mbni.utils {
import flash.net.URLRequest;
import flash.net.navigateToURL;

public class ModelOperationMenuItemLink extends ModelOperationMenuItem{

    public function ModelOperationMenuItemLink( url:String, label:String ){
        super ( label );
        _url = url;
    }

    private var _url:String;

    override protected function onExecute():void{
        navigateToURL( new URLRequest( _url ), "_blank" );
    }
}
}
