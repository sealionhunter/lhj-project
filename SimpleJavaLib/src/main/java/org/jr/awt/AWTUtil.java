package org.jr.awt;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月5日
 * @author Cherami
 */
import java.awt.*;

/**
 * AWT工具类，提供常见的AWT相关的工具方法。
 * 
 * @since 0.5
 */
public class AWTUtil {

	/**
	 * 从HTML格式的字符串得到Color对象。 其前导'#'符号是可选的。
	 * 
	 * @param color
	 *            字符串格式的颜色值
	 * @return 对应的颜色，字符串不是合法格式时返回null
	 * @since 0.5
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
		} catch (NumberFormatException e) {
			return null;
		}
	}
}