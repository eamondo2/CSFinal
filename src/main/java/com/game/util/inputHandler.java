package com.game.util;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;


/**
 * Created by Eamon on 5/18/2015.
 */
public class inputHandler extends GLFWKeyCallback {
    public static boolean[] keys = new boolean[65535];

    //this handles the input
    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        //handle escape
        keys[key] = action != GLFW_RELEASE;
        System.out.println(key);
        System.out.println(keys[key]);


    }
}
