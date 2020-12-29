/**
 * Generated by Gas3 v2.1.0 (Granite Data Services).
 *
 * NOTE: this file is only generated if it does not exist. You may safely put
 * your custom code here.
 */

package edu.umich.med.mbni.pubanatomy.flash.dto {
import flash.geom.Vector3D;

[Bindable]
[RemoteClass(alias="edu.umich.med.mbni.pubanatomy.flash.dto.DtoModelMesh")]
public class DtoModelMesh extends DtoModelMeshBase {
    public function DtoModelMesh(){
        super();
    }


    private var _vertices:Vector.<Number>;

    public function getVertices():Vector.<Number>{
        if( vertices == null || vertices.length < 3 ){
            throw new Error();
        }

        if( vertices != null && _vertices == null ){
            _vertices = new Vector.<Number>( vertices.length );
            for( var i:int = 0; i < vertices.length; i ++ ){
                _vertices[ i ] = vertices[i];
            }
        }
        return _vertices;
    }

    private var _vertexVector3ds:Vector.<Vector3D>;

    public function getVertexVector3ds():Vector.<Vector3D>{
        if( _vertexVector3ds == null ){
            _vertexVector3ds = new <Vector3D>[];
            for( var i:int = 0; i < vertices.length; i ++ ){
                if( i % 3 == 0 ){
                    _vertexVector3ds.push( new Vector3D( vertices[ i ], vertices[ i + 1 ], vertices[ i + 2 ] ) );
                }
            }
        }
        return _vertexVector3ds;

    }

    private var _normalVector3ds:Vector.<Vector3D>;

    public function getNormalVector3ds():Vector.<Vector3D>{
        if( _normalVector3ds == null ){
            _normalVector3ds = new <Vector3D>[];
            for( var i:uint = 0; i < normals.length; i ++ ){
                if( i % 3 == 0 ){
                    _normalVector3ds.push( new Vector3D( normals[ i ], vertices[ i + 1 ], vertices[ i + 2 ] ) );
                }
            }
        }
        return _normalVector3ds;
    }

    private var _normals:Vector.<Number>;

    public function getNormals():Vector.<Number>{
        if( normals != null && _normals == null ){
            _normals = new Vector.<Number>( normals.length );
            for( var i:int = 0; i < normals.length; i ++ ){
                _normals[ i ] = normals[i];
            }
        }
        return _normals;
    }

    private var _indexes:Vector.<uint>;

    public function getIndexes():Vector.<uint>{
        if( indexes != null && _indexes == null ){
            _indexes = new Vector.<uint>( indexes.length );
            for( var i:uint = 0; i < indexes.length; i ++ ){
                _indexes[ i ] = indexes[ i ];
            }
        }
        return _indexes;
    }

    private var _uvs:Vector.<Number>;

    public function getUVs():Vector.<Number>{
        if( _uvs == null ){
            _uvs = new Vector.<Number>( getVertices().length * 2, true );
        }
        return _uvs;
    }

    public function getVerticesAndNormals():Vector.<Number>{
        var surf:Vector.<Number> = new <Number>[];

        var len:uint = getVertices().length;
        for( var i:int = 0; i * 3 < len; i ++ ){
            surf.push( getVertices()[ i * 3  ] );
            surf.push( getVertices()[ i * 3 + 1  ] );
            surf.push( getVertices()[ i * 3 + 2  ] );
            surf.push( getNormals()[ i * 3 ] );
            surf.push( getNormals()[ i * 3 + 1] );
            surf.push( getNormals()[ i * 3 + 2 ] );
        }
        return surf;
    }

}
}