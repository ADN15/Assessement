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
import com.example.movie.exception.GlobalCustomException;

@RestController
@RequestMapping("/api/assessement")
public class FlickrApplicationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlickrApplicationController.class);
	
	@Autowired
	FlickrApplicationService flickrApplicationService;
	
	@GetMapping("/search")
	public ResponseEntity<Object> search(@RequestParam("search") String search,@RequestParam("user_id") String userId) throws GlobalCustomException {
		LOGGER.info("Get List Photo");
		
		RequestDTO params = new RequestDTO();
		params.setSearch(search);
		params.setUserId(userId);
		
		Map<String,Object> response = flickrApplicationService.getList(params);
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
	public ResponseEntity<Object> listPhoto(@RequestParam("user_id") String userId) throws GlobalCustomException {
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