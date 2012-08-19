package com.lpatalas.spacegame;

/**
* User: Lukasz
* Date: 19.08.12
*/
class Score {
	private final String name;
	private final double timeInSeconds;

	public Score(String name, double timeInSeconds) {
		this.name = name;
		this.timeInSeconds = timeInSeconds;
	}

	public String getName() {
		return name;
	}

	public double getTimeInSeconds() {
		return timeInSeconds;
	}
}
