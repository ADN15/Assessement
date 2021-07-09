package com.example.movie.dto;

import org.apache.commons.lang3.time.FastDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.example.movie.util.DateUtil;
import com.example.movie.util.ValueUtil;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
public class GlobalResponseDTO<T> {

	@Setter
	@Getter
	@JsonProperty("Status")
	private int status;
	
	@Setter
	@Getter
	@JsonProperty("Message")
	private String message;
	
	@JsonProperty("DateTime")
	private String dateTime = FastDateFormat.getInstance("dd-MM-yyyy HH:mm:ss").format(DateUtil.getCurrentTimestamp());
	
	@Setter
	@Getter
	@JsonProperty("Data")
	private T data;
	
	public GlobalResponseDTO() {	
	}
	
	public GlobalResponseDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }
	
	public GlobalResponseDTO(int status, String message, T data) {
		super();
        if(ValueUtil.hasValue(message)) { 
        	this.message = message;
        	this.status = status;
        }
		this.data = data;
	}
}