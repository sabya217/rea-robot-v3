package com.reagroup.exercises.toyrobot.input;

import java.io.InputStream;
import java.util.stream.Stream;

import com.reagroup.exercises.toyrobot.command.Command;

/**
 * Represents an input source for the toy robot simulator. It can internally
 * read from a static source like a file or a dynamic one like stdin or an
 * {@link InputStream}.
 * 
 * @author Sabya
 *
 */
public interface InputSource {
	
	/**
	 * Polls for an input.
	 * 
	 * @return the String polled, null if end of the input source is reached.
	 */
	String poll();
	
	/**
	 * Gets the commands as a stream.
	 */
	Stream<Command> getCommandStream();
}
