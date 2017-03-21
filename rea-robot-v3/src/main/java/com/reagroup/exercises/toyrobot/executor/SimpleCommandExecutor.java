package com.reagroup.exercises.toyrobot.executor;

import com.reagroup.exercises.toyrobot.command.SimpleCommand;
import com.reagroup.exercises.toyrobot.position.Surface;
import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Executor of {@link SimpleCommand}s on a {@link MutablePosition} within a {@link Surface} area.
 * 
 * @author Sabya
 */
public class SimpleCommandExecutor {

	private final Mover mover;
	
	private final DirectionChanger directionChanger;
	
	private final Reporter reporter;
	
	/**
	 * Constructor
	 */
	private SimpleCommandExecutor() {
		this.mover = new Mover();
		this.directionChanger = new DirectionChanger();
		this.reporter = Reporter.instance();
	}
	
	private static SimpleCommandExecutor instance = null;
	
	/**
	 * Gets the SimpleCommandExecutor singleton instance
	 */
	public static SimpleCommandExecutor singleton() {
		if(instance == null) {
			instance = new SimpleCommandExecutor();
		}
		return instance;
	}
	
	/**
	 * Executes the command with respect to the mutable position within a designated surface area.
	 * 
	 * @param command
	 * @param mutablePosition
	 * @param surface
	 * 
	 * @return the new position after the command has been executed
	 */
	public void execute(
			final SimpleCommand command, final MutablePosition mutablePosition, final Surface surface) {
		Argument.notNull(command, "command");
		Argument.notNull(mutablePosition, "mutable position");
		Argument.notNull(surface, "surface");
		
		switch(command) {
		case LEFT:
			this.directionChanger.lookLeft(mutablePosition, surface);
			break;
		case RIGHT:
			this.directionChanger.lookRight(mutablePosition, surface);
			break;
		case MOVE:
			this.mover.tryMove(mutablePosition, surface);
			break;
		case REPORT:
			this.reporter.report(mutablePosition, surface);
			break;
		default:
			break;
		}
	}
}
