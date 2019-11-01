package com.softengine.free.MeshScrop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.softengine.free.MeshScrop.http.proceses.MashHttpClient;
import com.softengine.free.MeshScrop.links.LinkList;
import com.softengine.free.MeshScrop.model.Disease;
import com.softengine.free.MeshScrop.model.Parent;
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
    		Map<Disease,List<Map<Double,Parent>>> hesapMap = new HashMap<Disease, List<Map<Double,Parent>>>();
    		for (String disease : list) {
    			HttpResponse response = MashHttpClient.getInstance().getContext(LinkManager.DETAIL, disease);
    			ParseDetailsBD parseDetails = new ParseDetailsBD(response.getEntity().getContent());
    			 Disease diseaseModel = parseDetails.getDiseases();
    			 List<Map<Double,Parent>> liste = new ArrayList<>();
    			for (String tree : diseaseModel.get_TreeNumber()) {
    				HttpResponse respTree = MashHttpClient.getInstance().getContext(LinkManager.PARENT, tree);
					ParseParentBD bd = new ParseParentBD(respTree.getEntity().getContent());
					Map<Double, Parent> m = bd.getParentMap();
					writeTree = new WriteTree(diseaseModel,m,args[1]);
					writeTree.writeTextOutput(builder);
					liste.add(m);
				}
    			hesapMap.put(diseaseModel, liste);
    			builder.append("\n\n");
			}
    		writeTree.write(builder,"output.txt");
    		System.out.println(builder.toString());
    		StringBuilder st = writeTree.writeTextOutput2(hesapMap);
    		writeTree.write(st,"output2.txt");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
