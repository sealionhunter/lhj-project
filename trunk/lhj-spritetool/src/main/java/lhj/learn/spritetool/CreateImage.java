package lhj.learn.spritetool;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import lhj.learn.spritetool.utils.PNGFileUtilities;

public class CreateImage {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		File input = new File("./input");
		File output = new File("./output");
		if (!output.exists()) {
			output.mkdirs();
		}
		String[] srcs = { "I01_1.png", "I01_2.png", "I01_3.png", "I02_1.png",
				"I02_2.png", "I02_3.png", "I03_1.png", "I03_2.png",
				"I03_3.png", "I04_1.png", "I04_2.png", "I04_3.png" };

		for (String imgs : srcs) {
			// rename
			// new File(input, srcPre + src).renameTo(new File(output, outPre +
			// src));

			// copy
			// BufferedInputStream in = new BufferedInputStream(new
			// FileInputStream(new File(input, srcPre + src)));
			// BufferedOutputStream out = new BufferedOutputStream(new
			// FileOutputStream(new File(output, outPre + src)));
			// byte[] buffer = new byte[8196];
			// int len = 0;
			// while ((len = in.read(buffer, 0, 8196)) != -1) {
			// out.write(buffer, 0, len);
			// }
			// in.close();
			// out.close();

			BufferedImage bufferedImage = ImageIO.read(new File(input, imgs));
			BufferedImage sprite = new BufferedImage(bufferedImage.getWidth(),
					28, BufferedImage.TYPE_4BYTE_ABGR);

			int[] imgRGB = bufferedImage.getRGB(0, 13,
					bufferedImage.getWidth(), 28, null, 0,
					bufferedImage.getWidth());
			sprite.setRGB(0, 0, bufferedImage.getWidth(), 28, imgRGB, 0,
					bufferedImage.getWidth());
			// sprite.setRGB(5, 0, 1,
			// bufferedImage.getHeight(), imgRGB1, 0,
			// 1);
			// sprite1.setRGB(0, 0, 1,
			// bufferedImage1.getHeight(), imgRGB1, 5,
			// 1);
			// sprite1.setRGB(1, 0, 5,
			// bufferedImage1.getHeight(), imgRGB, 0,
			// 5);

			//
			// int[] imgRGB = bufferedImage.getRGB(0, 0, 5,
			// bufferedImage.getHeight(), null, 0, 5);
			// sprite1.setRGB(0, 0, 5,
			// bufferedImage.getHeight(), imgRGB, 0, 5);
			// imgRGB = bufferedImage1.getRGB(0, 0, 1,
			// bufferedImage1.getHeight(), null, 0, 1);
			// sprite1.setRGB(5, 0, 1,
			// bufferedImage1.getHeight(), imgRGB, 0,
			// 1);
			//
			// imgRGB = bufferedImage.getRGB(5, 0, 1, bufferedImage.getHeight(),
			// null, 0, 1);
			// sprite1.setRGB(0, 0, 1,
			// bufferedImage.getHeight(), imgRGB, 0,
			// 1);
			// imgRGB = bufferedImage1.getRGB(1, 0, 5,
			// bufferedImage.getHeight(), null, 0, 5);
			// sprite1.setRGB(1, 0, 5,
			// bufferedImage.getHeight(), imgRGB, 0,
			// 5);

			//
			// int[] imgRGB = bufferedImage.getRGB(0, 0,
			// bufferedImage.getWidth(),
			// 5, null, 0,
			// bufferedImage.getWidth());
			// sprite.setRGB(0, 0, bufferedImage.getWidth(),
			// 5, imgRGB, 0,
			// bufferedImage.getWidth());
			//
			// imgRGB = bufferedImage.getRGB(0, 5, bufferedImage.getWidth(),
			// 1, null, 0,
			// bufferedImage.getWidth());
			// for (int i = 0; i < 13; i++) {
			// sprite.setRGB(0, 5+i, bufferedImage.getWidth(),
			// 1, imgRGB, 0,
			// bufferedImage.getWidth());
			// }
			//
			// imgRGB = bufferedImage.getRGB(0, 5, bufferedImage.getWidth(),
			// 27, null, 0,
			// bufferedImage.getWidth());
			// sprite.setRGB(0, 17, bufferedImage.getWidth(),
			// 27, imgRGB, 0,
			// bufferedImage.getWidth());

			PNGFileUtilities.writeImageLocal(new File(output, imgs), sprite);
			// writeImageLocal(new File(output, outPre + src1), sprite1);
		}
	}
}
