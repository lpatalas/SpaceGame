package com.lpatalas.spacegame;

import com.badlogic.gdx.Game;

/**
 * User: Lukasz
 * Date: 05.08.12
 */
class SpaceGame extends Game {
	private GameplayScreen gameplayScreen = new GameplayScreen();

	@Override
    public void create() {
		setScreen(gameplayScreen);
    }

	@Override
    public void render() {
		super.render();
    }

	@Override
	public void dispose() {
		super.dispose();
	}
}
