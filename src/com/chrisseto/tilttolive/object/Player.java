package com.chrisseto.tilttolive.object;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.chrisseto.tilttolive.base.Ball;
import com.chrisseto.tilttolive.util.Assets;
import com.chrisseto.tilttolive.util.BVector;




public class Player extends Ball implements SensorEventListener
{
	boolean hasShield,hasSpikes;
	int powerUpRadius;
	public Player(VertexBufferObjectManager vbom)
	{
		super(12,12,40,Assets.getInstance().ball_region,vbom);
		hasShield = false;
		hasSpikes = false;
		powerUpRadius = 0;
		//init position will always be in the middle of the screen
	}	

	@Override
	public void update()
	{
		//Process User input or just read in
		super.update();
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
				setVelocity(new BVector(e.values[0],e.values[1]));//This fill have to be updated * speedMult or something
				break;
			}
		}
		// TODO Auto-generated method stub
		
	}
}
