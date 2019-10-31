package com.softengine.free.MeshScrop.links;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LoadTextDiseases {

	public static BufferedReader getTextInputStream(String filePath) throws Exception {

		File file = new File(filePath);

		try {
			return new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			throw new Exception("" + LoadTextDiseases.class.getName() + " hata veren class " + e.getMessage());
		}

	}

}
