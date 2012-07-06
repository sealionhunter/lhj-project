/**
 * 
 */
package lhj.learn.codeview.converte;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * @author hjliang
 */
public class HtmlConverter {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        new HtmlConverter().convert("", "");

    }

    public void convert(String from, String to) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fm = compiler.getStandardFileManager(null, null, Charset.forName("UTF-8"));
        File f = new File("./src/main/java/lhj/learn/codeview/converte/HtmlConverter.java");
        Iterable<? extends JavaFileObject> jfs = fm.getJavaFileObjects(f);
        for (JavaFileObject jf : jfs) {
            CharSequence cs = jf.getCharContent(false);
            StringBuilder out = new StringBuilder();
            convert(cs.toString().replaceAll("<","&lt;").replaceAll(">", "&gt;"), out);
            System.out.println(out.toString());
        }
    }

    private void convert(CharSequence chars, StringBuilder out) {
        int startPos = 0;
        int currentPos = 0;
        /* this is a block comment */
        
        int length = chars.length();
        // this is a line comment
        
        while (currentPos < length) {
            startPos = currentPos;
            char ch = chars.charAt(currentPos);
            if (ch == '/') {
                ch = chars.charAt(++currentPos);
                if (ch == '*') {
                    out.append("<span class='review-cmt'>");
                    currentPos = getDocCommentEndPos(chars, currentPos + 1);
                    out.append(chars.subSequence(startPos, 1+currentPos));
                    out.append("</span>");
                } else if (ch == '/') {
                    out.append("<span class='review-cmt'>");
                    currentPos = getLineCmtEnd(chars, currentPos + 1);
                    out.append(chars.subSequence(startPos, 1+currentPos));
                    out.append("</span>");
                } else if (ch == '=') {
                    out.append("<span class='review-cal'>");
                    out.append(chars.subSequence(startPos, 1+currentPos));
                    out.append("</span>");
                } else {
                    out.append("<span class='review-cal'>");
                    out.append(chars.subSequence(startPos, 1+currentPos));
                    out.append("</span>");
                }
            } else if (ch == '"') {
                out.append("<span class='review-str'>");
                if (chars.charAt(currentPos-1) == '\'') {
                    currentPos++;
                } else {
                    currentPos = getStringEnd(chars, currentPos + 1);
                }
                out.append(chars.subSequence(startPos, 1+currentPos));
                out.append("</span>");
            } else if (Character.isWhitespace(ch)) {
                currentPos = getWhitespace(chars, currentPos);
                out.append(chars.subSequence(startPos, currentPos+1));
            } else if (Character.isJavaIdentifierPart(ch)){
                currentPos = getWord(chars, currentPos + 1);
                String word = chars.subSequence(startPos, currentPos+1).toString();
                if (isKeyWord(word)) {
                    out.append("<span class='review-key'>");
                    out.append(word);
                    out.append("</span>");
                } else {
                    out.append(word);
                }
            } else {
                out.append(ch);
            }
            currentPos++;
        }
    }

    private final static Set<String> keyWord;
    static {
        String[] keys = {};
        keyWord = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(keys)));
    }

    private boolean isKeyWord(String word) {
        if (keyWord.contains(word)) {
            return true;
        }
        return false;
    }

    private int getWord(CharSequence chars, int startPos) {
        int currentPos = startPos;
        int length = chars.length();
        while (currentPos < length ) {
            if (!Character.isJavaIdentifierPart(chars.charAt(currentPos))) {
                currentPos--;
                break;
            }
            currentPos++;
        }
        if (currentPos == length) {
            currentPos--;
        }
        return currentPos;
    }

    private int getWhitespace(CharSequence chars, int startPos) {
        int currentPos = startPos;
        int length = chars.length();
        while (currentPos < length) {
            if (!Character.isWhitespace(chars.charAt(currentPos))) {
                currentPos--;
                break;
            }
            currentPos++;
        }
        if (currentPos == length) {
            currentPos--;
        }
        return currentPos;
    }

    private int getStringEnd(CharSequence chars, int startPos) {
        int currentPos = startPos;
        int length = chars.length();
        while (currentPos < length) {
            char ch = chars.charAt(currentPos);
            if (ch == '"') {
                break;
            }
            if (ch == '\\' && currentPos + 1 < length && chars.charAt(currentPos + 1) == '"') {
                currentPos++;
            }
            currentPos++;
        }
        if (currentPos == length) {
            currentPos--;
        }
        return currentPos;
    }

    private int getDocCommentEndPos(CharSequence chars, int startPos) {
        int currentPos = startPos;
        int length = chars.length();
        while (currentPos < length) {
            char ch = chars.charAt(currentPos);
            if (ch == '*' && currentPos + 1 < length && chars.charAt(currentPos + 1) == '/') {
                currentPos++;
                break;
            }
            currentPos++;
        }
        if (currentPos == length) {
            currentPos--;
        }
        return currentPos;
    }

    private int getLineCmtEnd(CharSequence chars, int startPos) {
        int currentPos = startPos;
        int length = chars.length();
        while (currentPos < length) {
            char ch = chars.charAt(currentPos);
            if (ch == '\r' || ch == '\n') {
                currentPos--;
                break;
            }
            currentPos++;
        }
        if (currentPos == length) {
            currentPos--;
        }
        return currentPos;
    }
}
