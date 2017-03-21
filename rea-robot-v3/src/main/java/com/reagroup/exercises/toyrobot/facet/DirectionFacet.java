package com.reagroup.exercises.toyrobot.facet;

import java.util.Optional;

import com.reagroup.exercises.toyrobot.input.MutableString;
import com.reagroup.exercises.toyrobot.position.Direction;
import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Verifies that the input value starts with one of the {@link Direction} elements.
 *  
 * @author Sabya
 */
public class DirectionFacet implements Facet<Direction> {

	private final GenericStringFacet wordFacet = GenericStringFacet.of("(NORTH|SOUTH|WEST|EAST)");
	
	private DirectionFacet() {
	}
	
	private static final DirectionFacet instance = new DirectionFacet();
	
	/**
	 * @return the instance
	 */
	public static DirectionFacet instance() {
		return instance;
	}
	
	@Override
	public Optional<Direction> matchValue(final MutableString input) {
		Argument.notNull(input, "input");
		
		final Optional<String> value = this.wordFacet.matchValue(input);
		if(!value.isPresent()) {
			return Optional.empty();
		}
		
		return Direction.from(value.get());
	}
}
