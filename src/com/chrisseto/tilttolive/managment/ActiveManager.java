package com.chrisseto.tilttolive.managment;

import java.util.Iterator;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.tilttolive.base.Manager;
import com.chrisseto.tilttolive.base.PowerUpBase;
import com.chrisseto.tilttolive.object.powerup.ExplosionPowerUp;
import com.chrisseto.tilttolive.object.powerup.DormantPowerUp.PowerUpType;

public class ActiveManager extends Manager<PowerUpBase> {
	
	public ActiveManager(Scene p, VertexBufferObjectManager vbom,
			Camera camera) {
		super(p, vbom, camera);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		Iterator<PowerUpBase> it = list.iterator();
		PowerUpBase base;
		while(it.hasNext())
		{
			base = it.next();
			base.update();
			if(base.isFinished())
				it.remove();
			//loop through enemies :[
		}
		//Collision and other such
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public void add(float x, float y, PowerUpType type) {
		
		switch(type)
		{
		case Explosion:
			list.add(new ExplosionPowerUp(x, y,  vbom, camera, this));
			break;
		}
		
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
