/**
 * 
 */
package com.reagroup.exercises.toyrobot.command;

import java.util.Optional;

import com.reagroup.exercises.toyrobot.facet.GenericStringFacet;
import com.reagroup.exercises.toyrobot.facet.PositionFacet;
import com.reagroup.exercises.toyrobot.input.MutableString;
import com.reagroup.exercises.toyrobot.position.Position;
import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Represents a place command which takes a {@link Position} as an input.
 * 
 * @author Sabya
 */
public class PlaceCommand implements FacetedCommand<Position> {

	private final Position position;

	private static final GenericStringFacet placeFacet = GenericStringFacet.of("PLACE");
	
	private static final PositionFacet positionFacet = PositionFacet.instance();
	
	private PlaceCommand(final Position position) {
		this.position = position;
	}

	public static Optional<PlaceCommand> from(final String input) {
		Argument.notNull(input, "input");
		
		final MutableString mutableInput = MutableString.of(input);

		return matchInput(mutableInput);
	}
	
	private static Optional<PlaceCommand> matchInput(MutableString input) {
		
		final Optional<String> placeOptional = placeFacet.matchValue(input);
		if(!placeOptional.isPresent()) {
			return Optional.empty();
		}
		
		final Optional<Position> positionOptional = positionFacet.matchValue(input);
		if(!positionOptional.isPresent()) {
			return Optional.empty();
		}
		
		final PlaceCommand placeCommand = new PlaceCommand(positionOptional.get());
		
		return Optional.of(placeCommand);
	}

	@Override
	public Position getFacetValue() {
		return this.position;
	}
}
