package com.chrisseto.tilttolive.object;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;

import com.chrisseto.tilttolive.base.Ball;

public class ExplosionPowerUp extends Ball{

	final Scene parent;
	public ExplosionPowerUp(float pX, float pY, int size,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, Camera camera, Scene parent) {
		super(pX, pY, size, pTextureRegion, pVertexBufferObjectManager, camera);
		this.parent = parent;
		startSpawnTimer();
		this.setAlpha(.7f);
		this.registerEntityModifier(new ScaleModifier(2, 1, 4));
		
		//Fade out
		//Update radius with scaling :s
	}
	private void startSpawnTimer()
	{
		TimerHandler lifeTime;
		parent.registerUpdateHandler(
		lifeTime = new 
	TimerHandler(5, new ITimerCallback()
		{
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				beginFade();
			}
		}));
	}

	private void finish() {	
		
		this.parent.unregisterUpdateHandler(this);
		this.parent.detachChild(this);
		this.dispose();
		 //?
		
	}
	
	private void beginFade()
	{
		this.registerEntityModifier(new AlphaModifier(1,.7f,0,new IEntityModifierListener() {
			
			@Override
			public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
				finish();
				
			}
		}));
	}
}
