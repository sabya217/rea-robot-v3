package com.reagroup.exercises.toyrobot.executor;

import com.reagroup.exercises.toyrobot.command.Command;
import com.reagroup.exercises.toyrobot.position.Position;
import com.reagroup.exercises.toyrobot.position.Surface;

/**
 * Represents a command executor.
 * 
 * @author Sabya
 */
public interface ICommandExecutor {

	/**
	 * Executes the command with respect to the mutable position against a particular surface area.
	 * 
	 * @param mutablePosition
	 * @param command
	 * @param surface
	 * 
	 * @return the new position after the command has been executed
	 */
	Position execute(
			final MutablePosition mutablePosition, final Command command, final Surface surface);
}
