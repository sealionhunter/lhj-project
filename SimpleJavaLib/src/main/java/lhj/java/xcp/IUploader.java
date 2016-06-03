/*
 * USI Co., Ltd. 2015. All rights reserved.
 */
package lhj.java.xcp;

/**
 * @author hjliang
 *
 */
public interface IUploader {
    boolean init(UploaderInitParameters initParameters) throws UploadException;

    Object startUpload(String localFilePath) throws UploadException;

    void stopUpload(Object object) throws UploadException;

    UploadStatus getStatus(Object object);

//    void restartUpload(Object object);
}
