package com.example.movie.dto;

import java.util.List;

import com.example.movie.dto.PhotoInfoDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class PhotoDetailDTO {
	
	@Getter
	@Setter
	@JsonProperty("page")
	private Integer pages;
	
	@Getter
	@Setter
	@JsonProperty("pages")
	private Integer allPages;
	
	@Getter
	@Setter
	@JsonProperty("perpage")
	private Integer perpage;
	
	@Getter
	@Setter
	@JsonProperty("total")
	private Integer total;
	
	@Getter
	@Setter
	@JsonProperty("photo")
	private List<PhotoInfoDTO> photo; 
	
}