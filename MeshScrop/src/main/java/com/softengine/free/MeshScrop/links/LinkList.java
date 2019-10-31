package com.softengine.free.MeshScrop.links;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class LinkList {

	public static List<String> getTextList(String filePath) throws Exception {
		List<String> arrayList = new ArrayList<String>();
		BufferedReader textBuffered;
		try {
			textBuffered = LoadTextDiseases.getTextInputStream(filePath);
			String st;
			while ((st = textBuffered.readLine()) != null) {
				arrayList.add(st);
			}
		} catch ( Exception e) {
			throw new Exception("" + LoadTextDiseases.class.getName() + " hata veren class " + e.getMessage());

		}
		return arrayList;
	}
}
