package edu.umich.med.mbni.pubanatomy.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * User: flashflexpro@gmail.com
 * Date: 8/5/13
 * Time: 3:17 PM
 */
@Controller
@RequestMapping( "/crossdomain.xml" )
public class CrossDomainXML{

    @RequestMapping( value = "", method = RequestMethod.GET, produces = "application/xml" )
    public
    @ResponseBody
    String getAppInitArgs() throws IOException, ClassNotFoundException{
        return "<?xml version=\"1.0\"?>\n" +
               "<cross-domain-policy>\n" +
               "    <allow-access-from domain=\"*\"/>\n" +
               "</cross-domain-policy>";
    }
}