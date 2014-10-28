package org.jr.text;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��28��
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
 * ��javaԴ����ת��ΪHTML��ʽ���ҿ������÷��Ĺ����ࡣ
 * �����Ӧ����һ�����Է���ʹ�õ��࣬��������̫�����ʵ����
 * �����Ժ��������������ķ��Ϳ��Լ���ʹ�á�
 * һ������¹�����һ��ʵ����Ҫ������Ҫ�Ĳ�����������Ȼ�Դ�������˴�������û���κ�Ч����
 * ��ȻҲ��������Ĭ��ģʽ��
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
   * ����һ��JavaToHTML��
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
   * ����ָ���Ĳ�������һ��JavaToHTML��
   * @param useDefault �Ƿ�ʹ��ȱʡ����
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
   * ����ʹ��ת������ȱʡ���á�
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
   * �����Ƿ���ʾ�кš�
   * @param lineNumberVisible �Ƿ���ʾ�кţ�true��ʱ����ʾ�к�
   * @since  0.5
   */
  public void setLineNumberVisible(boolean lineNumberVisible) {
    showLineNumber = lineNumberVisible;
  }

  /**
   * ���ñ����֡�
   * @param reservedWords ������
   * @since  0.5
   */
  public void setReservedWords(TreeSet reservedWords) {
    this.reservedWords = reservedWords;
  }

  /**
   * ������֪���������
   * @param classNames ��֪�������
   * @since  0.5
   */
  public void setClassNames(TreeSet classNames) {
    this.classNames = classNames;
  }

  /**
   * ���ñ����ֵķ��
   * @param reservedWordsStyle �����ֵķ��
   * @since  0.5
   */
  public void setReservedWordsStyle(HTMLStyle reservedWordsStyle) {
    this.reservedWordsStyle = reservedWordsStyle;
  }

  /**
   * ����ע�͵ķ��
   * @param commentStyle ע�͵ķ��
   * @since  0.5
   */
  public void setCommentStyle(HTMLStyle commentStyle) {
    this.commentStyle = commentStyle;
  }

  /**
   * ������֪�����ķ��
   * @param classNameStyle ��֪�����ķ��
   * @since  0.5
   */
  public void setClassNameStyle(HTMLStyle classNameStyle) {
    this.classNameStyle = classNameStyle;
  }

  /**
   * �����ַ����ķ��
   * @param quoteStyle �ַ����ķ��
   * @since  0.5
   */
  public void setQuoteStyle(HTMLStyle quoteStyle) {
    this.quoteStyle = quoteStyle;
  }

  /**
   * ��HTML��ǩ��'<'��'>'ת��Ϊ��Ӧ��ת���ı�"&lt"��"&gt"��
   * @param source ԭʼ�ı�
   * @return ת����Ľ��
   * @since  0.5
   */
  public static String escapeHTMLtag(String source) {
    String result = source.replaceAll("<", "&lt");
    result = result.replaceAll(">", "&gt");
    return result;
  }

  /**
   * ������ת��Ϊ���з��Ľ����
   * @param word ԭʼ�ĵ���
   * @return ת����Ľ��
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
   * ��һ������ת��Ϊ���з������ݡ�
   * @param line һ���ı�
   * @return ת����Ľ��
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
    //ѭ���ж�һ���е����һ���ַ�ǰ���ַ�״��
    for (int i = start; i < end; i++) {
      //���û�е�ǰ����
      if (currentType == NONTYPE) {
        //����ǺϷ���java�ַ���ʼ��ʶ������������Ϊ�������Ͳ�����ǰ�ַ���ӵ����ʻ�����
        if (Character.isJavaIdentifierStart(contents[i])) {
          currentType = WORD;
          wordBuffer.append(contents[i]);
          continue;
        }
        //����ǿ��ܵ�ע�Ϳ�ʼ
        else if (contents[i] == '/') {
          char nextChar = contents[i + 1];
          switch (nextChar) {
            //�����һ���ַ���'*'������Ƕ���ע�ͣ�����������Ϊ�����ַ�����Ӷ�Ӧ��������ɫ���ַ�
            case '*':
              currentType = COMMENT;
              lineBuffer.append(commentStyle.getBegin());
              lineBuffer.append(contents[i]);
              continue;
              //�����һ���ַ���'/'������ǵ���ע�ͣ���Ӷ�Ӧ��������ɫ��ֱ��ȡ�ַ�����ʣ������
            case '/':
              lineBuffer.append(commentStyle.getBegin());
              lineBuffer.append(line.substring(i));
              lineBuffer.append(commentStyle.getEnd());
              return lineBuffer.toString();
              //����ע�͵Ŀ�ʼ��ֱ���������
            default:
              lineBuffer.append(contents[i]);
              continue;
          }
        }
        //�����˫����
        else if (contents[i] == '\"') {
          //������ʼ��λ�û�����ǰһ���ַ�����'\'�������һ�����õĿ�ʼ���������Ͳ���Ӷ�Ӧ��������ɫ���ַ�
          if (i == 0 || contents[i - 1] != '\\') {
            currentType = QUOTE;
            lineBuffer.append(quoteStyle.getBegin());
            lineBuffer.append(contents[i]);
            continue;
          }
          //���������õ����ݣ��������
          else {
            lineBuffer.append(contents[i]);
            continue;
          }
        }
        //����������������������������������ݣ������д������ַ���ӵ�������
        else {
          lineBuffer.append(contents[i]);
          continue;
        }
      }
      //�����ǰ�����ǵ���
      else if (currentType == WORD) {
        //�����һ��java��ʶ���ĺϷ����ֱ�������û�н���������ǰ�ַ���ӵ����ʻ�����
        if (Character.isJavaIdentifierPart(contents[i])) {
          wordBuffer.append(contents[i]);
          continue;
        }
        //����������ʽ����������ʻ����������ݽ���ת������ӵ��������������жϺ��沿�ֵ��﷨�ɷ�
        else {
          lineBuffer.append(convertWord(wordBuffer.toString()));
          wordBuffer = new StringBuffer();
          //����ǿ��ܵ�ע�Ϳ�ʼ
          if (contents[i] == '/') {
            char nextChar = contents[i + 1];
            switch (nextChar) {
              //�����һ���ַ���'*'������Ƕ���ע�ͣ�����������Ϊ�����ַ�����Ӷ�Ӧ��������ɫ���ַ�
              case '*':
                currentType = COMMENT;
                lineBuffer.append(commentStyle.getBegin());
                lineBuffer.append(contents[i]);
                continue;
                //�����һ���ַ���'/'������ǵ���ע�ͣ���Ӷ�Ӧ��������ɫ��ֱ��ȡ�ַ�����ʣ������
              case '/':
                lineBuffer.append(commentStyle.getBegin());
                lineBuffer.append(line.substring(i));
                lineBuffer.append(commentStyle.getEnd());
                currentType = NONTYPE;
                return lineBuffer.toString();
            }
          }
          //�����˫�����������һ�����õĿ�ʼ���������Ͳ���Ӷ�Ӧ��������ɫ���ַ�
          else if (contents[i] == '\"') {
            currentType = QUOTE;
            lineBuffer.append(quoteStyle.getBegin());
            lineBuffer.append(contents[i]);
            continue;
          }
          //����������������������������������ݣ������д������ַ���ӵ�������
          else {
            currentType = NONTYPE;
            lineBuffer.append(contents[i]);
            continue;
          }
        }
      }
      //�����ǰ����������
      else if (currentType == QUOTE) {
        //�����ǰ�ַ��������Ż�����������ŵ�������ǰһ���ַ���'\'����������õ����ݣ��������
        if (contents[i] != '\"' || contents[i - 1] == '\\') {
          lineBuffer.append(contents[i]);
          continue;
        }
        //������������õĽ�����������ݲ�������������
        else {
          lineBuffer.append(contents[i]);
          lineBuffer.append(quoteStyle.getEnd());
          currentType = NONTYPE;
          continue;
        }
      }
      //�����ǰ�����Ƕ���ע��
      else if (currentType == COMMENT) {
        //����ǵ�һ���ַ�������Ƕ���ע�͵ļ�����һ�У������������
        if (i == 0) {
          lineBuffer.append(commentStyle.getBegin());
        }
        //�����ǰ�ַ���'/'��������ǰһ���ַ���'*'�������ע�͵Ľ�����������ݲ�������������
        if (contents[i] == '/' && contents[i - 1] == '*') {
          lineBuffer.append(contents[i]);
          lineBuffer.append(commentStyle.getEnd());
          currentType = NONTYPE;
          continue;
        }
        //�����������ע�͵Ľ������������
        else {
          lineBuffer.append(contents[i]);
          continue;
        }
      }
    }
    //�����һ���ַ����д����������е�ת��
    char endChar = contents[end];
    //�����û���κ����ͣ����һ���ַ����ܹ����κ������ֱ࣬���������
    if (currentType == NONTYPE) {
      lineBuffer.append(endChar);
      return lineBuffer.toString();
    }
    //����ǵ���
    else if (currentType == WORD) {
      //������һ���ַ��Ǳ�ʶ����һ���֣�������ݲ������������
      if (Character.isJavaIdentifierPart(endChar)) {
        wordBuffer.append(endChar);
        lineBuffer.append(convertWord(wordBuffer.toString()));
        wordBuffer = new StringBuffer();
      }
      //������������Ѿ�������ת�����ʺ���Ϊ��ͨ������ӡ�
      else {
        lineBuffer.append(convertWord(wordBuffer.toString()));
        wordBuffer = new StringBuffer();
        lineBuffer.append(endChar);
      }
      currentType = NONTYPE;
      return lineBuffer.toString();
    }
    //��������ã������ַ������ܿ��У���˽����ַ������ò��������⡣
    else if (currentType == QUOTE) {
      lineBuffer.append(endChar);
      lineBuffer.append(quoteStyle.getEnd());
      currentType = NONTYPE;
      return lineBuffer.toString();
    }
    else {
      //������һ���ַ���'/'��������ǰһ���ַ���'*'�������ע�͵Ľ�����������ݲ�������������
      if (endChar == '/' && contents[end - 1] == '*') {
        lineBuffer.append(endChar);
        lineBuffer.append(commentStyle.getEnd());
        currentType = NONTYPE;
      }
      //�����������ע�͵Ľ������������
      else {
        lineBuffer.append(endChar);
        lineBuffer.append(commentStyle.getEnd());
      }
      return lineBuffer.toString();
    }

  }

  /**
   * ��ָ���Ļ����Ķ����е����ݶ�������ת������ת����Ľ��д���¼����
   * ֮������Ҫ�����Ķ�������Ϊ���ڲ�������Ϊ��λ���д���ġ�
   * @param reader �Ķ���
   * @param writer ��¼��
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
   * ��¼��д���кſ��Ʋ��֡�
   * @param writer ��¼��
   * @param type �кſ�������
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