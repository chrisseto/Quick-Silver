package com.chrisseto.tilttolive.base;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.tilttolive.util.BVector;

public abstract class Ball extends Sprite
{

	private int size;
	private BVector velocity;
	
	public Ball(float pX, float pY, int size,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, size, size, pTextureRegion, pVertexBufferObjectManager);
		
		this.size = size;
	}
	
	public void setVelocity(BVector v)
	{
		this.velocity = v;
	}
	
	public BVector getVelocity()
	{
		return velocity;
	}
	
	public void update()
	{
		setX(velocity.x+getX());
		setY(velocity.y+getY());
	}
}