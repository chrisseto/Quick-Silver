package com.chrisseto.quicksilver.object.powerup;

import java.util.Random;

import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.chrisseto.quicksilver.base.Ball;
import com.chrisseto.quicksilver.managment.PowerUpManger;
import com.chrisseto.quicksilver.util.Assets;

public class DormantPowerUp extends Ball {

	PowerUpType type;
	final PowerUpManger parent;

	public DormantPowerUp(float x, float y, VertexBufferObjectManager vbom,
			Camera camera, PowerUpType t, ITextureRegion tex,
			PowerUpManger parent) {

		super(x, y, 30, tex, vbom, camera);
		this.type = t;
		this.parent = parent;
	}

	public void trigger() {
		// Add effect
		this.parent.active.add(this.getX(), this.getY(), type);
		Assets.getInstance().engine.runOnUpdateThread(new Runnable() {

			@Override
			public void run() {
				detachSelf();

			}
		});
	}

	// Static methods and Enum
	public static PowerUpType makeRandom() {
		return PowerUpType.values()[new Random()
				.nextInt(PowerUpType.values().length)];
	}

	public static ITextureRegion getRegion(PowerUpType type) {
		switch (type) {
		case Explosion:
			return Assets.getInstance().explosion_pu;
		case Shield:
			return Assets.getInstance().shield_pu;
		case Spikes:
			return Assets.getInstance().spike_pu;
		default:
			return Assets.getInstance().explosion_pu;
		}
	}

	// Could be a linked list... hmm
	public enum PowerUpType {
		Explosion, Shield, Spikes
	}
}
