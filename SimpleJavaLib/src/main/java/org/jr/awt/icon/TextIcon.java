package org.jr.awt.icon;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月16日
 * @author Cherami
 */

import java.util.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;

/**
 * 文本图标。
 * @since  0.1
 */

public class TextIcon extends MetalIconFactory.TreeLeafIcon {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1999592788878131789L;

	protected String label;

	private static HashMap<String, String> labels;

	/**
	 * 构造一个空白的TextIcon。
	 * @since  0.3
	 */
	public TextIcon() {
		label = " ";
	}

	/**
	 * 根据扩展名构造一个具有合适文本的TextIcon。
	 * @param ext 扩展名
	 * @since  0.3
	 */
	public TextIcon(String ext) {
		label = getLabel(ext);
	}

	/**
	 * 绘制图标
	 * @param c 绘制组件
	 * @param g 图形设备
	 * @param x 绘制的x坐标的起始点
	 * @param y 绘制的y坐标的起始点
	 * @since  0.1
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		super.paintIcon(c, g, x, y);
		if (label != null) {
			FontMetrics fm = g.getFontMetrics();

			int offsetX = (getIconWidth() - fm.stringWidth(label)) / 2;
			int offsetY = (getIconHeight() - fm.getHeight()) / 2 - 2;

			g.drawString(label, x + offsetX, y + offsetY + fm.getHeight());
		}
	}

	/**
	 * 根据指定的扩展名得到一个文本图标。
	 * @param ext 扩展名
	 * @return 指定的扩展名对应的文本图标
	 * @since  0.1
	 */
	public static Icon getIcon(String ext) {
		TextIcon icon = new TextIcon(getLabel(ext));
		return icon;
	}

	/**
	 * 设置扩展名对应的图标文本。
	 * @param ext 扩展名
	 * @param label 图标文本
	 * @since  0.1
	 */
	public static void setLabel(String ext, String label) {
		if (labels == null) {
			labels = new HashMap<String, String>();
			setDefaultSet();
		}
		labels.put(ext, label);
	}

	/**
	 * 得到扩展名对应的图标文本。
	 * 如果在缺省的对应集里面没有则使用扩展名的第一个字符作为图标文本并加入对应集。
	 * @param ext 扩展名
	 * @return 扩展名对应的图标文本
	 * @since  0.3
	 */
	public static String getLabel(String ext) {
		if (labels == null) {
			labels = new HashMap<String, String>();
			setDefaultSet();
		}
		String label = (String) labels.get(ext);
		if (label == null) {
			label = ext.substring(0, 1);
			setLabel(ext, label);
		}
		return label;
	}

	/**
	 * 初始化缺省的扩展名－图标文本集。
	 */
	private static void setDefaultSet() {
		labels.put("c", "C");
		labels.put("java", "C");
		labels.put("html", "D");
		labels.put("htm", "D");
		labels.put("txt", "T");
		labels.put("cc", "C");
		labels.put("cpp", "C");
		labels.put("exe", "B");
		labels.put("class", "B");
		labels.put("gif", "I");
		labels.put("jpg", "I");
		labels.put("bat", "S");
		labels.put("sh", "S");
		labels.put("pl", "S");
		labels.put("h", "H");
		labels.put("hpp", "H");
		labels.put("jsp", "C");
		labels.put("xml", "C");
		labels.put("doc", "T");
		labels.put("pdf", "D");
		labels.put("jar", "Z");
		labels.put("zip", "Z");
		labels.put("gz", "Z");
		labels.put("tar", "A");
	}

}
