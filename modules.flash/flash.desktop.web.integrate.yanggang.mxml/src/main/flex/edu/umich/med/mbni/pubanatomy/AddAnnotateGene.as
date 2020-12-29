/**
 * @author flashflexpro@gmail.com
 * Date: 2/15/13
 * Time: 3:41 PM
 */
package edu.umich.med.mbni.pubanatomy {
import edu.umich.med.mbni.pubanatomy.flash.dto.DtoGene;
import edu.umich.med.mbni.pubanatomy.voxel3d.model.RootModelVoxel3d;
import edu.umich.med.mbni.utils.ModelOperationMenuItem;

public class AddAnnotateGene extends ModelOperationMenuItem {

    public function AddAnnotateGene( rootModel:RootModelVoxel3d, geneId:uint, label:String = "" ){
        _rootModel = rootModel;
        _geneId = geneId;
        this.label = label + "( " + geneId + " )";
    }

    private var _rootModel:RootModelVoxel3d;

    private var _geneId:uint;

    override protected function onExecute():void{
        var dtoGene:DtoGene = new DtoGene();
        dtoGene.geneId = _geneId;
        dtoGene.acronym = label;
        _rootModel.on3dStageStructureTreeGridData.addGene( dtoGene );
    }
}
}
