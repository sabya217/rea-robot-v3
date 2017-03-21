package com.reagroup.exercises.toyrobot.facet;

import java.util.Optional;

import com.reagroup.exercises.toyrobot.input.MutableString;
import com.reagroup.exercises.toyrobot.position.Coordinates;
import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Verifies that the input value starts with the syntax of a {@link Coordinates} or not.
 * The syntax of the co-ordinate is <code>x(number),y(number)</code>.  
 *  
 * @author Sabya
 */
public class CoordinatesFacet implements Facet<Coordinates> {

	private final IntegerFacet integerFacet = IntegerFacet.instance();
	
	private final GenericStringFacet commaFacet = GenericStringFacet.of(",");
	
	private CoordinatesFacet() {
	}
	
	private static final CoordinatesFacet instance = new CoordinatesFacet();
	
	/**
	 * @return the instance
	 */
	public static CoordinatesFacet instance() {
		return instance;
	}
	
	@Override
	public Optional<Coordinates> matchValue(final MutableString input) {
		Argument.notNull(input, "input");
		
		input.mark();
		
		final Optional<Integer> optionalX = this.integerFacet.matchValue(input);
		if(!optionalX.isPresent()) {
			input.reset();
			return Optional.empty();
		}
		
		if(!this.commaFacet.matchValue(input).isPresent()) {
			input.reset();
			return Optional.empty();
		}
		
		final Optional<Integer> optionalY = this.integerFacet.matchValue(input);
		if(!optionalY.isPresent()) {
			input.reset();
			return Optional.empty();
		}
		
		input.discard();
		
		final Coordinates coordinates = Coordinates.of(optionalX.get(), optionalY.get());
		
		return Optional.of(coordinates);
	}
}
