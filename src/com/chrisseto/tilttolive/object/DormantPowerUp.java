package com.chrisseto.tilttolive.object;

import java.util.Random;

import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.tilttolive.base.Ball;
import com.chrisseto.tilttolive.managment.PowerUpManger;
import com.chrisseto.tilttolive.util.Assets;


public class DormantPowerUp extends Ball {

	PowerUpType type;
	final PowerUpManger parent;
	public DormantPowerUp(float x, float y, VertexBufferObjectManager vbom,
			Camera camera, PowerUpType t, ITextureRegion tex,PowerUpManger parent) {
		
		super(x, y, 30,tex, vbom, camera);
		this.type = t;
		this.parent = parent;
	}
	public void remove()
	{
		parent.remove(this);
	}
	public void trigger()
	{
		//Add effect
		remove();
	}
	//Static methods and Enum
	public static PowerUpType makeRandom()
	{
	    return PowerUpType.values()[new Random().nextInt(PowerUpType.values().length)];
	}
	public static ITextureRegion getRegion(PowerUpType type)
	{
		switch(type)
		{
		case Explosion:
			return Assets.getInstance().explosion_pu;
		default:
			return null;
		}
	}
	public enum PowerUpType
	{
		Explosion
	}
}
