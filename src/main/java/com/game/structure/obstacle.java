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
    public float leftSpeed = 0;
    public float blue = 1;
    public ArrayList<Vector3f> verts;
	public AABB bBox;
	public String tex;
	public Vector3f pos = new Vector3f(0, 0, 0);

    public obstacle(String obj, String tex, Vector3f pos, float leftSpeed, float blue) {
        this.blue = blue;
        this.leftSpeed = leftSpeed;
        this.tex = tex;
		try {
			this.verts = loadVertFromFile(new File(obj));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.bBox = new AABB();
		this.setPos(pos);

		this.pos = this.getCenter();
	}

	@Override
	public AABB getAABB() {
        this.bBox.updateAABB(verts);
        return this.bBox;
    }

	@Override
	public void render() {
        glColor3f(0, 0, blue);
        glBegin(GL_QUADS);
		for (Vector3f v : verts) {
			glVertex3f(v.x, v.y, v.z);
		}
		glEnd();
	}

	@Override
	public void update() {
        //move left
        this.pos = this.getCenter();
        this.setPos(this.pos.x - leftSpeed, this.pos.y, this.pos.z);
        this.bBox.updateAABB(this.verts);




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

    public void setPos(float x, float y, float z) {
        this.setPos(new Vector3f(x, y, z));
    }

    @Override
	public void setPos(Vector3f v) {
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
		float x = (this.bBox.topLeft.x + this.bBox.botRight.x) / 2;
		float y = (this.bBox.topLeft.y + this.bBox.botRight.y) / 2;
		return new Vector3f(x, y, 0);
	}

    @Override
    public String getName() {
        return "obstacle";
    }

    @Override
    public void renderAABB() {
        glBegin(GL_POINTS);
        glColor3f(0, 0, 0);
        glVertex2f(this.bBox.topLeft.x, this.bBox.topLeft.y);
        glVertex2f(this.bBox.botRight.x, this.bBox.botRight.y);
        glEnd();
    }

	@Override
	public void scale(float iScale) {

	}

	@Override
	public boolean isAlive() {
		return false;
	}


}
