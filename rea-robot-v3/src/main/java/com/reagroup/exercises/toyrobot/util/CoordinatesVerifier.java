package com.reagroup.exercises.toyrobot.util;

import com.reagroup.exercises.toyrobot.position.Coordinates;
import com.reagroup.exercises.toyrobot.position.Surface;

/**
 * Utility class to verify coordinates within a surface area.
 * 
 * @author Sabya
 *
 */
public abstract class CoordinatesVerifier {

	private CoordinatesVerifier() {
		// Instantiation is disabled
	}
	
	/**
	 * Checks whether the given co-ordinate is within the surface area or not.
	 * 
	 * @param coordinates
	 * @param surface
	 * @return true if the co-ordinate is within the surface area, false otherwise 
	 */
	public static boolean isWithin(final Coordinates coordinates, final Surface surface) {
		Argument.notNull(coordinates, "co-ordinates");
		Argument.notNull(surface, "surface");
		
		return 
				coordinates.isGreaterThanOrEqualTo(surface.getStart())
				&&
				surface.getEnd().isGreaterThanOrEqualTo(coordinates);
	}
}
