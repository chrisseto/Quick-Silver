package com.chrisseto.tilttolive.managment;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.os.Debug;

import com.chrisseto.tilttolive.base.Manager;
import com.chrisseto.tilttolive.object.EnemyBall;
import com.chrisseto.tilttolive.object.Player;
import com.chrisseto.tilttolive.util.Assets;

public class EnemyManger extends Manager<EnemyBall>{
	final Player player;
	public EnemyManger(Scene p, VertexBufferObjectManager vbom, Camera camera, Player player)
	{
		super(p, vbom, camera);
		tRadius=6;
		this.player = player;
		this.spawnDelay = 4;
		this.startSpawnTimer();
		add(70,70);
		add(20,20);
	}
	@Override
	public void onUpdate(float pSecondsElapsed) {
		for(EnemyBall b : list)
		{
			b.updateVelocity(list, player.getPosition());
			if(b.collidesWith(player))
			{
				//Game Over here
			}
		}
		
	}
	@Override
	public void add(float x, float y) { //Random location needs to be improved, not spawning on or too close to player. could check here?
		list.add(new EnemyBall(x, y, vbom, camera));
		this.parent.attachChild(list.get(list.size()-1));
		count++;
		org.andengine.util.debug.Debug.d("New enmey at (" + x + ", "+ y + ")" );
	}
	@Override
	public float makeNewDelay() {
		//This needs to be better
		//lower with less on screen
		//higher with more
		//faster as time progresses
		return (float)Assets.random((10/count)+1, (30/count)+2);
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
