package com.chrisseto.tilttolive.scene;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

import android.hardware.Sensor;

import com.chrisseto.tilttolive.managment.EnemyManger;
import com.chrisseto.tilttolive.managment.PowerUpManger;
import com.chrisseto.tilttolive.managment.SceneManager;
import com.chrisseto.tilttolive.managment.SceneManager.SceneType;
import com.chrisseto.tilttolive.object.ExplosionPowerUp;
import com.chrisseto.tilttolive.object.Player;
import com.chrisseto.tilttolive.util.Assets;


public class GameScene extends com.chrisseto.tilttolive.base.BaseScene implements IOnSceneTouchListener
{
	private int score = 0;
	
	private HUD gameHUD;
	private Text scoreText;
	
	private Player player;
	private EnemyManger enemyManger;
	private PowerUpManger powerUpManager;
	
	private Text gameOverText;
	private boolean gameOverDisplayed = false;
	
	@Override
	public void createScene()
	{
		createBackground();
		createHUD();
		loadLevel(1);
		createGameOverText();
		
		//levelCompleteWindow = new LevelCompleteWindow(vbom);
		player = new Player(vbom,camera);
		
		
		Assets.getInstance().sensorManager.registerListener(player, Assets.getInstance().sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),Assets.getInstance().sensorManager.SENSOR_DELAY_GAME);
		this.attachChild(player);
		enemyManger = new EnemyManger(this, vbom, camera, player);
		powerUpManager = new PowerUpManger(this, vbom, camera, player);
		this.attachChild(new ExplosionPowerUp(30, 40, 20, Assets.getInstance().explosion_pu, vbom, camera,this));
		setOnSceneTouchListener(this); 
	}

	@Override
	public void onBackKeyPressed()
	{
		SceneManager.getInstance().loadMenuScene(engine);
	}

	@Override
	public SceneType getSceneType()
	{
		return SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene()
	{
		camera.setHUD(null);
		camera.setChaseEntity(null); //TODO
		camera.setCenter(400, 240);
		
		// TODO code responsible for disposing scene
		// removing all game scene objects.
	}
	
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent)
	{
		//TODO Put somthing in here
		return false;
	}
	
	private void loadLevel(int levelID)
	{
		//Might need something here in the future
		//Difficulty? eh
	}
	
	private void createGameOverText()
	{
		gameOverText = new Text(0, 0, resourcesManager.font, "Game Over!", vbom);
	}
	
	private void displayGameOverText()
	{
		camera.setChaseEntity(null);
		gameOverText.setPosition(camera.getCenterX(), camera.getCenterY());
		attachChild(gameOverText);
		gameOverDisplayed = true;
	}
	
	private void createHUD()
	{
		gameHUD = new HUD();
		
		scoreText = new Text(20, 420, resourcesManager.font, "Score: 0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
		scoreText.setAnchorCenter(0, 0);	
		scoreText.setText("Score: 0");
		gameHUD.attachChild(scoreText);
		
		camera.setHUD(gameHUD);
	}
	
	private void createBackground()
	{
		setBackground(new Background(Color.GREEN));
	}
	
	private void addToScore(int i)
	{
		score += i;
		scoreText.setText("Score: " + score);
	}
	
}