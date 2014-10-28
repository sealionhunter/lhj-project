package lhj.learn.spritetool.bean;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * PNG position bean
 */
public class PNGPositionBean implements Comparable<PNGPositionBean>, Cloneable {

    /** PNG file path */
    private String pngFilePath;

    /** PNG file position x */
    private int x;

    /** PNG file position y */
    private int y;

    /** PNG file width */
    private int width = 0;

    /** PNG file height */
    private int height = 0;
    
    private BufferedImage img;

    public static PNGPositionBean newInstance(File imgFile) throws Exception {
        try {
            BufferedImage img = ImageIO.read(imgFile);
            System.out.println(img.getType());
            PNGPositionBean rtn = new PNGPositionBean();
            rtn.pngFilePath = imgFile.getAbsolutePath();
            rtn.width = img.getWidth();
            rtn.height = img.getHeight();
            rtn.setImg(img);
            return rtn;
        } catch (Exception ex) {
            System.out.println("File not exist:" + imgFile.getAbsolutePath());
            throw ex;
        }
    }

    /**
     * @return the pngFilePath
     */
    public String getPngFilePath() {
        return pngFilePath;
    }

    /**
     * @param paramPngFilePath
     *            the pngFilePath to set
     */
    public void setPngFilePath(final String paramPngFilePath) {
        this.pngFilePath = paramPngFilePath;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param paramX
     *            the x to set
     */
    public void setX(final int paramX) {
        this.x = paramX;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param paramY
     *            the y to set
     */
    public void setY(final int paramY) {
        this.y = paramY;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param paramWidth
     *            the width to set
     */
    public void setWidth(final int paramWidth) {
        this.width = paramWidth;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param paramHeight
     *            the height to set
     */
    public void setHeight(final int paramHeight) {
        this.height = paramHeight;
    }

    /**
     * @return the area
     */
    public int getArea() {
        return width * height;
    }

    /**
     * compareTo
     */
    @Override
    public int compareTo(final PNGPositionBean target) {
        if ((target.getArea() - getArea()) != 0) {
            return target.getArea() - getArea();
        }

        if ((target.getWidth() - getWidth()) != 0) {
            return target.getWidth() - getWidth();
        }

        return 0;
    }

    /**
     * 
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * 
     */
    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    /**
     * clone PNG position
     */
    @Override
    public PNGPositionBean clone() throws CloneNotSupportedException {
        PNGPositionBean pngPosition = null;
        pngPosition = (PNGPositionBean) super.clone();
        return pngPosition;
    }

    /**
     * @return the img
     */
    public BufferedImage getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(BufferedImage img) {
        this.img = img;
    }

}
