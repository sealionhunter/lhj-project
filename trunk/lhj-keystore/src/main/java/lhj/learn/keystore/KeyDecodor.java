package lhj.learn.keystore;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.InflaterOutputStream;

/**
 * zip -> secret -> zip
 * 
 * @author hjliang
 */
public class KeyDecodor {
    private static final BigInteger Y = new BigInteger(
                                              "175845801807739556741985638471907435407906519684886250467122046110651102903524232308422954530244828520123804538590747839594573587314842443685339455763388997963553445324632942903698972601771833157363636582608740853356188983398410271374644153217772756758217653606491933898218575162433144932807326786113341713881");
    private static final BigInteger Q = new BigInteger("864205495604807476120572616017955259175325408501");
    private static final BigInteger P = new BigInteger(
                                              "178011905478542266528237562450159990145232156369120674273274450314442865788737020770612695252123463079567156784778466449970650770920727857050009668388144034129745221171818506047231150039301079959358067395348717066319802262019714966524135060945913707594956514672855690606794135837542707371727429551343320695239");
    private static final BigInteger G = new BigInteger(
                                              "174068207532402095185811980123523436538604490794561350978495831040599953488455823147851597408940950725307797094915759492368300574252438761037084473467180148876118103083043754985190983472601550494691329488083395492313850000361646482644608492304078721818959999056496097769368017749273708962006689187956744210730");

    /**
     * @param args
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public static void main(String[] args) throws Exception {
    }

    public static void decodeFile(String src, String dest) throws Exception {
        InputStream in = new BufferedInputStream(new FileInputStream(src));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        upzipp(in, baos);
        in.close();
        baos.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        OutputStream out = new BufferedOutputStream(new FileOutputStream(dest));
        InflaterOutputStream dos = new InflaterOutputStream(out, new Inflater());
        decode(bais, dos);
        dos.close();
        out.close();
        bais.close();
    }

    private static void upzipp(InputStream in, OutputStream out) throws Exception {

        InflaterInputStream dos = new InflaterInputStream(in, new Inflater());
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = dos.read(buf, 0, 1024)) != -1) {
            out.write(buf, 0, len);
        }
        out.flush();
        dos.close();
    }

    private static PublicKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        DSAPublicKeySpec pks = new DSAPublicKeySpec(Y, P, Q, G);
        PublicKey pk = keyFactory.generatePublic(pks);
        return pk;
    }

    private static void decode(InputStream in, OutputStream out) throws Exception {
        DataInputStream dis = new DataInputStream(in);
        Signature signature = Signature.getInstance("SHA1withDSA");
        signature.initVerify(getPrivateKey());
        int len = -1;
        while (dis.available() > 0) {
            len = dis.readInt();
            byte[] buf = new byte[len];
            dis.read(buf);
            signature.update(buf);
            int signLen = dis.readInt();
            System.out.println(signLen);
            byte[] sign = new byte[signLen];
            dis.read(sign);
            signature.verify(sign);
            out.write(buf);
            out.flush();
        }
    }

}
