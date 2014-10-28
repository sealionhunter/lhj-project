package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月14日
 * @author Cherami
 */

import java.io.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.jr.swing.filter.*;

/**
 * 文件选取器面板。
 * 简化了选择一个文件的组件的创建工作。
 * @since  0.5
 */

public class FileSelector
    extends JPanel {
  protected JTextField nameField;
  protected JButton browserButton;
  protected JFileChooser chooser;
  /**
   * 以指定参数为父窗口创建一个FileSelector。
   * 文本框中显示的将是系统的临时目录的路径。
   * 文件的过滤器使用全部文本过滤器。
   * @param parent 父窗口
   * @since  0.5
   */
  public FileSelector(Component parent) {
    this(parent, new File(System.getProperty("java.io.tmpdir", ".")),
         new AllFileFilter(), "...");
  }

  /**
   * 以指定的父窗口和过滤器创建一个FileSelector。
   * @param parent 父窗口
   * @param filter 过滤器
   * @since  0.5
   */
  public FileSelector(Component parent,
                      javax.swing.filechooser.FileFilter filter) {
    this(parent, new File(System.getProperty("java.io.tmpdir", ".")), filter,
         "...");
  }

  /**
   * 以指定的参数构造一个FileSelector。
   * @param parent 父窗口
   * @param dir 文件选取器的初始目录
   * @param filter 过滤器
   * @param text 浏览按钮的名字
   * @since  0.5
   */
  public FileSelector(final Component parent, File dir,
                      javax.swing.filechooser.FileFilter filter, String text) {
    setLayout(new BorderLayout());
    nameField = new JTextField(dir.getAbsolutePath());
    browserButton = new JButton(text);
    add(nameField, "Center");
    add(browserButton, "East");
    chooser = new JFileChooser(dir);
    chooser.setFileFilter(filter);
    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    browserButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int result = chooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
          File file = chooser.getSelectedFile();
          nameField.setText(file.getAbsolutePath());
        }
      }
    }
    );
  }

  /**
   * 得到选定文件或者目录的名称。
   * @return 选定文件或者目录的名称
   * @since  0.5
   */
  public String getFile() {
    return nameField.getText();
  }

}