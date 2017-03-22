package com.reagroup.exercises.toyrobot.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reagroup.exercises.toyrobot.command.Command;
import com.reagroup.exercises.toyrobot.command.CommandFactory;
import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Represents an input file as an input source.
 * 
 * @author Sabya
 *
 */
public class FileInputSource implements InputSource {

	private static final Logger LOG = LoggerFactory.getLogger(FileInputSource.class);
	
	private final Queue<String> lines;
	
	/**
	 * @param lines
	 */
	private FileInputSource(final Queue<String> lines) {
		this.lines = lines;
	}

	/**
	 * Creates an {@link InputSource} from the path specified
	 * 
	 * @param inputPath
	 * @return an {@link InputSource}
	 */
	public static InputSource from(final Path inputPath) {
		Argument.notNull(inputPath, "inputPath");
		
		LinkedList<String> lines;
		try {
			lines = new LinkedList<>(Files.readAllLines(inputPath));
		} catch (IOException e) {
			LOG.error("Error reading contents from the file.", e);
			lines = new LinkedList<>();
		}
		
		return new FileInputSource(lines);
	}
	
	@Override
	public String poll() {
		return this.lines.poll();
	}

	@Override
	public Stream<Command> getCommandStream() {
		final CommandFactory factory = CommandFactory.instance();
		
		LOG.debug("Generating command stream.");
		
		return this.lines.stream().map(command -> factory.create(command));
	}
}
