package lhj.learn.spritetool.bean;

/**
 * The ceiling of container
 */
public class CeilingBean implements Comparable<CeilingBean> {

    /** bottom position x */
    private int x;

    /** bottom position y */
    private int y;

    /** bottom width */
    private int width;

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param paramX the x to set
     */
    public void setX(final int paramX) {
        x = paramX;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param paramY the y to set
     */
    public void setY(final int paramY) {
        y = paramY;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param paramWidth the width to set
     */
    public void setWidth(final int paramWidth) {
        width = paramWidth;
    }

    /**
     * compare to target
     */
    @Override
    public int compareTo(final CeilingBean target) {
        return getX() - target.getX();
    }

    /**
     * 
     */
    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    /**
    * 
    */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
