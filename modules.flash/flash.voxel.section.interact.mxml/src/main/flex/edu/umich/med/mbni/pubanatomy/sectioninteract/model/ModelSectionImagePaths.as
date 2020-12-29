/**
 * @author flashflexpro@gmail.com
 * Date: 2/3/13
 * Time: 12:47 PM
 */
package edu.umich.med.mbni.pubanatomy.sectioninteract.model {
import flash.display.GraphicsPath;

public class ModelSectionImagePaths {
    public function ModelSectionImagePaths( value:String ){
        if( !value ){
            _segments = new Vector.<PathSegment>();
            return;
        }

        var newSegments:Vector.<PathSegment> = new Vector.<PathSegment>();
        var charCount:int = value.length;
        var c:Number; // current char code, String.charCodeAt() returns Number.
        var useRelative:Boolean;
        var prevIdentifier:Number = 0;
        var prevX:Number = 0;
        var prevY:Number = 0;
        var lastMoveX:Number = 0;
        var lastMoveY:Number = 0;
        var x:Number;
        var y:Number;
        var controlX:Number;
        var controlY:Number;
        var control2X:Number;
        var control2Y:Number;
        var lastMoveSegmentIndex:int = -1;

        _dataLength = charCount;
        _charPos = 0;
        while( true ){
            // Skip any whitespace or commas first
            skipWhiteSpace( value );

            // Are we done parsing?
            if( _charPos >= charCount )
                break;

            // Get the next character
            c = value.charCodeAt( _charPos++ );

            // Is this a start of a number?
            // The RegExp for a float is /[+-]?\d*\.?\d+([Ee][+-]?\d+)?/
            if( (c >= 0x30 && c < 0x3A) ||   // A digit
                    (c == 0x2B || c == 0x2D) ||  // '+' & '-'
                    (c == 0x2E) )                 // '.'
            {
                c = prevIdentifier;
                _charPos--;
            }
            else if( c >= 0x41 && c <= 0x56 ) // Between 'C' and 'V'
                useRelative = false;
            else if( c >= 0x61 && c <= 0x7A ) // Between 'c' and 'v'
                useRelative = true;

            switch( c ){
                case 0x63:  // c
                case 0x43:  // C
                    controlX = getNumber( useRelative, prevX, value );
                    controlY = getNumber( useRelative, prevY, value );
                    control2X = getNumber( useRelative, prevX, value );
                    control2Y = getNumber( useRelative, prevY, value );
                    x = getNumber( useRelative, prevX, value );
                    y = getNumber( useRelative, prevY, value );
                    newSegments.push( new CubicBezierSegment( controlX, controlY,
                                                              control2X, control2Y,
                                                              x, y ) );
                    prevX = x;
                    prevY = y;
                    prevIdentifier = 0x63;

                    break;

                case 0x6D:  // m
                case 0x4D:  // M
                    x = getNumber( useRelative, prevX, value );
                    y = getNumber( useRelative, prevY, value );
                    newSegments.push( new MoveSegment( x, y ) );
                    prevX = x;
                    prevY = y;
                    // If a moveto is followed by multiple pairs of coordinates,
                    // the subsequent pairs are treated as implicit lineto commands.
                    prevIdentifier = (c == 0x6D) ? 0x6C : 0x4C; // c == 'm' ? 'l' : 'L'

                    // Fix for bug SDK-24457:
                    // If the Quadratic segment is isolated, the Player
                    // won't draw fill correctly. We need to generate
                    // a dummy line segment.
                    var curSegmentIndex:int = newSegments.length - 1;
                    if( lastMoveSegmentIndex + 2 == curSegmentIndex &&
                            newSegments[lastMoveSegmentIndex + 1] is QuadraticBezierSegment ){
                        // Insert a dummy LineSegment
                        newSegments.splice( lastMoveSegmentIndex + 1, 0, new LineSegment( lastMoveX, lastMoveY ) );
                        curSegmentIndex++;
                    }

                    lastMoveSegmentIndex = curSegmentIndex;
                    lastMoveX = x;
                    lastMoveY = y;
                    break;

                case 0x6C:  // l
                case 0x4C:  // L
                    x = getNumber( useRelative, prevX, value );
                    y = getNumber( useRelative, prevY, value );
                    newSegments.push( new LineSegment( x, y ) );
                    prevX = x;
                    prevY = y;
                    prevIdentifier = 0x6C;
                    break;

                case 0x68:  // h
                case 0x48:  // H
                    x = getNumber( useRelative, prevX, value );
                    y = prevY;
                    newSegments.push( new LineSegment( x, y ) );
                    prevX = x;
                    prevY = y;
                    prevIdentifier = 0x68;
                    break;

                case 0x76:  // v
                case 0x56:  // V
                    x = prevX;
                    y = getNumber( useRelative, prevY, value );
                    newSegments.push( new LineSegment( x, y ) );
                    prevX = x;
                    prevY = y;
                    prevIdentifier = 0x76;
                    break;

                case 0x71:  // q
                case 0x51:  // Q
                    controlX = getNumber( useRelative, prevX, value );
                    controlY = getNumber( useRelative, prevY, value );
                    x = getNumber( useRelative, prevX, value );
                    y = getNumber( useRelative, prevY, value );
                    newSegments.push( new QuadraticBezierSegment( controlX, controlY, x, y ) );
                    prevX = x;
                    prevY = y;
                    prevIdentifier = 0x71;
                    break;

                case 0x74:  // t
                case 0x54:  // T
                    // control is a reflection of the previous control point
                    if( prevIdentifier == 0x74 || prevIdentifier == 0x71 ) // 't' or 'q'
                    {
                        controlX = prevX + (prevX - controlX);
                        controlY = prevY + (prevY - controlY);
                    }
                    else{
                        controlX = prevX;
                        controlY = prevY;
                    }

                    x = getNumber( useRelative, prevX, value );
                    y = getNumber( useRelative, prevY, value );
                    newSegments.push( new QuadraticBezierSegment( controlX, controlY, x, y ) );
                    prevX = x;
                    prevY = y;
                    prevIdentifier = 0x74;

                    break;

                case 0x73:  // s
                case 0x53:  // S
                    if( prevIdentifier == 0x73 || prevIdentifier == 0x63 ) // s or c
                    {
                        controlX = prevX + (prevX - control2X);
                        controlY = prevY + (prevY - control2Y);
                    }
                    else{
                        controlX = prevX;
                        controlY = prevY;
                    }

                    control2X = getNumber( useRelative, prevX, value );
                    control2Y = getNumber( useRelative, prevY, value );
                    x = getNumber( useRelative, prevX, value );
                    y = getNumber( useRelative, prevY, value );
                    newSegments.push( new CubicBezierSegment( controlX, controlY,
                                                              control2X, control2Y, x, y ) );
                    prevX = x;
                    prevY = y;
                    prevIdentifier = 0x73;

                    break;

                case 0x7A:  // z
                case 0x5A:  // Z
                    x = lastMoveX;
                    y = lastMoveY;
                    newSegments.push( new LineSegment( x, y ) );
                    prevX = x;
                    prevY = y;
                    prevIdentifier = 0x7A;

                    break;

                default:
                    // unknown identifier, throw error?
                    _segments = new Vector.<PathSegment>();
                    return;
            }
        }

        // Fix for bug SDK-24457:
        // If the Quadratic segment is isolated, the Player
        // won't draw fill correctly. We need to generate
        // a dummy line segment.
        curSegmentIndex = newSegments.length;
        if( lastMoveSegmentIndex + 2 == curSegmentIndex &&
                newSegments[lastMoveSegmentIndex + 1] is QuadraticBezierSegment ){
            // Insert a dummy LineSegment
            newSegments.splice( lastMoveSegmentIndex + 1, 0, new LineSegment( lastMoveX, lastMoveY ) );
            curSegmentIndex++;
        }

        _segments = newSegments;
    }

    private var _segments:Vector.<PathSegment>;

    /**
     *  A Vector of the actual path segments. May be empty, but always non-null.
     */
    public function get data():Vector.<PathSegment>{
        return _segments;
    }


    public function generateGraphicsPath( graphicsPath:GraphicsPath, tx:Number, ty:Number, sx:Number, sy:Number ):void{
        graphicsPath.commands = null;
        graphicsPath.data = null;

        // Always start by moving to drawX, drawY. Otherwise
        // the path will begin at the previous pen location
        // if it does not start with a MoveSegment.
        graphicsPath.moveTo( tx, ty );

        var curSegment:PathSegment;
        var prevSegment:PathSegment;
        var count:int = _segments.length;
        for( var i:int = 0; i < count; i++ ){
            prevSegment = curSegment;
            curSegment = _segments[i];
            curSegment.draw( graphicsPath, tx, ty, sx, sy, prevSegment );
        }
    }

    //--------------------------------------------------------------------------
    //
    //  Private methods
    //
    //--------------------------------------------------------------------------

    private var _charPos:int = 0;
    private var _dataLength:int = 0;

    private function skipWhiteSpace( data:String ):void{
        while( _charPos < _dataLength ){
            var c:Number = data.charCodeAt( _charPos );
            if( c != 0x20 && // Space
                    c != 0x2C && // Comma
                    c != 0xD && // Carriage return
                    c != 0x9 && // Tab
                    c != 0xA )    // New line
            {
                break;
            }
            _charPos++;
        }
    }

    private function getNumber( useRelative:Boolean, offset:Number, value:String ):Number{
        // Parse the string and find the first occurrance of the following RexExp
        // numberRegExp:RegExp = /[+-]?\d*\.?\d+([Ee][+-]?\d+)?/g;

        skipWhiteSpace( value ); // updates _charPos
        if( _charPos >= _dataLength )
            return NaN;

        // Remember the start of the number
        var numberStart:int = _charPos;
        var hasSignCharacter:Boolean = false;
        var hasDigits:Boolean = false;

        // The number could start with '+' or '-' (the "[+-]?" part of the RegExp)
        var c:Number = value.charCodeAt( _charPos );
        if( c == 0x2B || c == 0x2D ) // '+' or '-'
        {
            hasSignCharacter = true;
            _charPos++;
        }

        // The index of the '.' if any
        var dotIndex:int = -1;

        // First sequence of digits and optional dot in between (the "\d*\.?\d+" part of the RegExp)
        while( _charPos < _dataLength ){
            c = value.charCodeAt( _charPos );

            if( c >= 0x30 && c < 0x3A ) // A digit
            {
                hasDigits = true;
            }
            else if( c == 0x2E && dotIndex == -1 ) // '.'
            {
                dotIndex = _charPos;
            }
            else
                break;

            _charPos++;
        }

        // Now check whether we had at least one digit.
        if( !hasDigits ){
            // Go to the end of the data
            _charPos = _dataLength;
            return NaN;
        }

        // 1. Was the last character a '.'? If so, rewind one character back.
        if( c == 0x2E )
            _charPos--;

        // So far we have a valid number, remember its end character index
        var numberEnd:int = _charPos;

        // Check to see if we have scientific notation (the "([Ee][+-]?\d+)?" part of the RegExp)
        if( c == 0x45 || c == 0x65 ){
            _charPos++;

            // Check for '+' or '-'
            if( _charPos < _dataLength ){
                c = value.charCodeAt( _charPos );
                if( c == 0x2B || c == 0x2D )
                    _charPos++;
            }

            // Find all the digits
            var digitStart:int = _charPos;
            while( _charPos < _dataLength ){
                c = value.charCodeAt( _charPos );

                // Not a digit?
                if( !(c >= 0x30 && c < 0x3A) ){
                    break;
                }

                _charPos++;
            }

            // Do we have at least one digit?
            if( digitStart < _charPos )
                numberEnd = _charPos; // Scientific notation, update the end index of the number.
            else
                _charPos = numberEnd; // No scientific notation, rewind back to the end index of the number.
        }

        // Use parseFloat to get the actual number.
        // TODO (egeorgie): we could build the number while matching the RegExp which will save the substr and parseFloat
        var subString:String = value.substr( numberStart, numberEnd - numberStart );
        var result:Number = parseFloat( subString );
        if( isNaN( result ) ){
            // Go to the end of the data
            _charPos = _dataLength;
            return NaN;
        }
        _charPos = numberEnd;
        return useRelative ? result + offset : result;
    }
}
}

//--------------------------------------------------------------------------
//
//  Internal Helper Class - PathSegment
//
//--------------------------------------------------------------------------
import flash.display.GraphicsPath;
import flash.geom.Point;

class PathSegment extends Object {


    public function PathSegment( _x:Number = 0, _y:Number = 0 ){
        super();
        x = _x;
        y = _y;
    }


    public var x:Number = 0;


    public var y:Number = 0;

    public function draw( graphicsPath:GraphicsPath, dx:Number, dy:Number, sx:Number, sy:Number, prev:PathSegment ):void{
        // Override to draw your segment
    }

}


class LineSegment extends PathSegment {

    public function LineSegment( x:Number = 0, y:Number = 0 ){
        super( x, y );
    }

    override public function draw( graphicsPath:GraphicsPath, dx:Number, dy:Number, sx:Number, sy:Number, prev:PathSegment ):void{
        graphicsPath.lineTo( dx + x * sx, dy + y * sy );
    }
}

class MoveSegment extends PathSegment {
    public function MoveSegment( x:Number = 0, y:Number = 0 ){
        super( x, y );
    }

    override public function draw( graphicsPath:GraphicsPath, dx:Number, dy:Number, sx:Number, sy:Number, prev:PathSegment ):void{
        graphicsPath.moveTo( dx + x * sx, dy + y * sy );
    }
}

class CubicBezierSegment extends PathSegment {
    public function CubicBezierSegment( _control1X:Number = 0, _control1Y:Number = 0, _control2X:Number = 0, _control2Y:Number = 0, x:Number = 0, y:Number = 0 ){
        super( x, y );

        control1X = _control1X;
        control1Y = _control1Y;
        control2X = _control2X;
        control2Y = _control2Y;
    }

    private var _qPts:QuadraticPoints;

    public var control1X:Number = 0;

    public var control1Y:Number = 0;

    public var control2X:Number = 0;

    public var control2Y:Number = 0;

    override public function draw( graphicsPath:GraphicsPath, dx:Number, dy:Number, sx:Number, sy:Number, prev:PathSegment ):void{
        var qPts:QuadraticPoints = getQuadraticPoints( prev );

        graphicsPath.curveTo( dx + qPts.control1.x * sx, dy + qPts.control1.y * sy, dx + qPts.anchor1.x * sx, dy + qPts.anchor1.y * sy );
        graphicsPath.curveTo( dx + qPts.control2.x * sx, dy + qPts.control2.y * sy, dx + qPts.anchor2.x * sx, dy + qPts.anchor2.y * sy );
        graphicsPath.curveTo( dx + qPts.control3.x * sx, dy + qPts.control3.y * sy, dx + qPts.anchor3.x * sx, dy + qPts.anchor3.y * sy );
        graphicsPath.curveTo( dx + qPts.control4.x * sx, dy + qPts.control4.y * sy, dx + qPts.anchor4.x * sx, dy + qPts.anchor4.y * sy );
    }

    private function getQuadraticPoints( prev:PathSegment ):QuadraticPoints{
        if( _qPts )
            return _qPts;

        var p1:Point = new Point( prev ? prev.x : 0, prev ? prev.y : 0 );
        var p2:Point = new Point( x, y );
        var c1:Point = new Point( control1X, control1Y );
        var c2:Point = new Point( control2X, control2Y );

        // calculates the useful base points
        var PA:Point = Point.interpolate( c1, p1, 3 / 4 );
        var PB:Point = Point.interpolate( c2, p2, 3 / 4 );

        // get 1/16 of the [p2, p1] segment
        var dx:Number = (p2.x - p1.x) / 16;
        var dy:Number = (p2.y - p1.y) / 16;

        _qPts = new QuadraticPoints;

        // calculates control point 1
        _qPts.control1 = Point.interpolate( c1, p1, 3 / 8 );

        // calculates control point 2
        _qPts.control2 = Point.interpolate( PB, PA, 3 / 8 );
        _qPts.control2.x -= dx;
        _qPts.control2.y -= dy;

        // calculates control point 3
        _qPts.control3 = Point.interpolate( PA, PB, 3 / 8 );
        _qPts.control3.x += dx;
        _qPts.control3.y += dy;

        // calculates control point 4
        _qPts.control4 = Point.interpolate( c2, p2, 3 / 8 );

        // calculates the 3 anchor points
        _qPts.anchor1 = Point.interpolate( _qPts.control1, _qPts.control2, 0.5 );
        _qPts.anchor2 = Point.interpolate( PA, PB, 0.5 );
        _qPts.anchor3 = Point.interpolate( _qPts.control3, _qPts.control4, 0.5 );

        // the 4th anchor point is p2
        _qPts.anchor4 = p2;

        return _qPts;
    }
}

class QuadraticPoints {
    public var control1:Point;
    public var anchor1:Point;
    public var control2:Point;
    public var anchor2:Point;
    public var control3:Point;
    public var anchor3:Point;
    public var control4:Point;
    public var anchor4:Point;

    public function QuadraticPoints(){
        super();
    }
}


class QuadraticBezierSegment extends PathSegment {

    public function QuadraticBezierSegment( _control1X:Number = 0, _control1Y:Number = 0, x:Number = 0, y:Number = 0 ){
        super( x, y );

        control1X = _control1X;
        control1Y = _control1Y;
    }

    public var control1X:Number = 0;

    public var control1Y:Number = 0;

    override public function draw( graphicsPath:GraphicsPath, dx:Number, dy:Number, sx:Number, sy:Number, prev:PathSegment ):void{
        graphicsPath.curveTo( dx + control1X * sx, dy + control1Y * sy, dx + x * sx, dy + y * sy );
    }

}


