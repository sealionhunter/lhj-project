/**
 * 
 */
package lhj.learn.swing.command;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

/**
 *
 */
public class CommandManager {
	private static CommandManager instance = new CommandManager();
	//
	// private MenuLocator root = new MenuLocator();

	private List<Command> commands = new ArrayList<Command>();

	public static CommandManager getInstance() {
		return instance;
	}

	public void init() {

	}

	public void registCommand(Command command) {
		commands.add(command);
	}

	public JMenuBar generateMenuBar() {
		return null;
	}

	public JToolBar generateToolBar() {
		return null;
	}

	public JPopupMenu generatePopup(Object comp) {
		return null;
	}

	public void selectedCompChanged(JComponent oldComp, JComponent newComp) {

	}
}
