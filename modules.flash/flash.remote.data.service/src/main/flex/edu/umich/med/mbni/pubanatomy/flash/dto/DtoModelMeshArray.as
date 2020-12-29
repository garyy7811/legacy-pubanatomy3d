/**
 * Generated by Gas3 v2.1.0 (Granite Data Services).
 *
 * NOTE: this file is only generated if it does not exist. You may safely put
 * your custom code here.
 */

package edu.umich.med.mbni.pubanatomy.flash.dto {

    [Bindable]
    [RemoteClass(alias="edu.umich.med.mbni.pubanatomy.flash.dto.DtoModelMeshArray")]
    public class DtoModelMeshArray extends DtoModelMeshArrayBase {
        public function DtoModelMeshArray(){
            super();
        }

        public function getComplexity():Array{
            const len:int = flashMeshes.length;
            var c:int = 0;
            for( var i:int = 0; i < len; i ++ ){
                var geo:DtoModelMesh = flashMeshes[ i ] as DtoModelMesh;
                c += geo.getVertices().length;
            }
            return [ len,  c ];
        }

    }
}