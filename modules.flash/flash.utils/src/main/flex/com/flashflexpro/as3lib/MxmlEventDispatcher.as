/*
 * Copyright (C) 2011 flashflexpro@gmail.com  All Rights Reserved.  No
 *   use, copying or distribution of this work may be made except in
 *   accordance with a valid license agreement from flashflexpro@gmail.com
 *   This notice must be included on all copies, modifications and
 *   derivatives of this work.
 *
 *   flashflexpro@gmail.com MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT
 *   THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 *   INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 *   MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 *   NON-INFRINGEMENT. flashflexpro@gmail.com SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED
 *   BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS
 *   SOFTWARE OR ITS DERIVATIVES.
 */

/**
 * @author flashflexpro@gmail.com
 * Date: 11-2-24
 * Time: ����6:01
 */
package com.flashflexpro.as3lib {
import mx.core.MXMLObjectAdapter;
[Bindable]
public class MxmlEventDispatcher extends MXMLObjectAdapter{

    private var _id:String;
    public function get id():String{
        return _id;
    }

    override public function initialized( document:Object, id:String ):void{
        super.initialized( document, id );
        _id = id;
    }
}
}
