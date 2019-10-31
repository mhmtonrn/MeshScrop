package com.softengine.free.MeshScrop.parse;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.softengine.free.MeshScrop.model.Disease;

public class DiseaseCvt {
	
	public static Disease getDisease(String json) {
		Disease disease = new Disease();
		JSONObject rootObj = new JSONObject(json);
		
		
		String descriptionUI =rootObj.getJSONObject(Disease.DescriptorUI).getString(Disease.t);
		disease.set_DescriptorUI(descriptionUI);
		
		String annotation =rootObj.getJSONObject(Disease.Annotation).getString(Disease.t);
		disease.set_Annotation(annotation);
		
		String DescriptorName =rootObj.getJSONObject(Disease.DescriptorName).getJSONObject(Disease.String).getString(Disease.t);
		disease.set_DescriptorName(DescriptorName);
		
		List<String> treeList = new ArrayList<>();
		rootObj.getJSONObject(Disease.TreeNumberList).getJSONArray(Disease.TreeNumber).forEach(item->{
			String tree = ((JSONObject) item).getString(Disease.t);
			treeList.add(tree);
		});
		disease.set_TreeNumber(treeList);
		
		
		return disease;
		
	}

}
