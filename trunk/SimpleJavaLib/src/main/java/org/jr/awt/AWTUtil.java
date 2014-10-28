package org.jr.awt;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��5��
 * @author Cherami
 */

import java.awt.*;

/**
 * AWT�����࣬�ṩ������AWT��صĹ��߷�����
 * @since  0.5
 */

public class AWTUtil {

  /**
   * ��HTML��ʽ���ַ����õ�Color����
   * ��ǰ��'#'�����ǿ�ѡ�ġ�
   * @param color �ַ�����ʽ����ɫֵ
   * @return ��Ӧ����ɫ���ַ������ǺϷ���ʽʱ����null
   * @since  0.5
   */
  public static Color getColor(String color) {
    if (color.charAt(0) == '#') {
      color = color.substring(1);
    }
    if (color.length() != 6) {
      return null;
    }
    try {
      int r = Integer.parseInt(color.substring(0, 2), 16);
      int g = Integer.parseInt(color.substring(2, 4), 16);
      int b = Integer.parseInt(color.substring(4), 16);
      return new Color(r, g, b);
    }
    catch (NumberFormatException e) {
      return null;
    }
  }
}