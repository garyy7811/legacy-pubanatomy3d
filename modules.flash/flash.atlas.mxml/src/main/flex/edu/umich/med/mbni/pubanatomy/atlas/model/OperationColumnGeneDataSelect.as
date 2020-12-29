/**
 * @author flashflexpro@gmail.com
 * Date: 1/29/13
 * Time: 12:00 PM
 */
package edu.umich.med.mbni.pubanatomy.atlas.model {
import edu.umich.med.mbni.utils.ModelOperationMenuItem;
import edu.umich.med.mbni.utils.ModelOperationMenuType;

[Bindable]
[ResourceBundle("TextAtlas")]
public class OperationColumnGeneDataSelect extends ModelOperationMenuItem {

    public function OperationColumnGeneDataSelect( column:StructureTreeGridDataColumnGene, targetVal:MDLDataSet, exprType:String ){
        super( "Select this experiment", target, ModelOperationMenuType.TYPE_RADIO );
        var exprAvail:Boolean = targetVal.exprLevelAvail[ exprType ];

        labelColor = exprAvail ? 0x000000 : 0xaa0000;

        _column = column;
        _targetVal = targetVal;
        toggled = (column.selectedDataSet == targetVal);

        toolTip = "data set's section thickness in um |^|" + targetVal.dataSetXml["section-thickness"]
                + "\r\ndata set's label( made up name ) |^|" + targetVal.label
                + "\r\n#t(menuitem.gene.col.selectdataset)p#";
    }

    private var _column:StructureTreeGridDataColumnGene;

    private var _targetVal:MDLDataSet;

    public function get column():StructureTreeGridDataColumnGene{
        return _column;
    }

    public function get targetVal():Boolean{
        return _targetVal;
    }

    override protected function onExecute():void{
        column.selectedDataSet = _targetVal;
    }
}
}
