package lhj.learn.spritetool.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import lhj.learn.spritetool.bean.Config;

public class ConfigReader {

    public List<Config> read(String config) throws Exception {
        List<Config> confs = new ArrayList<Config>();
        BufferedReader in = new BufferedReader(new FileReader(config));
        String line;
        Config conf = null;
        List<String[]> maps = new ArrayList<String[]>();
        while ((line = in.readLine()) != null) {
            if (line.startsWith("##")) {
                if (conf != null) {
                    String[][] mapsss = new String[maps.size()][];
                    for (int i = 0; i < maps.size(); i++) {
                        mapsss[i] = maps.get(i);
                    }
                    conf.setImagePosition(mapsss);
                    maps = new ArrayList<String[]>();
                }
                conf = new Config();
                confs.add(conf);
                conf.setOutputSpriteImage(line.substring(2));
            } else if (line.startsWith("#left:")) {
                conf.setMargin_left(Integer.parseInt(line.substring(6)));
            } else if (line.startsWith("#right:")) {
                conf.setMargin_right(Integer.parseInt(line.substring(7)));
            } else if (line.startsWith("#middle:")) {
                conf.setMargin_middle(Integer.parseInt(line.substring(8)));
            } else if (line.startsWith("#top:")) {
                conf.setMargin_left(Integer.parseInt(line.substring(5)));
            } else if (line.startsWith("#bottom:")) {
                conf.setMargin_left(Integer.parseInt(line.substring(8)));
            } else if (line.matches("#\\d*#")) {
                int newline = Integer.parseInt(line.substring(1, line.length() - 1));
                for (int i = 0; i < newline; i++) {
                    maps.add(new String[0]);
                }
            } else if (line.length() > 0) {
                String[] fs = line.split("[ |\t]+");
                maps.add(fs);
            }
        }
        if (conf != null && conf.getImagePosition() == null && maps.size() > 0) {
            String[][] mapsss = new String[maps.size()][];
            for (int i = 0; i < maps.size(); i++) {
                mapsss[i] = maps.get(i);
            }
            conf.setImagePosition(mapsss);
        }
        in.close();
        return confs;
    }
}
