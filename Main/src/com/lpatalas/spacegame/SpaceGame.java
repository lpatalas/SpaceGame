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
		setScreen(new MainMenuScreen(this));
    }
}
