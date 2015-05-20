package com.game.structure;

import com.game.math.Vector3f;
import com.game.util.AABB;

import java.util.ArrayList;

/**
 * Created by eamon_000 on 5/20/2015.
 */
public interface rect {
	//suuper basic rect class for handling the player and the obstacles.
	//in code defined quads
	//

	AABB getAABB();

	void render();

	void update();

	boolean isStatic();

	boolean isCharacter();

	ArrayList<Vector3f> getVerts();

	void setPos(Vector3f v);


}
