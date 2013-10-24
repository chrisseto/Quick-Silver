package com.chrisseto.tilttolive.base;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.tilttolive.util.BVector;

public abstract class Ball extends Sprite
{

	private int size;
	private BVector velocity;
	private float WIDTH,HEIGHT;
	public Ball(float pX, float pY, int size,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, Camera camera) {
		super(pX, pY, size, size, pTextureRegion, pVertexBufferObjectManager);
		WIDTH = camera.getWidth();
		HEIGHT = camera.getHeight();
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
	public int getSize()
	{
		return size;
	}
	
	public int getRadius()
	{
		return size/2;
	}
	public void checkBounds()
	{
		if(getX() > WIDTH-getRadius())
			setX(WIDTH-getRadius());
		if(getX() < getRadius())
			setX(getRadius());
		if(getY() > HEIGHT-getRadius())
			setY(HEIGHT-getRadius());
		if(getY() < getRadius())
			setY(getRadius());
	}
}