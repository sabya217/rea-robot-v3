package com.reagroup.exercises.toyrobot.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reagroup.exercises.toyrobot.position.Direction;
import com.reagroup.exercises.toyrobot.position.Surface;
import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Represents a type of command executor that changes the direction of the robot.
 * 
 * @author Sabya
 */
class DirectionChanger {

	private static final Logger LOG = LoggerFactory.getLogger(DirectionChanger.class);
	
	/**
	 * Turns the robot to its left without making any move.
	 * 
	 * @param mutablePosition
	 * @param surface
	 */
	public void lookLeft(final MutablePosition mutablePosition, final Surface surface) {
		Argument.notNull(mutablePosition, "mutable position");
		Argument.notNull(surface, "surface");
		
		if(!mutablePosition.isPresent()) {
			LOG.warn("Robot is not placed yet.");
			return;
		}
		
		switch(mutablePosition.getActual().getDirection()) {
		case EAST:
			mutablePosition.updateDirection(Direction.NORTH);
			break;
		case NORTH:
			mutablePosition.updateDirection(Direction.WEST);
			break;
		case SOUTH:
			mutablePosition.updateDirection(Direction.EAST);
			break;
		case WEST:
			mutablePosition.updateDirection(Direction.SOUTH);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Turns the robot to its right without making any move.
	 * 
	 * @param mutablePosition
	 * @param surface
	 */
	public void lookRight(final MutablePosition mutablePosition, final Surface surface) {
		Argument.notNull(mutablePosition, "mutable position");
		Argument.notNull(surface, "surface");
		
		if(!mutablePosition.isPresent()) {
			LOG.warn("Robot is not placed yet.");
			return;
		}
		
		switch(mutablePosition.getActual().getDirection()) {
		case EAST:
			mutablePosition.updateDirection(Direction.SOUTH);
			break;
		case NORTH:
			mutablePosition.updateDirection(Direction.EAST);
			break;
		case SOUTH:
			mutablePosition.updateDirection(Direction.WEST);
			break;
		case WEST:
			mutablePosition.updateDirection(Direction.NORTH);
			break;
		default:
			break;
		}
	}
}
