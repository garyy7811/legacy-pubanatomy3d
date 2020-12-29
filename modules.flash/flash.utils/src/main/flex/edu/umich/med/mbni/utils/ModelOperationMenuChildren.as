/**
 * Created with IntelliJ IDEA.
 * User: garyyang
 * Date: 10/18/13
 * Time: 10:04 AM
 * To change this template use File | Settings | File Templates.
 */
package edu.umich.med.mbni.utils {
import com.flashflexpro.as3lib.Sync2Lists;

import mx.collections.ArrayList;

public class ModelOperationMenuChildren extends ModelOperationMenuItem {
    public function ModelOperationMenuChildren( children:Array, target:Object = null, label:String = "More" ){
        super( label );
        this.target = target;
        _children = new ArrayCollection( children );

        _syncChildWithParent = new Sync2Lists();
        _syncChildWithParent.getDestInst = addParentAndRoot;
        _syncChildWithParent.source = _children;
        _syncChildWithParent.destination = new ArrayList();
    }

    private var _syncChildWithParent:Sync2Lists;

    private function addParentAndRoot( i:ModelOperationMenuType ):ModelOperationMenuType{
        i._parent = this;
        if( i.rootModel == null ){
            i.rootModel = rootModel;
        }
        return i;
    }

    import mx.collections.ArrayCollection;

    public function getRoot():ModelOperationMenuItem{
        var rt:ModelOperationMenuChildren = this as ModelOperationMenuChildren;
        while( rt.parent != null ){
            rt = rt.parent;
        }
        return rt;
    }

    private var _children:ArrayCollection;

    public function get children():ArrayCollection{
        return _children;
    }

    override public function destroy():void{
        _syncChildWithParent.destroy();
        if( children != null ){
            for( var i:int = children.length - 1; i >= 0; i-- ){
                ( children.getItemAt( i ) as ModelOperationMenuType ).destroy();
            }
            children.removeAll();
            _children = null;
        }
        super.destroy();
        _parent = null;
    }
}
}
