package com.chrisseto.tilttolive.object.powerup;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;

import com.chrisseto.tilttolive.base.PowerUpBase;
import com.chrisseto.tilttolive.managment.ActiveManager;
import com.chrisseto.tilttolive.object.EnemyBall;
import com.chrisseto.tilttolive.util.Assets;

public class ExplosionPowerUp extends PowerUpBase{

	public ExplosionPowerUp(float pX, float pY,
			VertexBufferObjectManager pVertexBufferObjectManager, Camera camera, ActiveManager parent) {
		super(pX, pY, 20, Assets.getInstance().explosion_pu, pVertexBufferObjectManager, parent, 4);
		startSpawnTimer();
		this.setAlpha(.7f);
		this.registerEntityModifier(new ScaleModifier(2, 1, 4));
		
		//Fade out
		//Update radius with scaling :s
	}
	private void startSpawnTimer()
	{
		this.registerUpdateHandler(
		new 
	TimerHandler(3, new ITimerCallback()
		{
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				beginFade();
			}
		}));
	}

	
	private void beginFade()
	{
		this.registerEntityModifier(new AlphaModifier(1.5f,.7f,0,new IEntityModifierListener() {
			
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
	@Override
	protected void finish() {
		finished = true;
		this.detachSelf();
	//	this.parent.unregisterUpdateHandler(this);
		//this.parent.detachChild(this);
		this.dispose();
		 //?
		
	}
	
	public float getRadius()
	{
		return this.getWidth()/2;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean collideswith(EnemyBall ball) {
		return (((this.getX() - ball.getX())*(this.getX() - ball.getX()))+((this.getY() - ball.getY())*(this.getY() - ball.getY())))*(((this.getX() - ball.getX())*(this.getX() - ball.getX()))+((this.getY() - ball.getY())*(this.getY() - ball.getY()))) <= (getRadius()+ball.getRadius())*(getRadius()+ball.getRadius());
	}
}
