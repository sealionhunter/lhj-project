/*
 * USI Co., Ltd. 2015. All rights reserved.
 */
package lhj.java.xcp.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import lhj.java.xcp.IUploader;
import lhj.java.xcp.UploadException;
import lhj.java.xcp.UploadStatus;
import lhj.java.xcp.UploaderInitParameters;
import lhj.java.xcp.Utils;

/**
 * @author hjliang
 *
 */
public class FTPUploader implements IUploader {
    private Map<Object, FTPUploaderTask> tasks = new HashMap<Object, FTPUploaderTask>();
    UploaderInitParameters initSettings;

    private BlockingQueue<FTPUploaderTask> uploaderQueue;

    /*
     * (non-Javadoc)
     * 
     * @see lhj.java.xcp.IUploader#init(lhj.java.xcp.UploaderInitParameters)
     */
    @Override
    public boolean init(UploaderInitParameters initParameters) throws UploadException {
        this.initSettings = initParameters;
        uploaderQueue = new LinkedBlockingDeque<FTPUploaderTask>();
        new UploadExecutor(uploaderQueue).start();
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see lhj.java.xcp.IUploader#startUpload(java.lang.String)
     */
    @Override
    public Object startUpload(String localFilePath) throws UploadException {
        File localFile = new File(localFilePath);
        String remoteFileName = Utils.getRandomFileName(localFile.getName());
        UploadFileInfo fileInfo = new UploadFileInfo(localFilePath, remoteFileName);
        Utils.copyFile(localFile, new File(initSettings.getTempPath(), remoteFileName));
        FTPUploaderTask task = new FTPUploaderTask(initSettings.getFtpParameters(), localFilePath, remoteFileName);
        tasks.put(fileInfo, task);
        uploaderQueue.offer(task);
        return fileInfo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see lhj.java.xcp.IUploader#stopUpload(java.lang.Object)
     */
    @Override
    public void stopUpload(Object object) throws UploadException {
        FTPUploaderTask task = tasks.get(object);
        // cancel the task
        task.cancel();
        // remove the task from queue
        uploaderQueue.remove(task);
    }

    /*
     * (non-Javadoc)
     * 
     * @see lhj.java.xcp.IUploader#getStatus(java.lang.Object)
     */
    @Override
    public UploadStatus getStatus(Object object) {
        FTPUploaderTask task = tasks.get(object);
        if (task != null) {
            return task.getStatus();
        }
        return null;
    }

//    /*
//     * (non-Javadoc)
//     * 
//     * @see lhj.java.xcp.IUploader#restartUpload()
//     */
//    @Override
//    public void restartUpload(Object object) {
//        FTPUploaderTask task = tasks.get(object);
//        UploadFileInfo fileInfo = (UploadFileInfo) object;
//        if (task == null) {
//            task = new FTPUploaderTask(initSettings.getFtpParameters(), fileInfo.localPath, fileInfo.backupFileName);
//            tasks.put(object, task);
//        }
//        uploaderQueue.offer(task);
//    }

    class UploadFileInfo {
        String localPath;
        String backupFileName;

        /**
         * 
         */
        public UploadFileInfo(String localPath, String backupFileName) {
            this.localPath = localPath;
            this.backupFileName = backupFileName;
        }
    }

    class UploadExecutor extends Thread {
        Queue<FTPUploaderTask> queue;

        UploadExecutor(Queue<FTPUploaderTask> queue) {
            this.queue = queue;
        }

        public void run() {
            while (true) {
                FTPUploaderTask task = queue.poll();
                if (task != null) {
                    if (initSettings.isMultipleUpload()) {
                        task.start();
                    } else {
                        task.run();
                    }
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
