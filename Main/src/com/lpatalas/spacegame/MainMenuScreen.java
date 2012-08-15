package com.lpatalas.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.badlogic.gdx.utils.Scaling;

/**
 * User: Lukasz
 * Date: 15.08.12
 */
public class MainMenuScreen implements Screen {
	private Texture backgroundTexture;
	private Texture buttonTexture;
	private BitmapFont font;
	private final SpaceGame game;
	private Stage stage;

	public MainMenuScreen(SpaceGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		backgroundTexture = new Texture(Gdx.files.internal("images/title_scene.png"));
		buttonTexture = new Texture(Gdx.files.internal("images/button2.png"));
		font = new BitmapFont(Gdx.files.internal("fonts/dodger60.fnt"), false);
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);

		Stack stack = new Stack();
		stack.setFillParent(true);
		stage.addActor(stack);

		Image background = new Image(backgroundTexture, Scaling.stretch);
		stack.addActor(background);

		Table buttonsTable = new Table();
		buttonsTable.setFillParent(true);
		stack.addActor(buttonsTable);

		Color color = new Color(0, 1, 0, 1);
		NinePatch patch = new NinePatch(buttonTexture, 1, 1, 1, 1);
		patch.setColor(new Color(1, 1, 1, 0.5f));
		TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle(patch, patch, patch, 0, 0, 0, 0, font, color, color, color);

		TextButton startButton = new TextButton("START", buttonStyle);
		startButton.setClickListener(new ClickListener() {
			@Override
			public void click(Actor actor, float x, float y) {
				startGame();
			}
		});

		TextButton exitButton = new TextButton("EXIT", buttonStyle);
		exitButton.setClickListener(new ClickListener() {
			@Override
			public void click(Actor actor, float x, float y) {
				exitGame();
			}
		});

		buttonsTable.defaults().center().width(350).height(100).pad(10);
		buttonsTable.add(startButton);
		buttonsTable.row();
		buttonsTable.add(exitButton);

		Gdx.input.setInputProcessor(stage);
	}

	private void startGame() {
		game.setScreen(new GameplayScreen(game));
	}

	private void exitGame() {
		Gdx.app.exit();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		backgroundTexture.dispose();
		buttonTexture.dispose();
		font.dispose();
	}
}
