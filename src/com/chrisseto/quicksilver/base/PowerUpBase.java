package com.chrisseto.quicksilver.base;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.quicksilver.managment.ActiveManager;
import com.chrisseto.quicksilver.object.EnemyBall;
import com.chrisseto.quicksilver.util.Assets;
import com.chrisseto.quicksilver.util.Collision;

//Notes
//The beginFinish method should always call finish
//A life of 0 will have no timer
//and things about how to add new PU's in files

public abstract class PowerUpBase extends Ball {
	protected boolean finished;
	private float life, size;
	protected final ActiveManager parent; // To be made static

	public PowerUpBase(float pX, float pY, float size, ITextureRegion pTextureRegion, VertexBufferObjectManager vbom,
			ActiveManager parent, float life) {
		super(pX, pY, size, pTextureRegion);
		finished = false;
		this.life = life;
		this.parent = parent;
		this.size = size;
		startTimer();
		start();
	}

	public boolean isFinished() {
		return finished;
	}


	public float getRadius() {
		return this.getScaleX() * size / 2;
	}

	protected abstract void start();

	protected abstract void beginFinish();

	public abstract void update();

	public void finish() {
		finished = true;
		Assets.getInstance().engine.runOnUpdateThread(new Runnable() {

			@Override
			public void run() {
				detachSelf();
			}
		});
		//
		this.dispose();
	}

	private void startTimer() {
		if (life > 0)
			parent.parent.registerUpdateHandler(new TimerHandler(life, new ITimerCallback() {
				@Override
				public void onTimePassed(final TimerHandler pTimerHandler) {
					beginFinish();
				}
			}));
	}

}
