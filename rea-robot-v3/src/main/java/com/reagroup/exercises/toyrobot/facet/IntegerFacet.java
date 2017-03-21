package com.reagroup.exercises.toyrobot.facet;

import java.util.Optional;

import com.reagroup.exercises.toyrobot.input.MutableString;
import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Verifies that the input value starts with an integer or not.  
 *  
 * @author Sabya
 */
public class IntegerFacet implements Facet<Integer> {

	private IntegerFacet() {
	}
	
	private static final IntegerFacet instance = new IntegerFacet();
	
	/**
	 * @return the instance
	 */
	public static IntegerFacet instance() {
		return instance;
	}

	@Override
	public Optional<Integer> matchValue(final MutableString input) {
		Argument.notNull(input, "input");
		
		final String stringValue = input.findAndSnip("\\d+", true, true);
		if(stringValue == null) {
			return Optional.empty();
		}
		
		final Integer value = Integer.parseInt(stringValue);
		
		return Optional.of(value);
	}
}
