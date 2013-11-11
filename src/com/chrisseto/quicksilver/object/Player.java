package com.chrisseto.quicksilver.object;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.chrisseto.quicksilver.base.Ball;
import com.chrisseto.quicksilver.util.Assets;
import com.chrisseto.quicksilver.util.BVector;




public class Player extends Ball implements SensorEventListener
{
	boolean hasShield,hasSpikes,first;
	int powerUpRadius;
	float speed;
	float[] accelCal;
	
	public Player(VertexBufferObjectManager vbom,Camera camera)
	{
		super(camera.getCenterX(),camera.getCenterY(),35,Assets.getInstance().ball_region,vbom,camera); //Note to self change player size to 35ish and recreate png
		//Notes on leaking border
		//Make region larger/smaller?
		//add transparent edge to png?
		//try texture packer?
		//Make sizes power of 2? (Random internet tip)
		hasShield = false;
		hasSpikes = false;
		powerUpRadius = 0;
		first = true;
		speed = 1.5f;
		//init position will always be in the middle of the screen
	}	
	
	private void breakShield()
	{
		if(hasShield && !hasSpikes)
			powerUpRadius=0;
	}

	@Override
	public void update()
	{
		super.update();
		checkBounds();
		/*
		float  dx = touchX -  player.getX();
        float  dy = touchY -  player.getY();
       
        double  Radius = Math.atan2(dy,dx);
        double Angle = Radius * 180 / PI;
             
        player.setRotation((float)Angle);
*/
		
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
				setVelocity(new BVector((e.values[1]-accelCal[1])*speed,(-e.values[0]+accelCal[0])*speed));
				this.setRotation(-(float)Math.toDegrees(Math.atan2(e.values[1]-accelCal[1],e.values[0]-accelCal[0]))-180);//need a fix for crazy rotation while standing still/flat
				update();
				break;
			}
		}
	}
}
