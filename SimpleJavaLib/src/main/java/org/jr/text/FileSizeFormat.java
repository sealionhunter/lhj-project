package org.jr.text;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>×ûÖó¸?ĞÂÈÕÆÚ:2003Ä?3ÔÂ3ÈÕ
 * @author Cherami
 */

import java.text.*;

/**
 * ÎÄ¼?´óĞ¡¸ñÊ½»¯Àà¡£
 * Õâ¸öÀà±¾À´Ó¦¸ÃÊÇ´ÓNumberFormatÀà¼Ì³Ğ¶øÀ´£¬µ«ÊÇÓÉÓÚNumberFormatµÄformat(long number)±»¶¨ÒåÎª
 * finalµÄ£¬Òò´Ë²ÉÈ¡´ÓFormat¼Ì³Ğ£¬ÆäÄÚÈİÊµ¼Ê¾ÍÊÇÒ»¸öNumberFormat¡£
 * @since  0.5
 */
public class FileSizeFormat
    extends Format {
  public static final int BYTE = 0;
  public static final int KILO = 1;
  public static final int MEGA = 2;
  public static final int GIGA = 3;
  public static final long KILOBYTE = 1024;
  public static final long MEGABYTE = 1024 << 10;
  public static final long GIGABYTE = 1024 << 20;
  public static final String[] defaultUnitNames = {
      "B", "K", "M", "G"};
  public static final String defaultFormat = "#,##0.##";
  String format;
  String[] unitNames;
  boolean showUnitName = false;
  NumberFormat formatter;
  /**
   * µÃµ½Ò»¸öÈ±Ê¡µÄFileSizeFormat¡£
   * ¼´²»ÏÔÊ¾³ß´çµÄµ¥Î»£¬¸ñÊ½ÎªÈ±Ê¡¸ñÊ½"#,##0.##"¡£
   * @since  0.5
   */
  public FileSizeFormat() {
    this(defaultFormat, false, null);
  }

  /**
   * ¸ù¾İÖ¸¶¨µÄ¸ñÊ½¹¹Ô?Ò»¸öFileSizeFormat¡£
   * ²»ÏÔÊ¾³ß´çµÄµ¥Î»¡£
   * @param format ¸ñÊ½
   * @since  0.5
   */
  public FileSizeFormat(String format) {
    this(format, false, null);
  }

  /**
   * ¸ù¾İÖ¸¶¨µÄÊôĞÔ¹¹Ô?Ò»¸öFileSizeFormat¡£
   * @param showUnitName ÊÇ·ñÏÔÊ¾µ¥Î»
   * @since  0.5
   */
  public FileSizeFormat(boolean showUnitName) {
    this(defaultFormat, showUnitName, defaultUnitNames);
  }

  /**
   * ¸ù¾İÖ¸¶¨µ¥Î»¹¹Ô?Ò»¸öFileSizeFormat¡£
   * ÏÔÊ¾³ß´çµÄµ¥Î»¡£
   * @param unitNames µ¥Î»£¬ÒÀ´ÎÎª×Ö½Ú¡¢Ç§×Ö½Ú¡¢Õ××Ö½ÚºÍ°ÙÕ××Ö½ÚµÄµ¥Î»¡£
   * @since  0.5
   */
  public FileSizeFormat(String[] unitNames) {
    this(defaultFormat, true, unitNames);
  }

  /**
   * ¸ù¾İÖ¸¶¨µÄ¸ñÊ½ºÍµ¥Î»¹¹Ô?Ò»¸öFileSizeFormat¡£
   * ÏÔÊ¾³ß´çµÄµ¥Î»¡£
   * @param format ¸ñÊ½
   * @param unitNames µ¥Î»£¬ÒÀ´ÎÎª×Ö½Ú¡¢Ç§×Ö½Ú¡¢Õ××Ö½ÚºÍ°ÙÕ××Ö½ÚµÄµ¥Î»¡£
   * @since  0.5
   */
  public FileSizeFormat(String format, String[] unitNames) {
    this(format, true, unitNames);
  }

  /**
   * ¸ù¾İÖ¸¶¨µÄ¸ñÊ½ºÍÊÇ·ñÏÔÊ¾³ß´çµ¥Î»¹¹Ô?Ò»¸öFileSizeFormat¡£
   * @param format ¸ñÊ½
   * @param showUnitName trueµÄÊ±ºòÏÔÊ¾µ¥Î»£¬Ê¹ÓÃÈ±Ê¡µÄµ¥Î»¡£
   * @since  0.5
   */
  public FileSizeFormat(String format, boolean showUnitName) {
    this(format, showUnitName, defaultUnitNames);
  }

  /**
   * ¸ù¾İÖ¸¶¨µÄ¸ñÊ½ºÍÊÇ·ñÏÔÊ¾³ß´çµ¥Î»¹¹Ô?Ò»¸öFileSizeFormat¡£
   * @param format ¸ñÊ½
   * @param showUnitName trueµÄÊ±ºòÏÔÊ¾µ¥Î»£¬Ê¹ÓÃÈ±Ê¡µÄµ¥Î»¡£
   * @param unitNames µ¥Î»£¬ÒÀ´ÎÎª×Ö½Ú¡¢Ç§×Ö½Ú¡¢Õ××Ö½ÚºÍ°ÙÕ××Ö½ÚµÄµ¥Î»¡£
   * @since  0.5
   */
  public FileSizeFormat(String format, boolean showUnitName, String[] unitNames) {
    this.format = format;
    formatter = new DecimalFormat(format);
    this.showUnitName = showUnitName;
    this.unitNames = unitNames;
  }

  /**
   * µÃµ½¿É¶ÁµÄÊ?×Ö´óĞ¡£¬Ò»°ãÓÃÓÚÎÄ¼?³ß´ç¡£
   * @param number Ê?×Ö£¬Ò»°ãÓ¦¸ÃÊÇLongÀàĞÍµÄ
   * @return ¸ñÊ½»¯ÒÔºóµÄ×Ö·û´®
   * @since  0.5
   */
  public String format(Number number) {
    return format(number.longValue(), getUnit(number.longValue()));
  }

  /**
   * µÃµ½¿É¶ÁµÄÊ?×Ö´óĞ¡¡£
   * @param size Ô­Ê¼´óĞ¡
   * @return ¸ñÊ½»¯ÒÔºóµÄ×Ö·û´®
   * @since  0.5
   */
  public String format(long size) {
    return format(size, getUnit(size));
  }

  /**
   * µÃµ½Ê?×ÖµÄµ¥Î»£¬Ò»°ãÓÃÓÚÎÄ¼?³ß´ç¡£
   * @param number Ê?×Ö£¬Ò»°ãÓ¦¸ÃÊÇLongÀàĞÍµÄ
   * @return numberµÄlongÖµÔÚ1024ÒÔÏÂÊ±·µ»ØBYTE£¬ÒÀ´ÎÀàÍÆÖ±µ½GIGA¡£
   * @since  0.5
   */
  public int getUnit(Number number) {
    return getUnit(number.longValue());
  }

  /**
   * µÃµ½Ê?×ÖµÄµ¥Î»¡£
   * @param size Ô­Ê¼´óĞ¡
   * @return sizeµÄÖµÔÚ1024ÒÔÏÂÊ±·µ»ØBYTE£¬ÒÀ´ÎÀàÍÆÖ±µ½GIGA¡£
   * @since  0.5
   */
  public int getUnit(long size) {
    if (size < KILOBYTE) {
      return BYTE;
    }
    else if (size < MEGABYTE) {
      return KILO;
    }
    else if (size < GIGABYTE) {
      return MEGA;
    }
    else {
      return GIGA;
    }
  }

  /**
   * µÃµ½¸ñÊ½»¯µÄ´óĞ¡¡£
   * @param number Ô­Ê¼´óĞ¡
   * @param unit µ¥Î»
   * @return ¸ù¾İµ¥Î»½øĞĞ¸ñÊ½»¯ºóµÄ×Ö·û´®´óĞ¡
   * @since  0.5
   */
  public String format(Number number, int unit) {
    return format(number.longValue(), unit);
  }

  /**
   * ÉèÖÃ¸ñÊ½»¯Ê±µÄ¸ñÊ½¡£
   * @param format ¸ñÊ½
   * @since  0.5
   */
  public void setFormat(String format) {
    this.format = format;

  }

  /**
   * µÃµ½¸ñÊ½Ê±µÄ¸ñÊ½¡£
   * @return ¸ñÊ½Ê±µÄ¸ñÊ½
   * @since  0.5
   */
  public String getFormat() {
    return format;
  }

  /**
   * µÃµ½¸ñÊ½»¯µÄ´óĞ¡¡£
   * @param size Ô­Ê¼´óĞ¡
   * @param unit µ¥Î»
   * @return ¸ù¾İµ¥Î»½øĞĞ¸ñÊ½»¯ºóµÄ×Ö·û´®´óĞ¡
   * @since  0.5
   */
  public String format(long size, int unit) {
    String result;
    switch (unit) {
      case BYTE:
        result = formatter.format(size);
        break;
      case KILO:
        result = formatter.format( ( (double) size) / ( (double) KILOBYTE));
        break;
      case MEGA:
        result = formatter.format( ( (double) size) / ( (double) MEGABYTE));
        break;
      case GIGA:
        result = formatter.format( ( (double) size) / ( (double) GIGABYTE));
        break;
      default:
        result = formatter.format(size);
        break;
    }
    if (showUnitName == true) {
      result += unitNames[unit];
    }
    return result;
  }

  /**
   * ÉèÖÃÏÔÊ¾µ¥Î»µÄÃû³Æ¡£
   * Õâ¸ö·½·¨Í¬Ê±Ò²»á½«ÉèÖÃÏÔÊ¾µ¥Î»Ãû³Æ¡£
   * @param unitNames Ãû³ÆÊ?×?
   * @since  0.5
   */
  public void setUnitNames(String[] unitNames) {
    showUnitName = true;
    this.unitNames = unitNames;
  }

  /**
   * ·µ»ØÏÔÊ¾Ãû³ÆÊ?×é¡£
   * @return Ãû³ÆÊ?×?
   * @since  0.5
   */
  public String[] getUnitNames() {
    return unitNames;
  }

  /**
   * ÉèÖÃÊÇ·ñÏÔÊ¾µ¥Î»Ãû³Æ¡£
   * Èç¹ûÏÔÊ¾µ¥Î»Ãû³Æµ«ÊÇÃû³ÆÊ?×éÎªnullÊ±ÉèÖÃÎªÈ±Ê¡µ¥Î»Ãû³Æ¡£
   * @param visible ÊÇ·ñÏÔÊ¾µ¥Î»Ãû³Æ
   * @since  0.5
   */
  public void setUnitNameVisible(boolean visible) {
    showUnitName = visible;
    if (showUnitName == true && unitNames == null) {
      unitNames = defaultUnitNames;
    }
  }

  /**
   * ÊÇ·ñÏÔÊ¾µ¥Î»Ãû³Æ¡£
   * @return ÏÔÊ¾Ê±·µ»Øtrue£¬·ñÔò·µ»Øfalse
   * @since  0.5
   */
  public boolean isUnitNameVisible() {
    return showUnitName;
  }

  /**
   * ½âÎö×Ö·û´®ÎªÒ»¸ö¶ÔÏó¡£
   * @param source Òª½âÎöµÄ¶ÔÏó×Ö·û´®
   * @param pos ½âÎöÎ»ÖÃ
   * @return ¾­NumberFormat½âÎöµÄ½á¹?
   * @since  0.5
   */
  public Object parseObject(String source,
                            ParsePosition pos) {
    return formatter.parseObject(source, pos);
  }

  /**
   * ¸ù¾İÖ¸¶¨µÄ¶ÔÏóºÍ¸½¼Ó¶ÔÏó½øĞĞ¸ñÊ½»¯¡£
   * @param obj ¸ñÊ½»¯µÄ¶ÔÏ?
   * @param toAppendTo ¸½¼?µÄÎÄ±¾ĞÅÏ¢
   * @param pos ¸ñÊ½»¯µÄÎÄ±¾ÖĞµÄÎ»ÖÃĞÅÏ¢
   * @return ¾­NumberFormat¸ñÊ½»¯ÒÔºóµÄStringBuffer
   * @since  0.5
   */
  public StringBuffer format(Object obj,
                             StringBuffer toAppendTo,
                             FieldPosition pos) {
    return formatter.format(obj, toAppendTo, pos);

  }

}