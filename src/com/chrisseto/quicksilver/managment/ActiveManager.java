package com.chrisseto.quicksilver.managment;

import java.util.Iterator;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.quicksilver.EnemyBall;
import com.chrisseto.quicksilver.Player;
import com.chrisseto.quicksilver.base.Manager;
import com.chrisseto.quicksilver.base.PowerUpBase;
import com.chrisseto.quicksilver.powerup.ExplosionPowerUp;
import com.chrisseto.quicksilver.powerup.ShieldPowerUp;
import com.chrisseto.quicksilver.powerup.SpikePowerUp;
import com.chrisseto.quicksilver.powerup.DormantPowerUp.PowerUpType;
import com.chrisseto.quicksilver.scene.GameScene;
import com.chrisseto.quicksilver.util.Assets;

public class ActiveManager extends Manager<PowerUpBase> {
	
	private EnemyManger enemy;
	final Player player;
	private boolean hit;
	
	public ActiveManager(Scene p, VertexBufferObjectManager vbom,
			Camera camera,Player player) {
		super(p, vbom, camera);
		this.player = player;
		enemy = new EnemyManger(p, vbom, camera, player);
		hit = false;
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		Iterator<PowerUpBase> it = list.iterator();
		Iterator<EnemyBall> eIt = enemy.getIterator();
		PowerUpBase base;
		EnemyBall e;
		hit = false;
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
					hit = true;
					e.die();
					((GameScene)parent).addToScore(hit);
					eIt.remove();
				}
			}
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
			list.add(new ExplosionPowerUp(x, y,  vbom, this));
			break;
		case Shield:
			list.add(new ShieldPowerUp(x, y, vbom, this));
			break;
		case Spikes:
			list.add(new SpikePowerUp(x, y, vbom, this));
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

	public Player getPlayer()
	{
		return player;
	}
	
}
