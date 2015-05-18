package com.game.structure;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by eamon_000 on 5/17/2015.
 */
public class wall implements object {
	@Override
	public mesh getMesh() {
		return null;
	}

	@Override
	public Vec2d getCenter() {
		return null;
	}

	@Override
	public boolean isStatic() {
		return true;
	}

	@Override
	public boolean isPlayer() {
		return false;
	}
}
