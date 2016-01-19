package lhj.js;

import java.io.FileReader;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ModelReader {
	private String jsPath;
	public ModelReader(String js) {
		this.jsPath = js;
	}
	
	@SuppressWarnings("unchecked")
	public List<Model> readModel() throws Exception {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("nashorn");
		engine.eval(new FileReader(jsPath));
		
		String js = "var moduleList = new java.util.ArrayList(), Model = Java.type('lhj.js.Model'), m;"
				+ "modules.forEach(function(item) {"
				+ "    m = new Model();"
				+ "    m.setName(item.name);"
				+ "    m.setJs(item.js);"
				+ "    m.setCss(item.css);"
				+ "    m.setDependency(item.dependency);"
				+ "    moduleList.add(m);"
				+ "});";
		engine.eval(js);
		Object modules = engine.get("moduleList");
		return (List<Model>) modules;
	}
}
