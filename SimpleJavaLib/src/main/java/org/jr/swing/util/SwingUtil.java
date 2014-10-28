package org.jr.swing.util;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月28日
 * @author Cherami,Barney
 */

import java.awt.*;
import javax.swing.*;
import java.net.*;
import org.jr.swing.*;

/**
 * Swing的工具类，封装一些Swing中常用的工具方法。
 * @since  0.3
 */

public class SwingUtil {
  /**
   * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
   */
  private SwingUtil() {
  }

  /**
   * 将组件定位在计算机屏幕的中间。
   * 要进行定位的组件一般应该是顶层容器，例如Dialog或者Frame。
   * @param component  需要定位到屏幕中间的组件
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
   * 创建子菜单项并添加到父菜单。
   * 如果父菜单为null则不添加。
   * @param property 菜单项属性
   * @param menu 子菜单项的父菜单
   * @return 根据菜单项属性创建的子菜单项
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
   * 创建单选子菜单项并添加到父菜单。
   * 如果父菜单为null则不添加。
   * @param property 菜单项属性
   * @param menu 子菜单项的父菜单
   * @return 根据菜单项属性创建的单选子菜单项
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
   * 创建复选子菜单项并添加到父菜单。
   * 如果父菜单为null则不添加。
   * @param property 菜单项属性
   * @param menu 子菜单项的父菜单
   * @return 根据菜单项属性创建的复选子菜单项
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
   * 创建子菜单并添加到父菜单。
   * 如果父菜单为null则不添加。
   * @param property 菜单属性
   * @param parentMenu 子菜单的父菜单
   * @return 根据菜单属性创建的子菜单
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
   * 创建动态按钮。
   * @param property 按钮属性
   * @return 根据按钮属性创建的动态按钮
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
   * 判断主框架是否是最大化状态
   * @param frame 要判断的主框架
   * @return 全部最大化时返回true，否则返回false
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