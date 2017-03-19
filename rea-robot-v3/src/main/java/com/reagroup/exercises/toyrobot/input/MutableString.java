package com.reagroup.exercises.toyrobot.input;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.reagroup.exercises.toyrobot.commad.Facet;
import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Represents a string input which can be mutated as it is processed across
 * different {@link Facet}s.
 * 
 * @author Sabya
 */
public class MutableString implements InputEntity {

	/** The current value */
	private String value;
	
	private MutableString(final String value) {
		this.value = value;
	}
	
	/** Creates the mutable String object to be processed.
	 * 
	 * @param value
	 * @return an instance of {@link MutableString}
	 */
	public static MutableString of(final String value) {
		return new MutableString(Argument.notNullOrEmpty(value, "value"));
	}
	
	@Override
	public String getCurrentValue() {
		return this.value;
	}
	
	/**
	 * Finds the next matched element from the string and updates the string snipping that portion.
	 *  
	 * @param pattern
	 * @return the string being read from the matcher
	 */
	public String findAndSnip(final Pattern pattern) {
		Argument.notNull(pattern, "pattern");
		
		final Matcher matcher = pattern.matcher(this.value);
		
		String foundString = null;
		if(matcher.find()) {
			foundString = this.value.substring(matcher.start(), matcher.end());
			this.value = matcher.replaceFirst("");
		}
		
		return foundString;
	}
	
	/**
	 * Finds the next matched element from the string and updates the string snipping that portion.
	 *  
	 * @param pattern
	 * @return the string being read from the matcher
	 */
	public String findAndSnip(final String pattern) {
		return findAndSnip(Pattern.compile(Argument.notNull(pattern, "pattern")));
	}
}
