package lhj.java.security;

/**
 * @author Sealion Hunter
 * @version Create On 2006/03/18
 */
public class LhjSecurity {
    private static final String SEED_STR = "lianghaijin19820227";
    private static final byte[] SEED_BYTES = SEED_STR.getBytes();

    public static int[] save(byte[] msg) {
        if (msg == null) {
            return null;
        }
        int[] retInt = new int[msg.length];
        for (int i = 0; i < msg.length; i++) {
            for (int j = 0; j < SEED_BYTES.length && i < msg.length; j++) {
                retInt[i] = msg[i++] + SEED_BYTES[j];
            }
            i--;
        }
        return retInt;
    }

    public static byte[] unsave(int[] msg) {
        if (msg == null) {
            return null;
        }
        byte[] retByte = new byte[msg.length];
        for (int i = 0; i < msg.length; i++) {
            for (int j = 0; j < SEED_BYTES.length && i < msg.length; j++) {
                retByte[i] = (byte) (msg[i++] - SEED_BYTES[j]);
            }
            i--;
        }
        return retByte;
    }

    public static void main(String[] args) throws Exception {
        String test = "This is my test";
        System.out.println(test);
        System.out.println(new String(unsave(save(test.getBytes()))));
        test = "试一试中文怎么样";
        System.out.println(test);
        System.out.println(new String(unsave(save(test.getBytes("gb2312"))), "gb2312"));
        test = "日本語もテストしてください";
        String encoded = KeyEncodor.encodeFile(test);
        String decoded = KeyDecodor.decodeFile(encoded);
        System.out.println(encoded.length());
        System.out.println(encoded);
        System.out.println(decoded);
        System.out.println(test);
        System.out.println(new String(unsave(save(test.getBytes("MS932"))), "MS932"));
    }
}
