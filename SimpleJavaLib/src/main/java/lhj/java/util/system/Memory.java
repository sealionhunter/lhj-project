package lhj.java.util.system;

public class Memory {
	public static long KB = 1024;
	public static long MB = KB << 10;
	public static long MB_16 = MB << 4;
	public static long MB_32 = MB_16 << 1;
	public static long MB_64 = MB_32 << 1;
	public static long MB_128 = MB_64 << 1;
	private byte[] memLow = new byte[(int)MB_64];
	private boolean isReady;
	private Memory instance = new Memory();

	private Memory() {
		isReady = true;
	}

	public Memory getInstance() {
		return instance;
	}

	public boolean isReady() {
		return isReady;
	}

	/**
	 * Store data into memory.
	 * 
	 * @param src
	 *            data to store
	 * @param srcStartIndex
	 *            src start index
	 * @param memStartIndex
	 *            memory start index
	 * @param length
	 *            length
	 * @return true if store successfully, other false
	 */
	public boolean store(byte[] src, int srcStartIndex, int memStartIndex,
			int length) {
		if (srcStartIndex < 0 || memStartIndex < 0
				|| srcStartIndex + length > src.length
				|| memStartIndex + length > memLow.length) {
			return false;
		}
		while (!isReady()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return false;
			}
		}
		isReady = false;
		System.arraycopy(src, srcStartIndex, memLow, memStartIndex, length);
		isReady = true;
		return true;
	}

	/**
	 * @param dest
	 * @param destStartIndex
	 * @param memStartIndex
	 * @param length
	 * @return
	 */
	public boolean load(byte[] dest, int destStartIndex, int memStartIndex,
			int length) {
		if (destStartIndex < 0 || memStartIndex < 0
				|| destStartIndex + length > dest.length
				|| memStartIndex + length > memLow.length) {
			return false;
		}
		while (!isReady()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return false;
			}
		}
		isReady = false;
		System.arraycopy(memLow, memStartIndex, dest, destStartIndex, length);
		isReady = true;
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Memory();
		System.out.println(Memory.KB);
		System.out.println(Memory.MB);
		System.out.println(Memory.MB_16);
		System.out.println(Memory.MB_32);
		System.out.println(Memory.MB_64);
		System.out.println(Memory.MB_128);
	}

}
