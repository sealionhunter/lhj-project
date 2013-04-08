/**
 * 
 */
package lhj.learn.swing.plugin;

import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.tree.MutableTreeNode;

/**
 *
 */
public interface Plugin {

	void terminate(int confirm);

	List<JMenuItem> getFileMenus();

	MutableTreeNode getTreeNode();

}
