package com.ustcsoft.generalsolution.dmat.webui.menu;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class MenuParser {
	private static final String DEFAULT_MENU_FILE = "/META-INF/menu.xml";

	private String menuFile = "";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(MenuParser.getInstance().getMenu());
	}

	private static long lastCheckTime;

	private boolean checkModified(String file) {
		File f = new File(file);
		if (lastCheckTime == 0 || lastCheckTime < f.lastModified()) {
			lastCheckTime = f.lastModified();
			return true;
		}
		return false;
	}

	private static MenuParser instance;

	private MenuParser() {
		menuFile = System
				.getProperty("com.ustcsoft.generalsolution.dmat.util.menu.filepath");
		if (menuFile == null || menuFile.length() == 0) {
			menuFile = DEFAULT_MENU_FILE;
		}
	}

	private MenuRoot menu;

	private void parse() throws Exception {
		JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Object o = unmarshaller.unmarshal(getClass().getResourceAsStream(
				menuFile));
		menu = (MenuRoot) o;
	}

	public static final MenuParser getInstance() {
		if (instance == null) {
			instance = new MenuParser();
		}
		return instance;
	}

	public MenuRoot getMenu() throws Exception {
		if (checkModified(menuFile)) {
			parse();
		}
		return menu;
	}
}
