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
import com.reagroup.exercises.toyrobot.position.Direction;

public class DirectionFacetTest {

	@Test
	public void directionTestPass() {
		DirectionFacet directionFacet = DirectionFacet.instance();
		assertNotNull(directionFacet);
		
		List<String> inputs = Arrays.asList("NORTH", "south rea", "  East", " WESt dsds");
		Queue<Direction> expected = new LinkedList<>(Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
		inputs.forEach(inputString -> testDirection_pass(inputString, directionFacet, expected.poll()));
	}

	@Test
	public void directionTestFail() {
		DirectionFacet directionFacet = DirectionFacet.instance();
		assertNotNull(directionFacet);
		
		List<String> inputs = Arrays.asList("aNORTH", "rea South", "  ", " eats");
		inputs.forEach(inputString -> testDirection_fail(inputString, directionFacet));
	}
	
	private void testDirection_pass(String inputString, DirectionFacet directionFacet, Direction direction) {
		MutableString input = MutableString.of(inputString);
		String currentValue = input.getValue();
		assertNotNull(currentValue);
		
		Optional<Direction> matched = directionFacet.matchValue(input);
		assertTrue(matched.isPresent());
		assertEquals(matched.get(), direction);
		
		assertNotEquals(currentValue, input.getValue());
	}
	
	private void testDirection_fail(String inputString, DirectionFacet directionFacet) {
		MutableString input = MutableString.of(inputString);
		String currentValue = input.getValue();
		assertNotNull(currentValue);
		
		Optional<Direction> matched = directionFacet.matchValue(input);
		assertFalse(matched.isPresent());
		
		assertEquals(currentValue, input.getValue());
	}
}
