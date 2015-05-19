package com.game.structure;

import com.game.math.Vector3f;

/**
 * Created by eamon_000 on 5/17/2015.
 */
public class monster implements actor {
	@Override
	public mesh getMesh() {
		return null;
	}

	@Override
	public Vector3f getCenter() {
		return null;
	}

	@Override
	public boolean isStatic() {
		return false;
	}

	@Override
	public boolean isPlayer() {
		return false;
	}
}
