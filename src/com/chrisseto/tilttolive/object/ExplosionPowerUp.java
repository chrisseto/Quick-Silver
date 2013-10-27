package com.chrisseto.tilttolive.object;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.tilttolive.base.Ball;

public class ExplosionPowerUp extends Ball {

	public ExplosionPowerUp(float pX, float pY, int size,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, Camera camera) {
		super(pX, pY, size, pTextureRegion, pVertexBufferObjectManager, camera);
		this.registerEntityModifier(new ScaleModifier(1, 1, 1));
	}

}
