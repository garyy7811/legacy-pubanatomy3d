/**
 * Generated by Gas3 v2.1.0 (Granite Data Services).
 *
 * NOTE: this file is only generated if it does not exist. You may safely put
 * your custom code here.
 */

package edu.umich.med.mbni.pubanatomy.flash.dto {

    [Bindable]
    [RemoteClass(alias="edu.umich.med.mbni.pubanatomy.flash.dto.DtoPlane")]
    public class DtoPlane extends DtoPlaneBase {
        public function DtoPlane(){
            super();
        }
        
        public function equals( another:DtoPlane ):Boolean{
            return another != null && another.direction == direction &&another.value == value;
        }


        public function toString():String {
            return "DtoPlane[dir=" + direction  + ",val=" + value + "]";
        }
    }
}