package com.chrisseto.quicksilver.object;

import java.util.ArrayList;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;

import com.chrisseto.quicksilver.base.Ball;
import com.chrisseto.quicksilver.util.Assets;
import com.chrisseto.quicksilver.util.Vector;


public class EnemyBall extends Ball
{
	Vector velocity;
	private static float ATTRACTION = 4;
	private static float SPEED = 1.5f;
	public EnemyBall(float x, float y)
	{
		super(x,y,20,Assets.getInstance().enemy_region);
	}
	public void die()
	{
		unregisterUpdateHandler(getRootEntity());
		this.registerEntityModifier(new AlphaModifier(.25f, 1, 0, new IEntityModifierListener() {
			
			@Override
			public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
				
				
			}
			
			@Override
			public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
				Assets.getInstance().engine.runOnUpdateThread(new Runnable() {
					
					@Override
					public void run() {
						detachSelf();
						
					}
				});
			}
		}));
	}
	public void update(float x, float y)
	{
		this.setX(x+getX());
		this.setY(y+getY());
	}
	public void updateVelocity(ArrayList<EnemyBall> list, Vector player)
	{
		velocity = new Vector();
		velocity.add(moveToPlayer(player));
		velocity.add(getRepulse(list));
		updatePosition(velocity);
		
	}
	private Vector getRepulse(ArrayList<EnemyBall> list)
	{
		//TODO
		Vector vel = new Vector();
		Vector temp;
		float dist;
		for(EnemyBall b : list)
		{
		 temp = ((Vector)getPosition()).substract((Vector)b.getPosition());
		 dist = (float)getPosition().distance(b.getPosition());
	      if (dist <= 0)
	      {
	        temp.divide(.00001f);   
	        temp.multiply(ATTRACTION*getDiameter()/(.000001f*.000001f));
	      }
	      else
	      {
	        temp.divide(dist);   
	        temp.multiply(ATTRACTION*getDiameter()/(dist*dist));
	      }
	      vel.add(temp);
		}
		return vel;
	}
	private Vector moveToPlayer(Vector player)
	{
		   return player.substract((Vector) getPosition()).normalize().multiply(SPEED);
	}
}
