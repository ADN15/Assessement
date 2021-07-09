package com.example.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.movie.dto.PhotoDetailDTO;

import lombok.Getter;
import lombok.Setter;

public class PhotoHeadDTO {
	
	@Getter
	@Setter
	@JsonProperty("photos")
	private PhotoDetailDTO photos;
	
	@Getter
	@Setter
	@JsonProperty("stat")
	private String status;
	
}