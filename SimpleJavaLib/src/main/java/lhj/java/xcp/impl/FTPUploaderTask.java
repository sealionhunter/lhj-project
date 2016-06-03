/*
 * USI Co., Ltd. 2015. All rights reserved.
 */
package lhj.java.xcp.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import lhj.java.xcp.FtpParameters;
import lhj.java.xcp.LoginFailedException;
import lhj.java.xcp.UploadException;
import lhj.java.xcp.UploadStatus;
import lhj.java.xcp.UploadStatus.Status;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hjliang
 *
 */
public class FTPUploaderTask extends Thread {
    Logger logger = LoggerFactory.getLogger(FTPUploaderTask.class);
    private FtpParameters settings;
    private String filePath;
    private int retryCount = 0;
    private int maxRetryCount = 5;

    private UploadStatus status;
    private String remote;

    /**
     * 
     */
    public FTPUploaderTask(FtpParameters settings, String filePath, String remote) {
        this.settings = settings;
        this.filePath = filePath;
        this.remote = remote;
        status = new UploadStatus();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        logger.info("Start upload");
        FTPClient client = new FTPClient();
        InputStream in = null;
        boolean state = true;
        Exception error = null;
        try {
            File localFile = new File(this.filePath);
            if (!localFile.exists()) {
                logger.warn("Local file not exists");
                status.setStatus(Status.COMPLETE_WITH_WARN);
                status.setError(new Exception("Local File does not Exists"));
                return;
            } else if (!localFile.isFile()) {
                status.setStatus(Status.COMPLETE_WITH_WARN);
                status.setError(new Exception("Local File is not a file"));
                return;
            }
            status.setStatus(Status.UPLOADING);
            
            while (retryCount < maxRetryCount && !Thread.interrupted() && status.getStatus() != Status.CANCEL) {
                error = null;
                try {
                    if (!client.isConnected()) {
                        openSession(client);
                    }
                    in = new FileInputStream(localFile);
                    state = client.storeFile(remote, in);
                    if (!state) {
                        throw new UploadException(client.getReplyString());
                    }
                    break;
                } catch (Exception e) {
                    error = e;
                    logger.debug("Error occurs!", e);
                    retryCount++;
                    if (in != null) {
                        try {
                            in.close();
                            in = null;
                        } catch (Exception ex) {
                            logger.warn("error close input stream", ex);
                        }
                    }
                }
            }
        } finally {
            if (client != null) {
                try {
                    if (client.isConnected()) {
                        client.disconnect();
                    }
                } catch (IOException e) {
                    logger.error("Error occurs", e);
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("Error occurs", e);
                }
            }
        }
        if (error != null) {
            logger.error("Error occurs!", error);
            status.setStatus(Status.COMPLETE_WITH_ERROR);
            status.setError(error);
        } else if (status.getStatus() != Status.CANCEL) {
            status.setStatus(Status.COMPLETE);
        }
        logger.info("End upload");
    }

    /**
     * @param client
     * @throws SocketException
     * @throws IOException
     * @throws UploadException 
     */
    private void openSession(FTPClient client) throws SocketException, IOException, UploadException {
        client.setConnectTimeout(settings.getTimeout());

        logger.debug("connect to {}:{}", settings.getServer(), settings.getPort());
        client.connect(settings.getServer(), settings.getPort());

        logger.debug("Login use {}", settings.getUser());
        boolean state = client.login(settings.getUser(), settings.getPassword());
        if (!state) {
            logger.debug("Login failed! User: {}", settings.getUser());
            throw new LoginFailedException("Login failed! ");
        }
        client.setBufferSize(1024 * 1024);
        client.setFileType(settings.getFileType());
        if (settings.getLocation() != null) {
            state = client.changeWorkingDirectory(settings.getLocation());
            if (!state) {
                logger.debug("Remote directory not exists: {}", settings.getLocation());
                throw new UploadException("Remote directory does not exists");
            }
        }
    }

    public void cancel() {
         status.setStatus(Status.CANCEL);
    }

    public UploadStatus getStatus() {
        return status;
    }

}
