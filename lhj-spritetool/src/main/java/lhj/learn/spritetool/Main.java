package lhj.learn.spritetool;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lhj.learn.spritetool.bean.Config;
import lhj.learn.spritetool.bean.PNGPositionBean;
import lhj.learn.spritetool.bean.SizeBean;
import lhj.learn.spritetool.utils.CSSFileUtilities;
import lhj.learn.spritetool.utils.ConfigReader;
import lhj.learn.spritetool.utils.PNGFileUtilities;
import lhj.learn.spritetool.utils.PNGLayoutCalculator2;

public class Main {
	public static void main(final String[] args) throws Exception {
		if (args.length != 3) {
			printHelp();
			return;
		}

		List<Config> confs = new ConfigReader().read(args[0]);
		File imgDir = new File(args[1]);
		File output = new File(args[2]);
		if (!output.exists()) {
			output.mkdirs();
		}
		for (Config conf : confs) {

			List<PNGPositionBean> imgList = new ArrayList<PNGPositionBean>();

			PNGPositionBean[][] rows = getRows(conf, imgDir);

			PNGLayoutCalculator2 tool = new PNGLayoutCalculator2(conf);
			tool.calcPos(rows);

			for (PNGPositionBean[] row : rows) {
				if (row != null && row.length > 0)
					imgList.addAll(Arrays.asList(row));
			}
			File newImgFile = new File(output, conf.getOutputSpriteImage());
			new PNGFileUtilities().connectFile(imgList, new SizeBean(
					tool.width, tool.height), newImgFile);
			new CSSFileUtilities().createCssFile(rows, output,
					conf.getOutputSpriteImage());
		}

		System.out.println("done.");
	}

	private static PNGPositionBean[][] getRows(Config conf, File imgDir)
			throws Exception {
		PNGPositionBean[][] rows = new PNGPositionBean[conf.getImagePosition().length][];
		for (int i = 0; i < conf.getImagePosition().length; i++) {
			if (conf.getImagePosition()[i] == null
					|| conf.getImagePosition()[i].length == 0) {

			} else {
				rows[i] = new PNGPositionBean[conf.getImagePosition()[i].length];
				for (int j = 0; j < conf.getImagePosition()[i].length; j++) {
					if (conf.getImagePosition()[i][j] != null
							&& conf.getImagePosition()[i][j].length() > 0) {
						PNGPositionBean newOne = PNGPositionBean
								.newInstance(new File(imgDir, (String) conf
										.getImagePosition()[i][j]));
						if (newOne != null) {
							rows[i][j] = newOne;
						}
					}
				}
			}
		}
		return rows;
	}

	public static void printHelp() {
		String help = "usage:\n"
				+ "java lhj.learn.spritetool.Main configfile images-folder sprite-images-folder\n"
				+ "\n"
				+ "    configfile:            sprite images config,see images-orig\\config.txt\n"
				+ "    images-folder:         original images folder\n"
				+ "    sprite-images-folder:  sprite-image and css output folder \n"
				+ "sample: \n"
				+ "    java lhj.learn.spritetool.Main ./images-orig/config.txt ./images-orig ./images-sprite\n";
		System.out.println(help);
	}

}
