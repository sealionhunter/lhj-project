package org.jr.util;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月28日
 * @author Cherami
 */

/**
 * 此类中收集Java编程中常用的常量。 不局限于某个包中，当然不包括Math类中的那些常量。 为避免生成此类的实例，构造方法被申明为private类型的。
 * 
 * @since 0.12
 */

public class Constants {
	/**
	 * 换行符。 <b>由于在设计的时候忘记final变量是会经过预编译优化的，因此定义为final变量就没有跨平台的能力了。因此在0.
	 * 5beta版中取消了final声明。</b>
	 * 
	 * @since 0.12
	 */
	public static String LINE_SEPARATOR = System.getProperty("line.separator");
	/**
	 * 文件分隔符。 <b>由于在设计的时候忘记final变量是会经过预编译优化的，因此定义为final变量就没有跨平台的能力了。因此在0.
	 * 5beta版中取消了final声明。</b>
	 * 
	 * @since 0.12
	 */
	public static String FILE_SEPARATOR = System.getProperty("file.separator");
	/**
	 * 路径分隔符。 <b>由于在设计的时候忘记final变量是会经过预编译优化的，因此定义为final变量就没有跨平台的能力了。因此在0.
	 * 5beta版中取消了final声明。</b>
	 * 
	 * @since 0.12
	 */
	public static String PATH_SEPARATOR = System.getProperty("path.separator");

	/**
	 * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
	 */
	private Constants() {
	}

}