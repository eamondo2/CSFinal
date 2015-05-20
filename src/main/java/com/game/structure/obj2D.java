package com.game.structure;

import com.game.math.Vector3f;

/**
 * Created by eamon_000 on 5/17/2015.
 */
public interface obj2D {
	//will be the master class for all objects, moving or static.
	//will contain the mesh of points for rendering, as well as anything else I can think of.

	mesh getMesh();

	Vector3f getCenter();

	void render();

	boolean isStatic();

	boolean isPlayer();

    void setPosition(Vector3f vIn);

    AABB2d getAABB();



}
