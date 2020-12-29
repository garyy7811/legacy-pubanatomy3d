/**
 * Created with IntelliJ IDEA.
 * User: garyyang
 * Date: 7/17/13
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */
package edu.umich.med.mbni.pubanatomy.integrate.view {
import flash.display.Sprite;

import mx.core.BitmapAsset;

public class TooltipExPubImg extends Sprite{

    [Embed(source='/../resources/assets/helpAvail.png')]
    [Bindable]
    private var helpAvailImg:Class;

    public function TooltipExPubImg(){
        var i:BitmapAsset = new helpAvailImg();
        addChild( i );
    }
}
}
