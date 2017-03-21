package com.reagroup.exercises.toyrobot.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reagroup.exercises.toyrobot.position.Coordinates;
import com.reagroup.exercises.toyrobot.position.Position;
import com.reagroup.exercises.toyrobot.position.Surface;
import com.reagroup.exercises.toyrobot.util.Argument;
import com.reagroup.exercises.toyrobot.util.CoordinatesVerifier;

/**
 * Represents a type of command executor that verifies robot's position and
 * moves one unit if it is a valid move.
 * 
 * @author Sabya
 *
 */
class Mover {

	private static final Logger LOG = LoggerFactory.getLogger(Mover.class);
	
	/**
	 * Tries to move one unit in the direction specified if it is a valid within the surface area.
	 * 
	 * @param mutablePosition
	 * @param surface
	 */
	public void tryMove(final MutablePosition mutablePosition, final Surface surface) {
		Argument.notNull(mutablePosition, "mutable position");
		Argument.notNull(surface, "surface");
		
		if(!mutablePosition.isPresent()) {
			LOG.warn("Robot is not placed yet.");
			return;
		}
		
		final Coordinates probablePosition = getNextPosition(mutablePosition.getActual());
		if(!CoordinatesVerifier.isWithin(probablePosition, surface)) {
			LOG.warn("New position will be out of range, not proceeding.");
			return;
		}
		
		mutablePosition.updateCoordinates(probablePosition);
		LOG.debug("Updated position coordinates to \"{}\"", probablePosition);
	}

	/**
	 * Gets the next coordinates after moving one unit.
	 * @param position
	 */
	private Coordinates getNextPosition(final Position position) {
		final Coordinates currentPos = position.getCoordinates();
		
		switch(position.getDirection()) {
		case EAST:
			return Coordinates.of(currentPos.getX() + 1, currentPos.getY());
		case WEST:
			return Coordinates.of(currentPos.getX() - 1, currentPos.getY());
		case NORTH:
			return Coordinates.of(currentPos.getX(), currentPos.getY() - 1);
		case SOUTH:
			return Coordinates.of(currentPos.getX(), currentPos.getY() + 1);
		default:
			return currentPos;
		}
	}
}
