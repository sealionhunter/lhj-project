/**
 * 
 */
package lhj.learn.swing.plugin;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PluginManager {
	private static PluginManager instance = new PluginManager();
	private List<Plugin> plugins = new ArrayList<Plugin>();

	public static PluginManager getInstance() {
		return instance;
	}

	public void init() {

	}

	public List<Plugin> getPlugins() {
		return plugins;
	}

}
