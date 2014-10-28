package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��14��
 * @author Cherami
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.jr.text.*;
import org.jr.util.*;

/**
 * �ڴ�״��������塣
 * ���˼�������ʹ��״���Ĺ�����
 * @since  0.5
 */

public class MemoryWatchPanel
    extends JPanel {
  private static final String defaultFormat = "%1/%2(M)";
  int interval;
  String[] values = new String[2];
  private Timer timer;
  protected JLabel label;
  Runtime runtime = Runtime.getRuntime();
  String format;
  FileSizeFormat formatter = new FileSizeFormat();
  /**
   * ����һ�����������ڴ�ʹ��״����MemoryWatchPanel��
   * @since  0.5
   */
  public MemoryWatchPanel() {
    this(3000, defaultFormat);
  }

  /**
   * ����ָ���ĸ�ʽ��ʾ�����������״����MemoryWatchPanel��
   * @param format ��ʾ��ʽ
   * @see org.jr.util.StringUtil#getReplaceString(String source, String[] values) StringUtil.getReplaceString(String source, String[] values)
   * @since  0.5
   */
  public MemoryWatchPanel(String format) {
    this(3000, format);
  }

  /**
   * ��ָ���ĸ�ʽ��ʱ��������һ����ʾ�����������״����MemoryWatchPanel��
   * @param interval ����ʱ�������Ժ���Ϊ��λ
   * @param format ��ʾ��ʽ
   * @see org.jr.util.StringUtil#getReplaceString(String source, String[] values) StringUtil.getReplaceString(String source, String[] values)
   * @since  0.5
   */
  public MemoryWatchPanel(int interval, String format) {
    this.interval = interval;
    this.format = format;
    setLayout(new BorderLayout());
    label = new JLabel();
    add(label, "Center");
    updateStatus();
    timer = new Timer(interval, new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        updateStatus();
      }
    });
    timer.setRepeats(true);
    timer.start();

  }

  /**
   * ��ָ����ʱ��������һ����ʾ�����������״����MemoryWatchPanel��
   * @param interval ����ʱ�������Ժ���Ϊ��λ
   */
  public MemoryWatchPanel(int interval) {
    this(3000, defaultFormat);
  }

  /**
   * ������ʾ��ʽ��
   * @param format ��ʽ
   */
  public void setFormat(String format) {
    this.format = format;
  }

  /**
   * ����ʱ������
   * @param interval ����ʱ�������Ժ���Ϊ��λ
   */
  public void setInterval(int interval) {
    this.interval = interval;
    timer.setDelay(interval);
  }

  /**
   * �����ڴ�״����
   */
  private void updateStatus() {
    values[0] = formatter.format(runtime.freeMemory(), FileSizeFormat.MEGA);
    values[1] = formatter.format(runtime.totalMemory(), FileSizeFormat.MEGA);
    label.setText(StringUtil.getReplaceString(format, values));
  }
}