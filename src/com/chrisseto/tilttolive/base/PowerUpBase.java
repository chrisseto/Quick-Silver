package com.chrisseto.tilttolive.base;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.tilttolive.managment.ActiveManager;
import com.chrisseto.tilttolive.object.EnemyBall;
import com.chrisseto.tilttolive.util.Assets;

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

	public boolean collidesWith(EnemyBall ball) {
		
		return (
				((getX()-ball.getX()) * (getX()-ball.getX())) 
				+ ((getY()-ball.getY())*(getY()-ball.getY())) 
				<= ((getRadius() + ball.getRadius()) * (getRadius() + ball.getRadius())));
	}
	
	public float getRadius()
	{
		return this.getScaleX()*this.getWidth()/2;
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
