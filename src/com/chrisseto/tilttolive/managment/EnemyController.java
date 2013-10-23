package com.chrisseto.tilttolive.managment;

import java.util.ArrayList;
import java.math.*;

import com.chrisseto.tilttolive.util.Assets;
import com.chrisseto.tilttolive.util.BVector;
import com.chrisseto.tilttolive.util.Timer;

public class EnemyController {
	private ArrayList<EnemyBall> reds;
	Timer spawnNext;

	EnemyController() {
		reds = new ArrayList<EnemyBall>();
		spawnNext = new Timer(1);
	}

	void draw() {
		for (int i = 0; i < reds.size(); i++)
			reds.get(i).draw();
	}

	void update(BVector player) {
		for (int i = 0; i < reds.size(); i++)
			reds.get(i).update(player, reds);
	}

	void destroyBall(int i) {
		reds.remove(i);
	}

	void endStage() {
		reds.clear();
	}

	boolean checkCollision(Player player) {
		for (EnemyBall b : reds) {
			if (player.checkCollision(b))
				return true;
		}
		return false;
	}

	void checkCollisionPowerUps(ArrayList<? extends PowerUpField> powerUps,
			ArrayList<? extends PlayerMod> playerMods) {
		for (int i = 0; i < powerUps.size(); i++) {
			for (int x = 0; x < reds.size(); x++) {
				if (powerUps.get(i).checkCollision(reds.get(x))) {
					switch (powerUps.get(i).getEffect()) {
					case KILL:
						destroyBall(x);
						break;
					}
				}
			}
		}
		for (int i = 0; i < playerMods.size(); i++) {
			for (int x = 0; x < reds.size(); x++) {
				if (playerMods.get(i).checkCollision(reds.get(x))) {
					switch (playerMods.get(i).getEffect()) {
					case KILL:
						destroyBall(x);
						break;
					}
					playerMods.get(i).collided();
				}
			}
		}
	}

	void generateEnemies() {
		if (spawnNext.checkDone()) {
			// TODO add random shape spawns after 30~ seconds
			double runningTime = System.currentTimeMillis() / 1000;
			double time1 = (30 / (runningTime + 1)) + 1;
			double time2 = (10 / (runningTime + 1));
			spawnNext = new Timer(Assets.random(time2, time1));
			reds.add(new EnemyBall(Assets.random(0, Assets.WIDTH), Assets.random(0, Assets.HEIGHT)));
			System.out.println("New Enemy spawned at "
					+ reds.get(reds.size() - 1).getPosition());
			System.out.println("Next Spawn in " + spawnNext.life + " seconds");
		}
	}

	void spawnRect(int x, int y, int Width, int Length) {
		for (int i = x; i < (x + Width); i += Assets.ENEMYSIZE) {
			reds.add(new EnemyBall(i, y));
			reds.add(new EnemyBall(i, y + Length));
		}
		for (int i = y; i < (y + Length); i += Assets.ENEMYSIZE) {
			reds.add(new EnemyBall(x, i));
			reds.add(new EnemyBall(x + Width, i));
		}
	}

	void spawnCircle(int x, int y, int radius) {
		for (float i = 0; i < (2 * Math.PI); i += (Math.PI / 8)) {
			reds.add(new EnemyBall(x + radius * Math.cos(i), y + radius * Math.sin(i)));
		}
	}
}
