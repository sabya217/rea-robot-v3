package com.reagroup.exercises.toyrobot.executor.capturer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * A capturer of report lines that stores data in a {@link Collection}.
 * 
 * @author Sabya
 *
 */
public class CollectionCapturer implements Capturer<Collection<String>> {

	private final Collection<String> store = new ArrayList<>();
	
	@Override
	public void init() {
		//
	}

	@Override
	public void capture(String line) {
		this.store.add(line);
	}

	@Override
	public void close() {
		//
	}

	@Override
	public Collection<String> get() {
		return Collections.unmodifiableCollection(this.store);
	}

}
