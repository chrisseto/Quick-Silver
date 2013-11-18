package com.chrisseto.quicksilver.util;

public class Vector extends Point{
	
	public float magnitudeSq()
	{
		return sq(getX())+sq(getY());
	}
	
	public float magnitude()
	{
		return (float)Math.sqrt(magnitudeSq());
	}

}
