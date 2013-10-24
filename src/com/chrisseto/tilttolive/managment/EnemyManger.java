package com.chrisseto.tilttolive.managment;

import java.util.ArrayList;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.tilttolive.object.EnemyBall;
import com.chrisseto.tilttolive.object.Player;

public class EnemyManger implements IUpdateHandler {
	final Scene parent;
	final VertexBufferObjectManager vbom;
	final Camera camera;
	ArrayList<EnemyBall> reds;
	final Player player;
	public EnemyManger(Scene p, VertexBufferObjectManager vbom, Camera camera, Player player)
	{
		this.parent = p;
		this.vbom = vbom;
		this.camera = camera;
		this.player = player;
		parent.registerUpdateHandler(this);
		reds = new ArrayList<EnemyBall>();
		addEnemy(70,70);
	}
	@Override
	public void onUpdate(float pSecondsElapsed) {
		for(EnemyBall b : reds)
		{
			b.updateVelocity(reds, player.getPosition());
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
}
