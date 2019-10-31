package com.softengine.free.MeshScrop.parse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.softengine.free.MeshScrop.model.Parent;

public class ParseParentBD {
	private InputStream inputStream;

	public ParseParentBD(InputStream inputStream) {
		super();
		this.inputStream = inputStream;
	}
	
	public Map<Integer, Parent> getParentMap() throws Exception {
		try {
			String context = convertString();
			return ParentCvt.getParent(context);
		} catch (IOException e) {
			throw new Exception(""+this.getClass().getName()+ " hata veren class "+ e.getMessage());
		}		
	}

	private String convertString() throws IOException {
		return IOUtils.toString(inputStream,"ISO-8859-1");
	}

}
