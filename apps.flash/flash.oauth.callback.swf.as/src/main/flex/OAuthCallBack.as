/**
 * @author flashflexpro@gmail.com
 * Date: 8/24/12
 * Time: 11:23 AM
 */
package {
import edu.umich.med.mbni.pubanatomy.oauth.callback.OAuthConnection;

import flash.display.Sprite;
import flash.events.Event;
import flash.external.ExternalInterface;
import flash.text.TextField;

public class OAuthCallBack extends Sprite{
    public function OAuthCallBack(){
        addEventListener( Event.ADDED_TO_STAGE, onAddedToStage );
    }

    private function onAddedToStage( event:Event ):void{
        removeEventListener( Event.ADDED_TO_STAGE, onAddedToStage );

        addChild( _label );
        if( loaderInfo.parameters.callBackId == null ){
            _label.text = "How do you get here?";
            return;
        }

        _authConnection = new OAuthConnection( null, null, callResult );
        _authConnection.callRemote( loaderInfo.parameters.callBackId, loaderInfo.parameters );
    }
    private var _label:TextField = new TextField();
    private var _authConnection:OAuthConnection;
    private function callResult( success:Boolean ):void{
        if( success ){
            _authConnection.destroy();
            ExternalInterface.call( "window.close()");
        }
        else{
            _label.text = "can't find the main app";
        }
    }


}
}
