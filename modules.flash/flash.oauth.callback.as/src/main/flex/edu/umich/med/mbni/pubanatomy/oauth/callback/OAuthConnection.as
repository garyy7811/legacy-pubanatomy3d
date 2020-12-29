/**
 * @author flashflexpro@gmail.com
 * Date: 8/24/12
 * Time: 11:33 AM
 */
package edu.umich.med.mbni.pubanatomy.oauth.callback {

import flash.events.StatusEvent;
import flash.net.LocalConnection;

public class OAuthConnection {

    private var _conn:LocalConnection;
    private var _onRemoteCalled:Function;
    private var _resultHandle:Function;

    public function OAuthConnection( listenTo:String, remoteCalled:Function, resultHandle:Function ){
        _conn = new LocalConnection();
        if( listenTo != null ){
            _conn.client = this;
            _conn.connect( listenTo );
        }
        _onRemoteCalled = remoteCalled;
        _resultHandle = resultHandle;
    }

    public function onCalledByRemote( o:Object ):void{
        _onRemoteCalled.apply( null, [ o ] );
    }

    public function callRemote( remoteConn:String, arg:Object ):void{
        if( ! _conn.hasEventListener( StatusEvent.STATUS ) ){
            _conn.addEventListener( StatusEvent.STATUS, onStatus );
        }
        _conn.send( remoteConn, "onCalledByRemote", arg );
    }

    private function onStatus( event:StatusEvent ):void{
        if( event.level == StatusEvent.STATUS ){
            _resultHandle.apply( null, [ true ] );
        }
        else{
            _resultHandle.apply( null, [ false ] );
        }
    }

    public function destroy():void{
        try{
            _conn.close();
        }
        catch( e:Error ){
        }
        _conn = null;
        _onRemoteCalled = null;
        _resultHandle = null;
    }
}
}
