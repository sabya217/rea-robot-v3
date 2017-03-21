package com.reagroup.exercises.toyrobot.command;

import com.reagroup.exercises.toyrobot.executor.MutablePosition;
import com.reagroup.exercises.toyrobot.position.Surface;

/**
 * Represents a command to be executed by a Robot.
 * 
 * @author Sabya
 *
 */
public interface Command {

	/**
	 * Applies the command on the mutable position within the surface area.
	 * 
	 * @param mutablePosition
	 * @param surface
	 */
	void apply(final MutablePosition mutablePosition, final Surface surface);
}
