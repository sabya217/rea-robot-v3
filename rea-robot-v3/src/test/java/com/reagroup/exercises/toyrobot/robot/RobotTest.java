package com.reagroup.exercises.toyrobot.robot;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.reagroup.exercises.toyrobot.command.Command;
import com.reagroup.exercises.toyrobot.command.PlaceCommand;
import com.reagroup.exercises.toyrobot.command.SimpleCommand;
import com.reagroup.exercises.toyrobot.executor.Reporter;
import com.reagroup.exercises.toyrobot.executor.capturer.CollectionCapturer;
import com.reagroup.exercises.toyrobot.position.Coordinates;
import com.reagroup.exercises.toyrobot.position.Direction;
import com.reagroup.exercises.toyrobot.position.Position;
import com.reagroup.exercises.toyrobot.position.Surface;

public class RobotTest {
	
	private IRobot robot;
	
	private Surface surface;
	
	private CollectionCapturer capturer;
	
	@Before
	public void setup(){
		this.robot = Robot.create();
		this.surface = Surface.create(Coordinates.of(0, 0), Coordinates.of(5, 5));
		this.capturer = new CollectionCapturer();
		Reporter.instance().setCapturer(this.capturer);
	}
	
	@Test
	public void testRobotSimple() {
		final List<Command> commands = 
				Arrays.asList(
						PlaceCommand.from("PLACE 0,1,SOUTH").get(),
						SimpleCommand.MOVE,
						SimpleCommand.REPORT);
		
		Position expected = Position.from(0, 2, Direction.SOUTH); 
		commands.forEach(command ->robot.execute(command, surface));
		List<String> output = new ArrayList<>(this.capturer.get());
		assertEquals(Arrays.asList(expected.printMinimal()), output);
	}
	
	@Test
	public void testRobotMultipleMoveDirection() {
		final List<Command> commands = 
				Arrays.asList(
						PlaceCommand.from("PLACE 0,1, SOUTH").get(),
						SimpleCommand.MOVE,
						SimpleCommand.LEFT,
						SimpleCommand.LEFT,
						SimpleCommand.MOVE,
						SimpleCommand.MOVE,
						SimpleCommand.RIGHT,
						SimpleCommand.REPORT);
		
		Position expected = Position.from(0, 0, Direction.EAST); 
		commands.forEach(command ->robot.execute(command, surface));
		List<String> output = new ArrayList<>(this.capturer.get());
		assertEquals(Arrays.asList(expected.printMinimal()), output);
	}
	
	@Test
	public void testRobotMultipleReport() {
		final List<Command> commands = 
				Arrays.asList(
						PlaceCommand.from("PLACE 0,1 ,SOUTH").get(),
						SimpleCommand.MOVE,
						SimpleCommand.LEFT,
						SimpleCommand.REPORT,
						SimpleCommand.MOVE,
						SimpleCommand.RIGHT,
						SimpleCommand.MOVE,
						SimpleCommand.RIGHT,
						SimpleCommand.REPORT);
		
		Position expected = Position.from(0, 2, Direction.EAST);
		Position expected1 = Position.from(1, 3, Direction.WEST); 
		commands.forEach(command ->robot.execute(command, surface));
		List<String> output = new ArrayList<>(this.capturer.get());
		assertEquals(Arrays.asList(expected.printMinimal(), expected1.printMinimal()), output);
	}
	
	@Test
	public void testRobotNoReport() {
		final List<Command> commands = 
				Arrays.asList(
						PlaceCommand.from("PLACE 0,1 , SOUTH").get(),
						SimpleCommand.MOVE,
						SimpleCommand.LEFT,
						/*SimpleCommand.REPORT,*/
						SimpleCommand.MOVE,
						SimpleCommand.RIGHT,
						SimpleCommand.MOVE,
						SimpleCommand.RIGHT
						/*SimpleCommand.REPORT*/);
		
		commands.forEach(command ->robot.execute(command, surface));
		assertTrue(this.capturer.get().isEmpty());
	}
	
	@Test
	public void testRobotNoPlace() {
		final List<Command> commands = 
				Arrays.asList(
						/*PlaceCommand.from("PLACE 0,1, SOUTH").get(),*/
						SimpleCommand.MOVE,
						SimpleCommand.LEFT,
						SimpleCommand.REPORT,
						SimpleCommand.MOVE,
						SimpleCommand.RIGHT,
						SimpleCommand.MOVE,
						SimpleCommand.RIGHT,
						SimpleCommand.REPORT);
		
		commands.forEach(command ->robot.execute(command, surface));
		assertTrue(this.capturer.get().isEmpty());
	}
	
	@Test
	public void testRobotWrongPlace() {
		final List<Command> commands = 
				Arrays.asList(
						PlaceCommand.from("PLACE 6,1 , SOUTH").get(),
						SimpleCommand.REPORT);
		
		commands.forEach(command ->robot.execute(command, surface));
		assertTrue(this.capturer.get().isEmpty());
	}
	
	@Test
	public void testRobotWrongPlace1() {
		final List<Command> commands = 
				Arrays.asList(
						PlaceCommand.from("PLACE 0,1,SOUTH").get(),
						SimpleCommand.REPORT,
						PlaceCommand.from("PLACE 6,1 ,SOUTH").get(),
						SimpleCommand.REPORT);
		
		Position expected = Position.from(0, 1, Direction.SOUTH); 
		commands.forEach(command ->robot.execute(command, surface));
		List<String> output = new ArrayList<>(this.capturer.get());
		assertEquals(Arrays.asList(expected.printMinimal(), expected.printMinimal()), output);
	}
	
	@Test
	public void testRobotWrongPlace2() {
		final List<Command> commands = 
				Arrays.asList(
						PlaceCommand.from("PLACE 6,1,EAST").get(),
						SimpleCommand.REPORT,
						PlaceCommand.from("PLACE 0,1,EAST").get(),
						SimpleCommand.REPORT);
		
		Position expected = Position.from(0, 1, Direction.EAST); 
		commands.forEach(command ->robot.execute(command, surface));
		List<String> output = new ArrayList<>(this.capturer.get());
		assertEquals(Arrays.asList(expected.printMinimal()), output);
	}
}
