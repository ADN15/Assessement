package com.example.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class RequestDTO {
	
	@Getter
	@Setter
	@JsonProperty("movie_id")
	private String movieId;
	
	@Getter
	@Setter
	@JsonProperty("search")
	private String search;
	
	@Getter
	@Setter
	@JsonProperty("user_id")
	private String userId;
	
	@Getter
	@Setter
	@JsonProperty("title")
	private String title;
	
	@Getter
	@Setter
	@JsonProperty("year")
	private String year;
	
}
