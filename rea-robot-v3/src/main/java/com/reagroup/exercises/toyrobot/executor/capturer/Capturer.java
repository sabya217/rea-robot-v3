package com.reagroup.exercises.toyrobot.executor.capturer;

/**
 * Captures data. This can be an output file or even a list. 
 * 
 * @author Sabya
 *
 * <T> the type of capturer
 */
public interface Capturer<T> {

	/**
	 * Initializes the capturer.
	 */
	void init();
	
	/**
	 * Captures a line.
	 * 
	 * @param line
	 */
	void capture(String line);
	
	/**
	 * Finalizes the capturer.
	 */
	void close();
	
	/**
	 * Gets the capturer.
	 * 
	 * @return the capturer.
	 */
	T get();
}
