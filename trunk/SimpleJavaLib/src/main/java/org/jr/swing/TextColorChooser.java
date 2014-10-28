package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月12日
 * @author Cherami
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 文本色彩选择器。
 * @since  0.1
 */

public class TextColorChooser
    extends JColorChooser {
  /**
   * 根据初始颜色构造一个TextColorChooser。
   * @param initialColor 初始的文本颜色
   * @param reference 初始的背景颜色
   * @param isForgroundSelection 是否选中前景色
   * @since  0.1
   */
  public TextColorChooser(Color initialColor, Color reference,
                          boolean isForgroundSelection) {
    super(initialColor);
    if (isForgroundSelection) {
      setPreviewPanel(new TextPreviewLabel(initialColor, reference,
                                           isForgroundSelection));
    }
    else {
      setPreviewPanel(new TextPreviewLabel(reference, initialColor,
                                           isForgroundSelection));
    }
    updateUI();
  }

  /**
   * 显示文本色彩选择对话框。
   * @param component 主框架的一个子组件
   * @param title 对话框标题
   * @return 用户选择的颜色。
   * @since  0.1
   */
  public Color showDialog(Component component, String title) {
    ColorChooserDialog colorDialog = new ColorChooserDialog(component, title, this);
    colorDialog.show();
    Color color = colorDialog.getColor();
    colorDialog.dispose();
    return color;
  }

  /**
   * 预览文本域。
   * @since  0.1
   */
  class TextPreviewLabel
      extends JLabel {
    private String sampleText = "  Sample Text  Sample Text  ";
    boolean isForgroundSelection;
    /**
     * 构造一个缺省的TextPreviewLabel。
     * @since  0.1
     */
    public TextPreviewLabel() {
      this(Color.black, Color.white, true);
    }

    /**
     * 以指定颜色构造一个TextPreviewLabel。
     * @param fore 前景色
     * @param back 背景色
     * @param isForgroundSelection 是否使用前景色
     * @since  0.1
     */
    public TextPreviewLabel(Color fore, Color back,
                            boolean isForgroundSelection) {
      setOpaque(true);
      this.isForgroundSelection = isForgroundSelection;
      setForeground(fore);
      setBackground(back);
      setText(sampleText);
    }

    /**
     * 设置前景色。
     * @param color 前景色
     * @since  0.1
     */
    public void setForeground(Color color) {
      if (isForgroundSelection) {
        super.setForeground(color);
      }
      else {
        super.setBackground(color);
      }
    }

  }

  /**
   * 颜色选择对话框。
   * @since  0.1
   */
  class ColorChooserDialog
      extends JDialog {
    private Color initialColor;
    private Color returnColor;
    private JColorChooser chooserPane;
    /**
     * 构造一个ColorChooserDialog。
     * @param c 对话框的父容器
     * @param title 对话框的标题
     * @param chooserPane 对话框的颜色选择器
     * @since  0.1
     */
    public ColorChooserDialog(Component c, String title,
                              final JColorChooser chooserPane) {

      super(JOptionPane.getFrameForComponent(c), title, true);
      setResizable(false);

      this.chooserPane = chooserPane;

      String okString = UIManager.getString("ColorChooser.okText");
      String cancelString = UIManager.getString("ColorChooser.cancelText");
      String resetString = UIManager.getString("ColorChooser.resetText");

      Container contentPane = getContentPane();
      contentPane.setLayout(new BorderLayout());
      contentPane.add(chooserPane, BorderLayout.CENTER);

      JPanel buttonPane = new JPanel();
      buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
      JButton okButton = new JButton(okString);
      getRootPane().setDefaultButton(okButton);
      okButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          returnColor = chooserPane.getColor();
          setVisible(false);
        }
      });
      buttonPane.add(okButton);

      JButton cancelButton = new JButton(cancelString);
      cancelButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          returnColor = null;
          setVisible(false);
        }
      });
      buttonPane.add(cancelButton);

      JButton resetButton = new JButton(resetString);
      resetButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          chooserPane.setColor(initialColor);
        }
      });
      buttonPane.add(resetButton);
      contentPane.add(buttonPane, BorderLayout.SOUTH);

      pack();
      setLocationRelativeTo(c);
      addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          setVisible(false);
        }
      });
    }

    /**
     * 得到用户选择的颜色。
     * @return 用户选择的颜色
     * @since  0.1
     */
    public Color getColor() {
      return returnColor;
    }

  }
}
