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
				List<Entry<Double, Parent>> l = new ArrayList<Entry<Double, Parent>>();
				List<Entry<Double, Parent>> l1 = new ArrayList<Entry<Double, Parent>>();
				Map<Integer, Parent> a = new HashMap<Integer, Parent>();
				Map<Integer, Parent> b = new HashMap<Integer, Parent>();
				double payda1 = 1;
				double pay = 0;
				for (Map<Double, Parent> paydaList : item.getValue()) {
					for (Entry<Double, Parent> paydaDliste : paydaList.entrySet()) {
						Entry<Double, Parent> c = listedenCikar(l, paydaDliste);
						double eksipauda = 0;
						l.remove(c);
						if (c != null) {
							eksipauda = c.getKey();
						}
						if (!l.contains(paydaDliste)) {
							double d = paydaDliste.getKey().doubleValue();
							payda1 += d;
							payda1 -= eksipauda;
							Parent p = paydaDliste.getValue();
							p.setTemp(d);
							a.put(paydaDliste.getValue().hashCode(), p);
							l.add(paydaDliste);
						}
					}
				}

				double payda2 = 1;
				for (Map<Double, Parent> paydaList : innerItem.getValue()) {
					for (Entry<Double, Parent> paydaDliste : paydaList.entrySet()) {
						Entry<Double, Parent> c = listedenCikar(l1, paydaDliste);
						double eksipauda = 0;
						l1.remove(c);
						if (c != null) {
							eksipauda = c.getKey();
						}
						if (!l1.contains(paydaDliste)) {
							double d = paydaDliste.getKey().doubleValue();
							payda2 += d;
							payda2 -= eksipauda;
							Parent p = paydaDliste.getValue();
							p.setTemp(d);
							b.put(paydaDliste.getValue().hashCode(), p);
							l1.add(paydaDliste);
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

	private Entry<Double, Parent> listedenCikar(List<Entry<Double, Parent>> l, Entry<Double, Parent> paydaDliste) {
		Entry<Double, Parent> cikartilacakEntry = null;
		for (Entry<Double, Parent> entry : l) {
			if (entry.getValue().get_RecordUI().equals(paydaDliste.getValue().get_RecordUI())) {
				if (entry.getKey()<=paydaDliste.getKey()) {
					cikartilacakEntry = entry;
					break;
				}
			}
		}
		return cikartilacakEntry;
	}

}
