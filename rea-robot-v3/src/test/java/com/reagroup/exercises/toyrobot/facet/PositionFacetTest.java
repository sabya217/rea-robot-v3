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
import com.reagroup.exercises.toyrobot.position.Direction;
import com.reagroup.exercises.toyrobot.position.Position;

public class PositionFacetTest {

	@Test
	public void directionTestPass() {
		PositionFacet positionFacet = PositionFacet.instance();
		assertNotNull(positionFacet);
		
		List<String> inputs = Arrays.asList("1,2 NORTH", "1,3SOUTH", "1, 2 east", " 1,3weST", 
									" 4, 3 WESt", " 0, 3 WEST rea", " 6,3 EASTrea");
		Queue<Position> expected = 
				new LinkedList<>(
						Arrays.asList(
								Position.from(Coordinates.of(1, 2), Direction.NORTH), Position.from(1, 3, Direction.SOUTH), 
								Position.from(Coordinates.of(1, 2), Direction.EAST), Position.from(1, 3, Direction.WEST), 
								Position.from(Coordinates.of(4, 3), Direction.WEST), Position.from(Coordinates.of(0, 3), Direction.WEST),
								Position.from(6, 3, Direction.EAST)
								));
		inputs.forEach(inputString -> testCoordinates_pass(inputString, positionFacet, expected.poll()));
	}

	@Test
	public void integerTestFail() {
		PositionFacet positionFacet = PositionFacet.instance();
		assertNotNull(positionFacet);
		
		List<String> inputs = Arrays.asList("w,1 NORTH", "1,r2 SOUTH", "1,22 SOU", "1,  4 SOU TH", "1, 4, EAST", "0,4", "  ");
		inputs.forEach(inputString -> testPosition_fail(inputString, positionFacet));
	}
	
	private void testCoordinates_pass(String inputString, PositionFacet positionFacet, Position position) {
		MutableString input = MutableString.of(inputString);
		String currentValue = input.getValue();
		assertNotNull(currentValue);
		
		Optional<Position> matched = positionFacet.matchValue(input);
		assertTrue(matched.isPresent());
		assertEquals(matched.get(), position);
		
		assertNotEquals(currentValue, input.getValue());
	}
	
	private void testPosition_fail(String inputString, PositionFacet positionFacet) {
		MutableString input = MutableString.of(inputString);
		String currentValue = input.getValue();
		assertNotNull(currentValue);
		
		Optional<Position> matched = positionFacet.matchValue(input);
		assertFalse(matched.isPresent());
		
		assertEquals(currentValue, input.getValue());
	}
}
