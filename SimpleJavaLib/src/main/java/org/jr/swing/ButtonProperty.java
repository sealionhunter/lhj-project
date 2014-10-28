package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��7��
 * @author Cherami
 */
import org.jr.*;

/**
 * ��������ͼ��ť��������Եĳ����ࡣ
 * <p>������ť����ʾ���֣���ʾ��Ϣ��ͼ���ļ����ļ���
 * @since  0.4
 */
public class ButtonProperty
    extends CharSplitProperty {
  /**
   * ��ť����ʾ���֡�
   * @since  0.4
   */
  public final String text;
  /**
   * ��ť����ʾ��Ϣ��
   * @since  0.4
   */
  public final String tooltip;
  /**
   * ͼ���ļ���·����
   * @since  0.4
   */
  public final String image;
  /**
   * ��ʾ���ֵ�����������
   * @since  0.4
   */
  public static final int TEXT = 0;
  /**
   * ��ʾ��Ϣ������������
   * @since  0.4
   */
  public static final int TOOLTIP = 1;
  /**
   * ͼ��·��������������
   * @since  0.4
   */
  public static final int IMAGE = 2;

  private static final int LEAST_PROPERTY = 1;
  private static final int PROPERTY_COUNT = 3;

  /**
   * ���췽��������ԭʼ��Ϣ�����õ���Ҫ�ĸ�������Ϣ�������ķָ���Ϊ'*'��
   * @param source δ��������ԭʼ��Ϣ
   * @since  0.4
   */
  public ButtonProperty(String source) {
    this(source, '*');
  }

  /**
   * ���췽��������ԭʼ��Ϣ�����õ���Ҫ�ĸ�������Ϣ��
   * @param source δ��������ԭʼ��Ϣ
   * @param splitChar �����ķָ���
   * @since  0.4
   */
  public ButtonProperty(String source, char splitChar) {
    super(source, splitChar);
    text = getProperty(TEXT);
    tooltip = getProperty(TOOLTIP);
    image = getProperty(IMAGE);
  }

  /**
   * �õ�����Ӧ�е����ٸ�����
   * @return ����Ӧ�е����ٸ���������ֵΪ1��
   * @since  0.4
   */
  public int getLeastPropertyCount() {
    return LEAST_PROPERTY;
  }

  /**
   * �õ�����Ӧ�еĸ�����
   * ���ڶ��ھ���ĵ���Ŀ���ԣ�����Ҫ�����Ը����ǲ�ͬ�ģ���˴˷���������Ϊ����ġ�
   * @return Ӧ�е����Ը���
   * @since  0.4
   */
  public int getPropertyCount() {
    return PROPERTY_COUNT;
  }

  /**
   * �õ����������е�ָ�����������Բ�����ʱ�ķ���ֵ��
   * @param index ���������е�����
   * @return ���������е�ָ�����������Բ�����ʱ�ķ���ֵ
   * @since  0.4
   */
  public String getDefaultProperty(int index) {
    switch (index) {
      case TEXT:
        return "Button " + getNumber();
      case TOOLTIP:
        return "press this button";
      case IMAGE:
        return "";
      default:
        return "";
    }
  }
}
