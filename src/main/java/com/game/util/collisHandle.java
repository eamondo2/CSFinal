package com.game.util;

import com.game.math.Vector3f;

/**
 * Created by Eamon on 5/20/2015.
 */
public class collisHandle {
    //This will be a data type to hold information from the collision events.
    private Vector3f MTV;
    private boolean isColliding;
    private float overlap;
    //Will extend as needed

    public collisHandle() {
        this.MTV = new Vector3f(0, 0, 0);
        this.isColliding = false;
        this.overlap = 0;
    }

    public collisHandle(Vector3f MTV, boolean isColliding, float overlap) {
        this.MTV = MTV;
        this.isColliding = isColliding;
        this.overlap = overlap;
    }


}
