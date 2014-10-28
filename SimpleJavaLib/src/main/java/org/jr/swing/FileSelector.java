package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��14��
 * @author Cherami
 */

import java.io.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.jr.swing.filter.*;

/**
 * �ļ�ѡȡ����塣
 * ����ѡ��һ���ļ�������Ĵ���������
 * @since  0.5
 */

public class FileSelector
    extends JPanel {
  protected JTextField nameField;
  protected JButton browserButton;
  protected JFileChooser chooser;
  /**
   * ��ָ������Ϊ�����ڴ���һ��FileSelector��
   * �ı�������ʾ�Ľ���ϵͳ����ʱĿ¼��·����
   * �ļ��Ĺ�����ʹ��ȫ���ı���������
   * @param parent ������
   * @since  0.5
   */
  public FileSelector(Component parent) {
    this(parent, new File(System.getProperty("java.io.tmpdir", ".")),
         new AllFileFilter(), "...");
  }

  /**
   * ��ָ���ĸ����ں͹���������һ��FileSelector��
   * @param parent ������
   * @param filter ������
   * @since  0.5
   */
  public FileSelector(Component parent,
                      javax.swing.filechooser.FileFilter filter) {
    this(parent, new File(System.getProperty("java.io.tmpdir", ".")), filter,
         "...");
  }

  /**
   * ��ָ���Ĳ�������һ��FileSelector��
   * @param parent ������
   * @param dir �ļ�ѡȡ���ĳ�ʼĿ¼
   * @param filter ������
   * @param text �����ť������
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
   * �õ�ѡ���ļ�����Ŀ¼�����ơ�
   * @return ѡ���ļ�����Ŀ¼������
   * @since  0.5
   */
  public String getFile() {
    return nameField.getText();
  }

}