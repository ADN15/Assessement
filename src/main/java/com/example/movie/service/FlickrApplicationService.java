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
import com.example.movie.model.PhotoDetail;
import com.example.movie.dto.PhotoHeadDTO;
import com.example.movie.exception.GlobalCustomException;
import com.example.movie.repository.PhotoDetailRepository;

@Service
@Slf4j
public class FlickrApplicationService {
	
	@Value("${url.publicapi.flickr}")
	private String urlFlicker;
	
	@Value("${id.key.flickr}")
	private String paramKey;
	
	@Value("${id.key.user}")
	private String userKey;
	
	@Value("${id.param.method.search}")
	private String methodSearch;
	
	@Value("${id.param.method.getpopolar}")
	private String methodFindAll;
	
	@Autowired
	private PhotoDetailRepository photoDetailRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(Constants.DEFAULT_TIMEOUT);
        clientHttpRequestFactory.setReadTimeout(Constants.DEFAULT_TIMEOUT);

        return clientHttpRequestFactory;
    }
	
	public PhotoDetail getDataLocal (RequestDTO params) throws GlobalCustomException { 
		PhotoDetail result = new PhotoDetail();
		try {
			result = photoDetailRepository.findOneByTitle(params.getSearch().toUpperCase());
		}catch (Exception e) {
			throw new GlobalCustomException("Failed get data Photo on Local "+e.getMessage());
		}
		return result;
	}
	
	public Map<String, Object> getDataFlickr (RequestDTO params) throws GlobalCustomException {
		Map<String,Object> result = new HashMap<String,Object>();
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		String url = urlFlicker;
		
		ResponseEntity<String> responseEntity;
		try {
			url = url+"?method="+methodSearch+"&api_key="+paramKey+"&user_id="+params.getUserId()+"&tags="+params.getSearch()+"&format=json&nojsoncallback=1";
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity entity = new HttpEntity(headers);
				
	        responseEntity = restTemplate.exchange(url,HttpMethod.GET,entity,String.class);
			String json = String.valueOf(responseEntity.getBody());
			
			String mapper = json;
			
			PhotoHeadDTO resultMap = objectMapper.readValue(mapper, PhotoHeadDTO.class);
			
			JSONObject json2 = new JSONObject(json);
			result = jsonToMap(json2);
		} catch (Exception e) {
			throw new GlobalCustomException("Failed get data Photo "+e.getMessage());
		}
		return result;
	}
	
	public Boolean saveData (PhotoHeadDTO params,String userId) {
		Boolean result = false;
		int sizeData = params.getPhotos().getTotal();
		for (int i=0;i<sizeData;i++) {
			PhotoDetail metadata = new PhotoDetail();
			metadata.setUserId(params.getPhotos().getPhoto().get(i).getOwner());
			metadata.setServerId(params.getPhotos().getPhoto().get(i).getServer());
			metadata.setTitle(params.getPhotos().getPhoto().get(i).getTitle().toUpperCase());
			photoDetailRepository.save(metadata);
		}
		
		List<PhotoDetail> cekhasil = photoDetailRepository.findAllByUserId(userId);
		if (cekhasil.size() == sizeData) {
			result = true;
		}
		
		return result;
	}
	
	public Map<String, Object> findAll (RequestDTO params) throws GlobalCustomException {
		Map<String,Object> result = new HashMap<String,Object>();
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		String url = urlFlicker;
		
		ResponseEntity<String> responseEntity;
		try {
			url = url+"?method="+methodFindAll+"&api_key="+paramKey+"&user_id="+params.getUserId()+"&format=json&nojsoncallback=1";
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity entity = new HttpEntity(headers);
				
	        responseEntity = restTemplate.exchange(url,HttpMethod.GET,entity,String.class);
			String json = String.valueOf(responseEntity.getBody());
			String mapper = json;
			PhotoHeadDTO resultMap = objectMapper.readValue(mapper, PhotoHeadDTO.class);
			
			JSONObject json2 = new JSONObject(json);
			result = jsonToMap(json2);
			
			Boolean save = saveData(resultMap, params.getUserId());
			if (save == true) {
				result.put("save","berhasil");
			} else {
				result.put("save","gagal");
			}
			
			
		} catch (Exception e) {
			throw new GlobalCustomException("Failed get data Photo "+e.getMessage());
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