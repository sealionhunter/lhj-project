/*
 * (C) Fuji Xerox Co., Ltd. 2015
 *
 * $Id$
 */
package lhj.learn.cgi;

public class JsonBean {
	private String xuxVersion;
	private String compAll;
	private String style;

	private String size;
	private String basgLang;
	private boolean useBasgGlobalization;
	private boolean usePIE;
	private String additionalStyle;
	private String basgModule;
	private boolean useBasgBox;
	private boolean useBasgSuggestion;
	private String basgCulture;

	private String easgLang;
	private boolean useEasgGlobalization;
	private String ewbVersion;
	private String easgModule;
	private boolean useEasgBox;
	private boolean useEasgSuggestion;
	private String easgCulture;

	public String getXuxVersion() {
		return xuxVersion;
	}

	public void setXuxVersion(String xuxVersion) {
		this.xuxVersion = xuxVersion;
	}

	public String getCompAll() {
		return compAll;
	}

	public void setCompAll(String compAll) {
		this.compAll = compAll;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getBasgLang() {
		return basgLang;
	}

	public void setBasgLang(String basgLang) {
		this.basgLang = basgLang;
	}

	public boolean isUseBasgGlobalization() {
		return useBasgGlobalization;
	}

	public void setUseBasgGlobalization(boolean useBasgGlobalization) {
		this.useBasgGlobalization = useBasgGlobalization;
	}

	public boolean isUsePIE() {
		return usePIE;
	}

	public void setUsePIE(boolean usePIE) {
		this.usePIE = usePIE;
	}

	public String getAdditionalStyle() {
		return additionalStyle;
	}

	public void setAdditionalStyle(String additionalStyle) {
		this.additionalStyle = additionalStyle;
	}

	public String getBasgModule() {
		return basgModule;
	}

	public void setBasgModule(String basgModule) {
		this.basgModule = basgModule;
	}

	public boolean isUseBasgBox() {
		return useBasgBox;
	}

	public void setUseBasgBox(boolean useBasgBox) {
		this.useBasgBox = useBasgBox;
	}

	public boolean isUseBasgSuggestion() {
		return useBasgSuggestion;
	}

	public void setUseBasgSuggestion(boolean useBasgSuggestion) {
		this.useBasgSuggestion = useBasgSuggestion;
	}

	public String getBasgCulture() {
		return basgCulture;
	}

	public void setBasgCulture(String basgCulture) {
		this.basgCulture = basgCulture;
	}

	public String getEasgLang() {
		return easgLang;
	}

	public void setEasgLang(String easgLang) {
		this.easgLang = easgLang;
	}

	public boolean isUseEasgGlobalization() {
		return useEasgGlobalization;
	}

	public void setUseEasgGlobalization(boolean useEasgGlobalization) {
		this.useEasgGlobalization = useEasgGlobalization;
	}

	public String getEwbVersion() {
		return ewbVersion;
	}

	public void setEwbVersion(String ewbVersion) {
		this.ewbVersion = ewbVersion;
	}

	public String getEasgModule() {
		return easgModule;
	}

	public void setEasgModule(String easgModule) {
		this.easgModule = easgModule;
	}

	public boolean isUseEasgBox() {
		return useEasgBox;
	}

	public void setUseEasgBox(boolean useEasgBox) {
		this.useEasgBox = useEasgBox;
	}

	public boolean isUseEasgSuggestion() {
		return useEasgSuggestion;
	}

	public void setUseEasgSuggestion(boolean useEasgSuggestion) {
		this.useEasgSuggestion = useEasgSuggestion;
	}

	public String getEasgCulture() {
		return easgCulture;
	}

	public void setEasgCulture(String easgCulture) {
		this.easgCulture = easgCulture;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JsonBean [xuxVersion=");
		builder.append(xuxVersion);
		builder.append(", compAll=");
		builder.append(compAll);
		builder.append(", style=");
		builder.append(style);
		builder.append(", size=");
		builder.append(size);
		builder.append(", basgLang=");
		builder.append(basgLang);
		builder.append(", useBasgGlobalization=");
		builder.append(useBasgGlobalization);
		builder.append(", usePIE=");
		builder.append(usePIE);
		builder.append(", additionalStyle=");
		builder.append(additionalStyle);
		builder.append(", basgModule=");
		builder.append(basgModule);
		builder.append(", useBasgBox=");
		builder.append(useBasgBox);
		builder.append(", useBasgSuggestion=");
		builder.append(useBasgSuggestion);
		builder.append(", basgCulture=");
		builder.append(basgCulture);
		builder.append(", easgLang=");
		builder.append(easgLang);
		builder.append(", useEasgGlobalization=");
		builder.append(useEasgGlobalization);
		builder.append(", ewbVersion=");
		builder.append(ewbVersion);
		builder.append(", easgModule=");
		builder.append(easgModule);
		builder.append(", useEasgBox=");
		builder.append(useEasgBox);
		builder.append(", useEasgSuggestion=");
		builder.append(useEasgSuggestion);
		builder.append(", easgCulture=");
		builder.append(easgCulture);
		builder.append("]");
		return builder.toString();
	}
}
