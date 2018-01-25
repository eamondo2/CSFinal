package com.game.math;

/**
 * Created by Eamon on 5/18/2015.
 */
public class Matrix4f {
    public static final int SIZE = 4 * 4;
    public float[] elements = new float[SIZE];

    public Matrix4f() {
    }

    public static Matrix4f identity() {
        // Initialize a new matrix
        Matrix4f matrix = new Matrix4f();

        // The Identity Matrix:
        // [ 1 , 0 , 0 , 0 ]
        // [ 0 , 1 , 0 , 0 ]
        // [ 0 , 0 , 1 , 0 ]
        // [ 0 , 0 , 0 , 1 ]
        // [row 0 + col 0 * 4] = 1.0f etc..
        matrix.elements[0 + 0 * 4] = 1.0f;
        matrix.elements[1 + 1 * 4] = 1.0f;
        matrix.elements[2 + 2 * 4] = 1.0f;
        matrix.elements[3 + 3 * 4] = 1.0f;

        // Return our created Identity Matrix
        return matrix;
    }

    // this translation function basically gives us the ability to
// move any of our objects by simply inputing a vector3f to it.
// Say you wanted to move an object 5 places to the right, you'd
// simple add a vector3f which equalled (0.0f, 5.0f, 0.0f) to your
// matrix and it would translate it 5 places to the right.
    public static Matrix4f translate(Vector3f vector) {
        Matrix4f matrix = identity();
        matrix.elements[0 + 3 * 4] = vector.x;
        matrix.elements[1 + 3 * 4] = vector.y;
        matrix.elements[2 + 3 * 4] = vector.z;
        return matrix;
    }

    // Our rotation function does exactly what it says on
// the tin. We give it the angle with which we want to
// rotate our object and it converts it to radians and
// performs a matrix rotation given the cos and sin
// values computed from this radian.
    public static Matrix4f rotate(float angle) {
        Matrix4f matrix = identity();
        float r = (float) Math.toRadians(angle);
        float cos = (float) Math.cos(r);
        float sin = (float) Math.sin(r);

        matrix.elements[0 + 0 * 4] = cos;
        matrix.elements[1 + 0 * 4] = sin;
        matrix.elements[0 + 1 * 4] = -sin;
        matrix.elements[1 + 1 * 4] = cos;
        return matrix;
    }


}
