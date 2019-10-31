package com.softengine.free.MeshScrop.writer;

import java.io.FileOutputStream;
import java.util.Map;
import java.util.Map.Entry;

import com.softengine.free.MeshScrop.model.Disease;
import com.softengine.free.MeshScrop.model.Parent;

public class WriteTree {
	private Disease disease;
	private Map<Integer,Parent> treeMap;
	private String outputPath;
	public WriteTree(Disease disease, Map<Integer, Parent> treeMap, String outputPath) {
		super();
		this.disease = disease;
		this.treeMap = treeMap;
		this.outputPath = outputPath;
	}
	
	public void writeTextOutput(StringBuilder builder) {
		builder.append("["+disease.get_DescriptorUI()+"-"+disease.get_DescriptorName()+"]");
		for (Entry<Integer, Parent> mp : treeMap.entrySet()) {
			builder.append(" ===> ["+mp.getKey()+"-"+mp.getValue().get_RecordName()+"]");
		}
		builder.append("\n");
//		System.out.println(stringBuilder.toString());
	}

	public void write(StringBuilder builder) throws Exception {
		try {
			FileOutputStream fout=new FileOutputStream(outputPath+"\\output.txt");   
			byte b[]=builder.toString().getBytes();//converting string into byte array    
			fout.write(b);    
			fout.close();   
			
		} catch (Exception e) {
			throw new Exception(""+this.getClass().getName()+ " hata veren class "+ e.getMessage());
		}
		
	}
	
}
