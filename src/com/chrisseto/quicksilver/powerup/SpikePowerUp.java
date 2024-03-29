package com.chrisseto.quicksilver.powerup;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;

import com.chrisseto.quicksilver.base.PowerUpBase;
import com.chrisseto.quicksilver.managment.ActiveManager;
import com.chrisseto.quicksilver.util.Assets;

public class SpikePowerUp extends PowerUpBase {

	public SpikePowerUp(float pX, float pY,
		 VertexBufferObjectManager vbom,
			ActiveManager parent) {
		super(parent.getPlayer().getX(), parent.getPlayer().getY(), 70,
				Assets.getInstance().spikes, vbom, parent, 5);
	}
	//This should rotate with the player
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
		this.setRotation(this.parent.getPlayer().getRotation());

	}

}
