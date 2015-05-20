package com.game.structure;

import com.game.math.Vector3f;

/**
 * Created by eamon_000 on 5/17/2015.
 */
public class monster implements actor {
    private mesh m;
    private Vector3f pos;
    private String tex;

    public monster(String meshFile, Vector3f position, String tex) {
        this.m = new mesh(meshFile, position);
        this.pos = position;
        this.tex = tex;


    }

    public monster(mesh meshFile, Vector3f position, String tex) {
        this.m = meshFile;
        this.pos = position;
        this.tex = tex;


    }

	@Override
	public mesh getMesh() {
		return null;
	}

	@Override
	public Vector3f getCenter() {
		return null;
	}

    @Override
    public void render() {
        this.m.render();


    }

    @Override
    public boolean isStatic() {
		return false;
	}

	@Override
	public boolean isPlayer() {
		return false;
	}


}
