package com.game.structure;

import com.game.math.Vector3f;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by eamon_000 on 5/20/2015.
 */
public class axes implements obj2D {
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
		glBegin(1);
		glColor3f(0, .5f, 0);
		glVertex3f(-100, 0, 0);
		glVertex3f(100, 0, 0);
		glColor3f(0, 0, .5f);
		glVertex3f(0, -100, 0);
		glVertex3f(0, 100, 0);
		glColor3f(0, .5f, .5f);
		glVertex3f(0, 0, -100);
		glVertex3f(0, 0, 100);
		glEnd();

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
	public AABB2d getAABB() {
		return null;
	}
}
