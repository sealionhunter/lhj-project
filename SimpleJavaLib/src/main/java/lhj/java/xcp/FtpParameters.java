/*
 * USI Co., Ltd. 2015. All rights reserved.
 */
package lhj.java.xcp;

import java.io.Serializable;

import org.apache.commons.net.ftp.FTP;

/**
 * @author hjliang
 *
 */
public class FtpParameters implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5117930517271714536L;
    private String server;
    private int port = FTP.DEFAULT_PORT;
    private int timeout = 0;
    private int timeoutSeconds = 0;
    private String user;
    private String password;
    private String location;
    private int fileType = FTP.BINARY_FILE_TYPE;

    /**
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * @param server
     *            the server to set
     */
    public void setServer(String host) {
        this.server = host;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * @param timeout the timeout to set
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * @return the timeoutSeconds
     */
    public int getTimeoutSeconds() {
        return timeoutSeconds;
    }

    /**
     * @param timeoutSeconds the timeoutSeconds to set
     */
    public void setTimeoutSeconds(int timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(String username) {
        this.user = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     *            the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the fileType
     */
    public int getFileType() {
        return fileType;
    }

    /**
     * @param fileType
     *            the fileType to set
     */
    public void setFileType(int fileType) {
        this.fileType = fileType;
    }
}
