package com.example.movie.util;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtil {
	
	private DateUtil() {
	    throw new IllegalStateException("Utility class");
	}

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static Timestamp getCurrentTimestampGMT7() {
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Jakarta"));	
		return Timestamp.valueOf(zdt.toLocalDateTime());
	}
	
	public static Timestamp getCurrentTimestampGMT8() {
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Taipei"));	
		return Timestamp.valueOf(zdt.toLocalDateTime());
	}
	
	public static Timestamp getCurrentTimestampGMT9() {
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));	
		return Timestamp.valueOf(zdt.toLocalDateTime());
	}
	
	public static Timestamp getCurrentTimestampGMT0() {
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Zulu"));
		return Timestamp.valueOf(zdt.toLocalDateTime());
	}
	
}
