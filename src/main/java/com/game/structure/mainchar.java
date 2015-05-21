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
	public Vector3f pos = new Vector3f(0, 0, 0);
	public Vector3f speed;
	public ArrayList<Vector3f> verts;
	public AABB bBox;
	public String tex;
	public boolean canJump = true;



	public mainchar(String obj, String tex, Vector3f pos) {

		this.tex = tex;
		//load verts from file to arraylist
		try {
			this.verts = loadVertFromFile(new File(obj));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.bBox = new AABB();
		this.setPos(pos);
		this.pos = this.getCenter();

		this.speed = new Vector3f(0, 0, 0);

		this.bBox.updateAABB(verts);

		//System.out.println(verts);


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
		//only add to gravity if the player is at y<0;
		//only allow jumping if player is on ground. e.g bottom y is 0;
		//if player dips below ground, then set bottom right corner to 0, and speed to 0, and don't add speed.
		/*
		pseudocode time
		if playerboty <=0:
			set player bot y to 0
			set player velocity to 0
			player can jump
		else:
			add gravity
			update player position
			canjump = false

		 */

		float playerBotY = this.bBox.botRight.y;

		if (playerBotY < 0) {
			//set player to rest on the floor, cancel speed
			this.setPos(new Vector3f(0, (0 - this.pos.y), 0));
			this.speed.y = 0;
			canJump = true;


		} else if (playerBotY == 0) {
			canJump = true;
			this.speed.y = 0;
		} else {
			//can assume player is in air. add gravity to acceleration, update player position
			canJump = false;
			this.speed.y -= .25;
			this.setPos(new Vector3f(0, this.pos.y + this.speed.y, 0));

		}

		//update
		this.bBox.updateAABB(this.verts);
		this.pos = this.getCenter();
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

	public boolean canJump() {

		return canJump;
	}

	public void jump() {
		if (canJump) {
			this.speed.y += 1.5;
			this.setPos(new Vector3f(0, 0, 0));
		}

	}
}
