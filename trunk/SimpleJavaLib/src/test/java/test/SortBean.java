package test;

import java.util.HashMap;
import java.util.Map;

public class SortBean {
	Map<String, Object> values = new HashMap<String, Object>();

	public SortBean(Map<String, Object> initValue) {
		if (initValue != null) {
			values.putAll(initValue);
		}
	}

	public Object getValue(String field) {
		return values.get(field);
	}

	public String toString(){
		StringBuffer sb = new StringBuffer(); 
		for (Object v : values.values()) {
			sb.append(v + "\t");
		}
		return sb.toString();
	}
}
