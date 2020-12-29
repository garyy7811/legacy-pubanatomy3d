/**
 * @author flashflexpro@gmail.com
 * Date: 3/27/13
 * Time: 10:00 AM
 */
package edu.umich.med.mbni.utils {

import flash.display.Sprite;
import flash.events.Event;
import flash.utils.getDefinitionByName;

import mx.core.Singleton;

import mx.preloaders.SparkDownloadProgressBar;

public class PreLoadConfig extends SparkDownloadProgressBar {

    override public function set preloader( value:Sprite ):void{
        super.preloader = value;
        _prld = value;
        _prld.addEventListener( Event.COMPLETE, beforeSysMngrKickOff, false, int.MAX_VALUE );
    }

    private var _prld:Sprite;

    private function beforeSysMngrKickOff( event:Event ):void{
        Singleton.registerClass( "mx.managers::IToolTipManager2",
                                 Class( getDefinitionByName( "edu.umich.med.mbni.utils.ToolTipManagerImplExt" ) ) );

        _prld.removeEventListener( Event.COMPLETE, beforeSysMngrKickOff );
        _prld = null;
    }

}
}
