package com.suj1th.rabpubsub;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;





/**
 * This class provides an abstraction for mapping JSONObjects to POJOs and vice-versa. The current
 * implementation uses {@link ObjectMapper} to perform the transformation.
 *
 * @version 0.1
 * @author suj1th
 */

@Singleton
public class MapperService {

    private ObjectMapper jacksonMapper;
	
    @Inject
    public MapperService(ObjectMapper jacksonMapper){
    	this.jacksonMapper=jacksonMapper;
    }


	/**
     * Serializes a POJO into a String Object.
     * 
     * @param model
     * @return JSONObject
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public String serialize(Object model) throws JsonParseException, JsonMappingException, IOException {
        return this.jacksonMapper.writeValueAsString(model);
    }


    /**
     * Maps a JSON-formatted String Object into a POJO.
     *
     * @param document
     * @param claas
     * @return model
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public <T> T deserialize(String document, Class<T> claas) throws JsonParseException, JsonMappingException,
    IOException {
        return this.jacksonMapper.readValue(document, claas);
    }


    /**
     * @param document
     * @param claas
     * @return model
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public <T> T deserialize(String document, TypeReference<T> claas) throws JsonParseException, JsonMappingException,
    IOException {
        return (T) this.jacksonMapper.readValue(document, claas);
    }
}
