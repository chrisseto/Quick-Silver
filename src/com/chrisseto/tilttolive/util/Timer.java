package com.chrisseto.tilttolive.util;

//Timer.java Timer class for Explosions PowerUps etc
//May or may not work depending on host device -Worked Fine on my PC but meant for android

public class Timer
{
	long time;
	double life;
	public Timer(double time) //Should be in seconds
	{
		life = time * 1000; //Time -> millisecs
		time = System.currentTimeMillis();
	}
	public boolean checkDone()
	{
		return (System.currentTimeMillis() >= time + life) ? true : false;
	}

}
