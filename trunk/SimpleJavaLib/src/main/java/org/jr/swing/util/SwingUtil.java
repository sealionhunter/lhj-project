package org.jr.swing.util;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��28��
 * @author Cherami,Barney
 */

import java.awt.*;
import javax.swing.*;
import java.net.*;
import org.jr.swing.*;

/**
 * Swing�Ĺ����࣬��װһЩSwing�г��õĹ��߷�����
 * @since  0.3
 */

public class SwingUtil {
  /**
   * ˽�й��췽������ֹ���ʵ��������Ϊ�����಻��Ҫʵ������
   */
  private SwingUtil() {
  }

  /**
   * �������λ�ڼ������Ļ���м䡣
   * Ҫ���ж�λ�����һ��Ӧ���Ƕ�������������Dialog����Frame��
   * @param component  ��Ҫ��λ����Ļ�м�����
   * @since  0.3
   */
  public static void centerWindow(Component component) {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = component.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    component.setLocation( (screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);
  }

  /**
   * �����Ӳ˵����ӵ����˵���
   * ������˵�Ϊnull����ӡ�
   * @param property �˵�������
   * @param menu �Ӳ˵���ĸ��˵�
   * @return ���ݲ˵������Դ������Ӳ˵���
   * @since  0.4
   */
  public static JMenuItem createMenuItem(MenuItemProperty property, JMenu menu) {
    JMenuItem menuItem;
    menuItem = new JMenuItem(property.text);
    if (!property.tooltip.equals("")) {
      menuItem.setToolTipText(property.tooltip);
    }
    if (!property.image.equals("")) {
      try {
        menuItem.setIcon(new ImageIcon(new URL(property.image)));
      }
      catch (MalformedURLException e) {
        System.err.println("Error URL string:"+property.image);
      }
    }
    if (!property.mnemonic.equals("")) {
      try {
        int mnemonic = Integer.parseInt(property.mnemonic);
        menuItem.setMnemonic(mnemonic);
      }
      catch (NumberFormatException e) {
        menuItem.setMnemonic(property.mnemonic.charAt(0));
      }
    }
    if (!property.accelerator.equals("")) {
      KeyStroke keyStroke = KeyStroke.getKeyStroke(property.accelerator);
      menuItem.setAccelerator(keyStroke);

    }
    if (menu != null) {
      menu.add(menuItem);
    }
    return menuItem;
  }

  /**
   * ������ѡ�Ӳ˵����ӵ����˵���
   * ������˵�Ϊnull����ӡ�
   * @param property �˵�������
   * @param menu �Ӳ˵���ĸ��˵�
   * @return ���ݲ˵������Դ����ĵ�ѡ�Ӳ˵���
   * @since  0.4
   */
  public static JMenuItem createRadioButtonMenuItem(MenuItemProperty property,
      JMenu menu) {
    JRadioButtonMenuItem menuItem;
    menuItem = new JRadioButtonMenuItem(property.text);
    if (!property.tooltip.equals("")) {
      menuItem.setToolTipText(property.tooltip);
    }
    if (!property.image.equals("")) {
      menuItem.setIcon(new ImageIcon(property.image));
    }
    if (!property.mnemonic.equals("")) {
      try {
        int mnemonic = Integer.parseInt(property.mnemonic);
        menuItem.setMnemonic(mnemonic);
      }
      catch (NumberFormatException e) {
        menuItem.setMnemonic(property.mnemonic.charAt(0));
      }
    }
    if (!property.accelerator.equals("")) {
      KeyStroke keyStroke = KeyStroke.getKeyStroke(property.accelerator);
      menuItem.setAccelerator(keyStroke);

    }
    if (menu != null) {
      menu.add(menuItem);
    }
    return menuItem;
  }

  /**
   * ������ѡ�Ӳ˵����ӵ����˵���
   * ������˵�Ϊnull����ӡ�
   * @param property �˵�������
   * @param menu �Ӳ˵���ĸ��˵�
   * @return ���ݲ˵������Դ����ĸ�ѡ�Ӳ˵���
   * @since  0.4
   */
  public static JMenuItem createCheckBoxMenuItem(MenuItemProperty property,
                                                 JMenu menu) {
    JCheckBoxMenuItem menuItem;
    menuItem = new JCheckBoxMenuItem(property.text);
    if (!property.tooltip.equals("")) {
      menuItem.setToolTipText(property.tooltip);
    }
    if (!property.image.equals("")) {
      menuItem.setIcon(new ImageIcon(property.image));
    }
    if (!property.mnemonic.equals("")) {
      try {
        int mnemonic = Integer.parseInt(property.mnemonic);
        menuItem.setMnemonic(mnemonic);
      }
      catch (NumberFormatException e) {
        menuItem.setMnemonic(property.mnemonic.charAt(0));
      }
    }
    if (!property.accelerator.equals("")) {
      KeyStroke keyStroke = KeyStroke.getKeyStroke(property.accelerator);
      menuItem.setAccelerator(keyStroke);
    }
    if (menu != null) {
      menu.add(menuItem);
    }
    return menuItem;
  }

  /**
   * �����Ӳ˵�����ӵ����˵���
   * ������˵�Ϊnull����ӡ�
   * @param property �˵�����
   * @param parentMenu �Ӳ˵��ĸ��˵�
   * @return ���ݲ˵����Դ������Ӳ˵�
   * @since  0.4
   */
  public static JMenu createMenu(MenuProperty property, JMenu parentMenu) {
    JMenu menu;
    menu = new JMenu(property.text);
    if (!property.mnemonic.equals("")) {
      try {
        int mnemonic = Integer.parseInt(property.mnemonic);
        menu.setMnemonic(mnemonic);
      }
      catch (NumberFormatException e) {
        menu.setMnemonic(property.mnemonic.charAt(0));
      }
    }
    if (parentMenu != null) {
      parentMenu.add(menu);
    }
    return menu;
  }

  /**
   * ������̬��ť��
   * @param property ��ť����
   * @return ���ݰ�ť���Դ����Ķ�̬��ť
   * @since  0.4
   */
  public static JDynamicButton createDynamicButton(ButtonProperty property) {
    JDynamicButton button;
    button = new JDynamicButton(property.text);
    if (!property.tooltip.equals("")) {
      button.setToolTipText(property.tooltip);
    }
    if (!property.image.equals("")) {
      button.setIcon(new ImageIcon(property.image));
    }
    return button;
  }

  /**
   * �ж�������Ƿ������״̬
   * @param frame Ҫ�жϵ������
   * @return ȫ�����ʱ����true�����򷵻�false
   * @since  0.5
   */
  public static boolean isMaximized(Frame frame) {
    if (frame.getExtendedState() == Frame.MAXIMIZED_BOTH) {
      return true;
    }
    else {
      return false;
    }
  }

}