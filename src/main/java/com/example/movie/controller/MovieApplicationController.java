package com.example.movie.controller;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import com.example.movie.service.MovieApplicationService;

import com.example.movie.dto.GlobalResponseDTO;
import com.example.movie.dto.RequestDTO;
import com.example.movie.exception.GlobalCustomException;

@RestController
@RequestMapping("/api/movie")
public class MovieApplicationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieApplicationController.class);
	
	@Autowired
	MovieApplicationService movieApplicationService;
	
	@GetMapping("/find")
	public ResponseEntity<Object> findMovie(@RequestParam("search") String search) throws GlobalCustomException {
		LOGGER.info("find movie api");
    	Map<String, Object> response =  movieApplicationService.findMovie(search);
    	int status = 0;
    	String message = "Not Found !";
    	if (response.get("Response").equals("True")) {
    		status = 1;
    		message = "OK";
    		response.remove("Response");
    	} else {
    		message = response.get("Error").toString();
    		response.remove("Response");
    		response.remove("Error");
    	}
    	return ResponseEntity.ok(new GlobalResponseDTO<>(status, message, response));
    }
}
