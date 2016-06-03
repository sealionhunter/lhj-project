/*
 * USI Co., Ltd. 2015. All rights reserved.
 */
package lhj.java.xcp.test;

import java.io.File;

import lhj.java.xcp.FtpParameters;
import lhj.java.xcp.IUploader;
import lhj.java.xcp.UploaderInitParameters;
import lhj.java.xcp.impl.FTPUploader;

/**
 * @author hjliang
 *
 */
public class FTPUploaderTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        IUploader uploader = new FTPUploader();
        UploaderInitParameters initParameters = new UploaderInitParameters();
        initParameters.setMaxRetryCount(10);
        initParameters.setMultipleUpload(false);
        initParameters.setTempPath("./temp");
        FtpParameters settings = new FtpParameters();
        settings.setServer("localhost");
        settings.setUser("username");
        settings.setPassword("password");
        settings.setLocation("/location");
        initParameters.setFtpParameters(settings);
        uploader.init(initParameters);
        new File("./temp").mkdir();
        uploader.startUpload("test.txt");
        uploader.startUpload("pom.xml");
        
        Thread.sleep(120000);
    }

}
