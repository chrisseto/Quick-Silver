package com.chrisseto.quicksilver.object;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.chrisseto.quicksilver.base.Ball;
import com.chrisseto.quicksilver.util.Assets;
import com.chrisseto.quicksilver.util.BVector;
import com.chrisseto.quicksilver.util.Collision;
import com.chrisseto.quicksilver.util.Point;

public class Player extends Sprite implements SensorEventListener {
	private static float speed = 1.5f;
	public static float[] accelCal = new float[3];

	public Player(VertexBufferObjectManager vbom, Camera camera) {
		super(camera.getCenterX(), camera.getCenterY(), Assets.getInstance().ball_region, vbom);
	}

	public boolean collidesWith(Ball b) {
		if (super.collidesWith(b))
		{
		/*	float sq = b.getRadius()*b.getRadius();
			float dx,dy;
			for(int i = 0; i < 3; i++)
			{
				dx = getVertexX(i+1) - b.getX();
				dy = getVertexY(i+1) - b.getY();
				if(sq <= (dx*dx) + (dy*dy))
					return true;
			}
			
			
			return false;// Add triangular collision here
			*/
			return Collision.collides(b, getX(), getY(), getRadius());
			
			//return true;
		}
		else
			return false;
	}

	private void checkBounds() {
		setX(bound(getX(), Assets.getInstance().boundingBox[2] - getRadius(), Assets.getInstance().boundingBox[0]
				+ getRadius()));
		setY(bound(getY(), Assets.getInstance().boundingBox[3] - getRadius(), Assets.getInstance().boundingBox[1]
				+ getRadius()));
	}

	public float getRadius() {
		return getWidth() / 2;
	}

	public Point getPosition() {
		return new Point(this.getX(), this.getY());
	}

	private void rotate(float x, float y) {

		if (Math.abs(x) > .05 && Math.abs(y) > .05) {
			float turn = -(float) Math.toDegrees(Math.atan2(x, y)) - 180;
			//if (turn <)
				setRotation(turn);
		}
	}

	@Override
	public void onSensorChanged(SensorEvent e) {
		synchronized (this) {
			switch (e.sensor.getType()) {
			case Sensor.TYPE_ACCELEROMETER:
				setX(getX() + ((e.values[1] - accelCal[1]) * speed));
				setY(getY() + ((-e.values[0] + accelCal[0]) * speed));
				rotate(e.values[1] - accelCal[1], e.values[0] - accelCal[0]);
				// rotate(-(float) Math.toDegrees(Math.atan2(e.values[1] -
				// accelCal[1], e.values[0] - accelCal[0])) - 180);// need
				// a
				// fix
				// for
				// crazy
				// rotation
				// while
				// standing
				// still/flat
				
				//Also fix orientation
				checkBounds();
				break;
			}
		}
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// Unused method

	}

	private float bound(float toBound, float high, float low) {
		if (toBound > high)
			return high;
		else if (toBound < low)
			return low;
		else
			return toBound;
	}
	
	private float getVertexY(int num)
	{
		switch(num)
		{
		case 1:
			return (float) (this.getY()+(Math.sin(Math.toRadians(getRotation())*(this.getHeight()/2))));
		case 2:
			return (float) (this.getY()+(Math.sin(Math.toRadians(getRotation()-60)*(this.getHeight()/2))));
		case 3:
			return (float) (this.getY()+(Math.sin(Math.toRadians(getRotation()+60)*(this.getHeight()/2))));
		}
		return 0;
	}
	
	private float getVertexX(int num)
	{
		switch(num)
		{
		case 1:
			return this.getX();
		case 2:
			return this.getX()-(this.getWidth()/2);
		case 3:
			return this.getX()+(this.getWidth()/2);
		}
		return 0;
	}
}
