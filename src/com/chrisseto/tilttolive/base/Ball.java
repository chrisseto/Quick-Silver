package com.chrisseto.tilttolive.base;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class Ball extends Sprite
{

	private int size;
	public Ball(float pX, float pY, int size,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, size, size, pTextureRegion, pVertexBufferObjectManager);
		
		this.size = size;
	}
	
}