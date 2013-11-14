package com.chrisseto.quicksilver.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;
import org.andengine.util.adt.align.HorizontalAlign;

import android.hardware.Sensor;

import com.chrisseto.quicksilver.managment.PowerUpManger;
import com.chrisseto.quicksilver.managment.SceneManager;
import com.chrisseto.quicksilver.managment.SceneManager.SceneType;
import com.chrisseto.quicksilver.object.Player;
import com.chrisseto.quicksilver.util.Assets;

public class GameScene extends com.chrisseto.quicksilver.base.BaseScene
		implements IOnSceneTouchListener {
	private int score = 0;
	private double mult = 1;

	private HUD gameHUD;
	private Text scoreText;

	private Player player;
	private PowerUpManger powerUpManager;

	private Text gameOverText;
	private boolean gameOverDisplayed = false;

	@Override
	public void createScene() {
		createBackground();
		createHUD();
		createGameOverText();

		player = new Player(vbom, camera);

		Assets.getInstance().sensorManager.registerListener(player, Assets
				.getInstance().sensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), Assets
				.getInstance().sensorManager.SENSOR_DELAY_GAME);
		this.attachChild(player);
		powerUpManager = new PowerUpManger(this, vbom, camera, player);
		setOnSceneTouchListener(this);
	}

	@Override
	public void onBackKeyPressed() {
		SceneManager.getInstance().loadMenuScene(engine);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene() {
		camera.setHUD(null);
		camera.setChaseEntity(null); // TODO
		camera.setCenter(400, 240);

		// TODO code responsible for disposing scene
		// removing all game scene objects.
	}

	private void createGameOverText() {
		gameOverText = new Text(0, 0, resourcesManager.font, "Game Over!", vbom);
	}

	private void displayGameOverText() {
		camera.setChaseEntity(null);
		gameOverText.setPosition(camera.getCenterX(), camera.getCenterY());
		attachChild(gameOverText);
		gameOverDisplayed = true;
	}

	private void createHUD() {
		gameHUD = new HUD();

		scoreText = new Text(20, 420, resourcesManager.font,
				"Score: 0123456789", new TextOptions(HorizontalAlign.LEFT),
				vbom);
		scoreText.setAnchorCenter(0, 0);
		scoreText.setText("Score: 0");
		gameHUD.attachChild(scoreText);

		camera.setHUD(gameHUD);
	}

	private void createBackground() {
		
		attachChild(new Sprite(400, 240, resourcesManager.game_background, vbom)
		{
    		@Override
            protected void preDraw(GLState pGLState, Camera pCamera) 
    		{
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
		});
	}

	public void addToScore(boolean mul) {
		if (mul)
			mult += 1;
		else
			mult = 1;
		score += mult;
		scoreText.setText("Score: " + score);
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		return false;
	}

}