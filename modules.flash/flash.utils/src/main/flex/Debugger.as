/**
 * @author flashflexpro@gmail.com
 * Date: 12/3/12
 * Time: 10:25 AM
 */
package {
import flash.events.Event;
import flash.net.FileReference;
import flash.net.ObjectEncoding;
import flash.utils.ByteArray;

import mx.utils.ObjectUtil;

public class Debugger {
    public function Debugger(){
    }

    CONFIG::debugging{
        public static function checkPropertyExist( obj:Object, property:String ):Boolean{
            var cls:Object = ObjectUtil.getClassInfo( obj );
            var parr:Array = cls[ "properties" ] as Array;
            for( var i:int = parr.length - 1; i >= 0; i-- ){
                if( parr[i] as String == property ){
                    return true;
                }
                else if( ( parr[i] as QName ).localName == property ){
                    return true;
                }

            }
            return false;
        }

        public static function dumpObj( obj:Object ):void{
            var b:ByteArray = new ByteArray();
            b.objectEncoding = ObjectEncoding.AMF3;
            b.writeObject( obj );
            var fr:FileReference = new FileReference();
            fr.save( b );
        }


        private static var _fr:FileReference = new FileReference();

        public static function readDump():void{
            _fr.addEventListener( Event.SELECT, onSelected );
            _fr.browse()
        }

        private static function onSelected( event:Event ):void{
            var b:ByteArray = _fr.data;
            b.objectEncoding = ObjectEncoding.AMF3;
            var dump:* = b.readObject();
        }
    }
}
}
