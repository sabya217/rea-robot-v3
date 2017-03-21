package com.reagroup.exercises.toyrobot.command;

import java.util.Optional;

import com.reagroup.exercises.toyrobot.executor.MutablePosition;
import com.reagroup.exercises.toyrobot.executor.SimpleCommandExecutor;
import com.reagroup.exercises.toyrobot.position.Direction;
import com.reagroup.exercises.toyrobot.position.Surface;
import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Represents all the simple commands which can be used as inputs to the Robot.
 * 
 * @author Sabya
 *
 */
public enum SimpleCommand implements Command {
	
	/** Indicates the robot to move by one unit in the designated direction.
	 * This can be extended to be a {@link FacetedCommand} with the number of units. */
	MOVE,
	/**
	 * Indicates the robot to turn to the left i.e. the next movement will be in
	 * the direction of left from the current position.
	 */
	LEFT,
	/**
	 * Indicates the robot to turn to the right i.e. the next movement will
	 * be in the direction of right from the current position.
	 */
	RIGHT,
	/**
	 * Indicates the robot details to be printed to the output source. 
	 */
	REPORT;
	
	/**
	 * Gets the {@link SimpleCommand} from the input.
	 * It is wrapped within an {@link Optional} element to handle null entries.
	 *  
	 * @param input
	 * @return an Optional form of {@link Direction}
	 */
	public static Optional<SimpleCommand> from(final String input) {
		Argument.notNull(input, "input");
		
		final String inputFinal = input.trim();
		for(SimpleCommand command : SimpleCommand.values()) {
			if(command.name().equalsIgnoreCase(inputFinal)) {
				return Optional.of(command);
			}
		}
		
		return Optional.empty();
	}

	@Override
	public void apply(MutablePosition mutablePosition, Surface surface) {
		SimpleCommandExecutor.singleton().execute(this, mutablePosition, surface);
	}
}
