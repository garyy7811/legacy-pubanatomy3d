/**
 * Created with IntelliJ IDEA.
 * User: garyyang
 * Date: 4/11/13
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
package edu.umich.med.mbni.pubanatomy.search.model {
import edu.umich.med.mbni.utils.ModelOperationMenuItem;
import edu.umich.med.mbni.utils.ModelOperationMenuType;

public class OperationAddRemoveFacet extends ModelOperationMenuItem {
    public function OperationAddRemoveFacet( qm:Result, tf:QueryField ){
        super( tf.label, null, ModelOperationMenuType.TYPE_CHECK );
        toggled = ( qm.facetFields.getItemIndex( tf ) >= 0 );

        _queryModel = qm;
        _target = tf;

        toolTip = "#t(searchResultFacet_" + tf.toolTip + ")p#";
    }

    private var _queryModel:Result;

    private var _target:QueryField;

    override protected function onExecute():void{
        if( _queryModel.facetFields.getItemIndex( _target ) >= 0 ){
            _queryModel.facetFields.removeItem( _target );
        }
        else{
            _queryModel.facetFields.addItem( _target );
        }
    }
}
}
