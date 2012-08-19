package com.lpatalas.spacegame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: Lukasz
 * Date: 19.08.12
 */
class HighScores implements Iterable<Score> {
	private final List<Score> scores = new ArrayList<Score>();

	public void addScore(String name, double timeInSeconds) {
		scores.add(new Score(name, timeInSeconds));
	}

	@Override
	public Iterator<Score> iterator() {
		return scores.iterator();
	}
}
