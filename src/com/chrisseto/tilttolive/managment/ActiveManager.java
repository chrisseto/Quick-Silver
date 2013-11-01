package com.chrisseto.tilttolive.managment;

import java.util.Iterator;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.tilttolive.base.Manager;
import com.chrisseto.tilttolive.base.PowerUpBase;
import com.chrisseto.tilttolive.object.EnemyBall;
import com.chrisseto.tilttolive.object.Player;
import com.chrisseto.tilttolive.object.powerup.ExplosionPowerUp;
import com.chrisseto.tilttolive.object.powerup.DormantPowerUp.PowerUpType;
import com.chrisseto.tilttolive.util.Assets;

public class ActiveManager extends Manager<PowerUpBase> {
	
	private EnemyManger enemy;
	final Player player;
	
	public ActiveManager(Scene p, VertexBufferObjectManager vbom,
			Camera camera,Player player) {
		super(p, vbom, camera);
		this.player = player;
		enemy = new EnemyManger(p, vbom, camera, player);
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		Iterator<PowerUpBase> it = list.iterator();
		Iterator<EnemyBall> eIt = enemy.getIterator();
		PowerUpBase base;
		EnemyBall e;
		while(it.hasNext())
		{
			base = it.next();
			base.update();
			if(base.isFinished())
				it.remove();
			while(eIt.hasNext())
			{
				e = eIt.next();
				if(base.collidesWith(e))//Overload this to ball check in base class Override in classes like shield etc.
				{
					enemy.remove(e);
					eIt.remove();
				}
			}
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
			//parent.attachChild(list.get(list.size()-1));
			break;
		}
		Assets.getInstance().engine.runOnUpdateThread(new Runnable() {
			
			@Override
			public void run() {
				parent.attachChild(list.get(list.size()-1));
				
			}
		});
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
