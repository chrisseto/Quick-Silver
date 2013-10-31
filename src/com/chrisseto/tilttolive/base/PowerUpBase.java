package com.chrisseto.tilttolive.base;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class PowerUpBase extends Sprite{
	private boolean finished;
	private float life;
	private final Scene parent;
	
	public PowerUpBase(float pX, float pY, float size,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager vbom, Scene parent, float life) {
		super(pX, pY, size, size, pTextureRegion, vbom);
		finished = false;
		this.life = life;
		this.parent = parent;
		startSpawnTimer();
	}
	
	public boolean isFinished()
	{
		return finished;
	}
	
	protected abstract void finish();
	protected abstract void update();
	
	private void startSpawnTimer()
	{
		parent.registerUpdateHandler(new 
	TimerHandler(life, new ITimerCallback()
		{
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				finish();
			}
		}));
	}
}
