package com.reagroup.exercises.toyrobot.robot;

import com.reagroup.exercises.toyrobot.command.Command;
import com.reagroup.exercises.toyrobot.position.Surface;

/**
 * Represents a simple toy robot that can execute {@link Command}s in a
 * {@link Surface}.
 * 
 * @author Sabya
 *
 */
public interface IRobot {
	
	void execute(final Command command, final Surface surface);
}
