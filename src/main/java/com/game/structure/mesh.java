package com.game.structure;

import com.sun.javafx.geom.Vec2d;

import java.util.ArrayList;

/**
 * Created by eamon_000 on 5/17/2015.
 */
public class mesh {
	//this will be the class used to store the mesh of points for all objects
	//what data type to use to store the points? - Array of Vec2d objects.
	private Vec2d center;
	private ArrayList<Vec2d> mesh;


	public mesh() {
		//Instantiates a mesh.
	}

	public mesh(String file, Vec2d location) {
		//instantiates mesh from file

	}

	//NOTE:
	/* When a mesh is created, by being loaded from a file, the points are all loaded into an array, first. Then, according to the point that is the location of the shape,
	All the points are shifted with a loop, in order to shift the shape to the right location.

	 */
	public ArrayList<Vec2d> loadMesh(String file) {
		//loads from file
		//mesh files will be comma and parenthesis separated point pairs.
		ArrayList<Vec2d> out = new ArrayList<Vec2d>();


		return out;
	}


}
