package com.game.util;

import com.game.math.Vector3f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by eamon_000 on 5/20/2015.
 */
public class fileLoader {
	public static ArrayList<String> readFromFile(File f) throws IOException {
		ArrayList<String> out = new ArrayList<String>();
		BufferedReader br = null;
		String sCurrentLine;
		br = new BufferedReader(new FileReader(f));
		while ((sCurrentLine = br.readLine()) != null) {
			out.add(sCurrentLine);
		}
		br.close();


		return out;
	}

	public static ArrayList<Vector3f> vertize(ArrayList<String> sIn) {
		ArrayList<Vector3f> out = new ArrayList<Vector3f>();
		for (String s : sIn) {
			String[] broken = s.split(",");
			float x = Float.parseFloat(broken[0]), y = Float.parseFloat(broken[1]), z = Float.parseFloat(broken[2]);
			out.add(new Vector3f(x, y, z));
		}


		return out;
	}

	public static ArrayList<Vector3f> loadVertFromFile(File f) throws IOException {
		return vertize((readFromFile(f)));

	}


}
