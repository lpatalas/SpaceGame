package com.lpatalas.spacegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * User: Lukasz
 * Date: 05.08.12
 */
class SpaceGame extends Game {
	@Override
    public void create() {
		//setScreen(new MainMenuScreen(this));
		HighScores scores = new HighScores();

		for (int i = 0; i < 50; i++) {
			scores.addScore("User " + i, i * 7.13);
		}

		setScreen(new HighScoresScreen(scores));
    }
}
