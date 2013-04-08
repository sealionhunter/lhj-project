/**
 * 
 */
package lhj.learn.swing.command;

import javax.swing.Icon;

/**
 *
 */
public interface Command {
	boolean associalMenu();
	boolean associalToolBar();
	boolean associalPopup();
	String getText();
	Icon getDefaultIcon();
	char getMnemonic();
	String getTooltips();
	void execute();
	int getResultType();
	Object getResult();
}
