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
import com.chrisseto.quicksilver.util.BVector;


public class EnemyBall extends Ball
{
	BVector velocity;
	private static float ATTRACTION = 4;
	private static float SPEED = 1.5f;
	public EnemyBall(float x, float y, VertexBufferObjectManager vbom,Camera camera)
	{
		super(x,y,20,Assets.getInstance().enemy_region,vbom,camera);
	}
	public void die()
	{
		unregisterUpdateHandler(getRootEntity());
		this.registerEntityModifier(new AlphaModifier(.5f, 1, 0, new IEntityModifierListener() {
			
			@Override
			public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
				
				
			}
			
			@Override
			public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
				// TODO Auto-generated method stub
				Assets.getInstance().engine.runOnUpdateThread(new Runnable() {
					
					@Override
					public void run() {
						detachSelf();
						
					}
				});
			}
		}));
		//Spawns a Dead Enemy class which is just drawn and faded out
		//Could probs be done better
		//Entity modifier would work much better
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
		BVector vel = new BVector();
		BVector temp;
		float dist;
		for(EnemyBall b : list)
		{
		 temp = BVector.sub(getPosition(), b.getPosition());
		 dist = (float)getPosition().distance(b.getPosition());
	      if (dist <= 0)
	      {
	        temp.div(.00001f);   
	        temp.mult(ATTRACTION*getSize()/(.000001f*.000001f));
	      }
	      else
	      {
	        temp.div(dist);   
	        temp.mult(ATTRACTION*getSize()/(dist*dist));
	      }
	      vel.add(temp);
		}
		return vel;
	}
	private BVector moveToPlayer(BVector player)
	{
		 	BVector temp = BVector.sub(player, getPosition());
		    temp.normalize();
		    temp.mult(SPEED);
		    return temp;
	}
}
