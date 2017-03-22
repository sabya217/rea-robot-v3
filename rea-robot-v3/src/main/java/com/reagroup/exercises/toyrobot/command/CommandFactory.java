package com.reagroup.exercises.toyrobot.command;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * A factory to create {@link Command} from String inputs.
 * It is stateless and is accessed via its singleton.
 * 
 * @author Sabya
 */
public class CommandFactory {

	private static final Logger LOG = LoggerFactory.getLogger(CommandFactory.class);
	
	private CommandFactory(){
		//
	}
	
	private static CommandFactory instance = new CommandFactory();
	
	/**
	 * Gets the singleton instance.
	 */
	public static CommandFactory instance() {
		return instance;
	}
	
	/**
	 * Creates an instance of {@link Command} from the String input.
	 * 
	 * <p>
	 * If the input string does not match any of the available command types, a
	 * no-op command is returned.
	 * 
	 * @param input
	 * @return an instance of {@link Command}
	 */
	public Command create(final String input) {
		Argument.notNull(input, "input");
		
		Optional<? extends Command> optionalCommand = SimpleCommand.from(input);
		if(optionalCommand.isPresent()) {
			LOG.trace("Command found \"{}\"", optionalCommand.get());
			return optionalCommand.get();
		}
		
		optionalCommand = PlaceCommand.from(input);
		if(optionalCommand.isPresent()) {
			LOG.trace("Command found \"{}\"", optionalCommand.get());
			return optionalCommand.get();
		}
	
		LOG.warn("Invalid command \"{}\", ignored.", input);
		return NoOpCommand.instance();
	}
}
