package com.chrisseto.quicksilver;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.chrisseto.quicksilver.events.ScoreEvents;

public class ScoreKeeper{
	int currentScore;
	float multiplier, multiplierCheckPoint;
	Timer timer;
	TimerTask task;
	final long DELAY = 1000; 
	ArrayList<ScoreEvents> listeners;
	
	
	public ScoreKeeper()
	{
		currentScore = 0;
		multiplier = 1.0f;
		timer = new Timer();
		listeners = new ArrayList<ScoreEvents>();
		multiplierCheckPoint = 10;
		
		task = new TimerTask() {
			
			@Override
			public void run() {
				if(multiplier > 1)
					multiplier-=0.01f;
				if(multiplier >= multiplierCheckPoint)
					emitMultiplier();
			}
		};
	}

	public void stop()
	{
		timer.cancel();
	}

	private void start()
	{
		timer.schedule(task, DELAY);
	}
	
	public void addScore()
	{
		multiplier += 0.1f;
		currentScore += multiplier;
	}

	public float getMultiplier()
	{
		return multiplier;
	}
	
	public int getScore()
	{
		return currentScore;
	}
	
	public void addListener(ScoreEvents listener)
	{
		listeners.add(listener);
	}
	
	private void emitMultiplier()
	{
		for (ScoreEvents e : listeners) {
			e.multiplierCheckPoint(multiplier);
		}
		//TODO come up with a better equation
		multiplierCheckPoint *=2;
	}
	
}
