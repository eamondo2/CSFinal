package com.game.structure;

import com.game.math.Vector3f;

import java.util.ArrayList;

/**
 * Created by eamon_000 on 5/17/2015.
 */
public class wall implements object {
	private mesh m;
	private String tex;
	private Vector3f position;
	public wall(String m, Vector3f position, String tex){
		this.m = new mesh(m, position);
		this.tex = tex;
		this.position = position;
	}

	@Override
	public mesh getMesh()
	{
		return m;
	}

	@Override
	public Vector3f getCenter() {
		return position;
	}

	@Override
	public void render() {
		this.getMesh().render();
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
        return null;
    }
}
