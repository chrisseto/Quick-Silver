package com.chrisseto.tilttolive.object;

import android.graphics.Color;
import com.chrisseto.tilttolive.base.Ball;
import com.chrisseto.tilttolive.util.Assets;




public class Player extends Ball
{
	boolean hasShield,hasSpikes;
	int powerUpRadius;
	public Player()
	{
		super(Assets.WIDTH/2,Assets.HEIGHT/2,Assets.PLAYERSIZE,Color.BLUE);
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
	
	public void addPowerUp(PowerUpType type)
	{
			switch(type)
			{
			case Shield:
					hasShield = true;
				break;
			case Spike:
					hasSpikes = true;
				break;
			}
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
}
