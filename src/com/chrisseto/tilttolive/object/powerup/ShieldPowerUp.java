
package com.chrisseto.tilttolive.object.powerup;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;

import com.chrisseto.tilttolive.base.PowerUpBase;
import com.chrisseto.tilttolive.managment.ActiveManager;
import com.chrisseto.tilttolive.object.EnemyBall;
import com.chrisseto.tilttolive.util.Assets;

public class ShieldPowerUp extends PowerUpBase {


	public ShieldPowerUp(float pX, float pY, VertexBufferObjectManager vbom,
			ActiveManager parent) {
		super(parent.getPlayer().getX(), parent.getPlayer().getY(), 40, Assets.getInstance().shield, vbom, parent, 0);
	}

	@Override
	public void update() {
		this.setX(this.parent.getPlayer().getX());
		this.setY(this.parent.getPlayer().getY());
	}

	@Override
	protected void start() {
		//this.setAlpha(.5f);
		
	}

	@Override
	protected void beginFinish() {
		this.registerEntityModifier(new AlphaModifier(.25f,1,0,new IEntityModifierListener() {
			
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
	public boolean collidesWith(EnemyBall ball) {
		if(super.collidesWith(ball))
		{
			beginFinish();
			return true;
		}
		return false;
	}
}
