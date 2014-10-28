package lhj.learn.spritetool.bean;

public class Config {
    private int margin_left = 0;
    private int margin_right = 0;
    private int margin_middle = 1;
    private int margin_top = 0;
    private int margin_bottom = 0;
    private String outputSpriteImage;
    private String[][] imagePosition;

    /**
     * @return the margin_left
     */
    public int getMargin_left() {
        return margin_left;
    }

    /**
     * @param margin_left
     *            the margin_left to set
     */
    public void setMargin_left(int margin_left) {
        this.margin_left = margin_left;
    }

    /**
     * @return the margin_right
     */
    public int getMargin_right() {
        return margin_right;
    }

    /**
     * @param margin_right
     *            the margin_right to set
     */
    public void setMargin_right(int margin_right) {
        this.margin_right = margin_right;
    }

    /**
     * @return the margin_middle
     */
    public int getMargin_middle() {
        return margin_middle;
    }

    /**
     * @param margin_middle the margin_middle to set
     */
    public void setMargin_middle(int margin_middle) {
        this.margin_middle = margin_middle;
    }

    /**
     * @return the margin_top
     */
    public int getMargin_top() {
        return margin_top;
    }

    /**
     * @param margin_top
     *            the margin_top to set
     */
    public void setMargin_top(int margin_top) {
        this.margin_top = margin_top;
    }

    /**
     * @return the margin_bottom
     */
    public int getMargin_bottom() {
        return margin_bottom;
    }

    /**
     * @param margin_bottom
     *            the margin_bottom to set
     */
    public void setMargin_bottom(int margin_bottom) {
        this.margin_bottom = margin_bottom;
    }

    /**
     * @return the outputSpriteImage
     */
    public String getOutputSpriteImage() {
        return outputSpriteImage;
    }

    /**
     * @param outputSpriteImage
     *            the outputSpriteImage to set
     */
    public void setOutputSpriteImage(String outputSpriteImage) {
        this.outputSpriteImage = outputSpriteImage;
    }

    /**
     * @return the imagePosition
     */
    public String[][] getImagePosition() {
        return imagePosition;
    }

    /**
     * @param imagePosition
     *            the imagePosition to set
     */
    public void setImagePosition(String[][] imagePosition) {
        this.imagePosition = imagePosition;
    }
}
