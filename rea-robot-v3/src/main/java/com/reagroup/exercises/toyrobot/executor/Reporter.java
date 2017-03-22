package com.reagroup.exercises.toyrobot.executor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reagroup.exercises.toyrobot.executor.capturer.Capturer;
import com.reagroup.exercises.toyrobot.position.Surface;
import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Reports the Robot position. By default, this prints output to the stdout.
 * Additionally it can stream the output to different {@link Capturer}s.
 * 
 * @author Sabya
 *
 */
public class Reporter {

	private static final Logger LOG = LoggerFactory.getLogger(Reporter.class);
	
	private List<Capturer<?>> capturers = new ArrayList<>();
	
	private boolean minimal = true;
	
	private Reporter() {
		
	}
	
	private static final Reporter instance = new Reporter();
	
	/**
	 * Returns the singleton instance
	 */
	public static Reporter instance() {
		return instance;
	}
	
	/**
	 * Adds a capturer to the reporter.
	 * 
	 * @param capturer
	 */
	public void setCapturer(final Capturer<?> capturer) {
		Argument.notNull(capturer, "capturer");
		
		this.capturers.add(capturer);
	}
	
	/**
	 * Reports the position of the robot and additionally updates the capturers.
	 * 
	 * @param mutablePosition
	 * @param surface
	 */
	public void report(final MutablePosition mutablePosition, final Surface surface) {
		Argument.notNull(mutablePosition, "mutable position");
		Argument.notNull(surface, "surface");
		
		if(!mutablePosition.isPresent()) {
			LOG.warn("Robot is not placed yet.");
			return;
		}
		
		final String reportString = 
				this.minimal ? 
						mutablePosition.getActual().printMinimal() : mutablePosition.getActual().print();	
		
		doSimpleReport(reportString);
		notifyCapturers(reportString);
	}

	/**
	 * Notifies the capturers with the report string.
	 * 
	 * @param reportString
	 */
	private void notifyCapturers(final String reportString) {
		for(Capturer<?> capturer : this.capturers) {
			capturer.capture(reportString);
		}
	}

	/**
	 * Writes the report string to the std out.
	 * 
	 * @param reportString
	 */
	private void doSimpleReport(final String reportString) {
		System.out.println(reportString);
	}
	
	/**
	 * Finishes any finalization tasks like closing the capturers. 
	 */
	public void finish() {
		this.capturers.forEach(capturer -> capturer.close());
	}

	/**
	 * @param minimal the minimal to set
	 */
	public void setMinimal(boolean minimal) {
		this.minimal = minimal;
	}
}
