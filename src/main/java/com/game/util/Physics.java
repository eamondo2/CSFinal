package com.game.util;

import com.game.math.Vector3f;
import com.game.structure.rect;

import java.util.ArrayList;

/**
 * Brought into existence by Eamon on 5/19/2015.
 */
//This will do ALL the physics.
public class Physics {
    private static Vector3f v = null;
    //This will consist of simple AABB collision code. Will make a method to get collision info from obj2D
    //implementation of SAT collision code and MTV check

    public static void updatePhysics(ArrayList<rect> physList) {
        for (rect r : physList) {
            for (rect or : physList) {
                if (!r.equals(or)) {
                    System.out.println(r.getName());
                    //x then y
                    Vector3f rv = r.getAABB().topLeft;
                    Vector3f rv2 = r.getAABB().botRight;
                    Vector3f ov = or.getAABB().topLeft;
                    Vector3f ov2 = or.getAABB().botRight;

                    boolean xCollides = (Intersects(rv.x, rv2.x, ov.x, ov2.x));
                    boolean yCollides = (Intersects(rv.y, rv2.y, ov.y, ov2.y));
                    if (xCollides && yCollides) {
                        System.out.println("FUCKYES");
                        float x = v.x;
                    }


                }
            }
        }


    }

    public static boolean Intersects(float a, float b, float c, float d) {
        if (a < c && c < b) {
            //right edge
            //a-b contains c
            return true;
        }
        if (a < d && d < b) {
            //left edge
            //a-b contains d
            return true;
        }
        return a < c && c < b && a > d && d < b;


    }

    public void findEdgeNormals(rect r) {

    }





}