/**
 * @author flashflexpro@gmail.com
 * Date: 2/15/13
 * Time: 3:41 PM
 */
package edu.umich.med.mbni.pubanatomy {
import edu.umich.med.mbni.pubanatomy.integrate.view.WebWinBase;
import edu.umich.med.mbni.utils.ModelOperationMenuItem;
import edu.umich.med.mbni.webdesktop.WebWinContainer;

import mx.core.FlexGlobals;

import spark.components.Application;

public class AddWindow extends ModelOperationMenuItem {


    public function AddWindow( wc:WebWinContainer, win:Class ){
        _win = win;
        _webWinContainer = wc;
        label = "Add window:" + win;
    }

    private var _webWinContainer:WebWinContainer;
    private var _win:Class;


    override protected function onExecute():void{
        var wb:WebWinBase = _webWinContainer.addElement( new _win() ) as WebWinBase;
        wb.rootView = ( FlexGlobals.topLevelApplication as Application ).skin as AppSkin;
        _webWinContainer = null;
        _win = null;
    }
}
}
