package com.game.structure;

import com.game.math.Vector3f;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.game.util.FileUtils.readSmallTextFile;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by eamon_000 on 5/17/2015.
 */
public class mesh {
	//this will be the class used to store the mesh of points for all objects
	//what data type to use to store the points? - Array of Vec2d objects.


    private Vector3f position;


    private ArrayList<Vector3f> meshVerts;
    private ArrayList<Vector3f> meshNorms;

	public mesh() {
		//Instantiates a mesh.
	}

	public mesh(String file, Vector3f location) {
		//instantiates mesh from file
		loadMesh(file);
        this.position = location;


	}

	//NOTE:
	/* When a mesh is created, by being loaded from a file, the points are all loaded into an array, first. Then, according to the point that is the location of the shape,
	All the points are shifted with a loop, in order to shift the shape to the right location.

	 */
	public void loadMesh(String file) {
		//loads from file
		//mesh files will be comma and parenthesis separated point pairs.
		ArrayList<Vector3f> outVerts = new ArrayList<Vector3f>();
		ArrayList<Vector3f> outNorms = new ArrayList<Vector3f>();
		List<String> inMesh = null;
		try {
			inMesh = readSmallTextFile(file);
			if (inMesh == null) {
				throw new FileNotFoundException("Cannot Load Mesh at " + file);

			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOEXCEPTION, cannot read file?");
		}

		for (String s : inMesh) {
			//System.out.println(s);
			if (s.charAt(0) == 'v' && s.charAt(1) == 'n') {
				String[] normls = s.split(" ");
				float x = Float.parseFloat(normls[1]);
				float y = Float.parseFloat(normls[2]);
				float z = Float.parseFloat(normls[3]);
				outNorms.add(new Vector3f(x, y, z));
			} else if (s.charAt(0) == 'v') {
				String[] verts = s.split(" ");
				float x = Float.parseFloat(verts[1]);
				float y = Float.parseFloat(verts[2]);
				float z = Float.parseFloat(verts[3]);
				outVerts.add(new Vector3f(x, y, z));
			}
		}
		this.meshNorms = outNorms;
		this.meshVerts = outVerts;
		for (Vector3f v : meshNorms) {
			System.out.println(v);

		}
		for (Vector3f v : meshVerts) {
			System.out.println(v);

		}


		return;
	}

	public void render() {
		glBegin(GL_POLYGON);
		for (Vector3f v : meshVerts) {
			glVertex3f(v.x, v.y, v.z);

		}
		glEnd();
	}

    public ArrayList<Vector3f> getMeshVerts() {
        return this.meshVerts;
    }

    public void setMeshVerts(ArrayList<Vector3f> meshVerts) {
        this.meshVerts = meshVerts;
    }

    public ArrayList<Vector3f> getMeshNorms() {
        return this.meshNorms;
    }

    public void setMeshNorms(ArrayList<Vector3f> meshNorms) {
        this.meshNorms = meshNorms;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }



}
