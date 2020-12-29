/**
 * @author flashflexpro@gmail.com
 * Date: 1/29/13
 * Time: 12:00 PM
 */
package edu.umich.med.mbni.pubanatomy.atlas.model {
import edu.umich.med.mbni.utils.ModelOperationMenuItem;

[Bindable]
public class OperationColumnBrainStrClose extends ModelOperationMenuItem {

    public function OperationColumnBrainStrClose( column:StructureTreeGridDataColumnStrCount ){
        super( "close this column" );
        _column = column;
    }

    private var _column:StructureTreeGridDataColumnStrCount;

    public function get column():StructureTreeGridDataColumnStrCount{
        return _column;
    }

    override protected function onExecute():void{
        var structureTreeGridData:StructureTreeGridData = ( column.treeGridData as StructureTreeGridData );
        structureTreeGridData.columns.removeItem( structureTreeGridData.str2countCol );
    }
}
}
