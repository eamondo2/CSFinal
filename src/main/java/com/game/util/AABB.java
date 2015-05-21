package com.game.util;

import com.game.math.Vector3f;

import java.util.ArrayList;

/**
 * Created by eamon_000 on 5/20/2015.
 */
public class AABB {
	//will be an AABB for the objects.
	//derived from the verts passed in
	public Vector3f topLeft;
	public Vector3f botRight;

	public AABB() {
        this.topLeft = new Vector3f(0, 0, 0);
        this.botRight = new Vector3f(0, 0, 0);

	}


	public Vector3f getMTV(AABB other) {

		return null;
	}

	public void updateAABB(ArrayList<Vector3f> vin) {
		AABB out = new AABB();
		Vector3f first = vin.get(0);
		float maxx = first.x, maxy = first.y, minx = first.x, miny = first.y;
		for (Vector3f v : vin) {
			if (v.x < minx) minx = v.x;
			if (v.x > maxx) maxx = v.x;
			if (v.y < miny) miny = v.y;
			if (v.y > maxy) maxy = v.y;
			//values should theoretically reach the lowest/highest respectively.

		}

		this.topLeft = new Vector3f(minx, maxy, 0);
		this.botRight = new Vector3f(maxx, miny, 0);


	}




}
