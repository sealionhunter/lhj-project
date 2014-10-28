package lhj.learn.spritetool.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lhj.learn.spritetool.bean.PNGPositionBean;

/**
 * PNG layout calculator class
 */
public class PNGLayoutCalculator {

    /** PNG position list */
    private List<PNGPositionBean> pngPositionList = new ArrayList<PNGPositionBean>();

    /** ceiling width step */
    private int widthStep;

    /** PNG MIN area */
    private int minArea = 0;

    /** ceiling max width */
    private int maxWidth;

    /** ceiling first width */
    private int firstWidth;

    /** best Container */
    private Container bestContainer;

    /**
     * calculate position
     * 
     * @throws CloneNotSupportedException
     */
    public void calculate() throws CloneNotSupportedException {
        if (pngPositionList == null || pngPositionList.size() == 0) {
            return;
        }

        preCalculate();

        List<PNGPositionBean> tempPosition = new ArrayList<PNGPositionBean>();

        for (int width = firstWidth; width <= maxWidth; width += widthStep) {
            for (PNGPositionBean pngPosition : pngPositionList) {
                tempPosition.add(pngPosition.clone());
            }
            Container container = new Container();

            container.start(width);
            while (tempPosition.size() != 0) {
                boolean hasPutBox = false;
                for (PNGPositionBean pngPosition : tempPosition) {
                    if (container.pubBox(pngPosition)) {
                        tempPosition.remove(pngPosition);
                        hasPutBox = true;
                        break;
                    }
                }
                if (!hasPutBox) {
                    container.coverTopCeiling();
                }
            }
            container.end();

            if (bestContainer == null) {
                bestContainer = container;
            } else if (bestContainer.getArea() > container.getArea()) {
                bestContainer = container;
            }

            if (bestContainer.getArea() == this.minArea) {
                break;
            }
        }
    }

    private void preCalculate() {

        Collections.sort(pngPositionList);
        widthStep = pngPositionList.get(pngPositionList.size() - 1).getWidth();
        firstWidth = pngPositionList.get(0).getWidth();
        for (PNGPositionBean pngPosition : pngPositionList) {
            if (pngPosition.getWidth() < widthStep) {
                widthStep = pngPosition.getWidth();
            }
            if (pngPosition.getWidth() > firstWidth) {
                firstWidth = pngPosition.getWidth();
            }
            minArea += pngPosition.getArea();
            maxWidth += pngPosition.getWidth();
        }
    }

    /**
     * @return the bestContainer
     */
    public Container getBestContainer() {
        return bestContainer;
    }

    /**
     * @param paramPngPositionList the pngPositionList to set
     */
    public void setPngPositionList(final List<PNGPositionBean> paramPngPositionList) {
        pngPositionList = paramPngPositionList;
    }
}
