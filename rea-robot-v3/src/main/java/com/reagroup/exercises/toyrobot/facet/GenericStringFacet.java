package com.reagroup.exercises.toyrobot.facet;

import java.util.Optional;
import java.util.regex.Pattern;

import com.reagroup.exercises.toyrobot.input.MutableString;
import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Verifies that the input value starts with a particular String or regular expression or not.
 * 
 * @author Sabya
 *
 */
public class GenericStringFacet implements Facet<String> {

	private final String regex;
	
	private final boolean caseInsensitive;
	
	private GenericStringFacet(final String regex, final boolean caseInsensitive) {
		this.regex = regex; 
		this.caseInsensitive = caseInsensitive;
	}
	
	/**
	 * Creates a {@link GenericStringFacet} with the regular expression.
	 * 
	 * @param regex
	 * @return an instance of {@link GenericStringFacet}
	 */
	public static GenericStringFacet of(final String regex) {
		return new GenericStringFacet(Argument.notNullOrEmpty(regex, "regex"), true);
	}
	
	/**
	 * Creates a {@link GenericStringFacet} with the regular expression.
	 * 
	 * @param regex
	 * @param caseInsensitive
	 * @return an instance of {@link GenericStringFacet}
	 */
	public static GenericStringFacet of(final String regex, final boolean caseInsensitive) {
		return new GenericStringFacet(Argument.notNullOrEmpty(regex, "regex"), caseInsensitive);
	}
	
	/* (non-Javadoc)
	 * @see com.reagroup.exercises.toyrobot.facet.Facet#matchValue(com.reagroup.exercises.toyrobot.input.MutableString)
	 */
	@Override
	public Optional<String> matchValue(MutableString input) {
		Argument.notNull(input, "input");
		
		final Pattern pattern;
		if(this.caseInsensitive) {
			pattern = Pattern.compile(this.regex, Pattern.CASE_INSENSITIVE);
		} else {
			pattern = Pattern.compile(this.regex);
		}
		final String value = input.findAndSnip(pattern, true, true);
		
		return Optional.ofNullable(value);
	}
}
