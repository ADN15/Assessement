package com.example.movie.util;

public class ValueUtil {
	
	private ValueUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static boolean hasValue(String s) {
		return hasValue(s, true);
	}
	
	public static boolean hasValue(Object obj) {
		if(obj != null){
			if(String.class.isAssignableFrom(obj.getClass()))
				return hasValue((String)obj, false);
			else
				return true;
		}else{
			return false;
		}
	}
	
	public static boolean hasValue(String s, boolean ignoreSpace) {
		if(s == null || s.length()==0)
			return false;
		else {
			if(ignoreSpace) {
				return s.trim().length() > 0;
			}
	
			return true;
		}
	}
}
