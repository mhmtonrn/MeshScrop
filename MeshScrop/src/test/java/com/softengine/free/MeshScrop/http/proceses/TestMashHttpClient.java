package com.softengine.free.MeshScrop.http.proceses;

import org.apache.http.HttpResponse;

import com.softengine.free.MeshScrop.http.proceses.MashHttpClient;
import com.softengine.free.MeshScrop.tips.LinkManager;

import junit.framework.TestCase;

public class TestMashHttpClient extends TestCase {

	public void testHttpClient() throws Exception {
		MashHttpClient client = MashHttpClient.getInstance();
		HttpResponse response = client.getContext(LinkManager.DETAIL, "D008114");
		assertTrue(response.getStatusLine().getStatusCode()==200?true:false);
	}
}