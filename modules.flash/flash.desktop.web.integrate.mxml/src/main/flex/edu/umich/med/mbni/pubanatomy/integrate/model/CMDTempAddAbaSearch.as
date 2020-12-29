/**
 * Created with IntelliJ IDEA.
 * User: garyyang
 * Date: 5/21/13
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */
package edu.umich.med.mbni.pubanatomy.integrate.model {
import edu.umich.med.mbni.utils.ModelOperationMenuItem;

public class CMDTempAddAbaSearch extends ModelOperationMenuItem{
    public function CMDTempAddAbaSearch( abaSearchMdl:MDLTempAbaDatasetSearchRpc, label:String ){
        super( label );
        _abaSearchMdl = abaSearchMdl;
    }

    private var _abaSearchMdl:MDLTempAbaDatasetSearchRpc;

    override protected function onExecute():void{
        _abaSearchMdl.model = rootModel;
        ( rootModel as MDLIntegrationRootModel ).webWinModels.addItem( _abaSearchMdl );
    }
}
}
