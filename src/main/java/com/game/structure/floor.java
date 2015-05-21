package com.game.structure;

import com.game.math.Vector3f;
import com.game.util.AABB;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by eamon_000 on 5/20/2015.
 */
public class floor implements rect {
	private ArrayList<Vector3f> verts;
	private AABB bBox;
	private Vector3f pos;

	public floor() {
		this.verts = new ArrayList<Vector3f>();

	}


	@Override
	public AABB getAABB() {
		return null;
	}

	@Override
	public void render() {
		glColor3f(0, 1, 0);
		glBegin(GL_QUADS);
		glVertex3f(-100, -3, 0);
		glVertex3f(-100, 0, 0);
		glVertex3f(100, 0, 0);
		glVertex3f(100, -3, 0);
		glEnd();
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
		ArrayList<Vector3f> out = new ArrayList<Vector3f>();
		for (Vector3f vold : this.verts) {
			vold.x += (this.pos.x - v.x);
			vold.y += (this.pos.y - v.y);
			vold.z += (this.pos.z - v.z);
			out.add(vold);
		}
		this.verts = out;
		this.bBox = this.getAABB();
	}

	@Override
	public Vector3f getCenter() {
		return null;
	}
}
