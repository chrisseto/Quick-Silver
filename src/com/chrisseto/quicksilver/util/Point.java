package com.chrisseto.quicksilver.util;

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
	
	public static float distanceSq(Point p1, Point p2)
	{
		return sq(p1.getX()-p2.getX()) + sq(p1.getY() - p2.getY());
	}
	
	private static float sq(float num)
	{
		return num*num;
	}
}
