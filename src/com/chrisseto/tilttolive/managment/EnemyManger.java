package com.chrisseto.tilttolive.managment;

import java.util.ArrayList;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.tilttolive.object.EnemyBall;
import com.chrisseto.tilttolive.object.Player;

public class EnemyManger extends Manager<EnemyBall>{
	ArrayList<EnemyBall> reds;
	final Player player;
	public EnemyManger(Scene p, VertexBufferObjectManager vbom, Camera camera, Player player)
	{
		super(p, vbom, camera);
		this.player = player;
		reds = new ArrayList<EnemyBall>();
		addEnemy(70,70);
		addEnemy(20,20);
	}
	@Override
	public void onUpdate(float pSecondsElapsed) {
		for(EnemyBall b : reds)
		{
			b.updateVelocity(reds, player.getPosition());
			if(b.collidesWith(player))
			{
				//Game Over here
			}
		}
		
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	public void addEnemy(float x, float y)
	{
		reds.add(new EnemyBall(x, y, vbom, camera));
		this.parent.attachChild(reds.get(reds.size()-1));
	}
	@Override
	public void add(float x, float y) {
		// TODO Auto-generated method stub
		
	}
}
