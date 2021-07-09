package com.example.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class PhotoInfoDTO {
	
	@Getter
	@Setter
	@JsonProperty("id")
	private String id;
	
	@Getter
	@Setter
	@JsonProperty("owner")
	private String owner;
	
	@Getter
	@Setter
	@JsonProperty("secret")
	private String secret;
	
	@Getter
	@Setter
	@JsonProperty("server")
	private String server;
	
	@Getter
	@Setter
	@JsonProperty("farm")
	private Integer farm;
	
	@Getter
	@Setter
	@JsonProperty("title")
	private String title;
	
	@Getter
	@Setter
	@JsonProperty("ispublic")
	private Integer ispublic;
	
	@Getter
	@Setter
	@JsonProperty("isfriend")
	private Integer isfriend;
	
	@Getter
	@Setter
	@JsonProperty("isfamily")
	private Integer isfamily;
	
}