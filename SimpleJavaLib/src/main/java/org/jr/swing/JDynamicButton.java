package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月15日
 * @author Cherami
 */
import javax.swing.*;

/**
 * 可以动态管理按钮的文本、提示信息以及图像的显示状态的按钮组件。
 * @since  0.3
 */

public class JDynamicButton
    extends JButton {
  String text = "";
  String tooltipText;
  Icon icon;
  /**
   * 构造一个空白的JDynamicButton。
   * @since  0.3
   */
  public JDynamicButton() {
  }

  /**
   * 构造一个具有图像的JDynamicButton。
   * @param icon 图像
   * @since  0.3
   */
  public JDynamicButton(Icon icon) {
    super(icon);
    this.icon = icon;
  }

  /**
   * 构造一个具有按钮文本的JDynamicButton。
   * @param text 按钮文本
   * @since  0.4
   */
  public JDynamicButton(String text) {
    super(text);
    this.text = text;
  }

  /**
   * 构造一个具有按钮文本和提示信息的JDynamicButton。
   * @param text 按钮文本
   * @param tooltipText 提示信息
   * @since  0.4
   */
  public JDynamicButton(String text, String tooltipText) {
    super(text);
    this.text = text;
    setToolTipText(tooltipText);
  }

  /**
   * 构造一个具有文本和图像的JDynamicButton。
   * @param text 显示文本
   * @param icon 图像
   * @since  0.3
   */
  public JDynamicButton(String text, Icon icon) {
    super(text, icon);
    this.text = text;
    this.icon = icon;
  }

  /**
   * 构造一个具有文本、提示信息和图像的JDynamicButton。
   * @param text 显示文本
   * @param tooltipText 提示信息
   * @param icon 图像
   * @since  0.3
   */
  public JDynamicButton(String text, String tooltipText, Icon icon) {
    super(text, icon);
    this.text = text;
    this.icon = icon;
    setToolTipText(tooltipText);
  }

  /**
   * 设置按钮的显示文本。
   * 如果原来的显示文本的显示模式被设置为不显示，那么重新设置以后将会把新的文本显示出来。
   * @param text 显示文本
   * @since  0.3
   */
  public void setText(String text) {
    this.text = text;
    showText(true);
  }

  /**
   * 设置按钮的提示信息。
   * 如果原来的提示信息的显示模式被设置为不显示，那么重新设置以后将会把新的提示信息显示出来。
   * @param tooltipText 提示信息
   * @since  0.3
   */
  public void setToolTipText(String tooltipText) {
    this.tooltipText = tooltipText;
    showToolTipText(true);
  }

  /**
   * 设置按钮的图像。
   * 如果原来的图像的显示模式被设置为不显示，那么重新设置以后将会把新的图像显示出来。
   * @param defaultIcon 正常状态下的缺省图像
   * @since  0.3
   */
  public void setIcon(Icon defaultIcon) {
    this.icon = defaultIcon;
    showIcon(true);
  }

  /**
   * 设置是否显示文本
   * @param show true的时候显示，否则不显示。
   * @since  0.3
   */
  public void showText(boolean show) {
    if (show == true) {
      super.setText(text);
    }
    else {
      super.setText("");
    }
  }

  /**
   * 设置是否显示提示信息。
   * @param show true的时候显示，否则不显示。
   * @since  0.3
   */
  public void showToolTipText(boolean show) {
    if (show == true) {
      super.setToolTipText(tooltipText);
    }
    else {
      super.setToolTipText(null);
    }

  }

  /**
   * 设置图像是否显示。
   * @param show true的时候显示，否则不显示。
   * @since  0.3
   */
  public void showIcon(boolean show) {
    if (show == true) {
      super.setIcon(icon);
    }
    else {
      super.setIcon(null);
    }
  }

}