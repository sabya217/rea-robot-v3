package com.reagroup.exercises.toyrobot.commad;

import java.util.Optional;

import com.reagroup.exercises.toyrobot.input.MutableString;

/**
 * Represents a conditional object which can match an input and present a value
 * of a particular type.
 * 
 * @author Sabya
 * 
 * @param <T> - The type of the object the facet will return
 */
@FunctionalInterface
public interface Facet<T> {
	
	/**
	 * Tries to match the condition on the input string and returns the output as absent or present.
	 * 
	 * @param input
	 * @return an optional output after matching the condition
	 */
	Optional<T> matchValue(final MutableString input);
}
