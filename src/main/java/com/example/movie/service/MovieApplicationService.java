package com.example.movie.service;

import java.io.File;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;

import com.example.movie.common.Constants;
import com.example.movie.dto.RequestDTO;
import com.example.movie.exception.GlobalCustomException;

@Service
@Slf4j
public class MovieApplicationService {
	
	@Value("${url.publicapi.movie}")
	private String urlMovie;
	
	@Value("${id.param.movie.key}")
	private String paramKey;
	
	@Value("${id.key.publicapi}")
	private String key;
	
	private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(Constants.DEFAULT_TIMEOUT);
        clientHttpRequestFactory.setReadTimeout(Constants.DEFAULT_TIMEOUT);

        return clientHttpRequestFactory;
    }
	
	public Map<String, Object> findMovie (String params) throws GlobalCustomException {
		Map<String,Object> result = new HashMap<String,Object>();
		
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		String url = urlMovie;
		
		ResponseEntity<String> responseEntity;
		try {
			url = url+"?"+paramKey+"="+key+"&s="+params;
			
			//Header
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity entity = new HttpEntity(headers);
				
	        responseEntity = restTemplate.exchange(url,HttpMethod.GET,entity,String.class);
			String json = String.valueOf(responseEntity.getBody());
			
			JSONObject json2 = new JSONObject(json);
			result = jsonToMap(json2);
		} catch (Exception e) {
			throw new GlobalCustomException("Failed Find Movie "+e.getMessage());
		}
		return result;
	}
	
	public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
	
}