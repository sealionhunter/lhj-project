package lhj.java.util.system;

import lhj.java.util.HexTool;

public class Register {
	private byte[] data = new byte[2];

	public static final Register AX = new Register();
	public static final Register BX = new Register();
	public static final Register CX = new Register();
	public static final Register DX = new Register();
	public static final Register SP = new Register();
	public static final Register BP = new Register();
	public static final Register SI = new Register();
	public static final Register DI = new Register();
	public static final Register DS = new Register();
	public static final Register ES = new Register();
	public static final Register SS = new Register();
	public static final Register CS = new Register();
	public static final Register IP = new Register();

	private Register() {
	}

	protected static void setHigh(Register register, byte highValue) {
		register.data[0] = highValue;
	}

	protected static void setLow(Register register, byte lowValue) {
		register.data[1] = lowValue;
	}

	protected static byte getHigh(Register register) {
		return register.data[0];
	}

	protected static byte getLow(Register register) {
		return register.data[1];
	}

	protected static byte[] getValue(Register register) {
		byte[] newValue = new byte[2];
		newValue[0] = register.data[0];
		newValue[1] = register.data[1];
		return newValue;
	}

	protected static boolean setValue(Register register, byte[] data) {
		if (data == null) {
			return false;
		} else if (data.length == 1) {
			setLow(register, data[0]);
			setHigh(register, (byte) 0);
		} else if (data.length == 2) {
			register.data[0] = data[0];
			register.data[1] = data[1];
		} else {
			return false;
		}
		return true;
	}

	private String valueToHex() {
		return String.valueOf(HexTool.byteToHexChars(data)) + 'H';
	}

	public static String valueToHex(Register register) {
		return register.valueToHex();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Register.setHigh(AX, (byte) 127);
		Register.setLow(AX, (byte) 255);
		System.out.println(Register.valueToHex(AX));
	}

}
