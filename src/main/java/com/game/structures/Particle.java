package com.game.structures;

import com.game.GameMain;
import com.game.math.Vector3f;
import com.game.util.AABB;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.game.util.FileLoader.loadVertFromFile;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by eamon_000 on 5/21/2015.
 */
public class Particle implements GenericRect {
    private final float zDepth;
    private final float blue;
    private final float green;
    private final float red;
    private boolean isAlive = true;
	private float yVary;
	private float lspeed = 0;
	private Vector3f pos = new Vector3f(0, 0, 0);
	private AABB bBox = new AABB();
	private float lifespan = 0;
	private ArrayList<Vector3f> verts = new ArrayList<Vector3f>();


	public Particle(String obj, Vector3f ipos, float lifespan, float scale, float lspeed, float yVary, float red, float green, float blue, float zDepth) {
        this.zDepth = zDepth;
		String mesh = obj;
        this.red = red;this.green = green;this.blue = blue;
		//load verts from file to arraylist
		try {
			this.verts = loadVertFromFile(new File(mesh));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.lifespan = lifespan;
		float scale1 = scale;
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
        glPushMatrix();
        glTranslatef(0,0,zDepth);
		glColor3f(red, green, blue);
		glBegin(GL_QUADS);
		for (Vector3f v : verts) {
			glVertex3f(v.x, v.y, v.z);
		}
		glEnd();
        glPopMatrix();

	}

	@Override
	public void update() {
		this.lifespan -= 1;
		if (this.lifespan <= 0) this.isAlive = false;
		this.setPos((float) (this.pos.x - this.lspeed-(GameMain.hardMode?-.4:0)), this.pos.y + (Math.random() <= .5 ? -yVary : yVary), 0);
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
		return "Particle";
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
