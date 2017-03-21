package com.reagroup.exercises.toyrobot.command;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

import com.reagroup.exercises.toyrobot.position.Coordinates;
import com.reagroup.exercises.toyrobot.position.Direction;
import com.reagroup.exercises.toyrobot.position.Position;

public class CommandFactoryTest {

	@Test
	public void passTestSimple() {
		CommandFactory factory = CommandFactory.instance();
		assertNotNull(factory);
		
		List<String> inputs = Arrays.asList(
				"MOVE", "LEFT ", "    RIGHT", "  REPORT  ");
		Queue<SimpleCommand> expected = new LinkedList<>(
				Arrays.asList(
						SimpleCommand.MOVE, SimpleCommand.LEFT, 
						SimpleCommand.RIGHT, SimpleCommand.REPORT));
		inputs.forEach(inputString -> testSimpleCommand(factory, inputString, expected.poll()));
	}

	@Test
	public void passTestFaceted() {
		CommandFactory factory = CommandFactory.instance();
		assertNotNull(factory);
		
		List<String> inputs = Arrays.asList("PLACE 1,2 NORTH", "place 1,3SOUTH", "  PLAcE 1, 2 east", "PLACE        1,3weST", 
				" PLACE  4, 3 WESt", " PLACE 0 , 3 WEST", " PLACE 6 ,3 EAST");
		Queue<Position> expected = 
			new LinkedList<>(
				Arrays.asList(
						Position.from(Coordinates.of(1, 2), Direction.NORTH), Position.from(1, 3, Direction.SOUTH), 
						Position.from(Coordinates.of(1, 2), Direction.EAST), Position.from(1, 3, Direction.WEST), 
						Position.from(Coordinates.of(4, 3), Direction.WEST), Position.from(Coordinates.of(0, 3), Direction.WEST),
						Position.from(6, 3, Direction.EAST)
						));
		inputs.forEach(inputString -> testFacetedCommand(factory, inputString, expected.poll()));
	}
	
	@Test
	public void failTest() {
		CommandFactory factory = CommandFactory.instance();
		assertNotNull(factory);
		
		List<String> inputs = Arrays.asList(
				"MOV", "LEF T ", "    RIHGT", "  RE PORT  ", "MOVES", " LEFT P", " Y RIGHT",
				"PLACE 4 3 EAST", "PLACE 4,5 WES", "PLAC 3,2 EAST", "PLACE p,5 NORTH", "PLACE 0,0 SOUTH sads", " ", "");
		inputs.forEach(inputString -> testFailCommand(factory, inputString));
	}
	
	private void testFailCommand(CommandFactory factory, String inputString) {
		Command command = factory.create(inputString);
		assertNotNull(command);
		assertEquals(NoOpCommand.instance(), command);
	}

	private void testSimpleCommand(CommandFactory factory, String inputString, SimpleCommand expected) {
		Command command = factory.create(inputString);
		assertNotNull(command);
		assertEquals(expected, command);
	}

	private Object testFacetedCommand(CommandFactory factory, String inputString, Position expected) {
		Command command = factory.create(inputString);
		assertNotNull(command);
		assertTrue(command instanceof FacetedCommand);
		assertEquals(expected, ((FacetedCommand<?>)command).getFacetValue());
		
		return null;
	}
}
