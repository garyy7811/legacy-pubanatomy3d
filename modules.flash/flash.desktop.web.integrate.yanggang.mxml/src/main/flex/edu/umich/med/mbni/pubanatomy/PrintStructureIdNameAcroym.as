/**
 * @author flashflexpro@gmail.com
 * Date: 2/15/13
 * Time: 3:41 PM
 */
package edu.umich.med.mbni.pubanatomy {
import edu.umich.med.mbni.pubanatomy.atlas.model.RootModelAtlas;
import edu.umich.med.mbni.pubanatomy.atlas.model.StructureItem;
import edu.umich.med.mbni.utils.ModelOperationMenuItem;


public class PrintStructureIdNameAcroym extends ModelOperationMenuItem {

    public function PrintStructureIdNameAcroym( rootModel:RootModelAtlas ){
        _rootModel = rootModel;
    }

    private var _rootModel:RootModelAtlas;

    override protected function onExecute():void{
        var allChildren:Array = _rootModel.rootStructure.allChildren.toArray();
        allChildren.concat( [ _rootModel.rootStructure ] );
        for( var i:int = allChildren.length - 1; i >= 0; i-- ){
            var structureItem:StructureItem = allChildren[ i ] as StructureItem;

            trace( structureItem.dtoStructure.name + "\t" + structureItem.dtoStructure.structureId );
            trace( structureItem.dtoStructure.acronym + "\t" + structureItem.dtoStructure.structureId );
        }

    }
}
}
