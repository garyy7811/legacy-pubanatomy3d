package edu.umich.med.mbni.pubanatomy.mongo;

import edu.umich.med.mbni.pubanatomy.flash.dto.*;
import flex.messaging.util.ExceptionUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * User: flashflexpro@gmail.com
 * Date: 8/5/13
 * Time: 3:17 PM
 */
@Controller
@RequestMapping("/help")
public class MongoServiceProxyHelp{

    @Resource
    MongoService mongoService;

    @RequestMapping(value = "getTooltip", method = RequestMethod.GET)
    @Cacheable("getTooltip")
    public
    @ResponseBody
    String[] getTooltip( @RequestParam( "param0" ) String id ){
        return new String[]{ mongoService.getTooltip( id ) };
    }

    @RequestMapping(value = "getTooltipHelp", method = RequestMethod.GET)
    @Cacheable("getTooltipHelp")
    public
    @ResponseBody
    String[] getTooltipHelp( @RequestParam("param0") String id ){
        return new String[]{ mongoService.getTooltipHelp( id ) };
    }

    @RequestMapping(value = "saveTooltipHelp", method = RequestMethod.POST)
    @CacheEvict({ "getTooltip", "getTooltipHelp" })
    public
    @ResponseBody
    Boolean saveTooltipHelp( @RequestBody Object[] args ){
        return mongoService.saveTooltipHelp( ( String ) args[ 0 ], ( String ) args[ 1 ], ( String ) args[ 2 ] );
    }

    @ExceptionHandler
    @ResponseBody
    public DtoException handleException( Exception ex ){
        return new DtoException( 0, ExceptionUtil.exceptionToString( ex ) );
    }
}