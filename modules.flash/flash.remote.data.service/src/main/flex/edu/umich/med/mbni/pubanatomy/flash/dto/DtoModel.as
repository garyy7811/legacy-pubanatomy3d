/**
 * Generated by Gas3 v2.1.0 (Granite Data Services).
 *
 * NOTE: this file is only generated if it does not exist. You may safely put
 * your custom code here.
 */

package edu.umich.med.mbni.pubanatomy.flash.dto {

[Bindable]
[RemoteClass(alias="edu.umich.med.mbni.pubanatomy.flash.dto.DtoModel")]
public class DtoModel extends DtoModelBase {
    public function DtoModel(){
        super();
    }

    public function equals( v:DtoModel ):Boolean{
        return v == null ? false :
                ( v.structureId == structureId && v.dividedBy == dividedBy && v.processId == processId );
    }

}
}