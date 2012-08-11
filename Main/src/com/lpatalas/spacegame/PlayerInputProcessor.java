package com.lpatalas.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

/**
 * User: Lukasz
 * Date: 11.08.12
 */
public class PlayerInputProcessor extends InputAdapter {
	private final SpaceGame game;
	private final Player player;

	public PlayerInputProcessor(SpaceGame game, Player player) {
		this.game = game;
		this.player = player;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (!game.isGameOver())
			return movePlayerToPosition(x, y);
		else {
			game.resetGame();
			return true;
		}
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!game.isGameOver())
			return movePlayerToPosition(x, y);

		return  super.touchDragged(x, y, pointer);
	}

	private boolean movePlayerToPosition(int x, int y) {
		Vector2 position = transformPosition(x, y);
		player.moveTo(position);
		return true;
	}

	private static Vector2 transformPosition(float x, float y) {
		return new Vector2(x, Gdx.graphics.getHeight() - y);
	}
}
