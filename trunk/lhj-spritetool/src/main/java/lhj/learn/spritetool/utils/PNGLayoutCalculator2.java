package lhj.learn.spritetool.utils;

import lhj.learn.spritetool.bean.Config;
import lhj.learn.spritetool.bean.PNGPositionBean;

public class PNGLayoutCalculator2 {

    public int fixedX[];
    public int fixedY[];
    public int width = 0;
    public int height = 0;
    private Config conf;
    
    public PNGLayoutCalculator2 (Config conf) {
        this.conf = conf;
    }

    public void calcPos(PNGPositionBean[][] rows) {
        int rowLength = rows.length;
        int colLength = rows[0].length;
        fixedX = new int[colLength];
        width = fixedX[0] = conf.getMargin_left();
        fixedY = new int[rowLength];
        height = fixedY[0] = conf.getMargin_top();;
        for (int i = 0; i < rowLength; i++) {
            int maxHeight = 0;
            if (rows[i] == null || rows[i].length == 0) {
                maxHeight = 1;
            } else {
                for (int j = 0; j < colLength; j++) {
                    if (rows[i] == null || rows[i].length <= j || rows[i][j] == null) {
                        continue;
                    }
                    PNGPositionBean cell = rows[i][j];
                    int posHeight = cell.getHeight();
                    if (posHeight > maxHeight) {
                        maxHeight = posHeight;
                    }

                    if (i == 0) {
                    int maxWidth = 0;
                    for (int k = 0; k < rowLength; k++) {
                        if (rows[k] == null || rows[k].length <= j || rows[k][j] == null) {
                            continue;
                        }
                        PNGPositionBean cell1 = rows[k][j];
                        int posWidth = cell1.getWidth();
                        if (posWidth > maxWidth) {
                            maxWidth = posWidth;
                        }
                    }
                    if (j < colLength - 1) {
                        fixedX[j + 1] = width + maxWidth + conf.getMargin_middle();
                    }
                    width += maxWidth + (j < colLength - 1 ? conf.getMargin_middle() : 0);
                    }
                }
            }
            if (i < rowLength - 1) {
                fixedY[i + 1] = height + maxHeight;
            }
            height += maxHeight;
        }

        height += conf.getMargin_bottom();
        width += conf.getMargin_right();
        for (int i = 0; i < rows.length; i++) {
            if (rows[i] != null && rows[i].length > 0)
                for (int j = 0; j < rows[i].length; j++) {
                    if (rows[i][j] != null) {
                        rows[i][j].setX(fixedX[j]);
                        rows[i][j].setY(fixedY[i]);
                    }
                }
        }
    }

}
