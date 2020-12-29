/**
 * @author flashflexpro@gmail.com
 * Date: 1/29/13
 * Time: 12:00 PM
 */
package edu.umich.med.mbni.treedatagrid {
import edu.umich.med.mbni.utils.ModelOperation;

[Bindable]
public class OperationOpenMatching extends ModelOperation {


    public function OperationOpenMatching( rowData:TreeGridDataRow ){
        _rowData = rowData;
    }

    private var _rowData:TreeGridDataRow;
    override protected function onExecute():void{
        for( var j:int = _rowData.matchingChildren.length - 1; j >= 0; j-- ){
            var row:TreeGridDataRow = _rowData.matchingChildren[ j ] as TreeGridDataRow;
            row.parentRow.setOpenOrClose( true );
        }
        _rowData = null;
    }
}
}
