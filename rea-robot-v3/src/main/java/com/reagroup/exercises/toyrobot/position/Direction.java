package com.reagroup.exercises.toyrobot.position;

import java.util.Optional;

import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Represents the direction of movement of the robot.
 * 
 * @author Sabya
 */
public enum Direction {

	NORTH, SOUTH, EAST, WEST;
	
	/**
	 * Gets the {@link Direction} from the input.
	 * It is wrapped within an {@link Optional} element to handle null entries.
	 *  
	 * @param input
	 * @return an Optional form of {@link Direction}
	 */
	public static Optional<Direction> from(final String input) {
		Argument.notNull(input, "input");
		
		for(Direction direction : Direction.values()) {
			if(direction.name().equalsIgnoreCase(input)) {
				return Optional.of(direction);
			}
		}
		
		return Optional.empty();
	}
}
