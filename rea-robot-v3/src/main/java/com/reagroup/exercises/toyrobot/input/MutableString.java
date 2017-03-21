package com.reagroup.exercises.toyrobot.input;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.reagroup.exercises.toyrobot.facet.Facet;
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
	
	private LinkedList<String> markedStack = new LinkedList<>();
	
	private MutableString(final String value) {
		this.value = value;
	}
	
	/** Creates the mutable String object to be processed.
	 * 
	 * @param value
	 * @return an instance of {@link MutableString}
	 */
	public static MutableString of(final String value) {
		return new MutableString(Argument.notNull(value, "value"));
	}
	
	/**
	 * Returns a copy of the self
	 */
	public MutableString copy() {
		return new MutableString(this.value);
	}
	
	@Override
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Finds the next matched element from the string and updates the string snipping that portion.
	 *  
	 * @param pattern
	 * @return the string being read from the matcher
	 */
	public String findAndSnip(final Pattern pattern, 
			final boolean trim, final boolean start) {
		Argument.notNull(pattern, "pattern");

		String finalValue = trim ? this.value.trim() : this.value;
		
		final Matcher matcher = pattern.matcher(finalValue);
		
		String foundString = null;
		if(matcher.find() && (!start || matcher.start() == 0)) {
			foundString = finalValue.substring(matcher.start(), matcher.end());
			this.value = matcher.replaceFirst("");
		}
		
		return foundString;
	}
	
	/**
	 * Finds the next matched element from the string and updates the string snipping that portion.
	 *  
	 * @param pattern
	 * @param trim - Indicates the value string to be trimmed before matching or not
	 * @param start - Indicates whether the matched pattern should be at the start or not
	 * @return the string being read from the matcher
	 */
	public String findAndSnip(final String pattern,
			final boolean trim, final boolean start) {
		return findAndSnip(Pattern.compile(Argument.notNull(pattern, "pattern")), trim, start);
	}
	
	/**
	 * Saves the state of the entity by marking it.
	 */
	public void mark() {
		this.markedStack.addLast(this.value);
	}
	
	/**
	 * Sets the value to a previously marked state. It has no effect if no marked state is present.
	 */
	public void reset() {
		if(this.markedStack.isEmpty()) {
			return;
		}
		this.value = this.markedStack.pollLast();
	}
	
	/**
	 * Clears the last marked states.
	 */
	public void discard() {
		if(this.markedStack.isEmpty()) {
			return;
		}
		this.markedStack.removeLast();
	}
}
