package com.softengine.free.MeshScrop.parse;

import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.softengine.free.MeshScrop.model.Parent;

public class ParentCvt {

	public static Map<Integer, Parent> getParent(String json) {
		JSONArray rootarry = new JSONArray(json);
		Map<Integer, Parent> map = new TreeMap<Integer, Parent>();
		for (int i = rootarry.length(); i > 0 ; i--) {
			Parent parent = new Parent();
			parent.set_RecordName(((JSONObject) rootarry.get(rootarry.length()-i)).getString(Parent.RecordName));
			parent.set_RecordUI(((JSONObject) rootarry.get(rootarry.length()-i)).getString(Parent.RecordUI));
			parent.set_TreeNumber(((JSONObject) rootarry.get(rootarry.length()-i)).getString(Parent.TreeNumber));
			map.put(i, parent);
		}
		return map;

	}

}
