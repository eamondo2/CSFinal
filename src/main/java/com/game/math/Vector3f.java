package com.game.math;

/**
 * Created by Eamon on 5/18/2015.
 */
public class Vector3f {

    public float x = 0.0f;
    public float y = 0.0f;
    public float z = 0.0f;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f() {
    }

    public void normalize() {
        //normalizes this vector
    }

    public String toString() {
        return (x + " " + y + " " + z);
    }

}
