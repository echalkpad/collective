package com.collective.resources.writers;

import com.collective.model.persistence.enhanced.WebResourceEnhanced;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ArrayListResourceWriter
        implements MessageBodyWriter<ArrayList<WebResourceEnhanced>> {

    private static final Logger logger = Logger.getLogger(ArrayListResourceWriter.class);

    public boolean isWriteable(Class<?> aClass,
                               Type type,
                               Annotation[] annotations,
                               MediaType mediaType) {
        return ArrayList.class.isAssignableFrom(aClass);
    }

    public long getSize(ArrayList<WebResourceEnhanced> resources,
                        Class<?> aClass, Type type,
                        Annotation[] annotations,
                        MediaType mediaType) {
        return -1;
    }

    public void writeTo(ArrayList<WebResourceEnhanced> resources,
                        Class<?> aClass, Type type,
                        Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> stringObjectMultivaluedMap,
                        OutputStream outputStream)
            throws IOException, WebApplicationException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder
                .excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(resources);
        logger.debug("gson logging");
        logger.debug(json);
        BufferedOutputStream baos = new BufferedOutputStream(outputStream);
        baos.write(json.getBytes());
        baos.close();
    }
}