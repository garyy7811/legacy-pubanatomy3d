/**
 * Created with IntelliJ IDEA.
 * User: garyyang
 * Date: 4/11/13
 * Time: 3:59 PM
 * To change this template use File | Settings | File Templates.
 */
package edu.umich.med.mbni.pubanatomy.search.model {
import edu.umich.med.mbni.utils.ModelOperationMenuItem;
import edu.umich.med.mbni.utils.ModelOperationMenuType;

public class OperationAddRemoveColumn extends ModelOperationMenuItem {

    public function OperationAddRemoveColumn( result:Result, field:QueryField ){
        super( field.label, null, ModelOperationMenuType.TYPE_CHECK );
        toggled = ( result.columnFields.getItemIndex( field ) >= 0 );

        _queryModel = result;
        _target = field;
        toolTip = "#t(searchResultLiteField_" + field.toolTip + ")p#";
    }

    private var _queryModel:Result;
    private var _target:QueryField;

    override protected function onExecute():void{
        if( _queryModel.columnFields.getItemIndex( _target ) >= 0 ){
            _queryModel.columnFields.removeItem( _target );
        }
        else{
            _queryModel.columnFields.addItem( _target );
        }
    }
}
}
