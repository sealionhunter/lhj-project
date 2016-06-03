package lhj.learn.spritetool.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;

import lhj.learn.spritetool.bean.PNGPositionBean;
import lhj.learn.spritetool.bean.SizeBean;

import com.sun.imageio.plugins.png.PNGMetadata;

/**
 * PNG file utilities
 */
@SuppressWarnings("restriction")
public class PNGFileUtilities {

    private boolean isFirstPng = true;

    /**
     * @return the isFirstPng
     */
    public boolean isFirstPng() {
        return isFirstPng;
    }

    /**
     * @param paramIsFirstPng
     *            the isFirstPng to set
     */
    public void setFirstPng(final boolean paramIsFirstPng) {
        isFirstPng = paramIsFirstPng;
    }

    /**
     * 
     * @param pngPositionList
     * @param outputPNGSize
     * @param outputFileName
     * @throws Exception
     */
    public void connectFile(final List<PNGPositionBean> pngPositionList,
            final SizeBean outputPNGSize, final File outputFile)
            throws Exception {

        if (pngPositionList == null || pngPositionList.size() == 0) {
            throw new Exception("inputFileNameList is null or empty");
        }
        if (outputFile == null) {
            throw new Exception("outputFileName  is null or empty");
        }

        final BufferedImage sprite = new BufferedImage(
                outputPNGSize.getWidth(), outputPNGSize.getHeight(),
                BufferedImage.TYPE_4BYTE_ABGR);

        BufferedImage bufferedImage = null;
        // ImageIO.getImageWritersByFormatName("").
        for (PNGPositionBean pngPosition : pngPositionList) {
            if (pngPosition == null) {
                continue;
            }
            bufferedImage = pngPosition.getImg();

            final int[] imgRGB = bufferedImage.getRGB(0, 0,
                    bufferedImage.getWidth(), bufferedImage.getHeight(), null,
                    0, bufferedImage.getWidth());
            sprite.setRGB(pngPosition.getX(), pngPosition.getY(),
                    bufferedImage.getWidth(), bufferedImage.getHeight(),
                    imgRGB, 0, bufferedImage.getWidth());
        }
        PNGFileUtilities.writeImageLocal(outputFile, sprite);
    }

    /**
     * get PNG file pos
     * 
     * @param fileName
     * @return SizeBean
     * @throws IOException
     */
    public SizeBean getSize(final String fileName) throws IOException {

        BufferedImage bufferedImage = ImageIO.read(new File(fileName));
        if (bufferedImage != null) {
            return new SizeBean(bufferedImage.getWidth(),
                    bufferedImage.getHeight());
        }
        return new SizeBean();
    }

    public static void writeImageLocal(final File file, final BufferedImage image)
            throws Exception {

        if (file == null) {
            throw new Exception("fileName is null or empty");
        }
        if (image == null) {
            throw new Exception("image is null");
        }

        // TODO get bKGD info from parameter
        PNGMetadata png = new PNGMetadata();
        png.bKGD_present = false;
        png.bKGD_colorType = 2;
        png.bKGD_red = 0;
        png.bKGD_green = 0;
        png.bKGD_blue = 0;

        IIOImage iioImage = new IIOImage(image, null, png);

        ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();
        writer.setOutput(ImageIO.createImageOutputStream(file));
        writer.write(iioImage);
        writer.dispose();
    }
}
