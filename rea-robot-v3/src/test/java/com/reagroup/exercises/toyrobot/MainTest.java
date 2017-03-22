package com.reagroup.exercises.toyrobot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.reagroup.exercises.toyrobot.position.Direction;
import com.reagroup.exercises.toyrobot.position.Position;

public class MainTest {

	private String outputFile = "src\\test\\resources\\output.txt";
	
	private String[] args = new String[] {"-input", "", "-output", outputFile};

	@After
	public void tearDown() throws IOException {
		Files.deleteIfExists(Paths.get(outputFile));
	}
	
	@Test
	public void testMainSimple() throws IOException {
		Position expected = Position.from(0, 2, Direction.SOUTH);
		
		args[1] = "src\\test\\resources\\input1.txt";
		
		Main.main(args);
		
		test(expected);
	}

	private void test() throws IOException {
		List<String> output = Files.readAllLines(Paths.get(outputFile));
		
		assertTrue(output.isEmpty());
	}
	
	private void test(Position expected) throws IOException {
		List<String> output = Files.readAllLines(Paths.get(outputFile));
		
		assertEquals(Arrays.asList(expected.printMinimal()), output);
	}
	
	private void test(Position expected, Position expected1) throws IOException {
		List<String> output = Files.readAllLines(Paths.get(outputFile));
		
		assertEquals(Arrays.asList(expected.printMinimal(), expected1.printMinimal()), output);
	}
	
	@Test
	public void testMainMultipleMoveDirection() throws IOException {
		Position expected = Position.from(0, 0, Direction.EAST);
		
		args[1] = "src\\test\\resources\\input2.txt";
		
		Main.main(args);
		
		test(expected);
	}
	
	@Test
	public void testMainMultipleReport() throws IOException {
		Position expected = Position.from(0, 2, Direction.EAST);
		Position expected1 = Position.from(1, 3, Direction.WEST); 

		args[1] = "src\\test\\resources\\input3.txt";
		
		Main.main(args);
		
		test(expected, expected1);
	}
	
	@Test
	public void testMainNoReport() throws IOException {
		args[1] = "src\\test\\resources\\input4.txt";
		
		Main.main(args);
		
		test();
	}
	
	@Test
	public void testMainNoPlace() throws IOException {
		args[1] = "src\\test\\resources\\input5.txt";
		
		Main.main(args);
		
		test();
	}
	
	@Test
	public void testMainWrongPlace() throws IOException {
		args[1] = "src\\test\\resources\\input6.txt";
		
		Main.main(args);
		
		test();
	}
	
	@Test
	public void testMainWrongPlace1() throws IOException {
		Position expected = Position.from(0, 1, Direction.SOUTH);
		
		args[1] = "src\\test\\resources\\input7.txt";
		
		Main.main(args);
		
		test(expected, expected);
	}
	
	@Test
	public void testMainWrongPlace2() throws IOException {
		Position expected = Position.from(0, 1, Direction.EAST); 
		
		args[1] = "src\\test\\resources\\input8.txt";
		
		Main.main(args);
		
		test(expected);
	}
}
