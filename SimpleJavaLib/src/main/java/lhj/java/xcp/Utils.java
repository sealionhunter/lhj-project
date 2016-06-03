/*
 * USI Co., Ltd. 2015. All rights reserved.
 */
package lhj.java.xcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author hjliang
 *
 */
public final class Utils {
    private Utils() {
    }
    
    public static String getRandomFileName(String base) {
        UUID uuid = UUID.randomUUID();
        return uuid.toString() + base;
    }

    public static void copyFile(String from, String to) {
        copyFile(new File(from), new File(to));
    }

    public static void copyFile(File from, File to) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(from));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(to));) {
            int len = 0, buffLength = 1024;
            byte[] buffer = new byte[buffLength];
            while ((len = in.read()) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    
}
