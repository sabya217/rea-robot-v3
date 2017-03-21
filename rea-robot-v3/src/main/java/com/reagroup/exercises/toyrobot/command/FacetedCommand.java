package com.reagroup.exercises.toyrobot.command;

import com.reagroup.exercises.toyrobot.facet.Facet;

/**
 * Represents the type of {@link Command}s that require additional fields 
 * along with the original command switch. These additional fields are matched
 * by instances of {@link Facet} encapsulated within.
 * 
 * @author Sabya
 *
 * @param <T>
 *            the type of the facet or the additional parameter of the command
 */
public interface FacetedCommand<T> extends Command {

	T getFacetValue();
}
