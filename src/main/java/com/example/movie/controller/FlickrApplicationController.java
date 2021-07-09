package com.example.movie.controller;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import com.example.movie.service.FlickrApplicationService;

import com.example.movie.dto.GlobalResponseDTO;
import com.example.movie.dto.RequestDTO;
import com.example.movie.model.PhotoDetail;
import com.example.movie.exception.GlobalCustomException;

@RestController
@RequestMapping("/api/assessement")
public class FlickrApplicationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlickrApplicationController.class);
	
	@Autowired
	FlickrApplicationService flickrApplicationService;
	
	@PostMapping("/searchDb")
	public ResponseEntity<Object> searchDB(@RequestBody RequestDTO params) throws GlobalCustomException {
		LOGGER.info("Get List Photo on Database");
		PhotoDetail response = flickrApplicationService.getDataLocal(params);
		int status = 0;
		String message = "Not Found !";
		if(response.getPhotoId() != null) {
			status = 1;
			message = "OK";
		}
		return ResponseEntity.ok(new GlobalResponseDTO<>(status, message, response));
	}
	
	@GetMapping("/searchOnline")
	public ResponseEntity<Object> searchOnline(@RequestParam("search") String search,@RequestParam("user_id") String userId) throws GlobalCustomException {
		LOGGER.info("Get List Photo on Account Flickr");
		
		RequestDTO params = new RequestDTO();
		params.setSearch(search);
		params.setUserId(userId);
		
		Map<String,Object> response = flickrApplicationService.getDataFlickr(params);
		int status = 0;
		String message = "Not Found !";
		if (response.get("stat").equals("ok")) {
			status = 1;
			message = "OK";
			response.remove("stat");
		}
		return ResponseEntity.ok(new GlobalResponseDTO<>(status, message, response));
	}
	
	@GetMapping("/getallphoto")
	public ResponseEntity<Object> getAllPhoto(@RequestParam("user_id") String userId) throws GlobalCustomException {
		LOGGER.info("Get List Photo");
		
		RequestDTO params = new RequestDTO();
		params.setUserId(userId);
		
		Map<String,Object> response = flickrApplicationService.findAll(params);
		int status = 0;
		String message = "Not Found !";
		if (response.get("stat").equals("ok")) {
			status = 1;
			message = "OK";
			response.remove("stat");
		}
		return ResponseEntity.ok(new GlobalResponseDTO<>(status, message, response));
	}
}