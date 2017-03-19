package com.reagroup.exercises.toyrobot.input;

/**
 * Represents a single input entity.
 * <p>
 * If the input type is chosen as file, each line of the file will be a
 * potential {@link InputEntity}.
 * 
 * @author Sabya
 */
@FunctionalInterface
public interface InputEntity {

	String getCurrentValue();
}
