package com.game.structure;

import com.game.math.Vector3f;

/**
 * Created by eamon_000 on 5/17/2015.
 */
public class movingWall extends wall {

	public movingWall(String m, Vector3f point, String tex) {
		super(m, point, tex);
	}

	@Override
	public boolean isStatic() {
		return false;
	}
}
