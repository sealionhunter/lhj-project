package lhj.learn.spritetool;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import lhj.learn.spritetool.bean.PNGPositionBean;
import lhj.learn.spritetool.utils.PNGFileUtilities;

public class CutMain {
	@SuppressWarnings("rawtypes")
	public static void main(final String[] args) throws Exception {
		Pattern p = Pattern
				.compile(".*background-position: (0|-[0-9]*)(px)* (0|-[0-9]*)(px)*;  /\\*\\*### (.*) \\*/");
		BufferedReader in = new BufferedReader(new FileReader("./test.css"));
		List<PNGPositionBean> l = new ArrayList<PNGPositionBean>();
		List<PNGPositionBean> l_m = new ArrayList<PNGPositionBean>();

		String line = null;
		while ((line = in.readLine()) != null) {
			Matcher m = p.matcher(line);
			if (m.matches()) {
				System.out.println(line);
				boolean isMiddle = line.contains("_bg_");
				boolean isSmall = line.contains("_right_r_");
				PNGPositionBean pos = new PNGPositionBean();
				pos.setX(-(Integer.parseInt(m.group(1))));
				pos.setY(-(Integer.parseInt(m.group(3))));
				pos.setPngFilePath(m.group(5).replaceAll("_png", ".png"));
				pos.setHeight(45);
				pos.setWidth(isMiddle ? 1 : (isSmall ? 4 : 2));
				if (isMiddle) {
					l_m.add(pos);
				} else {
					l.add(pos);
				}
			}
		}
		in.close();
		File input = new File("./old");
		File output = new File("./cut");
		if (!output.exists()) {
			output.mkdirs();
		}
		String[] files = { "test1.png", "test2.png" };
		List[] posss = { l, l_m };
		for (int i = 0; i < files.length; i++) {
			BufferedImage spriteImg = ImageIO.read(new File(input, files[i]));
			for (int j = 0; j < posss[i].size(); j++) {
				PNGPositionBean pos = (PNGPositionBean) posss[i].get(j);
				try {
					BufferedImage img = new BufferedImage(pos.getWidth(),
							pos.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
					final int[] imgRGB = spriteImg.getRGB(pos.getX(),
							pos.getY(), pos.getWidth(), pos.getHeight(), null,
							0, pos.getWidth());
					img.setRGB(0, 0, pos.getWidth(), pos.getHeight(), imgRGB,
							0, pos.getWidth());

					PNGFileUtilities.writeImageLocal(
							new File(output, pos.getPngFilePath()), img);
				} catch (Exception ex) {
					System.out.println(pos.getPngFilePath());
				}
			}
		}
	}

}
