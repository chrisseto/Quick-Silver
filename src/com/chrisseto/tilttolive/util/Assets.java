package com.chrisseto.tilttolive.util;

//import android.graphics.Bitmap;
import java.util.Random;

//This class is purely for resources and what not

public class Assets {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static final int PLAYERSIZE = 20;
	public static final int ENEMYSIZE = 12;
	public static final int POWERUPSIZE = 19;
	public static final int SPIKERADIUS = 30;
	public static final int SHIELDRADIUS = 25;
	public static double random(double lower, double upper)
	{
		return new Random().nextDouble() * upper + lower;
	}
	public enum Effect {
		Kill, Freeze, Shock, Statis
	}

	public enum PowerUpType {
		Blast, Spike, Shield;
		public static final int length = 3;
	}
}
