package com.chrisseto.tilttolive.object.powerup;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;

import com.chrisseto.tilttolive.base.PowerUpBase;
import com.chrisseto.tilttolive.managment.ActiveManager;
import com.chrisseto.tilttolive.object.EnemyBall;
import com.chrisseto.tilttolive.util.Assets;

public class SpikePowerUp extends PowerUpBase {

	public SpikePowerUp(float pX, float pY,
		 VertexBufferObjectManager vbom,
			ActiveManager parent) {
		super(parent.getPlayer().getX(), parent.getPlayer().getY(), 45,
				Assets.getInstance().explosion_pu, vbom, parent, 5);
	}

	@Override
	public boolean collideswith(EnemyBall ball) {
		return (((this.getX() - ball.getX()) * (this.getX() - ball.getX())) + ((this
				.getY() - ball.getY()) * (this.getY() - ball.getY())))
				* (((this.getX() - ball.getX()) * (this.getX() - ball.getX())) + ((this
						.getY() - ball.getY()) * (this.getY() - ball.getY()))) <= (getRadius() + ball
				.getRadius()) * (getRadius() + ball.getRadius());
	}

	public float getRadius() {
		return this.getWidth() / 2;
	}

	@Override
	protected void start() {
		this.setScale(.1f);
		this.registerEntityModifier(new ScaleModifier(.5f, .1f, 1));
	}

	@Override
	protected void beginFinish() {
		this.registerEntityModifier(new ScaleModifier(.5f, 1, .1f,
				new IEntityModifierListener() {

					@Override
					public void onModifierStarted(IModifier<IEntity> pModifier,
							IEntity pItem) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						finish();
					}
				}));

	}

	@Override
	public void update() {
		this.setX(this.parent.getPlayer().getX());
		this.setY(this.parent.getPlayer().getY());

	}

}
