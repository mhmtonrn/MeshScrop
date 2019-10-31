package com.softengine.free.MeshScrop.tips;

public enum LinkManager {
	DETAIL("https://meshb.nlm.nih.gov/api/record/ui/"),
	PARENT("https://meshb.nlm.nih.gov/api/tree/parents/");
	
	private String link;
	
	LinkManager(String link){
		this.link = link;
	}

	public String getLink() {
		return link;
	}
	
}
