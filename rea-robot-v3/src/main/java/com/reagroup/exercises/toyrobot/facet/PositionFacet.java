package com.reagroup.exercises.toyrobot.facet;

import java.util.Optional;

import com.reagroup.exercises.toyrobot.input.MutableString;
import com.reagroup.exercises.toyrobot.position.Coordinates;
import com.reagroup.exercises.toyrobot.position.Direction;
import com.reagroup.exercises.toyrobot.position.Position;
import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Verifies that the input value starts with a {@link Position} element.
 *  
 * @author Sabya
 */
public class PositionFacet implements Facet<Position> {

	private final CoordinatesFacet coordinatesFacet = CoordinatesFacet.instance();
	
	private final GenericStringFacet commaFacet = GenericStringFacet.of(",");
	
	private final DirectionFacet directionFacet = DirectionFacet.instance();
	
	private PositionFacet() {
	}
	
	private static final PositionFacet instance = new PositionFacet();
	
	/**
	 * @return the instance
	 */
	public static PositionFacet instance() {
		return instance;
	}
	
	@Override
	public Optional<Position> matchValue(MutableString input) {
		Argument.notNull(input, "input");
	
		input.mark();
		
		final Optional<Coordinates> optionalCordinates = this.coordinatesFacet.matchValue(input);
		if(!optionalCordinates.isPresent()) {
			input.reset();
			return Optional.empty();
		}
		
		if(!this.commaFacet.matchValue(input).isPresent()) {
			input.reset();
			return Optional.empty();
		}
		
		final Optional<Direction> optionalDirection = this.directionFacet.matchValue(input);
		if(!optionalDirection.isPresent()) {
			input.reset();
			return Optional.empty();
		}
	
		input.discard();
		
		final Position position = Position.from(optionalCordinates.get(), optionalDirection.get());
		return Optional.of(position);
	}
}
