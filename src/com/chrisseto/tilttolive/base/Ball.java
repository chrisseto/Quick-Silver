package com.chrisseto.tilttolive.base;

import android.graphics.Color;

import org.andengine.entity.sprite.AnimatedSprite;

import com.chrisseto.tilttolive.util.BVector;

public abstract class Ball extends AnimatedSprite
{
	private BVector position, velocity;
	private double size;
	private Color color;
	Ball(double x, double y, double size, Color color)
	{
		super(x, y, ResourcesManager.getInstance().player_region, vbo);
		setPosition(new BVector(x,y));
		this.size = size;
		this.color = color;
		
	}
	
	public double getX()
	{
		return position.x;
	}
	
	public double getY()
	{
		return position.y;
	}

	public boolean checkCollision(Ball other)
  	{
    		return(BVector.sub(other.getPosition(),getPosition()).magsq() <= ((size+other.size)/2)*((size+other.size)/2));  
  	}

	public void update()
	{
		position.add(velocity);
		checkBoundaryCollition();
	}
	
	void checkBoundaryCollition()
	{
		if (position.x > Assets.WIDTH-size/2)
			position.x = Assets.WIDTH-size/2;
		if (position.x <size/2)
			position.x = size/2;
		if (position.y > Assets.HEIGHT-size/2)
			position.y = Assets.HEIGHT-size/2;
		if (position.y< size/2)
			position.y =size/2;
	}
	public void draw() //I dunno what args this needs lol canvas graphics?
	{
		//Draw circle with color color
		//Override to paint bitmap
	}

	public BVector getPosition() {
		return position;
	}

	public void setPosition(BVector position) {
		this.position = position;
	}
}
