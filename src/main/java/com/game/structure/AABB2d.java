package com.game.structure;

import com.game.math.Vector3f;

import java.util.ArrayList;

/**
 * Created by eamon_000 on 5/20/2015.
 */
public class AABB2d {
	private Vector3f[] aabbVerts;

	public AABB2d(Vector3f botL, Vector3f upLeft, Vector3f upRight, Vector3f botRight){
		aabbVerts = new Vector3f[]{botL, upLeft, upRight, botRight};

	}





}
