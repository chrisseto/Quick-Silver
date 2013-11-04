package com.chrisseto.tilttolive.base;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.tilttolive.managment.ActiveManager;
import com.chrisseto.tilttolive.object.EnemyBall;

//Notes
//The beginFinish method should always call finish
//A life of 0 will have no timer
//and things about how to add new PU's in files


public abstract class PowerUpBase extends Sprite {
	protected boolean finished;
	private float life;
	protected final ActiveManager parent;

	public PowerUpBase(float pX, float pY, float size,
			ITextureRegion pTextureRegion, VertexBufferObjectManager vbom,
			ActiveManager parent, float life) {
		super(pX, pY, size, size, pTextureRegion, vbom);
		finished = false;
		this.life = life;
		this.parent = parent;
		startTimer();
		start();
	}

	public boolean isFinished() {
		return finished;
	}

	public abstract boolean collideswith(EnemyBall ball);

	protected abstract void start();

	protected abstract void beginFinish();

	public abstract void update();

	public void finish() {
		finished = true;
		this.detachSelf();
		this.dispose();
	}

	private void startTimer() {
		if (life > 0)
			parent.parent.registerUpdateHandler(new TimerHandler(life,
					new ITimerCallback() {
						@Override
						public void onTimePassed(
								final TimerHandler pTimerHandler) {
							beginFinish();
						}
					}));
	}

}
