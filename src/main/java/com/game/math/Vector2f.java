package com.game.math;

/**
 * Created by Eamon on 5/18/2015.
 */
public class Vector2f {

	public float x = 0.0f;
	public float y = 0.0f;

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2f() {
	}

    public String toString() {
		return (x + " " + y);
	}

}
