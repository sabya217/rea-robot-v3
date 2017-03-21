package com.reagroup.exercises.toyrobot.facet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import org.junit.Test;

import com.reagroup.exercises.toyrobot.input.MutableString;
import com.reagroup.exercises.toyrobot.position.Coordinates;

public class CoordinatesFacetTest {

	@Test
	public void directionTestPass() {
		CoordinatesFacet coordinatesFacet = CoordinatesFacet.instance();
		assertNotNull(coordinatesFacet);
		
		List<String> inputs = Arrays.asList("1,2", "1, 3", "1,3 rea", "0, 3 rea", " 6,3", "  6, 5", " 4, 6 dsds");
		Queue<Coordinates> expected = 
				new LinkedList<>(
						Arrays.asList(
								Coordinates.of(1, 2), Coordinates.of(1, 3), 
								Coordinates.of(1, 3), Coordinates.of(0, 3), 
								Coordinates.of(6, 3), Coordinates.of(6, 5),
								Coordinates.of(4, 6)));
		inputs.forEach(inputString -> testCoordinates_pass(inputString, coordinatesFacet, expected.poll()));
	}

	@Test
	public void integerTestFail() {
		CoordinatesFacet coordinatesFacet = CoordinatesFacet.instance();
		assertNotNull(coordinatesFacet);
		
		List<String> inputs = Arrays.asList("dfd", "1,r2", "1, ", "r3,4", " ,4", "sdsd 1,2", "  ");
		inputs.forEach(inputString -> testCordinates_fail(inputString, coordinatesFacet));
	}
	
	private void testCoordinates_pass(String inputString, CoordinatesFacet coordinatesFacet, Coordinates coordinates) {
		MutableString input = MutableString.of(inputString);
		String currentValue = input.getValue();
		assertNotNull(currentValue);
		
		Optional<Coordinates> matched = coordinatesFacet.matchValue(input);
		assertTrue(matched.isPresent());
		assertEquals(matched.get(), coordinates);
		
		assertNotEquals(currentValue, input.getValue());
	}
	
	private void testCordinates_fail(String inputString, CoordinatesFacet coordinatesFacet) {
		MutableString input = MutableString.of(inputString);
		String currentValue = input.getValue();
		assertNotNull(currentValue);
		
		Optional<Coordinates> matched = coordinatesFacet.matchValue(input);
		assertFalse(matched.isPresent());
		
		assertEquals(currentValue, input.getValue());
	}
}
