package edu.umich.med.mbni.pubanatomy.service;

import flex.messaging.FlexContext;
import flex.messaging.io.SerializationContext;
import flex.messaging.io.SerializationException;
import flex.messaging.io.amf.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.flex.http.AmfHttpMessageConverter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * User: flashflexpro@gmail.com
 * Date: 10/23/13
 * Time: 2:13 PM
 */
public class CacheableAmfConverter extends AmfHttpMessageConverter{

    private static final Log log = LogFactory.getLog( CacheableAmfConverter.class );

    @Override
    protected void writeInternal( Object data, HttpOutputMessage outputMessage ) throws IOException,
            HttpMessageNotWritableException{

        try {
            AmfTrace trace = null;
            if (log.isDebugEnabled()) {
                trace = new AmfTrace();
            }

            if (data instanceof ActionMessage ) {
                writeActionMessage((ActionMessage) data, outputMessage, trace);
            } else {
                writeObject(data, outputMessage, trace);
            }

            if (log.isDebugEnabled()) {
                log.debug("Wrote AMF message:\n" + trace);
            }
        } finally {
            FlexContext.clearThreadLocalObjects();
            SerializationContext.clearThreadLocalObjects();
        }
    }

    private void writeActionMessage(ActionMessage message,
                                    HttpOutputMessage outputMessage, AmfTrace trace) throws IOException {
        AmfMessageSerializer serializer = new AmfMessageSerializer();
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        serializer.setVersion(message.getVersion());
        serializer.initialize(new SerializationContext(), outBuffer, trace);

        try {
            ActionContext context = new ActionContext();
            context.setVersion(message.getVersion());
            context.setResponseMessage(message);
            serializer.writeMessage(message);
            outBuffer.flush();
            outBuffer.close();
            outputMessage.getHeaders().setContentLength(outBuffer.size());
            outBuffer.writeTo(outputMessage.getBody());
        } catch (SerializationException se) {
            throw new HttpMessageNotWritableException("Could not write "+message+" as AMF message.", se);
        }
    }

    private void writeObject(Object data, HttpOutputMessage outputMessage,
                             AmfTrace trace) throws IOException {
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        Amf3Output serializer = new Amf3Output(new SerializationContext());
        serializer.setOutputStream(outBuffer);
        serializer.setDebugTrace(trace);
        try {
            serializer.writeObject(data);
            outBuffer.flush();
            outBuffer.close();
            outputMessage.getHeaders().setContentLength(outBuffer.size());
            outBuffer.writeTo(outputMessage.getBody());
        } catch (SerializationException se) {
            throw new HttpMessageNotWritableException("Could not write "+data+" as AMF message.", se);
        }
    }
}
