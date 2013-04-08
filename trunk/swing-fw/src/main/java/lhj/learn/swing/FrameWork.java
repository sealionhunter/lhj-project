/**
 * 
 */
package lhj.learn.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import lhj.learn.swing.command.CommandManager;
import lhj.learn.swing.plugin.Plugin;
import lhj.learn.swing.plugin.PluginManager;

/**
 *
 */
public class FrameWork {
	JFrame mainFrame = new JFrame();

	public void startup() {
		// mainFrame.add
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// mainFrame.setLayout(new BorderLayout());
		mainFrame.setSize(800, 600);

		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(mainFrame,
						"作業状態を保存しますか？（管理対象へは設定されません）", "作業の終了",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (confirm == JOptionPane.CANCEL_OPTION) {
					return;
				}

				for (Plugin plugin : PluginManager.getInstance().getPlugins()) {
					plugin.terminate(confirm);
				}
				System.exit(0);
			}
		});
		exit.setMnemonic('x');
		file.add(exit);
		List<Plugin> plugins = PluginManager.getInstance().getPlugins();
		for (Plugin plugin : plugins) {
			file.addSeparator();
			for (JMenuItem m : plugin.getFileMenus()) {
				file.add(m);
				// m.seti
			}
		}
		menuBar.add(file);

		mainFrame.setJMenuBar(menuBar);

		JToolBar toolBar = new JToolBar();
		JButton open = new JButton("Open");
		open.setActionCommand("Open");
		open.setText("Open");
		toolBar.add(open);
		mainFrame.getContentPane().add(toolBar, BorderLayout.NORTH);

		JPanel treePanel = new JPanel();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		DefaultMutableTreeNode common = new DefaultMutableTreeNode("共通");
		// common.
		root.add(common);
		DefaultMutableTreeNode device = new DefaultMutableTreeNode("機器");
		common.add(device);
		DefaultMutableTreeNode user = new DefaultMutableTreeNode("ユーザー");
		common.add(user);

		for (Plugin plugin : plugins) {
			root.add(plugin.getTreeNode());
		}
		final JTree tree = new JTree(root);

		tree.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger())
					popupTrigger(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger())
					popupTrigger(e);
			}

			private void popupTrigger(MouseEvent e) {
				TreePath path = tree.getSelectionPath();
				if (path == null) {
					return;
				}
				Object o = path.getLastPathComponent();
				if (o == null) {
					return;
				}
				tree.setSelectionPath(path);

				JPopupMenu popup = CommandManager.getInstance()
						.generatePopup(o);
				if (popup != null) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		TreeSelectionModel selectionModel = new DefaultTreeSelectionModel();
		selectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		selectionModel.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		tree.setSelectionModel(selectionModel );
		treePanel.add(tree);
		mainFrame.getContentPane().add(treePanel, BorderLayout.WEST);

		JPanel listPanel = new JPanel();
		JTable table = new JTable(4, 5);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger())
					popupTrigger(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger())
					popupTrigger(e);
			}

			private void popupTrigger(MouseEvent e) {
				JPopupMenu popup = new JPopupMenu();
				popup.add(new JMenuItem("Import"));
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		listPanel.add(table);
		mainFrame.getContentPane().add(listPanel, BorderLayout.CENTER);

		mainFrame.setVisible(true);
	}
}
