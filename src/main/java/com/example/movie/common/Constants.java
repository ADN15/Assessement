package com.example.movie.common;

public class Constants {
	
	private Constants() {
		throw new IllegalStateException("Utility class");
	}
	
	public static final String EMPTY_STRING = "";
	public static final int ZERO_NUMERIC = 0;
	
	public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";
	
	public static final String FORMAT_DATE_YYYMMDD_1 = "yyyy-mm-dd";
	public static final String FORMAT_DATE_YYYMMDD_2 = "yyyymmdd";
	public static final String FORMAT_DATE_YYYMMDD_3 = "yyyy/mm/dd";
	
	public static final String FORMAT_DATE_DDMMYYYY_1 = "dd-mm-yyyy";
	public static final String FORMAT_DATE_DDMMYYYY_2 = "ddmmyyyy";
	public static final String FORMAT_DATE_DDMMYYYY_3 = "dd/mm/yyyy";
	
	public static final int DEFAULT_TIMEOUT = 30000;
	
	public static final String DATA_NOT_FOUND = "Data tidak ditemukan.";

	public static final String STD_RESP_PROPERTY_STATUS = "status";
	public static final String STD_RESP_PROPERTY_MESSAGE = "message";
	public static final String STD_RESP_PROPERTY_DATA = "data";
	
	public static final String GLB_MESSAGE_SUCCESS = "Success !";
	public static final String GLB_MESSAGE_FAILED = "Failed !";
	public static final String GLB_MESSAGE_FOUND = "Data Found !";
	public static final String GLB_MESSAGE_NOT_FOUND = "Data Not Found !";
	
}