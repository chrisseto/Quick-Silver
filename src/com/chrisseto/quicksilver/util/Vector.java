package com.chrisseto.quicksilver.util;

public class Vector extends Point{
	
	public Vector()
	{
		super();
	}
	
	public Vector (float angle)
	{
		super((float)Math.cos(angle),(float)Math.sin(angle));	
	}
	
	public Vector(Point p1, Point p2)
	{
		super(p2.getX() - p1.getX(),p2.getY() - p1.getY());
	}
	
	public Vector(float x, float y)
	{
		super(x,y);
	}
	
	public Vector substract(Vector other)
	{
		return new Vector(getX() - other.getX(), getY() - other.getY());
	}
	
	public Vector substract(float scalar)
	{
		return new Vector(getX() - scalar, getY() - scalar);
	}
	
	public Vector add(Vector other)
	{
		return new Vector(getX() + other.getX(), getY() + other.getY());
	}
	
	public Vector add(float scalar)
	{
		return new Vector(getX() + scalar, getY() + scalar);
	}
	
	public Vector multiply(Vector other)
	{
		return new Vector(getX() * other.getX(), getY() * other.getY());
	}
	
	public Vector multiply(float scalar)
	{
		return new Vector(getX() * scalar, getY() * scalar);
	}
	
	public Vector divide(Vector other)
	{
		return new Vector(getX() / other.getX(), getY() / other.getY());
	}
	
	public Vector divide(float scalar)
	{
		return new Vector(getX() / scalar, getY() / scalar);
	}
	
	public float magnitudeSq()
	{
		return sq(getX())+sq(getY());
	}
	
	public float magnitude()
	{
		return (float)Math.sqrt(magnitudeSq());
	}
	
	public float dot(Vector other)
	{
		return (getX()*other.getX()) + (getY() * other.getX());
	}
	
	public Vector normalize()
	{
		float mag = magnitude();
		return new Vector(getX()/mag,getY()/mag);
	}
	
	public float project(Vector other)
	{
		return dot(other.normalize());
	}
	
	//public Vector project(Vector Other)
	
	
}
