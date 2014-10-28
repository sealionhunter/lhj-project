package lhj.java.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Primitives {
	private final static Map<Class<?>, Class<?>> priToWrap;
	private final static Map<Class<?>, Class<?>> wrapToPri;

	static {
		Map<Class<?>, Class<?>> tmp1 = new HashMap<Class<?>, Class<?>>();
		Map<Class<?>, Class<?>> tmp2 = new HashMap<Class<?>, Class<?>>();

		tmp1.put(int.class, Integer.class);
		tmp2.put(Integer.class, int.class);

		tmp1.put(short.class, Short.class);
		tmp2.put(Short.class, short.class);

		tmp1.put(float.class, Float.class);
		tmp2.put(Float.class, float.class);

		tmp1.put(long.class, Long.class);
		tmp2.put(Long.class, long.class);

		tmp1.put(double.class, Double.class);
		tmp2.put(Double.class, double.class);

		tmp1.put(byte.class, Byte.class);
		tmp2.put(Byte.class, byte.class);

		tmp1.put(char.class, Character.class);
		tmp2.put(Character.class, char.class);

		tmp1.put(boolean.class, Boolean.class);
		tmp2.put(Boolean.class, boolean.class);

		tmp1.put(void.class, Void.class);
		tmp2.put(Void.class, void.class);

		priToWrap = Collections.unmodifiableMap(tmp1);
		wrapToPri = Collections.unmodifiableMap(tmp2);
	}

	public static Set<Class<?>> getAllPrimitives() {
		return priToWrap.keySet();
	}

	public static Set<Class<?>> getAllWrapper() {
		return wrapToPri.keySet();
	}

	public static Class<?> wrap(Class<?> pri) {
		return priToWrap.containsKey(pri) ? priToWrap.get(pri) : pri;
	}

	public static Class<?> unwrap(Class<?> wrapper) {
		return wrapToPri.containsKey(wrapper) ? wrapToPri.get(wrapper) : wrapper;
	}
}
