package com.chrisseto.tilttolive.object;

import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.tilttolive.base.Ball;

public abstract class DormantPowerUp extends Ball {

	public DormantPowerUp(float pX, float pY, int size,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, Camera camera) {
		super(pX, pY, size, pTextureRegion, pVertexBufferObjectManager, camera);
		// TODO Auto-generated constructor stub
	}

}
