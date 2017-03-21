package com.reagroup.exercises.toyrobot.position;

import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Represents a rectangular area bounded by a start and end position
 * {@link Coordinates}.
 * 
 * @author Sabya
 */
public class Surface {

	private final Coordinates start;
	
	private final Coordinates end;

	public static final Surface REA_ROBOT_TABLE_TOP = 
			new Surface(Coordinates.of(0, 0), Coordinates.of(5, 5));
			
	/**
	 * @param start
	 * @param end
	 */
	private Surface(final Coordinates start, final Coordinates end) {
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Creates an instance of {@link Surface} using the start and end {@link Coordinates}.

	 * @param start
	 * @param end
	 * @throws IllegalArgumentException if the <code>end</code> is <strong>NOT</strong> greater or equal to the <code>start</code>.
	 * @return an instance of {@link Surface}
	 */
	public static final Surface create(final Coordinates start, final Coordinates end) {
		Argument.notNull(start, "start position");
		Argument.notNull(end, "end position");
		if(!end.isGreaterThanOrEqualTo(start)) {
			throw new IllegalArgumentException("End co-ordinates should be greater or equal to the start.");
		}
		
		return new Surface(start, end);
	}

	
	/**
	 * @return the start
	 */
	public Coordinates getStart() {
		return Coordinates.copyOf(this.start);
	}

	/**
	 * @return the end
	 */
	public Coordinates getEnd() {
		return Coordinates.copyOf(this.end);
	}
}
