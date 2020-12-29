/*
 * Copyright (C) 2011 flashflexpro@gmail.com  All Rights Reserved.  No
 *   use, copying or distribution of this work may be made except in
 *   accordance with a valid license agreement from flashflexpro@gmail.com
 *   This notice must be included on all copies, modifications and
 *   derivatives of this work.
 *
 *   flashflexpro@gmail.com MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT
 *   THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 *   INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 *   MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 *   NON-INFRINGEMENT. flashflexpro@gmail.com SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED
 *   BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS
 *   SOFTWARE OR ITS DERIVATIVES.
 */

/**
 *
 * Limitation: can only do string -> object mapping, not a object -> object mapping
 *
 * @author flashflexpro@gmail.com
 * Date: 10-12-31
 * Time: ����7:46
 */
package com.flashflexpro.as3lib {
import flash.utils.Dictionary;

import flash.utils.flash_proxy;

import flash.utils.Proxy;

import mx.events.PropertyChangeEvent;
import mx.events.PropertyChangeEventKind;

[Event( name="propertyChange", type="mx.events.PropertyChangeEvent") ]
[Bindable]
[RemoteClass]
public dynamic class EventDictionary extends Proxy {

    private var _dic:Dictionary;
    private var _names:Array;

    public function EventDictionary() {
        _dic = new Dictionary();
        _names = new Array();
    }

    private function getKey(name:*):String {
        if (name is String) {
            return name as String;
        }
        else if (name is QName) {
            return ( name as QName ).localName;
        }
        throw new Error("only accept string key!");

    }

    flash_proxy override function deleteProperty(nm:*):Boolean {
        var name:String = getKey(nm);
        var nameIndex:int = _names.indexOf(name);
        if (nameIndex < 0) {
            return false;
        }

        _names.splice(nameIndex, 1);
        var old:* = _dic[ name ];
//        _dic[ name ] = null;
        delete _dic[ name ];
        dispatchEvent(new PropertyChangeEvent(PropertyChangeEvent.PROPERTY_CHANGE, false, false,
                PropertyChangeEventKind.DELETE, name, old, null ));
        return true
    }

    flash_proxy override function getProperty(nm:*):* {
        var name:String = getKey(nm);
        return _dic[ name ];
    }

    flash_proxy override function setProperty(name:*, value:*):void {
        var key:String = getKey(name);
        var index:int = _names.indexOf(key);
        if (index < 0) {
            _names.push(key);
        }
        if( _dic[key] != value ){
            var old:* = _dic[ key ];
            _dic[ key ] = value;
            dispatchEvent(new PropertyChangeEvent(PropertyChangeEvent.PROPERTY_CHANGE, false, false,
                    PropertyChangeEventKind.UPDATE, key, old, value));
        }
    }


    flash_proxy override function nextNameIndex(index:int):int {
        if (index < _names.length) {
            return index + 1;
        }
        return 0;
    }


    flash_proxy override function nextName(index:int):String {
        return _names[ index - 1 ];
    }

    flash_proxy override function nextValue(index:int):* {
        return _dic[ _names[ index - 1 ]];
    }


    flash_proxy override function callProperty(name:*, ... rest):* {
        throw new Error( "when how?" );
    }


    public function toString():String {
        var rt:String = "{";
        var nameLen:uint = _names.length;
        for (var i:int = 0; i < nameLen; i ++) {
            var cName:String = _names[ i ];
            if (i > 0) {
                rt += ",";
            }
            rt = rt + cName + ":" + _dic[ cName ];
        }
        rt += "}";
        return "EventDictionary" + rt;
    }

    public function valueOf():String {
        throw new Error("Really?");
    }
}
}