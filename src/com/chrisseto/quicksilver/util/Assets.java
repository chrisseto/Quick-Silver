package com.chrisseto.quicksilver.util;

//import android.graphics.Bitmap;
import java.util.Random;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.graphics.Color;
import android.hardware.SensorManager;

import com.chrisseto.quicksilver.MainGameActivity;

//This class is purely for resources and what not

public class Assets {
	
	public static final Assets INSTANCE = new Assets();
	
	public static float[] ACCELEROMETERCALI;
	
	public static double random(double lower, double upper)
	{
		return new Random().nextDouble() * upper + lower;
	}
	public enum Effect {
		Kill, Freeze, Shock, Statis
	}

	public enum PowerUpType {
		Blast, Spike, Shield;
		public static final int length = 3;
	}
	
	public Engine engine;
	public MainGameActivity activity;
	public BoundCamera camera;
	public VertexBufferObjectManager vbom;
	public SensorManager sensorManager;
	
	public Font font;
	
	//---------------------------------------------
	// TEXTURES & TEXTURE REGIONS
	//---------------------------------------------
	
	public ITextureRegion splash_region;
	public ITextureRegion menu_background_region;
	public ITiledTextureRegion buttons;
	public ITextureRegion play_menu;
	public ITextureRegion menu_select;
	public ITextureRegion option_menu;
	
	// Game Texture
	public BuildableBitmapTextureAtlas gameTextureAtlas;
	
	// Game Texture Regions
	public ITextureRegion ball_region;
	public ITextureRegion enemy_region;
	public ITextureRegion explosion_pu;
	public ITextureRegion spike_pu;
	public ITextureRegion shield_pu;
	public ITextureRegion shield;
	public ITextureRegion game_background;
	
	private BitmapTextureAtlas splashTextureAtlas;
	private BuildableBitmapTextureAtlas menuTextureAtlas;
	
	// Level Complete Window
	public ITextureRegion complete_window_region;
	public ITiledTextureRegion complete_stars_region;
	
	//---------------------------------------------
	// CLASS LOGIC
	//---------------------------------------------

	public void loadMenuResources()
	{
		loadMenuGraphics();
		loadMenuAudio();
		loadMenuFonts();
	}
	
	public void loadGameResources()
	{
		loadGameGraphics();
		loadGameFonts();
		loadGameAudio();
	}
	
	private void loadMenuGraphics()
	{
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
        menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 2024, TextureOptions.BILINEAR);
        menu_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "background.png");
        buttons = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(menuTextureAtlas, activity, "buttons.png",1,3);
        play_menu = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "Play Menu.png");
        menu_select = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_select.png");
        option_menu = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "Option Menu.png");
    	try 
    	{
			this.menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.menuTextureAtlas.load();
		} 
    	catch (final TextureAtlasBuilderException e)
    	{
			Debug.e(e);
		}
	}
	
	private void loadMenuAudio()
	{
		
	}
	
	private void loadMenuFonts()
	{
		FontFactory.setAssetBasePath("font/");
		final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		font = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "font.ttf", 50, true, Color.WHITE, 2, Color.BLACK);
		font.load();
	}

	private void loadGameGraphics()
	
	{
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
        gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        
        game_background = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "Field.png");
       	ball_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "Player.png");
       	enemy_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "enemy.png");
        explosion_pu = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "Explosion Orb.png");
        spike_pu = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "Spike Orb.png");
        shield_pu = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "Shield Orb.png");
        shield = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "shield.png");
        
       // complete_window_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "levelCompleteWindow.png");

    	try 
    	{
			this.gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.gameTextureAtlas.load();
		} 
    	catch (final TextureAtlasBuilderException e)
    	{
			Debug.e(e);
		}
	}
	
	private void loadGameFonts()
	{
		
	}
	
	private void loadGameAudio()
	{
		
	}
	
	public void unloadGameTextures()
	{
		// TODO (Since we did not create any textures for game scene yet)
	}
	
	public void loadSplashScreen()
	{
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
        splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "splash.png", 0, 0);
        splashTextureAtlas.load();	
	}
	
	public void unloadSplashScreen()
	{
		splashTextureAtlas.unload();
		splash_region = null;
	}
	
	public void unloadMenuTextures()
	{
		menuTextureAtlas.unload();
	}
	
	public void loadMenuTextures()
	{
		menuTextureAtlas.load();
	}
	
	/**
	 * @param engine
	 * @param activity
	 * @param camera
	 * @param vbom
	 * <br><br>
	 * We use this method at beginning of game loading, to prepare Resources Manager properly,
	 * setting all needed parameters, so we can latter access them from different classes (eg. scenes)
	 */
	public static void prepareManager(Engine engine, MainGameActivity activity, BoundCamera camera, VertexBufferObjectManager vbom)
	{
		getInstance().engine = engine;
		getInstance().activity = activity;
		getInstance().camera = camera;
		getInstance().vbom = vbom;
	}
	public static Assets getInstance()
	{
		return INSTANCE;
	}
}
