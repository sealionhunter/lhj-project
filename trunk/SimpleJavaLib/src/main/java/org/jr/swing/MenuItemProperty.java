package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��28��
 * @author Cherami
 */
import org.jr.*;

/**
 * �˵������Եĳ���
 * <p>�����˵�����ʾ���֡���ʾ��Ϣ��ͼ���ļ����ļ�������ݼ��Լ����ٽ���
 * ��ݼ�����ʹ������ֵҲ����ʹ���ַ��������ٽ���������Ҫʹ��KeyStroke�е�˵����
 * @see "Java API�е�public static KeyStroke getKeyStroke(String s)��˵��"
 * @since  0.4
 */

public class MenuItemProperty
    extends CharSplitProperty {
  /**
   * �˵������ʾ���֡�
   * @since  0.4
   */
  public final String text;
  /**
   * �˵������ʾ��Ϣ��
   * @since  0.4
   */
  public final String tooltip;
  /**
   * �˵���ͼ���·����
   * <b>��ע���0.5beta�濪ʼ�������Ҫ����һ���Ϸ���URL·�����������ڴ�jar������ȡͼ��</b>
   * @since  0.4
   */
  public final String image;
  /**
   * �˵���Ŀ�ݼ���
   * @since  0.4
   */
  public final String mnemonic;
  /**
   * �˵���ļ��ټ���
   * @since  0.4
   */
  public final String accelerator;
  /**
   * �˵����ѡ��״̬��
   * @since  0.4
   */
  public final String selected;
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
  /**
   * ��ݼ�������������
   * @since  0.4
   */
  public static final int MNEMONIC = 3;
  /**
   * ���ټ�������������
   * @since  0.4
   */
  public static final int ACCELERATOR = 4;
  /**
   * ѡ��״̬������������
   * @since  0.4
   */
  public static final int SELECTED = 5;

  private static final int LEAST_PROPERTY = 1;
  private static final int PROPERTY_COUNT = 5;

  /**
   * ���췽��������ԭʼ��Ϣ�����õ���Ҫ�ĸ�������Ϣ�������ķָ���Ϊ'*'��
   * ����ʹ��'V'��Ϊ��ݼ���ʹ��CTRL+B��Ϊ���ٽ�����ôsource������Ӧ��Ϊ��
   * "�˵���*�˵���˵��*images/menuitem.jpg*V*control b"
   * @param source δ��������ԭʼ��Ϣ
   * @since  0.4
   */
  public MenuItemProperty(String source) {
    this(source, '*');
  }

  /**
   * ���췽��������ԭʼ��Ϣ�����õ���Ҫ�ĸ�������Ϣ��
   * �ο���һ�����췽���е�˵������source��
   * @param source δ��������ԭʼ��Ϣ
   * @param splitChar �����ķָ���
   * @since  0.4
   */
  public MenuItemProperty(String source, char splitChar) {
    super(source, splitChar);
    text = getProperty(TEXT);
    tooltip = getProperty(TOOLTIP);
    image = getProperty(IMAGE);
    mnemonic = getProperty(MNEMONIC);
    accelerator = getProperty(ACCELERATOR);
    selected = getProperty(SELECTED);
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
        return "Menu item " + getNumber();
      case TOOLTIP:
        return "press this menu item";
      case IMAGE:
        return "";
      case MNEMONIC:
        return "";
      case ACCELERATOR:
        return "";
      case SELECTED:
        return "false";
      default:
        return "";
    }
  }
}
