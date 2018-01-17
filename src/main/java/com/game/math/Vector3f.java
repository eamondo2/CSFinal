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

    public void normalise(Vector3f dest) {
        float l = this.length();
        if(dest == null) {
            dest = new Vector3f(this.x / l, this.y / l, this.z / l);
        } else {
            dest.set(this.x / l, this.y / l, this.z / l);
        }

    }
    public void normalise() {
        float l = this.length();
        this.x/=l;
        this.y/=l;
        this.z/=l;

    }
    public final float length() {
        return (float)Math.sqrt((double)this.lengthSquared());
    }
    public float lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public Vector3f set(Vector3f src) {
        this.x = src.getX();
        this.y = src.getY();
        this.z = src.getZ();
        return this;
    }
    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public String toString() {
        return (x + " " + y + " " + z);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
}
