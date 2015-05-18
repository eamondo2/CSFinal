package com.game.structure;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by eamon_000 on 5/17/2015.
 */
public interface obj2D {
	//will be the master class for all objects, moving or static.
	//will contain the mesh of points for rendering, as well as anything else I can think of.

	public mesh getMesh();

	public Vec2d getCenter();

	public boolean isStatic();

	public boolean isPlayer();


}
