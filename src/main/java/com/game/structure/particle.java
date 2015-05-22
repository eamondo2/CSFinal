package com.game.structure;

import com.game.math.Vector3f;
import com.game.util.AABB;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.game.util.fileLoader.loadVertFromFile;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by eamon_000 on 5/21/2015.
 */
public class particle implements rect {
	private boolean isAlive = true;
	private float yVary;
	private float scale = 0;
	private float lspeed = 0;
	private String mesh = "assets/particle.obj";
	private Vector3f pos = new Vector3f(0, 0, 0);
	private AABB bBox = new AABB();
	private float lifespan = 0;
	private ArrayList<Vector3f> verts = new ArrayList<Vector3f>();


	public particle(Vector3f ipos, float lifespan, float scale, float lspeed, float yVary) {

		//load verts from file to arraylist
		try {
			this.verts = loadVertFromFile(new File(mesh));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.lifespan = lifespan;
		this.scale = scale;
		this.lspeed = lspeed;
		this.yVary = yVary;
		this.scale(scale);
		this.bBox.updateAABB(this.verts);
		this.pos = this.getCenter();
		this.setPos(ipos);
		this.pos = this.getCenter();


	}


	@Override
	public AABB getAABB() {
		return this.bBox;
	}

	@Override
	public void render() {

		glColor3f(1, 0, 0);
		glBegin(GL_QUADS);
		for (Vector3f v : verts) {
			glVertex3f(v.x, v.y, v.z);
		}
		glEnd();

	}

	@Override
	public void update() {
		this.lifespan -= 1;
		if (this.lifespan <= 0) this.isAlive = false;
		this.setPos(this.pos.x - this.lspeed, this.pos.y + (Math.random() <= .5 ? -yVary : yVary), 0);
        this.bBox.updateAABB(this.verts);




	}

	@Override
	public boolean isStatic() {
		return false;
	}

	public boolean isAlive() {
		return this.isAlive;
	}

	@Override
	public boolean isCharacter() {
		return false;
	}

	@Override
	public ArrayList<Vector3f> getVerts() {
		return this.verts;
	}

	public void setPos(float x, float y, float z) {
		this.setPos(new Vector3f(x, y, z));
	}

	@Override
	public void setPos(Vector3f v) {
		//not sure if problem
		this.pos = this.getCenter();
		ArrayList<Vector3f> out = new ArrayList<Vector3f>();
		float dx = v.x - this.pos.x, dy = v.y - this.pos.y;
		for (Vector3f ve : this.verts) {
			out.add(new Vector3f(ve.x + dx, ve.y + dy, ve.z));


		}


		this.verts = out;
		this.bBox.updateAABB(verts);

	}

	@Override
	public Vector3f getCenter() {
		//possible problem spot
		float x = (this.bBox.topLeft.x + this.bBox.botRight.x) / 2;
		float y = (this.bBox.topLeft.y + this.bBox.botRight.y) / 2;
		return new Vector3f(x, y, 0);

	}

	@Override
	public String getName() {
		return "particle";
	}

	@Override
	public void renderAABB() {

	}

	@Override
	public void scale(float iScale) {
		ArrayList<Vector3f> out = new ArrayList<Vector3f>();
		for (Vector3f v : this.verts) {
			out.add(new Vector3f(v.x * iScale, v.y * iScale, v.z * iScale));

		}
		this.verts = out;
	}
}
