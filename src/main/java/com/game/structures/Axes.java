package com.game.structures;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by eamon_000 on 5/20/2015.
 */
public class Axes {

	public void render() {
		glBegin(1);
		glColor3f(0, .5f, 0);
		glVertex3f(-100, 0, 0);
		glVertex3f(100, 0, 0);
		glColor3f(0, 0, .5f);
		glVertex3f(0, -100, 0);
		glColor3f(0, .5f, 0);
		glVertex3f(0, 100, 0);
		glColor3f(0, .5f, .5f);
		glVertex3f(0, 0, -100);
		glVertex3f(0, 0, 100);
		glEnd();

	}
}

