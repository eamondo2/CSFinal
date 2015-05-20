package com.game.structure;

import com.game.math.Vector3f;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by eamon_000 on 5/17/2015.
 */
public class floor implements object {
    private mesh m;
	private Vector3f position;
	private String tex;
    public floor(String m, Vector3f position, String tex) {
        this.m = new mesh(m, position);
	    this.tex = tex;
	    this.position = position;
	    this.setPosition(position);


    }

	@Override
	public mesh getMesh() {
		return null;
	}

	@Override
	public Vector3f getCenter() {
		return this.position;
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
    public AABB2d getAABB() {
        return this.getMesh().getAABB();
    }
}
