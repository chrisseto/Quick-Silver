package com.chrisseto.tilttolive.object.powerup;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;

import com.chrisseto.tilttolive.base.PowerUpBase;
import com.chrisseto.tilttolive.managment.ActiveManager;
import com.chrisseto.tilttolive.util.Assets;

public class ExplosionPowerUp extends PowerUpBase {

	public ExplosionPowerUp(float pX, float pY,
			VertexBufferObjectManager pVertexBufferObjectManager,
			ActiveManager parent) {
		super(pX, pY, 20, Assets.getInstance().explosion_pu,
				pVertexBufferObjectManager, parent, 4);
		// Fade out
		// Update radius with scaling :s
	}

	@Override
	protected void start() {
		this.setAlpha(.7f);
		this.registerEntityModifier(new ScaleModifier(2, 1, 4));

	}

	protected void beginFinish() {
		this.registerEntityModifier(new AlphaModifier(1.5f, .7f, 0,
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
		// TODO Auto-generated method stub

	}


}
