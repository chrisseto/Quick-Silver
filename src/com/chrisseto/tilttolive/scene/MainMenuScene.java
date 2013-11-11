package com.chrisseto.tilttolive.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;
import org.andengine.util.modifier.IModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;

import com.chrisseto.tilttolive.managment.SceneManager;
import com.chrisseto.tilttolive.managment.SceneManager.SceneType;
import com.chrisseto.tilttolive.util.Assets;
import com.chrisseto.tilttolive.util.HeightModifier;

public class MainMenuScene extends com.chrisseto.tilttolive.base.BaseScene {
	// ---------------------------------------------
	// VARIABLES
	// ---------------------------------------------
	private TiledSprite buttons;
	private Sprite play_menu;
	private boolean play, options, raised;

	// ---------------------------------------------
	// METHODS FROM SUPERCLASS
	// ---------------------------------------------

	@Override
	public void createScene() {
		createBackground();
		createMenuChildScene();
	}

	@Override
	public void onBackKeyPressed() {
		System.exit(0);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_MENU;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
	}

	// ---------------------------------------------
	// CLASS LOGIC
	// ---------------------------------------------

	private void createBackground() {
		attachChild(new Sprite(400, 240, resourcesManager.menu_background_region, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		});
	}

	private void createMenuChildScene() {
		play = false;
		options = false;
		raised = false;
		// 0-199 = play
		// 200-400 = options
		// 278 139
		play_menu = new Sprite(400, -139, Assets.getInstance().play_menu, vbom);
		// play_menu.setHeight(0);
		this.attachChild(play_menu);

		buttons = new TiledSprite(400, 120, Assets.getInstance().buttons, vbom) {
			@Override
			public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pAreaTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					if (pTouchAreaLocalX >= this.getWidth() / 2)
						this.setCurrentTileIndex(2);
					else
						this.setCurrentTileIndex(1);
				} else if (pAreaTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					
					//if not raised, raise
					if(!raised)
					{
						raised = true;
						buttons.registerEntityModifier(new MoveYModifier(.25f, 120, 250, new IEntityModifierListener() {

							@Override
							public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
								if (pTouchAreaLocalX < getWidth() / 2)
								{
									play_menu.registerEntityModifier(new MoveYModifier(.25f, -139, 139));
									play = true;
								}
								else
								{
									//This is where the options menu would be raised
									options = true;
								}

							}
						}));
						return true;
					}
					
					if (pTouchAreaLocalX < this.getWidth() / 2)
					{
						if(play)
							play_menu.registerEntityModifier(new MoveYModifier(.25f, 139, -139, new IEntityModifierListener() {
								
								@Override
								public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
									if(play)
									{
									play = false;
									raised = false;
									buttons.registerEntityModifier(new MoveYModifier(.25f, 250, 120));
									}
									else
									{
										options = true;
										//Raise Other Menu here
									}
								}
							}));
					}
					else
					{
						//Copy the above to here with reversed logic
					}

					// SceneManager.getInstance().loadGameScene(engine);
					this.setCurrentTileIndex(0);
				}

				return true;

			}
		};
		this.registerTouchArea(buttons);
		this.attachChild(buttons);
	}
	
	
}