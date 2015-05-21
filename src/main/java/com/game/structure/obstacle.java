package com.game.structure;

import com.game.math.Vector3f;
import com.game.util.AABB;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.game.util.fileLoader.loadVertFromFile;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by eamon_000 on 5/20/2015.
 */
public class obstacle implements rect {

	public ArrayList<Vector3f> verts;
	public AABB bBox;
	public String tex;
	public Vector3f pos;

	public obstacle(String obj, String tex, Vector3f pos) {
		this.tex = tex;
		try {
			this.verts = loadVertFromFile(new File(obj));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.pos = pos;
	}

	@Override
	public AABB getAABB() {
		return null;
	}

	@Override
	public void render() {
		glColor3f(0, 0, 1);
		glBegin(GL_QUADS);
		for (Vector3f v : verts) {
			glVertex3f(v.x, v.y, v.z);
		}
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
		return null;
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
		this.bBox = new AABB();
		this.bBox.updateAABB(verts);
	}

	@Override
	public Vector3f getCenter() {
		return null;
	}
}
