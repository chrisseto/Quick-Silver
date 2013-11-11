package com.chrisseto.quicksilver.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;
import org.andengine.util.modifier.IModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;

import com.chrisseto.quicksilver.managment.SceneManager;
import com.chrisseto.quicksilver.managment.SceneManager.SceneType;
import com.chrisseto.quicksilver.util.Assets;

public class MainMenuScene extends com.chrisseto.quicksilver.base.BaseScene {
	// ---------------------------------------------
	// VARIABLES
	// ---------------------------------------------
	private TiledSprite buttons;
	private Sprite play_menu, menu_select, option_menu;
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

		menu_select = new Sprite(400, 100, Assets.getInstance().menu_select, vbom);
		menu_select.setVisible(false);
		
		option_menu = new Sprite(400, -139, Assets.getInstance().option_menu, vbom) {
			@Override
			public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pAreaTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {

					if (pTouchAreaLocalY > 69 * 2)
						menu_select.setY(190);
					else if (pTouchAreaLocalY > 69)
						menu_select.setY(115);
					else
						menu_select.setY(41);

					menu_select.setVisible(true);
				} else {
					//if (pTouchAreaLocalY > 69 * 2) Calibrate here pop up a toast saying "calibrated"
						
					// else if(pTouchAreaLocalY > 69) Pop up of how to play
					// menu_select.setY(115);
					// else
					// menu_select.setY(41); //About
					menu_select.setVisible(false);
				}
				return true;
			}
		};

		play_menu = new Sprite(400, -139, Assets.getInstance().play_menu, vbom) {
			@Override
			public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pAreaTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {

					if (pTouchAreaLocalY > 69 * 2)
						menu_select.setY(190);
					else if (pTouchAreaLocalY > 69)
						menu_select.setY(115);
					else
						menu_select.setY(41);

					menu_select.setVisible(true);
				} else {
					if (pTouchAreaLocalY > 69 * 2)
						SceneManager.getInstance().loadGameScene(engine);
					// else if(pTouchAreaLocalY > 69) Time attack mode?
					// menu_select.setY(115);
					// else
					// menu_select.setY(41); //Some other future mode?
					menu_select.setVisible(false);
				}
				return true;
			}
		};


		buttons = new TiledSprite(400, 120, Assets.getInstance().buttons, vbom) {
			@Override
			public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pAreaTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					if (pTouchAreaLocalX >= this.getWidth() / 2)
						this.setCurrentTileIndex(2);
					else
						this.setCurrentTileIndex(1);
				} else if (pAreaTouchEvent.getAction() == TouchEvent.ACTION_UP) {

					// if not raised, raise
					if (!raised) {
						raised = true;
						buttons.registerEntityModifier(new MoveYModifier(.25f, 120, 250, new IEntityModifierListener() {

							@Override
							public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
								if (pTouchAreaLocalX < getWidth() / 2) {
									play_menu.registerEntityModifier(new MoveYModifier(.25f, -139, 139));
									play = true;
								} else {
									option_menu.registerEntityModifier(new MoveYModifier(.25f, -139, 139));
									options = true;
								}

							}
						}));

						return true;
					}

					if (pTouchAreaLocalX < this.getWidth() / 2) {
						if (play)
							play_menu.registerEntityModifier(new MoveYModifier(.25f, 139, -139,
									new IEntityModifierListener() {

										@Override
										public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
											setCurrentTileIndex(0);

										}

										@Override
										public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
											play = false;
											raised = false;

											buttons.registerEntityModifier(new MoveYModifier(.25f, 250, 120));
										}
									}));
						else
							option_menu.registerEntityModifier(new MoveYModifier(.25f, 139, -139,
									new IEntityModifierListener() {

										@Override
										public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
											// TODO Auto-generated method stub

										}

										@Override
										public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
											play = true;
											options = false;
											play_menu.registerEntityModifier(new MoveYModifier(.25f, -139, 139));
										}
									}));
					} else {
						if (options)
							option_menu.registerEntityModifier(new MoveYModifier(.25f, 139, -139,
									new IEntityModifierListener() {

										@Override
										public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
											setCurrentTileIndex(0);

										}

										@Override
										public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
											options = false;
											raised = false;

											buttons.registerEntityModifier(new MoveYModifier(.25f, 250, 120));
										}
									}));
						else
							play_menu.registerEntityModifier(new MoveYModifier(.25f, 139, -139,
									new IEntityModifierListener() {

										@Override
										public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
											// TODO Auto-generated method stub

										}

										@Override
										public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
											options = true;
											play = false;
											option_menu.registerEntityModifier(new MoveYModifier(.25f, -139, 139));
										}
									}));
					}

				}

				return true;
			}
		};

		
		
		this.registerTouchArea(buttons);
		this.registerTouchArea(play_menu);
		this.registerTouchArea(option_menu);
		
		this.attachChild(play_menu);
		this.attachChild(option_menu);
		attachChild(menu_select);
		this.attachChild(buttons);
		
	}

}