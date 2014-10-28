package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��4��
 * @author Cherami
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.*;
import javax.swing.table.*;

/**
 * ������״̬��ʾ�ı��ͷ��Ⱦ����
 * @since  0.5
 */

public class SortableColumnHeaderRenderer
    implements TableCellRenderer {
  TableCellRenderer render; //�ڲ���Ⱦ��
  JPanel panel; //�ڲ����
  SortableLineDataTableModel model; //���ģ��
  /**
   * ���췽�������ݱ��ģ�ͺ�ָ���ĳ�ʼ��Ⱦ�������Լ�����ȾЧ����
   * @param model ���ģ��
   * @param render ��ʼ��Ⱦ��
   */
  public SortableColumnHeaderRenderer(SortableLineDataTableModel model,
                                      TableCellRenderer render) {
    this.model = model;
    this.render = render;
  }

  /**
   * ���췽�������ݱ��ģ���Լ�����ȾЧ����
   * @param model ���ģ��
   */
  public SortableColumnHeaderRenderer(SortableLineDataTableModel model) {
    this(model, null);
  }

  /**
   * ������Ⱦ��������
   * @param table ��Ҫ������Ⱦ�ı��
   * @param value ��Ⱦ��Ԫ��ֵ
   * @param isSelected �Ƿ�ѡ��
   * @param hasFocus �Ƿ��ý���
   * @param row ��Ҫ����ѡ����к�
   * @param column ��Ҫ������Ⱦ���к�
   * @return ������Ⱦ������
   */
  public Component getTableCellRendererComponent(JTable table, Object value,
                                                 boolean isSelected,
                                                 boolean hasFocus, int row,
                                                 int column) {
    panel = new JPanel();
    panel.setLayout(new BorderLayout());
    Component component;
    if (render != null) {
      component = render.getTableCellRendererComponent(table, value,
          isSelected, hasFocus, row, column);
    }
    else {
      component = new JLabel( (String) value, JLabel.CENTER);
      LookAndFeel.installColorsAndFont( (JComponent) component,
                                       "TableHeader.background",
                                       "TableHeader.foreground",
                                       "TableHeader.font");
    }
    establishComponent(component, column);
    return panel;
  }

  /**
   * ������Ⱦ��������
   * @param component ԭ��δ����Ⱦ�����
   * @param column ��Ҫ��Ⱦ���к�
   */
  private void establishComponent(Component component, int column) {
    panel.add(component, BorderLayout.CENTER);
    if (column == model.sortColumn) {
      BasicArrowButton arrowButton = new BasicArrowButton( (model.isAscent ?
          SwingConstants.NORTH : SwingConstants.SOUTH));
      panel.add(arrowButton, BorderLayout.EAST);
    }
  }

}
