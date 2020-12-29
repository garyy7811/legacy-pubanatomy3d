/**
 * @author flashflexpro@gmail.com
 * Date: 1/29/13
 * Time: 12:00 PM
 */
package edu.umich.med.mbni.pubanatomy.atlas.model {
import edu.umich.med.mbni.utils.ModelOperationMenuItem;
import edu.umich.med.mbni.utils.ModelOperationMenuType;

import mx.resources.ResourceManager;

[Bindable]
[ResourceBundle("TextAtlas")]
public class OperationColumnGeneSortType extends ModelOperationMenuItem {

    public function OperationColumnGeneSortType( column:StructureTreeGridDataColumnGene, targetVal:Boolean ){
        super( targetVal ?
                       ResourceManager.getInstance().getString( 'TextAtlas', 'menuitem.gene.col.selectGeneExprCalIncChildren' )
                       : ResourceManager.getInstance().getString( 'TextAtlas', 'menuitem.gene.col.selectGeneExprCalExcChildren' )
                , column, ModelOperationMenuType.TYPE_RADIO );
        _column = column;
        _targetVal = targetVal;
        toggled = ( column.childrenIncludeOrEx == targetVal );
        toolTip = "#t(menuitem.gene.col.selectGeneExprCalIncExcTooltip)p#";
    }

    private var _column:StructureTreeGridDataColumnGene;

    private var _targetVal:Boolean;

    public function get column():StructureTreeGridDataColumnGene{
        return _column;
    }

    public function get targetVal():Boolean{
        return _targetVal;
    }

    override protected function onExecute():void{
        column.childrenIncludeOrEx = targetVal;
    }
}
}
