package com.game.util;

import com.game.gameMain;
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
            if (r.isCharacter()) {
                for (rect or : physList) {
                    if (!r.equals(or)) {
                        //System.out.println(r.getName() + " against " + or.getName());
                        if (collides(r.getAABB(), or.getAABB())) {
                            gameMain.gameOver = true;
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }


                    }
                }
            }
        }


    }

    public static boolean Intersects(rect a, rect b) {


        return false;
    }

    public static boolean collides(AABB a, AABB b) {

        if (Math.abs(a.pos.x - b.pos.x) < a.halfWidths.x + b.halfWidths.x) {
            if (Math.abs(a.pos.y - b.pos.y) < a.halfWidths.y + b.halfWidths.y) {
                return true;
            }
        }

        return false;
    }
    /*
    public static boolean inside(AABB a, Vector2Float b)
    {
        if(Math.abs(a.pos.x - b.x) < a.size.x)
        {
            if(Math.abs(a.pos.y - b.y) < a.size.y)
            {
                return true;
            }
        }
        return false;
    }
    */

    public void findEdgeNormals(rect r) {

    }





}