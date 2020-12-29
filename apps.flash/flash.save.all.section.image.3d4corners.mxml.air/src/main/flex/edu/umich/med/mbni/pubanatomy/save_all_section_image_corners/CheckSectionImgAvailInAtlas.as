/**
 * @author flashflexpro@gmail.com
 * Date: 3/13/13
 * Time: 11:52 AM
 */
package edu.umich.med.mbni.pubanatomy.save_all_section_image_corners {
import edu.umich.med.mbni.pubanatomy.atlas.model.MDLISHImage;

import flash.events.Event;
import flash.events.IOErrorEvent;
import flash.net.URLLoader;
import flash.net.URLLoaderDataFormat;
import flash.net.URLRequest;

public class CheckSectionImgAvailInAtlas {

    private var _svgLoader:URLLoader;
    private var _model:SaveAllSectionImgCornerModel;
    private var _url:String;

    public function CheckSectionImgAvailInAtlas( model:SaveAllSectionImgCornerModel, img:MDLISHImage ){
        _model = model;
        _svgLoader = new URLLoader();
        _svgLoader.addEventListener( Event.COMPLETE, onLoaded );
        _svgLoader.addEventListener( IOErrorEvent.IO_ERROR, onLoaded );
        _svgLoader.dataFormat = URLLoaderDataFormat.TEXT;
        _url = "http://api.brain-map.org/api/v2/image_to_atlas/"
                + img.sectionImageId + ".xml"
                + "?x=" + img.width / 2 + "&y=" + img.height / 2 + "&atlas_id=1";
    }

    public function start():void{
        _model.chkWorking.addItem( this );
        _svgLoader.load( new URLRequest( _url ) );
    }

    private function onLoaded( event:Event ):void{
        _model.chkWorking.removeItem( this );
        _svgLoader.removeEventListener( Event.COMPLETE, onLoaded );
        _svgLoader.removeEventListener( IOErrorEvent.IO_ERROR, onLoaded );
        var x:XML = new XML( _svgLoader.data );
        try{
            _model.onAtlasId( x.children()[0]["section-image-id"].toString() );
        }
        catch( e:Error ){
            trace( e.getStackTrace() );
        }
    }
}
}
