/*
 * USI Co., Ltd. 2015. All rights reserved.
 */
package lhj.java.tools;

import java.io.File;
import java.io.FileFilter;

/**
 * @author Sealion Hunter
 *
 */
public class PatternFileFilter implements FileFilter {
    Settings settings;

    /**
     * 
     */
    public PatternFileFilter(Settings settings) {
        this.settings = settings;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.FileFilter#accept(java.io.File)
     */
    @Override
    public boolean accept(File pathname) {
        if (settings == null) {
            return true;
        }
        if (settings.excludes != null && settings.excludes.length() > 0) {
            String[] excludes = settings.excludes.split(";");
            for (String exclude : excludes) {
                if (pathname.getAbsolutePath().matches(exclude)) {
                    return false;
                }
            }
        }
        if (settings.includes != null && settings.includes.length() > 0) {
            String[] includes = settings.includes.split(";");
            for (String include : includes) {
                if (pathname.getAbsolutePath().matches(include)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
