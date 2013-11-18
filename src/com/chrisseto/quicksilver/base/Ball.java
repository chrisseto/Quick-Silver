package com.chrisseto.quicksilver.base;

import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.quicksilver.util.Assets;
import com.chrisseto.quicksilver.util.BVector;
import com.chrisseto.quicksilver.util.Collision;
import com.chrisseto.quicksilver.util.Vector;

public abstract class Ball extends ShiftCenter {

	private int size;
	private Vector velocity;

	public Ball(float pX, float pY, int size, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, Camera camera) {
		super(pX, pY, size, size, pTextureRegion, pVertexBufferObjectManager);

		this.size = size;
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

	public int getSize() {
		return size;
	}

	public int getRadius() {
		return size / 2;
	}

	public void checkBounds() {
		setX(bound(getX(), Assets.getInstance().boundingBox[2] - getRadius(), Assets.getInstance().boundingBox[0]
				+ getRadius()));
		setY(bound(getY(), Assets.getInstance().boundingBox[3] - getRadius(), Assets.getInstance().boundingBox[1]
				+ getRadius()));
	}

	public void updatePosition(Vector velocity) {
		super.getPosition().translate(velocity.getX(),velocity.getY());
		super.setPosition(super.getPosition());
	}

	public boolean collidesWith(Ball other) {
		return Collision.collides(this, other);
	}

}