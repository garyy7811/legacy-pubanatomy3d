package edu.umich.med.mbni.pubanatomy.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.flex.http.AmfHttpMessageConverter;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * User: flashflexpro@gmail.com
 * Date: 10/10/13
 * Time: 12:27 PM
 */
public class AddAmfConverter implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization( Object o, String s ) throws BeansException{

        if( o instanceof ContentNegotiationManagerFactoryBean ){
            ContentNegotiationManagerFactoryBean tmp = ( ContentNegotiationManagerFactoryBean ) o;
            MediaType d = new MediaType( "text", "html" );
            tmp.addMediaType( "html", d );
            tmp.addMediaType( "amf", new MediaType( "application", "x-amf" ) );
            tmp.setDefaultContentType( d );
        }

        return o;
    }

    @Override
    public Object postProcessAfterInitialization( Object o, String s ) throws BeansException{
        if( o instanceof RequestMappingHandlerAdapter ){
            AmfHttpMessageConverter e = new CacheableAmfConverter();
            ( ( RequestMappingHandlerAdapter ) o ).getMessageConverters().add( e );
        }
        return o;
    }
}
