package com.reagroup.exercises.toyrobot.input;

import java.util.stream.Stream;

import com.reagroup.exercises.toyrobot.command.Command;

/**
 * Represents the input source from stdin.
 * 
 * <p>
 * <strong>It is not supported yet.</strong>
 * 
 * @author Sabya
 *
 */
public class StdInInputSource implements InputSource {

	@Override
	public String poll() {
		throw new UnsupportedOperationException("StdIn Input source is not supported yet.");
	}

	@Override
	public Stream<Command> getCommandStream() {
		throw new UnsupportedOperationException("StdIn Input source is not supported yet.");
	}
}
