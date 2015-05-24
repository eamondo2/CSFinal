package com.game.structure;

import com.game.gameMain;
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
	public Vector3f pos = new Vector3f(0, 0, 0);
    public boolean touchingfloor = false;
    public Vector3f speed = new Vector3f(0, 0, 0);
    public ArrayList<Vector3f> verts = new ArrayList<Vector3f>();
    public AABB bBox = new AABB();
    public String tex = " ";
    public boolean canJump = true;


    public mainchar(String obj, String tex, Vector3f ipos) {

		this.tex = tex;
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
        /*
        if bottom is at 0, don't add gravity. don't set position, just leave it. allow jumping. update position based on speed.
        if bottom below 0, set bottom to 0. allow jumping, dont add gravity
        if bottom above 0, dont set position, add gravity, update position based on speed. disallow jumping

         */
        this.pos.y = (this.pos.y < .1 ? 0 : this.pos.y);
        this.bBox.updateAABB(this.verts);
        Vector3f bottomRight = this.getAABB().botRight;
        float x = bottomRight.x, y = bottomRight.y;
        //test for collision
        if (y < 0 || this.pos.y + this.speed.y < 0) {
            //below floor
	        // System.out.println("BELOW");
	        this.speed.y = 0;
            this.touchingfloor = true;
            this.setPos(this.pos.x, 1, this.pos.z);
        }
        if (y == 0) {
            //on floor
	        // System.out.println("ON");
	        if (!this.touchingfloor) this.speed.y = 0;
            this.touchingfloor = true;
        }
        if (y > 0) {
	        //System.out.println("ABOVE");
	        this.touchingfloor = false;
        }
        //add gravity
        if (!this.touchingfloor) this.speed.y -= .25;
        //update position
        this.pos = this.getCenter();
        this.setPos(this.pos.x, this.pos.y + this.speed.y, this.pos.z);


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
        return "mainchar";
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
		ArrayList<Vector3f> out = new ArrayList<Vector3f>();
		for (Vector3f v : this.verts) {
			out.add(new Vector3f(v.x * iScale, v.y * iScale, v.z * iScale));

		}
		this.verts = out;
	}

	@Override
	public boolean isAlive() {
		return false;
	}

	public boolean canJump() {

        return touchingfloor;
    }

	public void jump() {
        //possible problem spot
        if (touchingfloor) {
            this.speed.y += 1.5;
            gameMain.playSFX("assets/jump.wav");

        }

	}
}
