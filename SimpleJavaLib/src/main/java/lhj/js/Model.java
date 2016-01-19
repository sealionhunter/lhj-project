package lhj.js;

public class Model {
	private String name;
	private String[] js;
	private String[] css;
	private String dependency;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getJs() {
		return js;
	}

	public void setJs(String[] js) {
		this.js = js;
	}

	public String[] getCss() {
		return css;
	}

	public void setCss(String[] css) {
		this.css = css;
	}

	public String getDependency() {
		return dependency;
	}

	public void setDependency(String dependency) {
		this.dependency = dependency;
	}
}
