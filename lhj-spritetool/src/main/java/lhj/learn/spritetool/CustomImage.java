package lhj.learn.spritetool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import lhj.learn.spritetool.bean.SizeBean;
import lhj.learn.spritetool.utils.PNGFileUtilities;

public class CustomImage {

    private static final String TARGET_FILE = "./test/custom/menu.png";
	private static final String FROM_DIR = "./test/";

	public static void main(String[] args) throws Exception {
        File outputFile = new File(TARGET_FILE);
        outputFile.getParentFile().mkdirs();
        new CustomImage().connectFile(outputFile);

    }

    /**
     * 
     * @param pngPositionList
     * @param outputPNGSize
     * @param outputFileName
     * @throws Exception
     */
    public void connectFile(final File outputFile)
            throws Exception {
        
        String[][] images = {{"left-top.png", "right-top.png", "", "", ""},
                {"left-bottom.png", "right-bottom.png", "", "", ""},
                {"", "", "", "", "top.png"},
                {"", "", "", "", "bottom.png"},
                {"", "", "left.png", "right.png", ""}};
        

        final BufferedImage sprite = new BufferedImage(
                21, 21,
                BufferedImage.TYPE_4BYTE_ABGR);
        BufferedImage bufferedImage = null;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (images[i][j].length() == 0) {
                    continue;
                }
                bufferedImage = ImageIO.read(new File(FROM_DIR, images[i][j]));

                final int[] imgRGB = bufferedImage.getRGB(0, 0,
                        bufferedImage.getWidth(), bufferedImage.getHeight(), null,
                        0, bufferedImage.getWidth());

                sprite.setRGB(j*5,i*5, 
                        bufferedImage.getWidth(), bufferedImage.getHeight(),
                        imgRGB, 0, bufferedImage.getWidth());
                
            }
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
}
