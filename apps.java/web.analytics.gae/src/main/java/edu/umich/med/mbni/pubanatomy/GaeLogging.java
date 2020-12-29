package edu.umich.med.mbni.pubanatomy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

/**
 * User: flashflexpro@gmail.com
 * Date: 10/25/13
 * Time: 11:42 AM
 */
public class GaeLogging extends HttpServlet{

    private static final Logger log = Logger.getLogger( GaeLogging.class.getName() );

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException{
        logInfo( request, "|POST|" );
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException{
        logInfo( request, "|GET|" );
    }

    private void logInfo( HttpServletRequest request, String hm ){
        StringBuilder sb = new StringBuilder( hm );
        sb.append( request.getRemoteAddr() );
        Enumeration nms = request.getHeaderNames();
        while( nms.hasMoreElements() ){
            String name = ( String ) nms.nextElement();
            sb.append( "header[" + name + "->" + request.getHeader( name ) + "] " );
        }
        log.info( sb.toString() );
    }

}
