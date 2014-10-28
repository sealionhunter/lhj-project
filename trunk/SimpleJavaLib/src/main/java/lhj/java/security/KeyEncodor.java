package lhj.java.security;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

/**
 * zip -> secret -> zip
 * 
 * @author hjliang
 */
public class KeyEncodor {
    private static final BigInteger X = new BigInteger("768378061284635723515030294014136322184160170876");
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

    public static void encodeFile(String src, String dest) throws Exception {
        InputStream in = new BufferedInputStream(new FileInputStream(src));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        zipped(in, baos);
        in.close();
        baos.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        OutputStream out = new BufferedOutputStream(new FileOutputStream(dest));
        DeflaterOutputStream dos = new DeflaterOutputStream(out, new Deflater());
        encode(bais, dos);
        dos.close();
        out.close();
        bais.close();
    }

    private static void zipped(InputStream in, OutputStream baos) throws Exception {
        DeflaterOutputStream dos = new DeflaterOutputStream(baos, new Deflater());
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = in.read(buf, 0, 1024)) != -1) {
            dos.write(buf, 0, len);
        }
        dos.close();
        dos.flush();
    }

    private static PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        DSAPrivateKeySpec pks = new DSAPrivateKeySpec(X, P, Q, G);
        PrivateKey pk = keyFactory.generatePrivate(pks);
        return pk;
    }

    private static void encode(InputStream in, OutputStream out) throws Exception {
        Signature signature = Signature.getInstance("SHA1withDSA");
        signature.initSign(getPrivateKey());

        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = in.read(buf, 0, 1024)) != -1) {

            signature.update(buf, 0, len);
            byte[] sign = signature.sign();

            DataOutputStream dos = new DataOutputStream(out);
            dos.writeInt(len);
            dos.write(buf, 0, len);
            dos.writeInt(sign.length);
            dos.write(sign);
            dos.flush();
            System.out.println(len);
            System.out.println(sign.length);
        }
    }

}
