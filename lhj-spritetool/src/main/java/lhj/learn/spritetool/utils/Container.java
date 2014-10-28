package lhj.learn.spritetool.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lhj.learn.spritetool.bean.CeilingBean;
import lhj.learn.spritetool.bean.PNGPositionBean;

/**
 * the container to put box
 */
public class Container {

    /** connect PNG width */
    private int width;

    /** connect PNG height */
    private int height;

    /** connect PNG area */
    private int area;

    /** connect PNG position list */
    private List<PNGPositionBean> pngPositionList = new ArrayList<PNGPositionBean>();

    /** ceiling list */
    private List<CeilingBean> ceilingList = new ArrayList<CeilingBean>();

    /**
     * @return the pngPositionList
     */
    public List<PNGPositionBean> getPngPositionList() {
        return pngPositionList;
    }

    /**
     * Clear container
     * 
     * @param paramWidth
     */
    public void start(final int paramWidth) {

        CeilingBean firstCeiling = new CeilingBean();
        firstCeiling.setX(0);
        firstCeiling.setY(0);
        firstCeiling.setWidth(paramWidth);
        ceilingList.clear();
        ceilingList.add(firstCeiling);
        width = paramWidth;
        height = Integer.MAX_VALUE;
        area = Integer.MAX_VALUE;
        pngPositionList.clear();
    }

    /**
     * Put box to container
     * 
     * @param box
     * @return boolean
     * @throws CloneNotSupportedException
     */
    public boolean pubBox(final PNGPositionBean box) throws CloneNotSupportedException {
        if (box == null) {
            return false;
        }

        CeilingBean topCeiling = findTopCeiling();
        int diff = box.getWidth() - topCeiling.getWidth();
        if (diff > 0) {
            return false;
        }

        // add box to list
        PNGPositionBean cloneBox = box.clone();
        cloneBox.setX(topCeiling.getX());
        cloneBox.setY(topCeiling.getY());
        pngPositionList.add(cloneBox);

        if (diff < 0) {
            // add new ceiling
            CeilingBean newCeiling = new CeilingBean();
            newCeiling.setX(cloneBox.getWidth() + topCeiling.getX());
            newCeiling.setY(topCeiling.getY());
            newCeiling.setWidth(topCeiling.getWidth() - cloneBox.getWidth());
            ceilingList.add(newCeiling);
        }

        // change old ceiling
        topCeiling.setY(topCeiling.getY() + cloneBox.getHeight());
        topCeiling.setWidth(cloneBox.getWidth());

        clearCeiling();
        return true;
    }

    /**
     * cover top ceiling
     */
    public void coverTopCeiling() {

        Collections.sort(ceilingList);
        CeilingBean topCeiling = findTopCeiling();
        CeilingBean previousCeiling = null;
        CeilingBean nextCeiling = null;
        for (int i = 0; i < ceilingList.size(); i++) {
            CeilingBean tempCeiling = ceilingList.get(i);
            if (tempCeiling.getX() == topCeiling.getX()) {
                if (i > 0) {
                    previousCeiling = ceilingList.get(i - 1);
                }
                if (i < ceilingList.size() - 1) {
                    nextCeiling = ceilingList.get(i + 1);
                }
            }
        }
        if (previousCeiling == null && nextCeiling == null) {
            return;
        }

        if (previousCeiling != null && nextCeiling != null) {
            if (previousCeiling.getY() < nextCeiling.getY()) {
                previousCeiling.setWidth(previousCeiling.getWidth() + topCeiling.getWidth());
                ceilingList.remove(topCeiling);
            } else if (previousCeiling.getY() > nextCeiling.getY()) {
                nextCeiling.setX(topCeiling.getX());
                nextCeiling.setWidth(nextCeiling.getWidth() + topCeiling.getWidth());
                ceilingList.remove(topCeiling);
            } else {
                previousCeiling.setWidth(previousCeiling.getWidth() + topCeiling.getWidth() + nextCeiling.getWidth());
                ceilingList.remove(nextCeiling);
                ceilingList.remove(topCeiling);
            }
        } else if (previousCeiling != null) {
            previousCeiling.setWidth(previousCeiling.getWidth() + topCeiling.getWidth());
            ceilingList.remove(topCeiling);
        } else if (nextCeiling != null) {
            nextCeiling.setX(topCeiling.getX());
            nextCeiling.setWidth(nextCeiling.getWidth() + topCeiling.getWidth());
            ceilingList.remove(topCeiling);
        }
    }

    /**
     * end
     */
    public void end() {

        CeilingBean maxHeightCeiling = ceilingList.get(0);
        for (CeilingBean ceiling : ceilingList) {
            if (maxHeightCeiling.getY() < ceiling.getY()) {
                maxHeightCeiling = ceiling;
            }
        }
        height = maxHeightCeiling.getY();
        area = width * height;
    }

    private CeilingBean findTopCeiling() {

        CeilingBean topCeiling = null;
        for (CeilingBean temp : ceilingList) {
            if (topCeiling == null) {
                topCeiling = temp;
                continue;
            }
            if (topCeiling.getY() > temp.getY()) {
                topCeiling = temp;
            }
        }
        return topCeiling;
    }

    private void clearCeiling() {

        Collections.sort(ceilingList);
        CeilingBean oneCeiling = ceilingList.get(0);
        List<CeilingBean> removeCeiling = new ArrayList<CeilingBean>();
        int size = ceilingList.size();
        for (int i = 1; i < size; i++) {
            CeilingBean temp = ceilingList.get(i);
            if (oneCeiling.getY() == temp.getY()) {
                oneCeiling.setWidth(oneCeiling.getWidth() + temp.getWidth());
                removeCeiling.add(temp);
            } else {
                oneCeiling = temp;
            }
        }
        ceilingList.removeAll(removeCeiling);
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

    /**
     * @return the area
     */
    public int getArea() {
        return area;
    }
}
