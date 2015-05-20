package com.game.structure;

import com.game.math.Vector3f;

/**
 * Created by eamon_000 on 5/17/2015.
 */
public class wall implements object {
	@Override
	public mesh getMesh() {
		return null;
	}

	@Override
	public Vector3f getCenter() {
		return null;
	}

	@Override
	public void render() {

	}

	@Override
	public boolean isStatic() {
		return true;
	}

	@Override
	public boolean isPlayer() {
		return false;
	}

    @Override
    public void setPosition(Vector3f vIn) {

    }


    @Override
    public mesh getAABB() {
        return null;
    }
}
