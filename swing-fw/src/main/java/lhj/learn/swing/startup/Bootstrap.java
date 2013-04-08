/**
 * 
 */
package lhj.learn.swing.startup;

import lhj.learn.swing.FrameWork;
import lhj.learn.swing.command.CommandManager;
import lhj.learn.swing.plugin.PluginManager;

/**
 *
 */
public class Bootstrap {
	public static void main(String[] args) {
		FrameWork main = new FrameWork();
		PluginManager.getInstance().init();
		CommandManager.getInstance().init();
		main.startup();
	}
}
