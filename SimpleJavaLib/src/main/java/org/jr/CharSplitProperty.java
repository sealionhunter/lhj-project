package org.jr;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月20日
 * @author Cherami
 */
import java.util.*;

import org.jr.util.*;

/**
 * 以字符分隔的属性的抽象类。
 * <p>
 * 其他具体的属性类的父类，封装共通的方法并完成最初的属性数组的解析。
 * 这个类将一个字符串表达的属性值字符串解析为一个字符串数组，各个子类定义每个值对应何属性。
 * 
 * @since 0.4
 */

public abstract class CharSplitProperty {
	protected final String source; // 保持未经解析的最原始的信息
	protected char splitChar; // 信息的分隔符
	protected final String[] properties; // 解析以后的信息数组
	protected static HashMap<String, Integer> numbers = new HashMap<String, Integer>(); // 未得到最少属性时需要使用的序号

	/**
	 * 构造方法，根据原始信息解析得到需要的各个子信息，解析的分隔符为'*'。 source的一般形式为"value1*value2*value3"
	 * 
	 * @param source
	 *            未经解析的原始信息
	 * @since 0.4
	 */
	public CharSplitProperty(String source) {
		this(source, '*');
	}

	/**
	 * 构造方法，根据原始信息解析得到需要的各个子信息。
	 * 
	 * @param source
	 *            未经解析的原始信息
	 * @param splitChar
	 *            解析的分隔符
	 * @since 0.4
	 */
	public CharSplitProperty(String source, char splitChar) {
		this.source = source;
		this.splitChar = splitChar;
		PropertySerials ps = new PropertySerials(source);
		ps.setSplitChar(splitChar);
		properties = ps.getSerials();
		if (properties.length < getLeastPropertyCount() || properties.length == 0) {
			String className = this.getClass().getName();
			Integer number = (Integer) numbers.get(className);
			if (number == null) {
				number = new Integer(0);
				numbers.put(className, number);
			}
			numbers.put(className, new Integer(number.intValue() + 1));
		}
	}

	/**
	 * 得到对象的字符串表示。
	 * 
	 * @return 对象的字符串表示
	 * @since 0.4
	 */
	public String toString() {
		return source;
	}

	/**
	 * 得到信息解析时使用的分隔符。
	 * 
	 * @return 信息解析时使用的分隔符
	 * @since 0.4
	 */
	public char getSplitChar() {
		return splitChar;
	}

	/**
	 * 得到解析后的属性数组。
	 * 
	 * @return 解析后的属性数组
	 * @since 0.4
	 */
	public String[] getProperties() {
		return properties;
	}

	/**
	 * 得到属性应有的最少个数。 由于对于具体的的项目而言，其需要的最少个数是不同的，因此此方法被定义为抽象的。
	 * 
	 * @return 属性应有的最少个数
	 * @since 0.4
	 */
	public abstract int getLeastPropertyCount();

	/**
	 * 得到属性应有的个数。
	 * <p>
	 * 由于对于具体的的项目而言，其需要的属性个数是不同的，因此此方法被定义为抽象的。
	 * 
	 * @return 应有的属性个数
	 * @since 0.4
	 */
	public abstract int getPropertyCount();

	/**
	 * 得到属性数组中的指定索引的属性不存在时的返回值。
	 * 
	 * @param index
	 *            属性数组中的索引
	 * @return 属性数组中的指定索引的属性不存在时的返回值
	 * @since 0.4
	 */
	public String getDefaultProperty(int index) {
		return "";
	}

	/**
	 * 属性数组中的指定索引的属性。
	 * <p>
	 * 如果不存在时返回缺省值。
	 * 
	 * @param index
	 *            属性数组中的索引
	 * @return 属性数组中的指定索引的属性
	 * @see #getDefaultProperty(int index) getDefaultProperty(int index)
	 * @since 0.4
	 */
	public String getProperty(int index) {
		if (properties == null || index < 0 || index >= properties.length) {
			return getDefaultProperty(index);
		} else if (index == 0 && properties.length == 1 && properties[0].equals("")) {
			return getDefaultProperty(index);
		} else {
			return properties[index];
		}
	}

	/**
	 * 得到属性未满足最少属性数时需要使用的顺序序号。
	 * 
	 * @return 序号
	 * @since 0.4
	 */
	public int getNumber() {
		String className = this.getClass().getName();
		Integer number = (Integer) numbers.get(className);
		if (number == null) {
			numbers.put(className, new Integer(1));
			return 1;
		}
		return number.intValue();
	}
}
