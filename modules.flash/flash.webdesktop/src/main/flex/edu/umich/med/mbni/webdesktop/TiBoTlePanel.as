/**
 * @author flashflexpro@gmail.com
 * Date: 3/20/13
 * Time: 2:05 PM
 */
package edu.umich.med.mbni.webdesktop {
import spark.components.Group;
import spark.components.Label;
import spark.components.SkinnableContainer;
import spark.layouts.supportClasses.LayoutBase;

[Style(name="borderWeight", type="Number", inherit="no", theme="spark")]

[Style(name="borderAlpha", type="Number", inherit="no", theme="spark")]

[Style(name="borderColor", type="uint", format="Color", inherit="no", theme="spark")]

[Style(name="borderVisible", type="Boolean", inherit="no", theme="spark")]

[Style(name="cornerRadius", type="Number", format="Length", inherit="no", theme="spark")]

[SkinState("normal")]

[SkinState("disabled")]

public class TiBoTlePanel extends SkinnableContainer {


    public function TiBoTlePanel(){
        setStyle( "backgroundColor", 0xffffff );
        setStyle( "skinClass", getSkinClass() );
    }
    protected function getSkinClass():Class{
        return TiBoTlePanelSkin;
    }


    [Bindable]
    public var botleTooltip:String;

    [Bindable]
    public var titleTooltip:String;

    [SkinPart]
    public var titleLabel:Label;

    [Bindable]
    public var title:String;

    [SkinPart(required="false")]
    public var titleGroup:Group;

    [SkinPart(required="false")]
    public var botleGroup:Group;


    private var _titleGroupContent:Array;

    private var _botleGroupContent:Array;

    public function get titleGroupContent():Array{
        return _titleGroupContent;
    }

    public function set titleGroupContent( value:Array ):void{
        _titleGroupContent = value;
        if( titleGroup != null ){
            titleGroup.mxmlContent = value;
        }
        invalidateSkinState();
    }

    public function get botleGroupContent():Array{
        return _botleGroupContent;
    }

    public function set botleGroupContent( value:Array ):void{
        _botleGroupContent = value;
        if( botleGroup != null ){
            botleGroup.mxmlContent = value;
        }
        invalidateSkinState();
    }

    private var _titleGroupLayout:LayoutBase;
    private var _botleGroupLayout:LayoutBase;

    public function get titleGroupLayout():LayoutBase{
        return _titleGroupLayout;
    }

    public function set titleGroupLayout( value:LayoutBase ):void{
        _titleGroupLayout = value;
        if( titleGroup != null ){
            titleGroup.layout = value;
        }
    }

    public function get botleGroupLayout():LayoutBase{
        return _botleGroupLayout;
    }

    public function set botleGroupLayout( value:LayoutBase ):void{
        _botleGroupLayout = value;
        if( botleGroup != null ){
            botleGroup.layout = value;
        }
    }


    override protected function partAdded( partName:String, instance:Object ):void{
        super.partAdded( partName, instance );
        if( instance == titleGroup ){
            if( _titleGroupContent != null ){
                titleGroup.mxmlContent = _titleGroupContent;
            }
            if( _titleGroupLayout != null ){
                titleGroup.layout = _titleGroupLayout;
            }
        }
        else if( instance == botleGroup ){
            if( _botleGroupContent != null ){
                botleGroup.mxmlContent = _botleGroupContent;
            }
            if( _botleGroupLayout != null ){
                botleGroup.layout = _botleGroupLayout;
            }
        }
    }

    override protected function partRemoved( partName:String, instance:Object ):void{
        super.partRemoved( partName, instance );
        if( instance == titleGroup ){
            _titleGroupContent = null;
            _titleGroupLayout = null;
            if( titleGroup != null ){
                titleGroup.mxmlContent = null;
                titleGroup.layout = null;
            }
        }
        else if( instance == botleGroup ){
            _botleGroupContent = null;
            _botleGroupLayout = null;
            if( botleGroup != null ){
                botleGroup.mxmlContent = null;
                botleGroup.layout = null;
            }
        }
    }


    override protected function getCurrentSkinState():String{
        var rt:String = enabled ? "normal" : "disabled";
        if( this is WebWindowDrag ){
            rt += "Win";
        }
        return rt;
    }
}
}
