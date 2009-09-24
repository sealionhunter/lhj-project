package lhj.web.learn.springweb;

import java.io.File;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        File file = new File("e:\\temp\\test.txt"); 
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }

    }

}
