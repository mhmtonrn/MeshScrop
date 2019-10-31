package com.softengine.free.MeshScrop.http.proceses;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.softengine.free.MeshScrop.tips.LinkManager;

public class MashHttpClient {
	
	private static MashHttpClient mashHttpClient;

	private MashHttpClient() {
	}
	
	public static MashHttpClient getInstance() {
		if (mashHttpClient == null) {
			return mashHttpClient = new MashHttpClient();
		}else {
			return mashHttpClient;
		}
	}
	

	public HttpResponse getContext(LinkManager link, String param) throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(link.getLink()+param);
		HttpResponse response = null;
		try {
			 response = client.execute(request);	
//			 System.out.println(IOUtils.toString(response.getEntity().getContent(),"utf-8"));;
		}catch (IOException e) {
			throw new Exception(""+this.getClass().getName()+ " hata veren class "+ e.getMessage());
		}
		return response;

	}

}
