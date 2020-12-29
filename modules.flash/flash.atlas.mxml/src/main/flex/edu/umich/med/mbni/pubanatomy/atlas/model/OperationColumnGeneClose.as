/**
 * @author flashflexpro@gmail.com
 * Date: 1/29/13
 * Time: 12:00 PM
 */
package edu.umich.med.mbni.pubanatomy.atlas.model {
import edu.umich.med.mbni.utils.ModelOperationMenuItem;

import mx.resources.ResourceManager;

[Bindable]
[ResourceBundle("TextAtlas")]
public class OperationColumnGeneClose extends ModelOperationMenuItem {

    public function OperationColumnGeneClose( column:StructureTreeGridDataColumnGene ){
        super( ResourceManager.getInstance().getString( 'TextAtlas', 'menuitem.close.col' ) );
        _column = column;
        toolTip = '#t(menuitem.close.gene.col)p#';
    }

    private var _column:StructureTreeGridDataColumnGene;

    public function get column():StructureTreeGridDataColumnGene{
        return _column;
    }

    override protected function onExecute():void{
        var structureTreeGridData:StructureTreeGridData = ( column.treeGridData as StructureTreeGridData );

        if( structureTreeGridData.selectedGeneColumn == _column ){
            structureTreeGridData.selectedGeneColumn = null;
        }
        structureTreeGridData.geneList.removeItem( _column.gene );
    }
}
}
