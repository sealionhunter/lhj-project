package lhj.learn.spritetool.bean;

/**
 * PNG height width bean
 */
public class SizeBean {

    /** the PNG image width */
    private int width;

    /**
     * @param paramWidth the width to set
     */
    public void setWidth(final int paramWidth) {
        this.width = paramWidth;
    }

    /**
     * @param paramHeight the height to set
     */
    public void setHeight(final int paramHeight) {
        this.height = paramHeight;
    }

    /** the PNG image height */
    private int height;

    /**
     * constructor method
     * 
     * @param paramWidth
     * @param paramHeight
     */
    public SizeBean(final int paramWidth, final int paramHeight) {

        this.width = paramWidth;
        this.height = paramHeight;
    }

    /**
     * constructor
     */
    public SizeBean() {

    }

    /**
     * @return the width
     */
    public int getWidth() {

        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {

        return height;
    }
}
