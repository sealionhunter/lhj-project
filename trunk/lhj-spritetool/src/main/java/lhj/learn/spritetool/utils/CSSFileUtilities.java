package lhj.learn.spritetool.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lhj.learn.spritetool.bean.PNGPositionBean;

public class CSSFileUtilities {

    public CSSFileUtilities() {
    }

    public void modifyCssFile(List<PNGPositionBean> pngPositionList, String newImgFile, File cssFile) throws Exception {
        File newCssFile = new File(cssFile.getParentFile().getAbsolutePath() + File.separatorChar + "new_" + cssFile.getName());
        PrintWriter out = new PrintWriter(newCssFile);

        StringBuilder regStr = new StringBuilder();
        Map<String, PNGPositionBean> mapping = new HashMap<String, PNGPositionBean>();
        for (PNGPositionBean positionBean : pngPositionList) {
            String name = new File(positionBean.getPngFilePath()).getName();
            regStr.append(name);
            regStr.append("|");

            mapping.put(name, positionBean);
        }
        regStr.setLength(regStr.length() - 1);
        Pattern reg = Pattern.compile(regStr.toString());

        BufferedReader in = new BufferedReader(new FileReader(cssFile));
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            StringBuffer lineSB = new StringBuffer(line.length());
            Matcher m = reg.matcher(line);
            PNGPositionBean matchedPositionBean = null;
            if (m.find()) {
                String name = m.group();
                m.appendReplacement(lineSB, newImgFile);
                matchedPositionBean = mapping.get(name);
            }
            m.appendTail(lineSB);

            if (matchedPositionBean != null) {
                out.println("/* " + line + " */");
                out.println(lineSB.toString().trim());
                out.println("background-position: -" + matchedPositionBean.getX() + "px -" + matchedPositionBean.getY() + "px;");
                out.println("/* width: " + matchedPositionBean.getWidth() + "px; */");
                out.println("/* height: " + matchedPositionBean.getHeight() + "px; */");
            } else {
                out.println(line);
            }
        }
        in.close();
        out.flush();
        out.close();
    }

    public void createCssFile(List<PNGPositionBean> pngPositionList, File imgDir, String newImgFile) throws Exception {
        String baseName = newImgFile.split("\\.")[0];
        File newCssFile = new File(imgDir.getAbsolutePath(), baseName + ".css");
        PrintWriter out = new PrintWriter(newCssFile);

        for (PNGPositionBean positionBean : pngPositionList) {
            String tempName = new File(positionBean.getPngFilePath()).getName();
            tempName = tempName.replace(".", "_");
            out.println("." + tempName + "");
            out.println("{");
            out.println("\t" + "background-image: url('" + newImgFile + "');");
            out.println("\t" + "background-position: -" + positionBean.getX() + "px -" + positionBean.getY() + "px;");
            out.println("\t" + "/* width: " + positionBean.getWidth() + "px; */");
            out.println("\t" + "/* height: " + positionBean.getHeight() + "px; */");
            out.println("}");
        }
        
        out.flush();
        out.close();
    }
    public void createCssFile(PNGPositionBean[][] pngPositionList, File imgDir, String newImgFile) throws Exception {
        String baseName = newImgFile.split("\\.")[0];
        File newCssFile = new File(imgDir.getAbsolutePath(), baseName + ".css");
        PrintWriter out = new PrintWriter(newCssFile);

        for (PNGPositionBean[] positionBeans : pngPositionList) {
            if (positionBeans != null && positionBeans.length > 0)
            for (PNGPositionBean positionBean : positionBeans) {
                if (positionBean == null) {
                    continue;
                }
                String tempName = new File(positionBean.getPngFilePath()).getName();
                tempName = tempName.replace(".", "_");
                out.println("." + tempName + " {");
                out.println("\t" + "background-image: url('" + newImgFile + "');");
                out.print("\t" + "background-position: " + (positionBean.getX() == 0 ? "0 " : -positionBean.getX() + "px ") + (positionBean.getY() == 0 ? "0" : -positionBean.getY() + "px")  + ";");
                out.println("  /**### " + tempName + " */");
                out.println("\t" + "/* width: " + positionBean.getWidth() + "px; */");
                out.println("\t" + "/* height: " + positionBean.getHeight() + "px; */");
                out.println("}");
            }
        }
        
        out.flush();
        out.close();
    }
}
