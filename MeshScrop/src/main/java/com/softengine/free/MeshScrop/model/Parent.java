package com.softengine.free.MeshScrop.model;

public class Parent {
	public static final String RecordName = "RecordName";
	public static final String RecordUI = "RecordUI";
	public static final String TreeNumber = "TreeNumber";
	
	private String _RecordName;
	private String _RecordUI;
	private String _TreeNumber;
	public String get_RecordName() {
		return _RecordName;
	}
	public void set_RecordName(String _RecordName) {
		this._RecordName = _RecordName;
	}
	public String get_RecordUI() {
		return _RecordUI;
	}
	public void set_RecordUI(String _RecordUI) {
		this._RecordUI = _RecordUI;
	}
	public String get_TreeNumber() {
		return _TreeNumber;
	}
	public void set_TreeNumber(String _TreeNumber) {
		this._TreeNumber = _TreeNumber;
	}
	@Override
	public String toString() {
		return "Parent [_RecordName=" + _RecordName + "]";
	}
	
	
	
	
	
	

}
