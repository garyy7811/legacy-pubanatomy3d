/**
 * @author flashflexpro@gmail.com
 * Date: 2/5/13
 * Time: 10:09 AM
 */
package edu.umich.med.mbni.pubanatomy.flash.dto {
import mx.collections.IList;

public interface IModelSectionEdge {

    function getModel():DtoModel;

    function getEdge():IList;

    function equals( another:IModelSectionEdge ):Boolean;
}
}
