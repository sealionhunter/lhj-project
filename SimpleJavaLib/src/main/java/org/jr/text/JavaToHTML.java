package org.jr.text;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月28日
 * @author Cherami
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.TreeSet;

import org.jr.util.ClassUtil;
import org.jr.util.Constants;
import org.jr.util.StringUtil;
import org.jr.util.TreeSetUtil;

/**
 * 将java源代码转换为HTML格式并且可以设置风格的工具类。
 * 这个类应该是一个可以反复使用的类，不需生成太多的类实例。
 * 用完以后重新设置其他的风格就可以继续使用。
 * 一般情况下构造完一个实例后要设置需要的参数，否则虽然对代码进行了处理，但是没有任何效果。
 * 当然也可以启用默认模式。
 * @since  0.5
 */

public class JavaToHTML {
  public static final int NONTYPE = -1;
  public static final int WORD = 0;
  public static final int QUOTE = 1;
  public static final int LINE_COMMENT = 2;
  public static final int COMMENT = 3;
  protected static final TreeSet defaultReservedWords = TreeSetUtil.
      ArrayToTreeSet(StringUtil.split("break,byte,boolean,catch,case,class,char,continue,default,double,do,else,extends,false,final,float,for,finally,if,import,implements,int,interface,instanceof,long,length,native,new,null,package,private,protected,public,final,return,switch,synchronized,short,static,super,try,true,this,throw,throws,threadsafe,transient,void,volatile,while"));
  protected static final TreeSet defaultClassNames = ClassUtil.getAllClassname();
  protected static final HTMLStyle defaultReservedWordsStyle = new HTMLStyle(
      "<b><font color='#0000ff'>",
      "</font></b>");
  protected static final HTMLStyle defaultCommentStyle = new HTMLStyle(
      "<i><font color='#339900'>", "</font></i>");
  protected static final HTMLStyle defaultClassNameStyle = new HTMLStyle(
      "<font color='#ff0000'>", "</font>");
  protected static final HTMLStyle defaultQuoteStyle = new HTMLStyle(
      "<font color='#ff33ff'>", "</font>");
  protected TreeSet reservedWords;
  protected TreeSet classNames;
  protected HTMLStyle reservedWordsStyle;
  protected HTMLStyle commentStyle;
  protected HTMLStyle classNameStyle;
  protected HTMLStyle quoteStyle;
  protected int currentType = NONTYPE;
  protected boolean showLineNumber = true;

  private final int HEAD = 0;
  private final int LINE = 1;
  private final int END = 2;

  /**
   * 构造一个JavaToHTML。
   * @since  0.5
   */
  public JavaToHTML() {
    reservedWords = new TreeSet();
    classNames = new TreeSet();
    reservedWordsStyle = new HTMLStyle();
    commentStyle = new HTMLStyle();
    classNameStyle = new HTMLStyle();
    quoteStyle = new HTMLStyle();
  }

  /**
   * 根据指定的参数构造一个JavaToHTML。
   * @param useDefault 是否使用缺省设置
   * @since  0.5
   */
  public JavaToHTML(boolean useDefault) {
    if (useDefault == true) {
      useDefaultSet();
    }
    else {
      reservedWords = new TreeSet();
      classNames = new TreeSet();
      reservedWordsStyle = new HTMLStyle();
      commentStyle = new HTMLStyle();
      classNameStyle = new HTMLStyle();
      quoteStyle = new HTMLStyle();
    }
  }

  /**
   * 设置使用转换器的缺省设置。
   */
  public void useDefaultSet() {
    reservedWords = defaultReservedWords;
    classNames = defaultClassNames;
    reservedWordsStyle = defaultReservedWordsStyle;
    commentStyle = defaultCommentStyle;
    classNameStyle = defaultClassNameStyle;
    quoteStyle = defaultQuoteStyle;
  }

  /**
   * 设置是否显示行号。
   * @param lineNumberVisible 是否显示行号，true的时候显示行号
   * @since  0.5
   */
  public void setLineNumberVisible(boolean lineNumberVisible) {
    showLineNumber = lineNumberVisible;
  }

  /**
   * 设置保留字。
   * @param reservedWords 保留字
   * @since  0.5
   */
  public void setReservedWords(TreeSet reservedWords) {
    this.reservedWords = reservedWords;
  }

  /**
   * 设置已知类的类名。
   * @param classNames 已知类的类名
   * @since  0.5
   */
  public void setClassNames(TreeSet classNames) {
    this.classNames = classNames;
  }

  /**
   * 设置保留字的风格。
   * @param reservedWordsStyle 保留字的风格
   * @since  0.5
   */
  public void setReservedWordsStyle(HTMLStyle reservedWordsStyle) {
    this.reservedWordsStyle = reservedWordsStyle;
  }

  /**
   * 设置注释的风格。
   * @param commentStyle 注释的风格
   * @since  0.5
   */
  public void setCommentStyle(HTMLStyle commentStyle) {
    this.commentStyle = commentStyle;
  }

  /**
   * 设置已知类名的风格。
   * @param classNameStyle 已知类名的风格
   * @since  0.5
   */
  public void setClassNameStyle(HTMLStyle classNameStyle) {
    this.classNameStyle = classNameStyle;
  }

  /**
   * 设置字符串的风格。
   * @param quoteStyle 字符串的风格
   * @since  0.5
   */
  public void setQuoteStyle(HTMLStyle quoteStyle) {
    this.quoteStyle = quoteStyle;
  }

  /**
   * 将HTML标签的'<'和'>'转换为对应的转义文本"&lt"和"&gt"。
   * @param source 原始文本
   * @return 转换后的结果
   * @since  0.5
   */
  public static String escapeHTMLtag(String source) {
    String result = source.replaceAll("<", "&lt");
    result = result.replaceAll(">", "&gt");
    return result;
  }

  /**
   * 将单词转换为具有风格的结果。
   * @param word 原始的单词
   * @return 转换后的结果
   */
  private String convertWord(String word) {
    if (reservedWords.contains(word)) {
      return reservedWordsStyle.getBegin() + word + reservedWordsStyle.getEnd();
    }
    else if (classNames.contains(word)) {
      return classNameStyle.getBegin() + word + classNameStyle.getEnd();
    }
    else {
      return word;
    }
  }

  /**
   * 将一行内容转换为具有风格的内容。
   * @param line 一行文本
   * @return 转换后的结果
   */
  private String convertLine(String line) {
    if (line.length() == 0) {
      return "";
    }
    StringBuffer lineBuffer = new StringBuffer(line.length() * 2);
    StringBuffer wordBuffer = new StringBuffer();
    int start = 0;
    int end = line.length() - 1;
    char[] contents = line.toCharArray();
    //循环判断一行中的最后一个字符前的字符状况
    for (int i = start; i < end; i++) {
      //如果没有当前类型
      if (currentType == NONTYPE) {
        //如果是合法的java字符开始标识符则将类型设置为单词类型并将当前字符添加到单词缓冲区
        if (Character.isJavaIdentifierStart(contents[i])) {
          currentType = WORD;
          wordBuffer.append(contents[i]);
          continue;
        }
        //如果是可能的注释开始
        else if (contents[i] == '/') {
          char nextChar = contents[i + 1];
          switch (nextChar) {
            //如果下一个字符是'*'则表明是多行注释，将类型设置为多行字符并添加对应的字体颜色及字符
            case '*':
              currentType = COMMENT;
              lineBuffer.append(commentStyle.getBegin());
              lineBuffer.append(contents[i]);
              continue;
              //如果下一个字符是'/'则表明是单行注释，添加对应的字体颜色并直接取字符串的剩余内容
            case '/':
              lineBuffer.append(commentStyle.getBegin());
              lineBuffer.append(line.substring(i));
              lineBuffer.append(commentStyle.getEnd());
              return lineBuffer.toString();
              //不是注释的开始则直接添加内容
            default:
              lineBuffer.append(contents[i]);
              continue;
          }
        }
        //如果是双引号
        else if (contents[i] == '\"') {
          //如果是最开始的位置或者其前一个字符不是'\'则表明是一个引用的开始，设置类型并添加对应的字体颜色及字符
          if (i == 0 || contents[i - 1] != '\\') {
            currentType = QUOTE;
            lineBuffer.append(quoteStyle.getBegin());
            lineBuffer.append(contents[i]);
            continue;
          }
          //否则不是引用的内容，添加内容
          else {
            lineBuffer.append(contents[i]);
            continue;
          }
        }
        //如果不是以上情况则表明是其他的杂项内容，不进行处理，将字符添加到缓冲区
        else {
          lineBuffer.append(contents[i]);
          continue;
        }
      }
      //如果当前类型是单词
      else if (currentType == WORD) {
        //如果是一个java标识符的合法部分表明单词没有结束，将当前字符添加到单词缓冲区
        if (Character.isJavaIdentifierPart(contents[i])) {
          wordBuffer.append(contents[i]);
          continue;
        }
        //否则表明单词结束，将单词缓冲区的内容进行转换并添加到缓冲区并继续判断后面部分的语法成分
        else {
          lineBuffer.append(convertWord(wordBuffer.toString()));
          wordBuffer = new StringBuffer();
          //如果是可能的注释开始
          if (contents[i] == '/') {
            char nextChar = contents[i + 1];
            switch (nextChar) {
              //如果下一个字符是'*'则表明是多行注释，将类型设置为多行字符并添加对应的字体颜色及字符
              case '*':
                currentType = COMMENT;
                lineBuffer.append(commentStyle.getBegin());
                lineBuffer.append(contents[i]);
                continue;
                //如果下一个字符是'/'则表明是单行注释，添加对应的字体颜色并直接取字符串的剩余内容
              case '/':
                lineBuffer.append(commentStyle.getBegin());
                lineBuffer.append(line.substring(i));
                lineBuffer.append(commentStyle.getEnd());
                currentType = NONTYPE;
                return lineBuffer.toString();
            }
          }
          //如果是双引号则表明是一个引用的开始，设置类型并添加对应的字体颜色及字符
          else if (contents[i] == '\"') {
            currentType = QUOTE;
            lineBuffer.append(quoteStyle.getBegin());
            lineBuffer.append(contents[i]);
            continue;
          }
          //如果不是以上情况则表明是其他的杂项内容，不进行处理，将字符添加到缓冲区
          else {
            currentType = NONTYPE;
            lineBuffer.append(contents[i]);
            continue;
          }
        }
      }
      //如果当前类型是引用
      else if (currentType == QUOTE) {
        //如果当前字符不是引号或者如果是引号但是他的前一个字符是'\'则表明是引用的内容，添加内容
        if (contents[i] != '\"' || contents[i - 1] == '\\') {
          lineBuffer.append(contents[i]);
          continue;
        }
        //否则表明是引用的结束，添加内容并结束字体设置
        else {
          lineBuffer.append(contents[i]);
          lineBuffer.append(quoteStyle.getEnd());
          currentType = NONTYPE;
          continue;
        }
      }
      //如果当前类型是多行注释
      else if (currentType == COMMENT) {
        //如果是第一个字符则表明是多行注释的继续的一行，添加字体设置
        if (i == 0) {
          lineBuffer.append(commentStyle.getBegin());
        }
        //如果当前字符是'/'并且他的前一个字符是'*'则表明是注释的结束，添加内容并结束字体设置
        if (contents[i] == '/' && contents[i - 1] == '*') {
          lineBuffer.append(contents[i]);
          lineBuffer.append(commentStyle.getEnd());
          currentType = NONTYPE;
          continue;
        }
        //否则表明不是注释的结束，添加内容
        else {
          lineBuffer.append(contents[i]);
          continue;
        }
      }
    }
    //对最后一个字符进行处理并结束本行的转换
    char endChar = contents[end];
    //如果是没有任何类型，最后一个字符不能构成任何特殊类，直接添加内容
    if (currentType == NONTYPE) {
      lineBuffer.append(endChar);
      return lineBuffer.toString();
    }
    //如果是单词
    else if (currentType == WORD) {
      //如果最后一个字符是标识符的一部分，添加内容并结束风格设置
      if (Character.isJavaIdentifierPart(endChar)) {
        wordBuffer.append(endChar);
        lineBuffer.append(convertWord(wordBuffer.toString()));
        wordBuffer = new StringBuffer();
      }
      //否则表明单词已经结束，转换单词后作为普通内容添加。
      else {
        lineBuffer.append(convertWord(wordBuffer.toString()));
        wordBuffer = new StringBuffer();
        lineBuffer.append(endChar);
      }
      currentType = NONTYPE;
      return lineBuffer.toString();
    }
    //如果是引用，由于字符串不能跨行，因此结束字符串引用不会有问题。
    else if (currentType == QUOTE) {
      lineBuffer.append(endChar);
      lineBuffer.append(quoteStyle.getEnd());
      currentType = NONTYPE;
      return lineBuffer.toString();
    }
    else {
      //如果最后一个字符是'/'并且他的前一个字符是'*'则表明是注释的结束，添加内容并结束字体设置
      if (endChar == '/' && contents[end - 1] == '*') {
        lineBuffer.append(endChar);
        lineBuffer.append(commentStyle.getEnd());
        currentType = NONTYPE;
      }
      //否则表明不是注释的结束，添加内容
      else {
        lineBuffer.append(endChar);
        lineBuffer.append(commentStyle.getEnd());
      }
      return lineBuffer.toString();
    }

  }

  /**
   * 将指定的缓冲阅读器中的内容读出进行转换并将转换后的结果写入记录器。
   * 之所以需要缓冲阅读器是因为在内部是以行为单位进行处理的。
   * @param reader 阅读器
   * @param writer 记录器
   * @since  0.5
   */
  public void convert(BufferedReader reader, Writer writer) {
    try {
      writer.write("<pre>");
      writeLineNumber(writer, HEAD);
      String line = reader.readLine();
      while (line != null) {
        writeLineNumber(writer, LINE);
        writer.write(convertLine(escapeHTMLtag(line)));
        writer.write(Constants.LINE_SEPARATOR);
        line = reader.readLine();
      }
      writeLineNumber(writer, END);
      writer.write("</pre>");
    }
    catch (IOException e) {

    }
  }

  /**
   * 记录器写入行号控制部分。
   * @param writer 记录器
   * @param type 行号控制类型
   * @throws IOException
   */
  private void writeLineNumber(Writer writer, int type) throws IOException {
    if (showLineNumber == true) {
      switch (type) {
        case HEAD:
          writer.write("<ol>");
          break;
        case LINE:
          writer.write("<li>");
          break;
        case END:
          writer.write("</ol>");
          break;
      }
    }
  }
}