package com.reagroup.exercises.toyrobot.robot;

import com.reagroup.exercises.toyrobot.command.Command;
import com.reagroup.exercises.toyrobot.executor.MutablePosition;
import com.reagroup.exercises.toyrobot.position.Surface;
import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * The toy robot who can move with some command instructions within a surface area.
 * 
 * @author Sabya
 */
public class Robot implements IRobot {

	private final MutablePosition mutablePosition;
	
	private Robot(final MutablePosition mutablePosition){
		this.mutablePosition = mutablePosition;
	}
	
	/**
	 * Creates an instance of the REA Robot
	 * 
	 * @return an instance of the {@link Robot}
	 */
	public static IRobot create() {
		return new Robot(MutablePosition.of());
	}
	
	@Override
	public void execute(final Command command, final Surface surface) {
		Argument.notNull(command, "command");
		Argument.notNull(surface, "surface");
		
		this.mutablePosition.apply(command, surface);
	}
}
