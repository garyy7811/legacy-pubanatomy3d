package edu.umich.med.mbni.pubanatomy.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author flashflexpro@gmail.com
 *         Date: 10/3/12
 *         Time: 11:22 AM
 */
public class AbaRestApi{

    public static String httpGet( String url ) throws IOException{
        HttpURLConnection tokenCon = ( HttpURLConnection )new URL( url ).openConnection();
        tokenCon.setDoOutput( true );
        tokenCon.setDoInput( true );
        tokenCon.setInstanceFollowRedirects( false );
        tokenCon.setRequestMethod( "GET" );

        tokenCon.setRequestProperty( "charset", "utf-8" );
        tokenCon.setUseCaches( false );

        BufferedReader in = new BufferedReader( new InputStreamReader( tokenCon.getInputStream() ) );

        StringBuffer result = new StringBuffer();
        String decodedString;
        while( ( decodedString = in.readLine() ) != null ){
            result.append( decodedString );
        }
        in.close();
        tokenCon.disconnect();

        return result.toString();
    }
}
