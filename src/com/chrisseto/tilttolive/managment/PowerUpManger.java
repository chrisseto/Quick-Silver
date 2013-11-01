package com.chrisseto.tilttolive.managment;

import java.util.Iterator;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.chrisseto.tilttolive.base.Manager;
import com.chrisseto.tilttolive.object.Player;
import com.chrisseto.tilttolive.object.powerup.DormantPowerUp;
import com.chrisseto.tilttolive.object.powerup.DormantPowerUp.PowerUpType;

//and another manager for active powerups? :x
public class PowerUpManger extends Manager<DormantPowerUp> {
	final Player player;
	public ActiveManager active;
	
	public PowerUpManger(Scene p, VertexBufferObjectManager vbom,
			Camera camera, Player player) {
		super(p, vbom, camera);
		
		active = new ActiveManager(p, vbom, camera,player);
		this.player = player;
		tRadius = 15;
		this.spawnDelay = 4;
		this.startSpawnTimer();
		add(70, 70);
		add(20, 20);

	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		Iterator<DormantPowerUp> i = list.iterator();
		DormantPowerUp p;
		while(i.hasNext())
		{
			p = i.next();
			if(p.collidesWith(player))
			{
				Debug.d("Power Up Triggered at " + p.getPosition().toString() + "by player at " + player.getPosition().toString());
				p.trigger();
				i.remove();
			}
		}
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(float x, float y) {
		PowerUpType t = DormantPowerUp.makeRandom();
		this.list.add(new DormantPowerUp(x, y, vbom, camera, t, DormantPowerUp
				.getRegion(t), this));
		this.parent.attachChild(list.get(list.size() - 1));
		count++;
	}

	@Override
	public float makeNewDelay() {
		// TODO Auto-generated method stub
		return 0;
	}

}