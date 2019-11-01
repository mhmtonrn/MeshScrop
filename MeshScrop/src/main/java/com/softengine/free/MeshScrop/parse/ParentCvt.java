package com.softengine.free.MeshScrop.parse;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.softengine.free.MeshScrop.model.Parent;

public class ParentCvt {

	public static Map<Double, Parent> getParent(String json) {
		JSONArray rootarry = new JSONArray(json);
		Map<Double, Parent> map = new HashMap<Double, Parent>();
		for (int i = 0; i< rootarry.length(); i++) {
			Parent parent = new Parent();
			parent.set_RecordName(((JSONObject) rootarry.get(rootarry.length()-i-1)).getString(Parent.RecordName));
			parent.set_RecordUI(((JSONObject) rootarry.get(rootarry.length()-i-1)).getString(Parent.RecordUI));
			parent.set_TreeNumber(((JSONObject) rootarry.get(rootarry.length()-i-1)).getString(Parent.TreeNumber));
			map.put(Math.pow((0.5),(i+1)), parent);
		}
		return map;

	}

}
