package com.reagroup.exercises.toyrobot.position;

import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Represents the position of a Robot. This holds the value of the co-ordinates
 * of the robot and the direction of movement.
 */
public class Position {

	/** Current position of the robot */
	private Coordinates coordinates;
	
	/** Direction of the movement of the robot */
	private Direction direction;

	/**
	 * @param coordinates
	 * @param direction
	 */
	private Position(Coordinates coordinates, Direction direction) {
		this.coordinates = coordinates;
		this.direction = direction;
	}
	
	/**
	 * Creates an instance of {@link Position} from the {@link Coordinates} and
	 * the {@link Direction}.
	 * 
	 * @param coordinates
	 * @param direction
	 * @return an instance of {@link Position}
	 */
	public static Position from(final Coordinates coordinates, final Direction direction) {
		return new Position(Argument.notNull(coordinates, "coordinates"), Argument.notNull(direction, "direction")); 
	}
	
	/**
	 * Creates an instance of {@link Position} from the X and Y Coordinates and
	 * the {@link Direction}.
	 * 
	 * @param X
	 * @param Y
	 * @param direction
	 * @return an instance of {@link Position}
	 */
	public static Position from(final int X, final int Y, final Direction direction) {
		return new Position(Coordinates.of(X, Y), Argument.notNull(direction, "direction")); 
	}
	
	/**
	 * @return the coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Generates a printable output.
	 * 
	 * @return the String representation.
	 */
	public String print() {
		return 
				new StringBuilder()
					.append("Position: ")
					.append(this.coordinates.print())
					.append(" Direction: ").append(this.direction)
					.toString();
	}
	
	@Override
	public String toString() {
		return print();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Position) {
			Position other = (Position) obj;
			return this.coordinates.equals(other.coordinates) && this.direction == other.direction;
		}

		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 31;
		result = result * 37 + this.coordinates.hashCode();
		result = result * 37 + this.direction.ordinal();
		
		return result;
	}
 }
