package com.reagroup.exercises.toyrobot.executor.capturer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * A capturer of report lines that stores data in an external file.
 * 
 * @author Sabya
 *
 */
public class OutputFileCapturer implements Capturer<String> {

	private static final Logger LOG = LoggerFactory.getLogger(OutputFileCapturer.class);
	
	private PrintWriter writer;
	
	private String fileName;
	
	/**
	 * @param writer
	 * @param fileName
	 */
	private OutputFileCapturer(PrintWriter writer, String fileName) {
		this.writer = writer;
		this.fileName = fileName;
	}
	
	/**
	 * Creates the output file capturer.
	 * 
	 * @param fileName
	 */
	public static Capturer<String> create(final Path outputPath) {
		Argument.notNull(outputPath, "outputPath");
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(outputPath.toFile());
		} catch (FileNotFoundException e) {
			LOG.error("Error creating the output file \"{}\"", outputPath);
		}
		
		return new OutputFileCapturer(writer, outputPath.getFileName().toString());
	}

	@Override
	public void init() {
		//
	}

	@Override
	public void capture(String line) {
		if(this.writer != null) {
			this.writer.println(line);
		}
	}

	@Override
	public void close() {
		if(this.writer != null) {
			this.writer.close();
		}
	}

	@Override
	public String get() {
		return this.fileName;
	}
}
