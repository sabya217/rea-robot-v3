package com.reagroup.exercises.toyrobot.util;

public abstract class Argument {

	private Argument(){}
	
	/**
	 * Verifies the string for null or empty validity.
	 *  
	 * @param value
	 * @param argName
	 * 
	 * @throws IllegalArgumentException if the String value is invalidated
	 * @return the String object itself if it is a valid object
	 */
	public static String notNullOrEmpty(final String value, final String argName) {
		if(value == null || value.trim().isEmpty()) {
			throw new IllegalArgumentException(
					String.format(" %s can not be null or empty", argName));
		}
		
		return value;
	}
	
	/**
	 * Verifies that the object is not null.
	 *  
	 * @param value
	 * @param argName
	 * 
	 * @throws IllegalArgumentException if the input value is null
	 * @return the object itself if it is a valid object
	 */
	public static <T> T notNull(final T value, final String argName) {
		if(value == null) {
			throw new IllegalArgumentException(
					String.format(" %s can not be null or empty", argName));
		}
		
		return value;
	}
}
