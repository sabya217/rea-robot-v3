package com.reagroup.exercises.toyrobot.position;

import com.reagroup.exercises.toyrobot.util.Argument;

/**
 * Represents the co-ordinates of a location.
 *  
 * @author Sabya
 *
 */
public class Coordinates {

	private int X;
	
	private int Y;
	
	/**
	 * @param x
	 * @param y
	 */
	private Coordinates(int x, int y) {
		this.X = x;
		this.Y = y;
	}
	
	/**
	 * Creates an instance of {@link Coordinates} using the X and Y values.
	 * 
	 * @param x
	 * @param y
	 * @return an instance of {@link Coordinates}
	 */
	public static Coordinates of(final int x, final int y) {
		return 
				new Coordinates(
						Argument.zeroOrPositive(x, "X cordinate"), 
						Argument.zeroOrPositive(y, "Y cordinate"));
	}
	
	/**
	 * Creates an instance of {@link Coordinates} using the X and Y values.
	 * 
	 * @param x
	 * @param y
	 * @return an instance of {@link Coordinates}
	 */
	public static Coordinates copyOf(final Coordinates coordinates) {
		Argument.notNull(coordinates, "coordinates");
		
		return 
				new Coordinates(
						coordinates.X, coordinates.Y);
	}
	
	/**
	 * Checks if the X and Y co-ordinates of this {@link Coordinates} is greater
	 * or equal to the given {@link Coordinates}s.
	 * 
	 * <p>
	 * <strong>Note - </strong> This is not the same as a {@link Comparable#compareTo(Object)}
	 * 
	 * @param another
	 * @return true if the X and Y co-ordinates are greater or equal to the one
	 *         being compare against, false otherwise
	 */
	public boolean isGreaterThanOrEqualTo(final Coordinates another) {
		Argument.notNull(another, "position");
		
		return this.X >= another.X && this.Y >= another.Y;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return X;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return Y;
	}

	/**
	 * Generates a printable output.
	 * 
	 * @return the String representation.
	 */
	public String print() {
		return 
				new StringBuilder()
					.append("Coordinates: ")
					.append("X: ").append(this.X)
					.append("  Y: ").append(this.Y)
					.toString();
	}
	
	/**
	 * Generates a minimal printable output.
	 * 
	 * @return the String representation.
	 */
	public String printMinimal() {
		return this.X + "," + this.Y;
	}
	
	@Override
	public String toString() {
		return printMinimal();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coordinates) {
			Coordinates other = (Coordinates) obj;
			return other.X == this.X && other.Y == this.Y;
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 31;
		result = result * 37 + this.X;
		result = result * 37 + this.Y;
		return result;
	}
}
