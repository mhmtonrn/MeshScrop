package com.softengine.free.MeshScrop.model;

import java.io.Serializable;
import java.util.List;

public class Disease implements Serializable {
	public static final String DescriptorUI = "DescriptorUI";
	public static final String Annotation = "Annotation";
	public static final String DescriptorName = "DescriptorName";
	public static final String TreeNumber = "TreeNumber";
	
	public static final String String = "String";
	public static final String TreeNumberList = "TreeNumberList";
	public static final String t = "t";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _DescriptorUI;
	private String _Annotation;
	private String _DescriptorName;
	private List<String> _TreeNumber;
	

	public List<String> get_TreeNumber() {
		return _TreeNumber;
	}

	public void set_TreeNumber(List<String> _TreeNumber) {
		this._TreeNumber = _TreeNumber;
	}

	public String get_DescriptorName() {
		return _DescriptorName;
	}

	public void set_DescriptorName(String _DescriptorName) {
		this._DescriptorName = _DescriptorName;
	}

	public String get_Annotation() {
		return _Annotation;
	}

	public void set_Annotation(String _Annotation) {
		this._Annotation = _Annotation;
	}

	public String get_DescriptorUI() {
		return _DescriptorUI;
	}

	public void set_DescriptorUI(String _DescriptorUI) {
		this._DescriptorUI = _DescriptorUI;
	}

}
