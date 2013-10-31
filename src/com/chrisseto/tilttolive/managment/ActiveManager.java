package com.chrisseto.tilttolive.managment;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.tilttolive.base.Manager;
import com.chrisseto.tilttolive.base.PowerUpBase;
import com.chrisseto.tilttolive.object.DormantPowerUp.PowerUpType;

public class ActiveManager extends Manager<PowerUpBase> {
	
	public ActiveManager(Scene p, VertexBufferObjectManager vbom,
			Camera camera) {
		super(p, vbom, camera);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public void add(float x, float y, PowerUpType type) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(float x, float y) {
		
		// This should never be called
		
	}
	
	@Override
	public float makeNewDelay() {
		// TODO Auto-generated method stub
		return 0;
	}

}
