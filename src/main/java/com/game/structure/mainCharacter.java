package com.game.structure;

import com.game.math.Vector3f;

import java.util.ArrayList;

/**
 * Created by eamon_000 on 5/17/2015.
 */
public class mainCharacter implements actor {

	private Vector3f force;
    private Vector3f position;
    private mesh m;

	public mainCharacter() {
        m = new mesh("player.obj", new Vector3f(0, 0, 0));
        this.force = new Vector3f(0, 0, 0);
        this.position = m.getPosition();
    }



	@Override
	public mesh getMesh() {
        return m;
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
		return true;
    }


    @Override
    public void setPosition(Vector3f vIn) {
        Vector3f diff = new Vector3f(vIn.x - this.position.x, vIn.y - this.position.y, vIn.z - this.position.z);
        ArrayList<Vector3f> verts = this.m.getMeshVerts();
        ArrayList<Vector3f> newVerts = new ArrayList<Vector3f>();
        for (Vector3f v : verts) {
            v.x += diff.x;
            v.y += diff.y;
            v.z += diff.z;
            newVerts.add(v);

        }
        this.m.setMeshVerts(newVerts);

	}

    @Override
    public mesh getAABB() {
        return null;
    }
}
