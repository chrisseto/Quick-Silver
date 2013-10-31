package com.chrisseto.tilttolive.base;

import java.util.ArrayList;
import java.util.Iterator;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.pool.GenericPool;

import com.chrisseto.tilttolive.object.powerup.DormantPowerUp.PowerUpType;
import com.chrisseto.tilttolive.util.Assets;

public abstract class Manager<T extends Sprite>  implements IUpdateHandler{
	protected ArrayList<T> list;
	protected final Scene parent;
	protected final VertexBufferObjectManager vbom;
	protected final Camera camera;
	protected int tRadius,spawnDelay,count;
	public Manager(Scene p, VertexBufferObjectManager vbom, Camera camera)
	{
		parent=p;
		this.vbom=vbom;
		this.camera = camera;
		list = new ArrayList<T>();
		parent.registerUpdateHandler(this);
		count=0;
		
	}
	
	public abstract void add(float x, float y);
	
	public void add()
	{
			this.add((float)Assets.random(tRadius, camera.getWidth()),(float)Assets.random(tRadius, camera.getHeight()));
	}
	
	public T get(int index)
	{
		return list.get(index);
	}
	
	public void remove(T index)
	{
		parent.detachChild(index);
	}
	
	public void remove(int index)
	{
		list.remove(index);
	}
	
	public abstract float makeNewDelay();
	
	protected void startSpawnTimer()
	{
		TimerHandler spawnHandler;
		//spawnHandler.isAutoReset();
		parent.registerUpdateHandler(spawnHandler = new 
	TimerHandler(spawnDelay, new ITimerCallback()
		{
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				//pTimerHandler.setTimerSeconds(makeNewDelay());
				pTimerHandler.reset();
				add();
			}
		}));
	}
}
