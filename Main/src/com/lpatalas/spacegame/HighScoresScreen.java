package com.lpatalas.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

/**
 * User: Lukasz
 * Date: 19.08.12
 */
class HighScoresScreen implements Screen, GestureDetector.GestureListener {
	private BitmapFont bigFont;
	private BitmapFont normalFont;
	private final HighScores highScores;
	private Stage stage;

	public HighScoresScreen(HighScores highScores) {
		this.highScores = highScores;
	}

	@Override
	public void show() {
		bigFont = new BitmapFont(Gdx.files.internal("fonts/dodger60.fnt"), false);
		normalFont = new BitmapFont(Gdx.files.internal("fonts/dodger.fnt"), false);
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

		Table scoresTable = new Table();
		scoresTable.setFillParent(true);
		scoresTable.columnDefaults(0).width(300);
		scoresTable.columnDefaults(1).width(100);
		scoresTable.pad(10);

		Label.LabelStyle evenRowLabelStyle = new Label.LabelStyle(normalFont, new Color(0, 1, 0, 1));
		Label.LabelStyle oddRowLabelStyle = new Label.LabelStyle(normalFont, new Color(0.0f, 0.7f, 0.0f, 1));

		boolean isEvenRow = true;
		for (Score score : highScores) {
			Label.LabelStyle labelStyle = isEvenRow ? evenRowLabelStyle : oddRowLabelStyle;
			Label nameLabel = new Label(score.getName(), labelStyle);
			Label timeLabel = new Label(formatTime(score.getTimeInSeconds()), labelStyle);
			timeLabel.setAlignment(Align.RIGHT);

			scoresTable.add(nameLabel);
			scoresTable.add(timeLabel);
			scoresTable.row();

			isEvenRow = !isEvenRow;
		}

		stage.addActor(scoresTable);
		scoresTable.align(Align.TOP);

		Gdx.input.setInputProcessor(new GestureDetector(this));
	}

	private String formatTime(double timeInSeconds) {
		double roundedTime = Math.floor(timeInSeconds * 10) / 10;
		return Double.toString(roundedTime);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void hide() {
		stage.dispose();
		bigFont.dispose();
		normalFont.dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean touchDown(int x, int y, int pointer) {
		return false;
	}

	@Override
	public boolean tap(int x, int y, int count) {
		return false;
	}

	@Override
	public boolean longPress(int x, int y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY) {
		return false;
	}

	@Override
	public boolean pan(int x, int y, int deltaX, int deltaY) {
		Camera camera = stage.getCamera();
		camera.translate(0, deltaY, 0);
		return true;
	}

	@Override
	public boolean zoom(float originalDistance, float currentDistance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer) {
		return false;
	}
}
