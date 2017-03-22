package com.reagroup.exercises.toyrobot.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reagroup.exercises.toyrobot.command.Command;
import com.reagroup.exercises.toyrobot.position.Coordinates;
import com.reagroup.exercises.toyrobot.position.Direction;
import com.reagroup.exercises.toyrobot.position.Position;
import com.reagroup.exercises.toyrobot.position.Surface;
import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Represents a mutable position element. This helps the robot change its
 * position upon acted by different commands.
 * 
 * @author Sabya
 */
public class MutablePosition {

	private static final Logger LOG = LoggerFactory.getLogger(MutablePosition.class);
	
	private Position position;
	
	private MutablePosition(Position position) {
		this.position = position;
	}
	
	/**
	 * Creates a {@link MutablePosition} with no initial position.
	 * @return an instance of {@link MutablePosition}
	 */
	public static MutablePosition of() {
		return new MutablePosition(null);
	}
	
	/**
	 * Creates a {@link MutablePosition} with an initial position.
	 * @return an instance of {@link MutablePosition}
	 */
	public static MutablePosition of(final Position position) {
		return new MutablePosition(position);
	}
	
	/**
	 * Gets the actual position element.
	 * 
	 * @return the actual position
	 */
	public Position getActual() {
		return this.position;
	}
	
	/**
	 * @return true if it contains a position data, false otherwise
	 */
	public boolean isPresent() {
		return this.position != null;
	}
	
	/**
	 * Applies the command on it with respect to the surface area.
	 * 
	 * @param command
	 * @param surface
	 */
	public void apply(final Command command, final Surface surface) {
		command.apply(this, surface);	
	}

	/**
	 * Updates the position of the mutable position.
	 * 
	 * @param position the position to set
	 */
	public void updatePosition(final Position position) {
		Argument.notNull(position, "position");
	
		LOG.trace("Trying to change the position from \"{}\" to \"{}\".", this.position, position);
		this.position = position;
	}
	
	/**
	 * Updates the co-ordinates of the mutable position.
	 * 
	 * @param newCoordinates
	 */
	public void updateCoordinates(final Coordinates newCoordinates) {
		Argument.notNull(newCoordinates, "new coordinates");
		
		LOG.trace("Trying to change the cordinates from \"{}\" to \"{}\".", this.position.getCoordinates(), newCoordinates);
		this.position = Position.from(newCoordinates, this.position.getDirection());
	}
	
	/**
	 * Updates the direction of the mutable position.
	 * 
	 * @param newDirection
	 */
	public void updateDirection(final Direction newDirection) {
		Argument.notNull(newDirection, "new direction");
		
		LOG.trace("Trying to change the direction from \"{}\" to \"{}\".", this.position.getDirection(), newDirection);
		this.position = Position.from(this.position.getCoordinates(), newDirection);
	}
}
