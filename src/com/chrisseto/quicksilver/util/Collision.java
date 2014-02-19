package com.chrisseto.quicksilver.util;

import com.chrisseto.quicksilver.base.Ball;

public class Collision {

	public static boolean collides(Ball ball, Ball other) {
		return collides(ball, other.getX(), other.getY(), other.getRadius());
	}

	public static boolean collides(Ball b, float x, float y, float radius) {
		return collides(b.getX(), b.getY(), b.getRadius(), x, y, radius);
	}
	
	public static boolean collides(float x1, float y1, float r1, float x2, float y2, float r2) {
		return sq(x2 - x1) + sq(y2 - y1) < sq(r2 + r1);
	}
	
	//TODO Double check and implment me.
	public static boolean inBounds(Ball b) {
		return b.getX() < 0 || Assets.getInstance().boundingBox[2] > b.getX() ||
				b.getY() < 0 || Assets.getInstance().boundingBox[3] > b.getY();
	}
	
	private static float sq(float n) {
		return n * n;
	}
}
