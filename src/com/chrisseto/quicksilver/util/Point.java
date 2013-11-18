package com.chrisseto.quicksilver.util;

import java.text.DecimalFormat;

public class Point {
	private float x,y;
	
	public Point()
	{
		this(0,0);
	}
	
	public Point(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public void setY(float y)
	{
		this.y = y;
	}
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	public void translate(float x, float y)
	{
		this.x+=x;
		this.y+=y;
	}
	
	public float distanceSq(Point other)
	{
		return distanceSq(this, other);
	}
	
	public float distance(Point other)
	{
		return distance(this,other);
	}
	
	public String toString() 
	{
			DecimalFormat f = new DecimalFormat();
			return "(" + f.format(x) + ", " + f.format(y) +")";
	}
	
	public static float distanceSq(Point p1, Point p2)
	{
		return sq(p1.getX()-p2.getX()) + sq(p1.getY() - p2.getY());
	}
	
	public static float distance(Point p1, Point p2)
	{
		return (float)Math.sqrt(distanceSq(p1, p2));
	}
	
	protected static float sq(float num)
	{
		return num*num;
	}
}
