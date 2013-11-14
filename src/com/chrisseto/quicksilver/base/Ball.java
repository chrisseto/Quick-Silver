package com.chrisseto.quicksilver.base;

import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.quicksilver.util.Assets;
import com.chrisseto.quicksilver.util.BVector;

public abstract class Ball extends ShiftCenter {

	private int size;
	private BVector velocity;

	public Ball(float pX, float pY, int size, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, Camera camera) {
		super(pX, pY, size, size, pTextureRegion, pVertexBufferObjectManager);
		
		this.size = size;
	}

	public void setVelocity(BVector v) {
		this.velocity = v;
	}

	public BVector getVelocity() {
		return velocity;
	}

	public void update() {
		setX(velocity.x + getX());
		setY(velocity.y + getY());
	}

	public int getSize() {
		return size;
	}

	public int getRadius() {
		return size / 2;
	}

	public void checkBounds() {
		setX(bound(getX(), Assets.getInstance().boundingBox[2]-getRadius(), Assets.getInstance().boundingBox[0]+getRadius()));
		setY(bound(getY(), Assets.getInstance().boundingBox[3]-getRadius(), Assets.getInstance().boundingBox[1]+getRadius()));
	}

	public void updatePosition(BVector velocity) {
		super.getPosition().add(velocity);
		super.setPosition(super.getPosition());
	}

	public boolean collidesWith(Ball other) {
		return BVector.sub(this.getPosition(), other.getPosition()).magsq() <= (getRadius() + other.getRadius())
				* (getRadius() + other.getRadius());
	}
	
}