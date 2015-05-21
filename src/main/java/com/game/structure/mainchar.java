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
public class mainchar implements rect {
	public Vector3f speed;
	public ArrayList<Vector3f> verts;
	public AABB bBox;
	public String tex;



	public mainchar(String obj, String tex, Vector3f pos) {
		this.tex = tex;
		//load verts from file to arraylist
		try {
			this.verts = loadVertFromFile(new File(obj));
		} catch (IOException e) {
			e.printStackTrace();
		}


	}


	@Override
	public AABB getAABB() {
		return null;
	}

	@Override
	public void render() {
		glColor3f(0, 1, 0);
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
		return false;
	}

	@Override
	public boolean isCharacter() {
		return true;
	}

	@Override
	public ArrayList<Vector3f> getVerts() {
		return null;
	}

	@Override
	public void setPos(Vector3f v) {

	}

	@Override
	public Vector3f getCenter() {
		return null;
	}

	public boolean canJump() {

		return false;
	}
}
