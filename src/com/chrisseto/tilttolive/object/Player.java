package com.chrisseto.tilttolive.object;

import org.andengine.engine.camera.Camera;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.chrisseto.tilttolive.base.Ball;
import com.chrisseto.tilttolive.util.Assets;
import com.chrisseto.tilttolive.util.BVector;




public class Player extends Ball implements SensorEventListener
{
	boolean hasShield,hasSpikes,first;
	int powerUpRadius;
	float speed;
	float[] accelCal;
	
	public Player(VertexBufferObjectManager vbom,Camera camera)
	{
		super(12,12,38,Assets.getInstance().ball_region,vbom,camera);
		hasShield = false;
		hasSpikes = false;
		powerUpRadius = 0;
		setX(camera.getCenterX());
		setY(camera.getCenterY());
		first = true;
		speed = 1.5f;
		//init position will always be in the middle of the screen
	}	
	
	private void breakShield()
	{
		if(hasShield && !hasSpikes)
			powerUpRadius=0;
	}
	public boolean checkCollisionPowerUps(BVector pos)
	{
		return false;
	}

	@Override
	public void update()
	{
		super.update();
		checkBounds();
	}
	//For power ups etc
	//Has another ball "kill zone?"
	//check collisionkillzone + check collision
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent e) {
		synchronized(this)
		{
			switch(e.sensor.getType())
			{
			case Sensor.TYPE_ACCELEROMETER:
				//x = e.value[0]
				//y = e.value[1]
				if(first)
				{
					first = false;
					accelCal = new float[3];
					System.arraycopy(e.values, 0, accelCal, 0, e.values.length);
				}
				setVelocity(new BVector((e.values[1]-accelCal[1]),(-e.values[0]+accelCal[0])));//This fill have to be updated * speedMult or something
				update();
				break;
			}
		}
		// TODO Auto-generated method stub
		
	}
}
