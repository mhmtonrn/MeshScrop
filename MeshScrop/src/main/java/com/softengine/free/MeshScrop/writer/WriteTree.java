package com.softengine.free.MeshScrop.writer;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.softengine.free.MeshScrop.model.Disease;
import com.softengine.free.MeshScrop.model.Parent;

public class WriteTree {
	private Disease disease;
	private Map<Double, Parent> treeMap;
	private String outputPath;

	public WriteTree(Disease disease, Map<Double, Parent> treeMap, String outputPath) {
		super();
		this.disease = disease;
		this.treeMap = treeMap;
		this.outputPath = outputPath;
	}

	public void writeTextOutput(StringBuilder builder) {
		builder.append("[" + disease.get_DescriptorUI() + "-" + disease.get_DescriptorName() + "]");
		for (Entry<Double, Parent> mp : treeMap.entrySet()) {
			builder.append(" ===> [" + mp.getKey() + "-" + mp.getValue().get_RecordUI() + "-"
					+ mp.getValue().get_RecordName() + "]");
		}
		builder.append("\n");
//		System.out.println(stringBuilder.toString());
	}

	public void write(StringBuilder builder, String name) throws Exception {
		try {
			FileOutputStream fout = new FileOutputStream(outputPath + "\\" + name);
			byte b[] = builder.toString().getBytes();// converting string into byte array
			fout.write(b);
			fout.close();

		} catch (Exception e) {
			throw new Exception("" + this.getClass().getName() + " hata veren class " + e.getMessage());
		}

	}

	public StringBuilder writeTextOutput2(Map<Disease, List<Map<Double, Parent>>> hesapMap) {
		StringBuilder stringBuilder = new StringBuilder();
		Map<Disease, List<Map<Double, Parent>>> newHashMap = new HashMap<Disease, List<Map<Double, Parent>>>(hesapMap);
		for (Entry<Disease, List<Map<Double, Parent>>> item : hesapMap.entrySet()) {
			for (Entry<Disease, List<Map<Double, Parent>>> innerItem : newHashMap.entrySet()) {
				List<String> l = new ArrayList<String>();
				List<String> l1 = new ArrayList<String>();
				Map<Integer, Parent> a = new HashMap<Integer, Parent>();
				Map<Integer, Parent> b = new HashMap<Integer, Parent>();
				double payda1 = 1;
				double pay = 0;
				for (Map<Double, Parent> paydaList : item.getValue()) {
					for (Entry<Double, Parent> paydaDliste : paydaList.entrySet()) {
						if (!l.contains(paydaDliste.getValue().get_RecordUI())) {
							double d = paydaDliste.getKey().doubleValue();
							payda1 += d;
							Parent p = paydaDliste.getValue();
							p.setTemp(d);
							a.put(paydaDliste.getValue().hashCode(), p);
							l.add(paydaDliste.getValue().get_RecordUI());
						}
					}
				}

				double payda2 = 1;
				for (Map<Double, Parent> paydaList : innerItem.getValue()) {
					for (Entry<Double, Parent> paydaDliste : paydaList.entrySet()) {
						if (!l1.contains(paydaDliste.getValue().get_RecordUI())) {
							double d = paydaDliste.getKey().doubleValue();
							payda2 += d;
							Parent p = paydaDliste.getValue();
							p.setTemp(d);
							b.put(paydaDliste.getValue().hashCode(), p);
							l1.add(paydaDliste.getValue().get_RecordUI());
						}
					}
				}
				double genelPaydaListe = payda1 + payda2;

				if (item.getKey().get_DescriptorUI().equals(innerItem.getKey().get_DescriptorUI())) {
					pay = genelPaydaListe;
				} else {
					for (Entry<Integer, Parent> a1 : a.entrySet()) {
						for (Entry<Integer, Parent> b1 : b.entrySet()) {
							if (a1.getValue().get_RecordUI().contentEquals(b1.getValue().get_RecordUI())) {
								pay += a1.getValue().getTemp() + b1.getValue().getTemp();
								break;
							}
						}
					}
				}

				stringBuilder.append(
						"[" + item.getKey().get_DescriptorUI() + "-" + item.getKey().get_DescriptorName() + "]/" + "["
								+ innerItem.getKey().get_DescriptorUI() + "-" + innerItem.getKey().get_DescriptorName()
								+ "] ==> " + pay + " / " + genelPaydaListe + " = " + (pay / genelPaydaListe) + "\n");

			}

			stringBuilder.append("\n\n");
		}
		return stringBuilder;
	}

}
