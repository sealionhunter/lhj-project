package org.jr.util;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��25��
 * @author Cherami
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.zip.*;

import java.awt.*;
import javax.swing.*;

import org.jr.io.*;

/**
 * ����ȡ�����Jar����Zip�ļ��е���Դ��
 * �����ṩһЩ����������Ĵ�Jar����Zip�ļ��еõ���Դ��
 * @since  0.4
 */
public final class JarResource {

  private HashMap<Object, Object> entries = new HashMap<Object, Object>();
  private HashMap<Object, Object> names = new HashMap<Object, Object>();

  private String fileName;
  private ZipFile file;

  /**
   * ����ָ�����ļ�������JarResource��
   * @param fileName �ļ���
   * @since  0.4
   */
  public JarResource(String fileName) {
    this.fileName = fileName;
    init();
  }

  /**
   * �����ļ����õ���ѹ�����е�ZipEntry��
   * ����һ���ļ������ж�����ܵĶ�Ӧ������Ӧ��һ���ȷ�������������������ʹ��ȫ�޶�·����
   * @param fileName �ļ���
   * @return ��Ӧ��ѹ�����е�ZipEntry��������ʱ����null
   */
  private ZipEntry getEntry(String fileName) {
    ZipEntry entry = (ZipEntry) entries.get(fileName);
    if (entry == null) {
      String entryName = (String) names.get(fileName);
      if (entryName != null) {
        return (ZipEntry) entries.get(entryName);
      }
      else {
        return null;
      }
    }
    else {
      return entry;
    }

  }

  /**
   * ��ȡָ�����ļ����ݲ�����һ���ֽ����顣
   * @param fileName ��Դ���ļ���
   * @return ָ�����ļ����ݵ��ֽ�����
   * @since  0.4
   */
  public byte[] getResource(String fileName) {
    ZipEntry entry = getEntry(fileName);
    if (entry != null) {
      try {
        InputStream inputStream = file.getInputStream(entry);
        int length = inputStream.available();
        byte contents[] = new byte[length];
        inputStream.read(contents);
        inputStream.close();
        return contents;
      }
      catch (IOException e) {
        return null;
      }
    }
    else {
      return null;
    }
  }

  /**
   * ��ȡָ�����ļ��������ͼ��
   * @param fileName ��Դ���ļ���
   * @return ָ�����ļ��������ͼ��
   * @since  0.4
   */
  public Image getImage(String fileName) {
    ZipEntry entry = getEntry(fileName);
    if (entry != null) {
      StringBuffer url = new StringBuffer("jar:file:/");
      url.append(FileUtil.getUNIXfilePath(this.fileName));
      url.append("!/");
      url.append(entry.getName());
      try {
        URL fileURL = new URL(url.toString());
        return new ImageIcon(fileURL).getImage();
      }
      catch (MalformedURLException e) {
        return null;
      }
    }
    else {
      return null;
    }
  }

  /**
   * ��ȡָ�����ļ���������ַ�����
   * @param fileName ��Դ���ļ���
   * @return ָ�����ļ���������ַ���
   * @since  0.4
   */
  public String getString(String fileName) {
    byte contents[] = getResource(fileName);
    if (contents != null) {
      return new String(contents);
    }
    else {
      return null;
    }
  }

  /**
   * ��ʼ���ڲ�����Դ��HashMap��
   */
  private void init() {
    try {
      file = new ZipFile(fileName);
      Enumeration enumeration = file.entries();
      while (enumeration.hasMoreElements()) {
        ZipEntry entry = (ZipEntry) enumeration.nextElement();
        if (!entry.isDirectory()) {
          entries.put(entry.getName(), entry);
          names.put(FileUtil.getFileName(entry.getName()), entry.getName());
        }
      }
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}
