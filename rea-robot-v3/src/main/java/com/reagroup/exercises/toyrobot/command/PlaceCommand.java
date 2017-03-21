/**
 * 
 */
package com.reagroup.exercises.toyrobot.command;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reagroup.exercises.toyrobot.executor.MutablePosition;
import com.reagroup.exercises.toyrobot.facet.GenericStringFacet;
import com.reagroup.exercises.toyrobot.facet.PositionFacet;
import com.reagroup.exercises.toyrobot.input.MutableString;
import com.reagroup.exercises.toyrobot.position.Position;
import com.reagroup.exercises.toyrobot.position.Surface;
import com.reagroup.exercises.toyrobot.util.Argument;
import com.reagroup.exercises.toyrobot.util.CoordinatesVerifier;

/**
 * Represents a place command which takes a {@link Position} as an input.
 * 
 * @author Sabya
 */
public class PlaceCommand implements FacetedCommand<Position> {

	private static final Logger LOG = LoggerFactory.getLogger(PlaceCommand.class); 
	
	private final Position position;

	private static final GenericStringFacet placeFacet = GenericStringFacet.of("PLACE");
	
	private static final PositionFacet positionFacet = PositionFacet.instance();
	
	private PlaceCommand(final Position position) {
		this.position = position;
	}

	/**
	 * Creates a {@link PlaceCommand} from the {@link Position}.
	 * 
	 * @param position
	 * @return an instance of {@link PlaceCommand}
	 */
	public static PlaceCommand of(final Position position) {
		return new PlaceCommand(Argument.notNull(position, "position"));
	}
	
	public static Optional<PlaceCommand> from(final String input) {
		Argument.notNull(input, "input");
		
		final MutableString mutableInput = MutableString.of(input);

		return matchInput(mutableInput);
	}
	
	private static Optional<PlaceCommand> matchInput(MutableString input) {
		
		input.mark();
		final Optional<String> placeOptional = placeFacet.matchValue(input);
		if(!placeOptional.isPresent()) {
			input.reset();
			return Optional.empty();
		}
		
		final Optional<Position> positionOptional = positionFacet.matchValue(input);
		if(!positionOptional.isPresent()) {
			input.reset();
			return Optional.empty();
		}
		
		input.discard();
		
		//Should invalidate if the input contains extraneous data
		if(!input.getValue().trim().isEmpty()) {
			return Optional.empty();
		}
		
		final PlaceCommand placeCommand = new PlaceCommand(positionOptional.get());
		
		return Optional.of(placeCommand);
	}

	@Override
	public Position getFacetValue() {
		return this.position;
	}

	@Override
	public void apply(final MutablePosition mutablePosition, final Surface surface) {
		if(!CoordinatesVerifier.isWithin(this.position.getCoordinates(), surface)) {
			LOG.warn("Co-ordinates \"{}\" out of range.", this.position.getCoordinates());
			return;
		}
		
		LOG.debug("Updated position to \"{}\"", this.position);
		mutablePosition.updatePosition(this.position);
	}
}
