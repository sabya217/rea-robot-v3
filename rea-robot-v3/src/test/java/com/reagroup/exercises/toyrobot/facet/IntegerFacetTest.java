package com.reagroup.exercises.toyrobot.facet;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import org.junit.Test;

import com.reagroup.exercises.toyrobot.input.MutableString;

public class IntegerFacetTest {

	@Test
	public void integerTestPass() {
		IntegerFacet integerFacet = IntegerFacet.instance();
		assertNotNull(integerFacet);
		
		List<String> inputs = Arrays.asList("123", "12 rea", "  321", " 23 dsds");
		Queue<Integer> expected = new LinkedList<>(Arrays.asList(123, 12, 321, 23));
		inputs.forEach(inputString -> testInteger_pass(inputString, integerFacet, expected.poll()));
	}

	@Test
	public void integerTestFail() {
		IntegerFacet integerFacet = IntegerFacet.instance();
		assertNotNull(integerFacet);
		
		List<String> inputs = Arrays.asList("a123", "rea 12", "  ", " dsds");
		inputs.forEach(inputString -> testInteger_fail(inputString, integerFacet));
	}
	
	private void testInteger_pass(String inputString, IntegerFacet integerFacet, Integer expected) {
		MutableString input = MutableString.of(inputString);
		String currentValue = input.getValue();
		assertNotNull(currentValue);
		
		Optional<Integer> matched = integerFacet.matchValue(input);
		assertTrue(matched.isPresent());
		assertEquals(matched.get(), expected);
		
		assertNotEquals(currentValue, input.getValue());
	}
	
	private void testInteger_fail(String inputString, IntegerFacet integerFacet) {
		MutableString input = MutableString.of(inputString);
		String currentValue = input.getValue();
		assertNotNull(currentValue);
		
		Optional<Integer> matched = integerFacet.matchValue(input);
		assertFalse(matched.isPresent());
		
		assertEquals(currentValue, input.getValue());
	}
}
