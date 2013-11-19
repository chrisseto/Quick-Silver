package com.chrisseto.quicksilver.base;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.quicksilver.util.Assets;
import com.chrisseto.quicksilver.util.Collision;
import com.chrisseto.quicksilver.util.Point;
import com.chrisseto.quicksilver.util.Vector;

public abstract class Ball extends Sprite {

	private int diameter;
	private Vector velocity;

	public Ball(float pX, float pY, int size, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, Camera camera) {
		super(pX, pY, size, size, pTextureRegion, pVertexBufferObjectManager);

		this.diameter = size;
	}

	public void setVelocity(Vector v) {
		this.velocity = new Vector(v.getX(),v.getY());
	}

	public Vector getVelocity() {
		return velocity;
	}

	public void update() {
		setX(velocity.getX() + getX());
		setY(velocity.getY() + getY());
	}

	public int getDiameter() {
		return diameter;
	}

	public int getRadius() {
		return diameter / 2;
	}

	public void checkBounds() {
		setX(bound(getX(), Assets.getInstance().boundingBox[2] - getRadius(), Assets.getInstance().boundingBox[0]
				+ getRadius()));
		setY(bound(getY(), Assets.getInstance().boundingBox[3] - getRadius(), Assets.getInstance().boundingBox[1]
				+ getRadius()));
	}
	
	public Point getPosition()
	{
		return new Point(getX(),getY());
	}
	
	public void setPosition(Point p)
	{
		this.setX(p.getX());
		this.setY(p.getY());
	}
	
	public void updatePosition(Vector velocity) {
		this.setX(getX() + velocity.getX());
		this.setY(getY() + velocity.getY());
	}

	public boolean collidesWith(Ball other) {
		return Collision.collides(this, other);
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