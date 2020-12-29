/**
 * @author flashflexpro@gmail.com
 * Date: 2/15/13
 * Time: 3:41 PM
 */
package edu.umich.med.mbni.pubanatomy {
import edu.umich.med.mbni.pubanatomy.atlas.model.StructureItem;
import edu.umich.med.mbni.pubanatomy.integrate.model.MDLIntegrationRootModel;
import edu.umich.med.mbni.utils.ModelOperationMenuItem;

import flash.utils.getTimer;

import mx.collections.ArrayList;

public class TstArrayListVSlst2arrayPerform extends ModelOperationMenuItem {

    public function TstArrayListVSlst2arrayPerform( rootModel:MDLIntegrationRootModel ){
        this.label = "TstArrayListVSlst2arrayPerform";
        _rootModel = rootModel;
    }

    private var _rootModel:MDLIntegrationRootModel;

    override protected function onExecute():void{

        var ts:int = getTimer();

        //
        var target:ArrayList = new ArrayList();

        for( var k:uint = 0; k < 99; k ++ ){
            target.addAll(_rootModel.rootModelAtlas.rootStructure.allChildren );
        }
        //
        trace( "111---->" + ( getTimer() - ts ) );
        ts = getTimer();

        var ta = [];
        var tarraall:Array = _rootModel.rootModelAtlas.rootStructure.allChildren.toArray();
        for( var p = 0; p < 99; p ++ ){
            ta = ta.concat( tarraall )
        }

        target = new ArrayList( ta );
        trace( "222---->" + ( getTimer() - ts ) );



        var tmpSum:uint = 0;

        ts = getTimer();
        const len:int = target.length;
        for( var i:int = 0; i < len; i++ ){
            var o:StructureItem = target.getItemAt( i ) as StructureItem;
            tmpSum += o.dtoStructure.structureId;
        }

        trace( "---->" + ( getTimer() - ts ) + "|tmpSum=" + tmpSum );

        var ta:Array = target.toArray();
        tmpSum = 0;
        ts = getTimer();
        ta.forEach( function ( s:StructureItem, index:int, arr:Array ):void{
            tmpSum += s.dtoStructure.structureId;
        } );
        trace( "---->" + ( getTimer() - ts ) + "|tmpSum=" + tmpSum );
    }
}
}
