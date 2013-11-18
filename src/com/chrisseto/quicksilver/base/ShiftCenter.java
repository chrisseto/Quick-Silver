package com.chrisseto.quicksilver.base;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.quicksilver.util.BVector;
import com.chrisseto.quicksilver.util.Point;

public abstract class ShiftCenter extends Sprite{

	private Point position,offset;
	private float width,height;
	
	public ShiftCenter(float pX, float pY, float pWidth, float pHeight, ITextureRegion pTextureRegion,
			VertexBufferObjectManager vbom) {
		super(pX, pY, pTextureRegion, vbom);
		
		width = pWidth;
		height = pHeight;
		position = new Point(pX, pY);
		offset = new Point(super.getWidth() - pWidth, pHeight - super.getHeight());
		this.setRotationCenter(width/this.getWidth(), height/this.getHeight());
		position.translate(offset.getX(),offset.getY());
		setPosition(position);
	}
	
	@Override
	public void setX(float x)
	{
		position.setX(x);
		super.setX(x);
	}

	@Override
	public void setY(float y)
	{
		position.setY(y);
		super.setY(y);
	}
	
	@Override
	public float getX()
	{
		return position.getX();
	}
	
	@Override
	public float getY()
	{
		return position.getY();
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
	public void setPosition(Point to)
	{
		position = new Point(to.getX(),to.getY());
		super.setX(position.getX());
		super.setY(position.getY());
	}
	
	public Point getPosition() {
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
