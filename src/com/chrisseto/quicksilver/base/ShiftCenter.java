package com.chrisseto.quicksilver.base;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.quicksilver.util.BVector;

public abstract class ShiftCenter extends Sprite{

	private BVector position,offset;
	private float width,height;
	
	public ShiftCenter(float pX, float pY, float pWidth, float pHeight, ITextureRegion pTextureRegion,
			VertexBufferObjectManager vbom) {
		super(pX, pY, pTextureRegion, vbom);
		
		width = pWidth;
		height = pHeight;
		position = new BVector(pX, pY);
		offset = new BVector(super.getWidth() - pWidth, pHeight - super.getHeight());
		this.setRotationCenter(width/this.getWidth(), height/this.getHeight());
		position.add(offset);
		setPosition(position);
	}
	
	@Override
	public void setX(float x)
	{
		position.x = x;
		super.setX(x);
	}

	@Override
	public void setY(float y)
	{
		position.y = y;
		super.setY(y);
	}
	
	@Override
	public float getX()
	{
		return position.x;
	}
	
	@Override
	public float getY()
	{
		return position.y;
	}
	/*
	@Override
	public float getWidth()
	{
		return this.width;
	}
	
	@Override
	public float getHeight()
	{
		return this.height;
	}
	*/
	public void setPosition(BVector to)
	{
		position = to.get();
		super.setX(position.x);
		super.setY(position.y);
	}
	
	public BVector getPosition() {
		return position;
	}
	
	public static float bound(float toBound, float high, float low)
	{
		if(toBound>high)
			return high;
		else if (toBound < low)
			return low;
		else 
			return toBound;
	}
}
