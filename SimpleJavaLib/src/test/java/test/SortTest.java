package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			List<SortBean> sbs = new ArrayList<SortBean>();
			sbs.addAll(initSortBean("F:\\workspace\\web-html5\\1.txt"));
			sbs.addAll(initSortBean("F:\\workspace\\web-html5\\2.txt"));
			sbs.addAll(initSortBean("F:\\workspace\\web-html5\\3.txt"));
			sbs.addAll(initSortBean("F:\\workspace\\web-html5\\4.txt"));
//			listValues(sbs);
			sbs.addAll(sbs);
			sbs.addAll(sbs);
//			sbs.addAll(sbs);
			System.out.println(sbs.size());
			long start = System.currentTimeMillis();
			Collections.sort(sbs, new SortCompare("startTime"));
			long end = System.currentTimeMillis();
			System.out.println("start = " + start + "  end = " + end + " end - start = " + (end-start));
//			listValues(sbs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<SortBean> initSortBean(String fileName) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String line1 = in.readLine();
		String regex = "\t";
		String[] keys = line1.split(regex);
//		listValues(keys);
		List<SortBean> sbs = new ArrayList<SortBean>();
		while ((line1 = in.readLine()) != null) {
			String[] values = line1.split(regex);

//			listValues(values);
			Map<String, Object> m = new HashMap<String, Object>();
			for (int i = 0; i < keys.length; i++) {
				if (i >= values.length) {
					m.put(keys[i], "");
				} else {
					m.put(keys[i], values[i]);
				}
			}
			sbs.add(new SortBean(m));
		}
		in.close();
		System.out.println(sbs.size());
		return sbs;
	}
	
	static void listValues(String[] vs) {
		for (String v : vs) {
			System.out.print(v + "\t");
		}
		System.out.println();
		System.out.println(vs.length);
	}

	
	static void listValues(List<SortBean> vs) {
		for (SortBean v : vs) {
			System.out.println(v);
		}
	}
	
}
