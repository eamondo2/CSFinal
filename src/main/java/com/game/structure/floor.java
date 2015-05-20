package com.game.structure;

import com.game.math.Vector3f;
import com.game.util.AABB;

import java.util.ArrayList;

/**
 * Created by eamon_000 on 5/20/2015.
 */
public class floor implements rect {
	private ArrayList<Vector3f> verts;

	public floor() {
		this.verts = new ArrayList<Vector3f>();

	}


	@Override
	public AABB getAABB() {
		return null;
	}

	@Override
	public void render() {

	}

	@Override
	public void update() {

	}

	@Override
	public boolean isStatic() {
		return true;
	}

	@Override
	public boolean isCharacter() {
		return false;
	}

	@Override
	public ArrayList<Vector3f> getVerts() {
		return verts;
	}

	@Override
	public void setPos(Vector3f v) {

	}
}
