package lhj.learn.spritetool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MergeCSS {
    private static final String LINE_SEP = System.getProperty("line.separator"); 
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
    	if (args.length != 4) {
    		printHelp();
    		return;
    	}
        Pattern p = Pattern
                .compile(".*background-position: (0|-[0-9]*)(px)* (0|-[0-9]*)(px)*;  /\\*\\*### (.*) \\*/");
        Map<String, String> cssmap = new HashMap<String, String>();
        String[] cssIns = args[3].split(":");
        for (String cssin : cssIns) {
            BufferedReader in = new BufferedReader(new FileReader(new File(
                    args[2], cssin)));
            String line = null;
            while ((line = in.readLine()) != null) {
                Matcher m = p.matcher(line);
                if (m.matches()) {
                    cssmap.put(m.group(5), line);
                }
            }
            in.close();
        }
        BufferedReader in2 = new BufferedReader(new FileReader(
                new File(args[0])));
        BufferedWriter output = new BufferedWriter(new FileWriter(new File(
                args[1])));
        String line = null;
        Set<String> cssmap2 = new HashSet<String>();
        while ((line = in2.readLine()) != null) {

            Matcher m = p.matcher(line);
            if (m.matches()) {
                if (cssmap.get(m.group(5)) != null) {
                    output.write(cssmap.get(m.group(5)));
                    cssmap2.add(m.group(5));
                } else {
                    output.write(line);
                    output.write("/** css not found! */");
                }
            } else {
                output.write(line);
            }
            output.write(LINE_SEP);
        }
        boolean first = true;
        for (String key : cssmap.keySet()) {
            if (!cssmap2.contains(key)) {
                if (first) {
                    output.write(LINE_SEP);
                    output.write("/** There are some css not definded: */");
                    output.write(LINE_SEP);
                    first = false;
                }
                output.write("///*" + cssmap.get(key));
                output.write(LINE_SEP);
            }
        }
        output.close();
        in2.close();
    }
    
    
    public static void printHelp() {
        String help = "usage:\n"
                + "java lhj.learn.spritetool.MergeCSS src-cssfile dest-cssfile sprite-css-folder sprite-css\n"
                + "\n"
                + "    src-cssfile:           original css file\n"
                + "    dest-cssfile:          destination css file\n"
                + "    sprite-css-folder:     sprite-css folder,see lhj.learn.spritetool.Main \n"
                + "    sprite-css:            sprite-css, see lhj.learn.spritetool.Main \n"
                + "sample: \n"
                + "    java lhj.learn.spritetool.MergeCSS ./images-sprite/sidebar.css ./images-sprite/sidebar_new.css ./images-sprite sidebar.css:sidebar_m.css:sidebar_small.css:sidebar_m_small.css \n";
    	System.out.println(help);
    }

}
