package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月4日
 * @author Cherami
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.*;
import javax.swing.table.*;

/**
 * 带排序状态显示的表格头渲染器。
 * @since  0.5
 */

public class SortableColumnHeaderRenderer
    implements TableCellRenderer {
  TableCellRenderer render; //内部渲染器
  JPanel panel; //内部面板
  SortableLineDataTableModel model; //表格模型
  /**
   * 构造方法，根据表格模型和指定的初始渲染器构造自己的渲染效果。
   * @param model 表格模型
   * @param render 初始渲染器
   */
  public SortableColumnHeaderRenderer(SortableLineDataTableModel model,
                                      TableCellRenderer render) {
    this.model = model;
    this.render = render;
  }

  /**
   * 构造方法，根据表格模型自己的渲染效果。
   * @param model 表格模型
   */
  public SortableColumnHeaderRenderer(SortableLineDataTableModel model) {
    this(model, null);
  }

  /**
   * 经过渲染后的组件。
   * @param table 需要进行渲染的表格
   * @param value 渲染单元的值
   * @param isSelected 是否被选中
   * @param hasFocus 是否获得焦点
   * @param row 需要进行选择的行号
   * @param column 需要进行渲染的列号
   * @return 经过渲染后的组件
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
   * 经过渲染后的组件。
   * @param component 原来未经渲染的组件
   * @param column 需要渲染的列号
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
