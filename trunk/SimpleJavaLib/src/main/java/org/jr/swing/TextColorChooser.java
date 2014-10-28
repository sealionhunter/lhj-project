package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��12��
 * @author Cherami
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * �ı�ɫ��ѡ������
 * @since  0.1
 */

public class TextColorChooser
    extends JColorChooser {
  /**
   * ���ݳ�ʼ��ɫ����һ��TextColorChooser��
   * @param initialColor ��ʼ���ı���ɫ
   * @param reference ��ʼ�ı�����ɫ
   * @param isForgroundSelection �Ƿ�ѡ��ǰ��ɫ
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
   * ��ʾ�ı�ɫ��ѡ��Ի���
   * @param component ����ܵ�һ�������
   * @param title �Ի������
   * @return �û�ѡ�����ɫ��
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
   * Ԥ���ı���
   * @since  0.1
   */
  class TextPreviewLabel
      extends JLabel {
    private String sampleText = "  Sample Text  Sample Text  ";
    boolean isForgroundSelection;
    /**
     * ����һ��ȱʡ��TextPreviewLabel��
     * @since  0.1
     */
    public TextPreviewLabel() {
      this(Color.black, Color.white, true);
    }

    /**
     * ��ָ����ɫ����һ��TextPreviewLabel��
     * @param fore ǰ��ɫ
     * @param back ����ɫ
     * @param isForgroundSelection �Ƿ�ʹ��ǰ��ɫ
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
     * ����ǰ��ɫ��
     * @param color ǰ��ɫ
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
   * ��ɫѡ��Ի���
   * @since  0.1
   */
  class ColorChooserDialog
      extends JDialog {
    private Color initialColor;
    private Color returnColor;
    private JColorChooser chooserPane;
    /**
     * ����һ��ColorChooserDialog��
     * @param c �Ի���ĸ�����
     * @param title �Ի���ı���
     * @param chooserPane �Ի������ɫѡ����
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
     * �õ��û�ѡ�����ɫ��
     * @return �û�ѡ�����ɫ
     * @since  0.1
     */
    public Color getColor() {
      return returnColor;
    }

  }
}
