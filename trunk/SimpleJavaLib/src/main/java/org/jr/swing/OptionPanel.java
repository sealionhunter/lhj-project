package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��13��
 * @author Cherami
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * ѡ����塣
 * ����ѡ�����Ĵ�����һ�������ֻ��Ҫ�������ı����Ԫ�صĲ��ּ��ɡ�
 * ʹ��GridLayout���ֹ��������Ƽ����÷��ǣ�
 * panel=new OptionPanel(title, rows, 1);
 * ������һ�����б��⣬ѡ�����ݴ�ֱ���е���塣
 * @since  0.5
 */

public class OptionPanel
    extends JPanel {
  public static final Color defaultShadowColor = new Color(148, 145, 140);
  public static final Color defaultHighlightColor = Color.white;
  protected Color shadowColor;
  protected Color highlightColor;
  protected String title;
  protected int rows;
  protected int columns;
  /**
   * ����ָ���Ĳ�������һ��OptionPanel��
   * �߿���ɫʹ��ȱʡ����ɫ��
   * @param title ��������
   * @param rows ����
   * @param columns ����
   * @since  0.5
   */
  public OptionPanel(String title, int rows, int columns) {
    this(title, rows, columns, defaultHighlightColor, defaultShadowColor);
  }

  /**
   * ����ָ���Ĳ�������һ��OptionPanel��
   * @param title ��������
   * @param rows ����
   * @param columns ����
   * @param highlightColor ��������ɫ
   * @param shadowColor ��������ɫ
   * @since  0.5
   */
  public OptionPanel(String title, int rows, int columns, Color highlightColor,
                     Color shadowColor) {
    this.title = title;
    this.rows = rows;
    this.columns = columns;
    this.highlightColor = highlightColor;
    this.shadowColor = shadowColor;

    setLayout(new GridLayout(rows, columns));
    setBorder(new TitledBorder(BorderFactory.
                               createEtchedBorder(highlightColor,
                                                  shadowColor),
                               title));
  }

}