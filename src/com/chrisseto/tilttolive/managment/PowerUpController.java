package com.chrisseto.tilttolive.managment;

import java.util.ArrayList;


public class PowerUpController
{
	ArrayList<PowerUp> dormantPowerUps;
	ArrayList<PowerUpField> activePowerUps;
	ArrayList<PlayerMod> playerMods;
	ArrayList<PowerUpProjectile> powerUpProjectiles;
	Timer spawnCounter;
	PowerUpController()
	{
		dormantPowerUps = new ArrayList<PowerUp>();
		activePowerUps = new ArrayList<PowerUpField>();
		powerUpProjectiles = new ArrayList<PowerUpProjectile>();
		playerMods = new ArrayList<PlayerMod>();
		spawnCounter= new Timer(Assets.random(1, 5));
	}
	void checkPlayerCollision(PlayerBall player) //This shit needs to be cleaned up
	{
		for ( int i = 0; i < dormantPowerUps.size(); i++)
		{
			if (player.checkCollision(dormantPowerUps.get(i)))
			{
				System.out.println("Player: " + player.position + " " + player.size);
				System.out.println("PowerUp: " + dormantPowerUps.get(i).getPosition() + " " + dormantPowerUps.get(i).size);
TopLoop: switch(dormantPowerUps.get(i).type)
	 {
		 //Note: Currently All PowerUps are generic will be more indepth Later
		 case 0: //Spikes
			 boolean needNew=true;
			 for (int x = 0; x < playerMods.size(); x++)
			 {
				 if (playerMods.get(x).getType() == PowerUpType.Spike)
				 {
					 playerMods.get(x).reset();
					 needNew=false;
					 break;
				 }
			 }
			 if (needNew)
				 playerMods.add(new SpikeMod(player.position));
			 break;
		 case 1: //Explosion
			 activePowerUps.add(new Explosion(dormantPowerUps.get(i).getPosition()));
			 break;
		 case 2: //Shield
			 for (int x = 0; x < playerMods.size(); x++)
			 {
				 if (playerMods.get(x).getType() == PowerUpType.Shield)
				 {
					 break TopLoop;
				 }
			 }
			 playerMods.add(new ShieldMod(player.position));
			 break;
	 }
	 dormantPowerUps.remove(i);
			}
		}
	}
	void draw()
	{
		for ( int i = 0; i < dormantPowerUps.size(); i++)
		{
			dormantPowerUps.get(i).draw();
			if (dormantPowerUps.get(i).done)
				dormantPowerUps.remove(i);
		}
		for (int i =0; i<activePowerUps.size(); i++)
		{
			activePowerUps.get(i).draw();
			if (activePowerUps.get(i).done)
				activePowerUps.remove(i);
		}
	}
	void spawnPowerUps()
	{
		if (spawnCounter.checkDone()&&dormantPowerUps.size()<=5)
		{
			dormantPowerUps.add(new PowerUp());
			spawnCounter = new Timer(Assets.random(0, (60/((System.currentTimeMillis()/1000)+9)+3)));
			System.out.println("new PowerUp spawned at point " + dormantPowerUps.get(dormantPowerUps.size()-1).getPosition());
			System.out.println("Next spawn in " + spawnCounter.life + " seconds");
		}
	}
	void updatePlayerMods(BVector player)
	{
		for (int i = 0; i < playerMods.size(); i++)
		{
			playerMods.get(i).position = player;
			playerMods.get(i).update();
			if (playerMods.get(i).done)
				playerMods.remove(i);
		}
	}
	void drawPlayerMods()
	{
		for (int i = 0; i < playerMods.size(); i++)
		{
			playerMods.get(i).draw();
		}
	}
}

