package edu.umich.med.mbni.pubanatomy.ncbipubmedsearchproxy;

import flex.messaging.util.ExceptionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.annotation.Resource;
import java.rmi.RemoteException;
import java.util.concurrent.Callable;

/**
 * User: flashflexpro@gmail.com
 * Date: 6/28/13
 * Time: 10:13 AM
 */

@Controller
@RequestMapping( "/pubmedproxy" )
public class PubMedSearchProxy{

    @Resource
    private PubMedSearch pubMedSearch;

    @RequestMapping( value = "count", method = RequestMethod.POST )
    public
    @ResponseBody
    String[] count( @RequestBody String q ) throws RemoteException{
        return pubMedSearch.count( q );
    }

    @RequestMapping( value = "list", method = RequestMethod.POST )
    public
    @ResponseBody
    WebAsyncTask<String> listPmidStr( @RequestBody final Object[] webevnKeyCount ) throws RemoteException{
        final Integer count = ( Integer ) webevnKeyCount[ 2 ];
        if( count > 3000 ){
            return null;
        }

        Callable<String> callable = new Callable<String>(){
            @Override
            public String call() throws Exception{
                String s;
                try{
                    s = pubMedSearch.listPmidStr( ( String ) webevnKeyCount[ 0 ], ( String ) webevnKeyCount[ 1 ],count );
                }
                catch( Throwable t ){
                    return ExceptionUtil.exceptionToString( t );
                }
                return s;
            }
        };

        return new WebAsyncTask<>( 30*1000, callable );
    }

    @ExceptionHandler
    @ResponseBody
    public String handleException( Exception ex ){
        ex.printStackTrace();
        return ex.getMessage();
    }
}