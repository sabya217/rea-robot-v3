package com.reagroup.exercises.toyrobot.executor.capturer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * A capturer of report lines that stores data in a an external file.
 * 
 * @author Sabya
 *
 */
public class OutputFileCapturer implements Capturer<String> {

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
	
	public static Capturer<String> create(final String fileName) {
		Argument.notNull(fileName, "fileName");
		
		PrintWriter pw;
		try {
			pw = new PrintWriter(fileName);
			return new OutputFileCapturer(pw, fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void capture(String line) {
		this.writer.println(line);

	}

	@Override
	public void close() {
		this.writer.close();
	}

	@Override
	public String get() {
		return this.fileName;
	}
}
