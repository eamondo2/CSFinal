package com.game.structures;

import com.game.math.Vector3f;
import com.game.util.AABB;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.game.util.FileLoader.loadVertFromFile;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by eamon_000 on 5/20/2015.
 */
public class Floor implements GenericRect {
    private ArrayList<Vector3f> verts = new ArrayList<Vector3f>();
    private AABB bBox = new AABB();
    private Vector3f pos = new Vector3f(0, 0, 0);

    public Floor(String obj, String tex, Vector3f ipos) {
        String tex1 = tex;
        //load verts from file to arraylist
        try {
            this.verts = loadVertFromFile(new File(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.bBox.updateAABB(this.verts);
        this.pos = this.getCenter();
        this.setPos(ipos);
        this.pos = this.getCenter();

	}


	@Override
	public AABB getAABB() {
        this.bBox.updateAABB(verts);
        return this.bBox;
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
        //possible problem spot
        float x = (this.bBox.topLeft.x + this.bBox.botRight.x) / 2;
        float y = (this.bBox.topLeft.y + this.bBox.botRight.y) / 2;
        return new Vector3f(x, y, 0);
    }

    @Override
    public String getName() {
        return "Floor";
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
