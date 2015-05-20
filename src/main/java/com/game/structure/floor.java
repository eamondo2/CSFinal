package com.game.structure;

import com.game.math.Vector3f;

/**
 * Created by eamon_000 on 5/17/2015.
 */
public class floor implements object {
    private mesh m;

    public floor() {
        m = new mesh("floor.obj", new Vector3f(0, 0, 0));
    }

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
        this.m.render();
    }

	@Override
	public boolean isStatic() {
		return false;
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
