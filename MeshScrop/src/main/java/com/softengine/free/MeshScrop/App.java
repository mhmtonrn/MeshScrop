package com.softengine.free.MeshScrop;

import java.util.List;

import org.apache.http.HttpResponse;

import com.softengine.free.MeshScrop.http.proceses.MashHttpClient;
import com.softengine.free.MeshScrop.links.LinkList;
import com.softengine.free.MeshScrop.model.Disease;
import com.softengine.free.MeshScrop.parse.ParseDetailsBD;
import com.softengine.free.MeshScrop.parse.ParseParentBD;
import com.softengine.free.MeshScrop.tips.LinkManager;
import com.softengine.free.MeshScrop.writer.WriteTree;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try {
    		WriteTree writeTree = null;
    		StringBuilder builder = new StringBuilder();
    		List<String> list = LinkList.getTextList(args[0]);
    		for (String disease : list) {
    			HttpResponse response = MashHttpClient.getInstance().getContext(LinkManager.DETAIL, disease);
    			ParseDetailsBD parseDetails = new ParseDetailsBD(response.getEntity().getContent());
    			 Disease diseaseModel = parseDetails.getDiseases();
    			for (String tree : diseaseModel.get_TreeNumber()) {
    				HttpResponse respTree = MashHttpClient.getInstance().getContext(LinkManager.PARENT, tree);
					ParseParentBD bd = new ParseParentBD(respTree.getEntity().getContent());
					writeTree = new WriteTree(diseaseModel,bd.getParentMap(),args[1]);
					writeTree.writeTextOutput(builder);
				}
    			builder.append("\n\n");
			}
    		writeTree.write(builder);
    		System.out.println(builder.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
