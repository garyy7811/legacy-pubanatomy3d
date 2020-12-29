package edu.umich.med.mbni.pubanatomy.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author flashflexpro@gmail.com
 *         Date: 1/17/12
 *         Time: 12:29 PM
 */
@Component
public class Persist<T>{

    public byte[] compressJavaObj( T obj ){
        GZIPOutputStream zos = null;
        ObjectOutputStream out = null;
        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            zos = new GZIPOutputStream( bos );
            out = new ObjectOutputStream( zos );
            out.writeObject( obj );
            out.flush();
            out.close();
            bos.close();
            zos.close();
            return bos.toByteArray();
        }
        catch( Exception ex ){
            ex.printStackTrace();
            throw new RuntimeException( "can't convert obj to ba" + obj );
        }
    }

    public T uncompressJavaObj( byte[] bytes ){
        if( bytes == null ){
            throw new RuntimeException();
        }
        ByteArrayInputStream bais = null;
        GZIPInputStream zis = null;
        ObjectInputStream ois = null;

        try{
            bais = new ByteArrayInputStream( bytes );
            zis = new GZIPInputStream( bais );
            ois = new ObjectInputStream( zis );
            return ( T )ois.readObject();
        }
        catch( Exception e ){
            e.printStackTrace();
            throw new RuntimeException( "can't uncompress!!" );
        }
    }

    private static final HashMap<String, Object> path2JavaObj = new HashMap<String, Object>();
    /*

    public void saveJavaObj( T obj, String path ){
        try{
            ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream( path ) );
            out.writeObject( obj );
            out.close();
            path2JavaObj.put( path, obj );
        }
        catch( Exception ex ){
            ex.printStackTrace();
            throw new RuntimeException( "can't save obj to" + path );
        }
    }*/

    public static ByteArrayInputStream getByteArrayInputStreamFromObject( Object object ) throws IOException{

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream op = new ObjectOutputStream( bo );
        op.writeObject( object );
        op.flush();
        op.close();
        return new ByteArrayInputStream( bo.toByteArray() );
    }


    public T loadJavaObj( String path ){
        FileInputStream fis = null;
        ObjectInputStream in = null;
        T loaded = ( T )path2JavaObj.get( path );
        if( loaded != null ){
            return loaded;
        }
        try{
            fis = new FileInputStream( path );
            in = new ObjectInputStream( fis );
            loaded = ( T )in.readObject();
            in.close();
        }
        catch( Exception ex ){
            ex.printStackTrace();
        }
        return loaded;
    }

}
