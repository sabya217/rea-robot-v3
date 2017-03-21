package com.reagroup.exercises.toyrobot.command;

/**
 * Represents a type of command which does nothing. 
 * It is basically used as a fall-back option while parsing for commands from inputs.
 * It is accessed by its singleton.
 *   
 * @author Sabya
 *
 */
public class NoOpCommand implements Command {

	private NoOpCommand() {
		//
	}
	
	private static NoOpCommand instance = new NoOpCommand();
	
	public static Command instance() {
		return instance;
	}
}
