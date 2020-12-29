/**
 * @author flashflexpro@gmail.com
 * Date: 5/6/12
 * Time: 8:11 PM
 */
package edu.umich.med.mbni.pubanatomy.search.view {
[Bindable]
public class ViewTermSuggestCountVO {

    public function ViewTermSuggestCountVO( term:String, count:int ){
        this.term = term;
        this.count = count;
    }

    public var term:String;

    public var count:int = - 1;
}
}
