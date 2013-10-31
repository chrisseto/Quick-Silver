package com.chrisseto.tilttolive.base;

import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.tilttolive.object.DormantPowerUp.PowerUpType;
import com.chrisseto.tilttolive.object.ExplosionPowerUp;

public class ActivePowerUp extends Ball {

	public ActivePowerUp(float pX, float pY, int size,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, Camera camera) {
		super(pX, pY, size, pTextureRegion, pVertexBufferObjectManager, camera);
		// TODO Auto-generated constructor stub
	}

	public static ActivePowerUp createPowerUp(PowerUpType type) {
		switch (type) {
		case Explosion:
			return null;
		default:
			return null;
		}
	}
}
