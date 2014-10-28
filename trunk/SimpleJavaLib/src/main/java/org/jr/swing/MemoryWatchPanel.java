package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月14日
 * @author Cherami
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.jr.text.*;
import org.jr.util.*;

/**
 * 内存状况监视面板。
 * 简化了监视内容使用状况的工作。
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
   * 构造一个监测虚拟机内存使用状况的MemoryWatchPanel。
   * @since  0.5
   */
  public MemoryWatchPanel() {
    this(3000, defaultFormat);
  }

  /**
   * 根据指定的格式显示虚拟机的内容状况的MemoryWatchPanel。
   * @param format 显示格式
   * @see org.jr.util.StringUtil#getReplaceString(String source, String[] values) StringUtil.getReplaceString(String source, String[] values)
   * @since  0.5
   */
  public MemoryWatchPanel(String format) {
    this(3000, format);
  }

  /**
   * 以指定的格式和时间间隔构造一个显示虚拟机的内容状况的MemoryWatchPanel。
   * @param interval 监测的时间间隔，以毫秒为单位
   * @param format 显示格式
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
   * 以指定的时间间隔构造一个显示虚拟机的内容状况的MemoryWatchPanel。
   * @param interval 监测的时间间隔，以毫秒为单位
   */
  public MemoryWatchPanel(int interval) {
    this(3000, defaultFormat);
  }

  /**
   * 设置显示格式。
   * @param format 格式
   */
  public void setFormat(String format) {
    this.format = format;
  }

  /**
   * 设置时间间隔。
   * @param interval 监测的时间间隔，以毫秒为单位
   */
  public void setInterval(int interval) {
    this.interval = interval;
    timer.setDelay(interval);
  }

  /**
   * 更新内存状况。
   */
  private void updateStatus() {
    values[0] = formatter.format(runtime.freeMemory(), FileSizeFormat.MEGA);
    values[1] = formatter.format(runtime.totalMemory(), FileSizeFormat.MEGA);
    label.setText(StringUtil.getReplaceString(format, values));
  }
}