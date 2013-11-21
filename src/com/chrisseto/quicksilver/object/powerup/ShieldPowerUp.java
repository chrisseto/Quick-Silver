
package com.chrisseto.quicksilver.object.powerup;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;

import com.chrisseto.quicksilver.base.Ball;
import com.chrisseto.quicksilver.base.PowerUpBase;
import com.chrisseto.quicksilver.managment.ActiveManager;
import com.chrisseto.quicksilver.util.Assets;

public class ShieldPowerUp extends PowerUpBase {


	public ShieldPowerUp(float pX, float pY, VertexBufferObjectManager vbom,
			ActiveManager parent) {
		super(parent.getPlayer().getX(), parent.getPlayer().getY(), 70, Assets.getInstance().shield, vbom, parent, 0);
	}

	@Override
	public void update() {
		this.setX(this.parent.getPlayer().getX());
		this.setY(this.parent.getPlayer().getY());
	}

	@Override
	protected void start() {
		registerEntityModifier(new LoopEntityModifier(new RotationModifier(5, 0, 350)));
		//Add a rotation modifier
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
	public boolean collidesWith(Ball ball) {
		if(super.collidesWith(ball))
		{
			beginFinish();
			return true;
		}
		return false;
	}
}
