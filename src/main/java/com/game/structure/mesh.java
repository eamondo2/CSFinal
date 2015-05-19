package com.game.structure;

import com.game.math.Vector3f;

import java.util.ArrayList;

/**
 * Created by eamon_000 on 5/17/2015.
 */
public class mesh {
	//this will be the class used to store the mesh of points for all objects
	//what data type to use to store the points? - Array of Vec2d objects.


	private Vector3f center;
	private ArrayList<Vector3f> mesh;


	public mesh() {
		//Instantiates a mesh.
	}

	public mesh(String file, Vector3f location) {
		//instantiates mesh from file


	}

	//NOTE:
	/* When a mesh is created, by being loaded from a file, the points are all loaded into an array, first. Then, according to the point that is the location of the shape,
	All the points are shifted with a loop, in order to shift the shape to the right location.

	 */
	public ArrayList<Vector3f> loadMesh(String file) {
		//loads from file
		//mesh files will be comma and parenthesis separated point pairs.
		ArrayList<Vector3f> out = new ArrayList<Vector3f>();


		return out;
	}


}
