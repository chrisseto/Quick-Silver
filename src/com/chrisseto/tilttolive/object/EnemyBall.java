package com.chrisseto.tilttolive.object;

import java.util.ArrayList;

import org.andengine.engine.camera.Camera;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.tilttolive.base.Ball;
import com.chrisseto.tilttolive.util.Assets;
import com.chrisseto.tilttolive.util.BVector;


public class EnemyBall extends Ball
{
	float speed;
	BVector velocity;
	public EnemyBall(float x, float y, VertexBufferObjectManager vbom,Camera camera)
	{
		super(x,y,20,Assets.getInstance().enemy_region,vbom,camera);
	}
	public void die()
	{
		//Spawns a Dead Enemy class which is just drawn and faded out
		//Could probs be done better
	}
	public void update(float x, float y)
	{
		this.setX(x+getX());
		this.setY(y+getY());
	}
	public void updateVelocity(ArrayList<EnemyBall> list, BVector player)
	{
		velocity = new BVector();
		velocity.add(moveToPlayer(player));
		velocity.add(getRepulse(list));
		updatePosition(velocity);
		
	}
	private BVector getRepulse(ArrayList<EnemyBall> list)
	{
		//TODO
		BVector temp = new BVector();
		return temp;
	}
	private BVector moveToPlayer(BVector player)
	{
		 	BVector temp = BVector.sub(player, getPosition());
		    temp.normalize();
		    return temp;
	}
}
