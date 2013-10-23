package com.chrisseto.tilttolive.scene;

import java.io.IOException;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.SAXUtils;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;
import org.andengine.util.level.EntityLoader;
import org.andengine.util.level.constants.LevelConstants;
import org.andengine.util.level.simple.SimpleLevelEntityLoaderData;
import org.andengine.util.level.simple.SimpleLevelLoader;
import org.xml.sax.Attributes;

import android.hardware.Sensor;

import com.chrisseto.tilttolive.managment.SceneManager;
import com.chrisseto.tilttolive.managment.SceneManager.SceneType;
import com.chrisseto.tilttolive.object.Player;
import com.chrisseto.tilttolive.util.Assets;


public class GameScene extends com.chrisseto.tilttolive.base.BaseScene implements IOnSceneTouchListener
{
	private int score = 0;
	
	private HUD gameHUD;
	private Text scoreText;
	
	private static final String TAG_ENTITY = "entity";
	private static final String TAG_ENTITY_ATTRIBUTE_X = "x";
	private static final String TAG_ENTITY_ATTRIBUTE_Y = "y";
	private static final String TAG_ENTITY_ATTRIBUTE_TYPE = "type";
	
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM1 = "platform1";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM2 = "platform2";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM3 = "platform3";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_COIN = "coin";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLAYER = "player";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_LEVEL_COMPLETE = "levelComplete";
	
	private Player player;
	
	private Text gameOverText;
	private boolean gameOverDisplayed = false;
	
	private boolean firstTouch = false;
	
	@Override
	public void createScene()
	{
		createBackground();
		createHUD();
		loadLevel(1);
		createGameOverText();
		
		//levelCompleteWindow = new LevelCompleteWindow(vbom);
		Assets.getInstance().sensorManager.registerListener(player, Assets.getInstance().sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),Assets.getInstance().sensorManager.SENSOR_DELAY_GAME);
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
		setBackground(new Background(Color.BLUE));
	}
	
	private void addToScore(int i)
	{
		score += i;
		scoreText.setText("Score: " + score);
	}
	
}