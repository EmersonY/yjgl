package com.kingtopinfo.base.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;

/**
 * 文件控制工具
 * @author wpf
 * 
 */
public class FileUtil {
	
	public static final int SUCCESS = 10;
	public static final int NOT_FILE = 0;
	public static final int ERROR_WRITE = 1;
	public final static int PER_RECEIVE_SIZE = 1024 * 4;
	private static final int BUFFER_SIZE = 1 * 1024;
	
	
	/**
	 * 将文本写入文件
	 * @param content 文本内容
	 * @param path 存放路径
	 * @param fileName 文件名
	 * @return
	 */
	public static int textWriteFile(String content, String path, String fileName) {
		File file = new File(path, fileName);
		return textWriteFile(content, file);
	}

	/**
	 * 将文本写入文件
	 * 
	 * @param content
	 *            文本内容
	 * @param filePath
	 *            文件路径
	 * @return
	 */
	public static int textWriteFile(String content, String filePath) {
		File file = new File(filePath);
		return textWriteFile(content, file);
	}

	/**
	 * 将文本写入文件
	 * 
	 * @param content
	 *            文本内容
	 * @param file
	 *            目标文件
	 * @return
	 * @throws Exception
	 */
	public static int textWriteFile(String content, File file) {
		String encoding = "gbk";
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, true), encoding));
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR_WRITE;
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return SUCCESS;
	}

	/**
	 * 写入文件
	 * 
	 * @param input
	 *            输入流
	 * @param filePath
	 *            写目的地
	 * @return
	 */
	public static int writeFile(InputStream input, String filePath) {
		File file = new File(filePath);
		return writeFile(input, file);
	}

	/**
	 * 写入文件
	 * 
	 * @param input
	 *            输入流
	 * @param path
	 *            保存路径
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static int writeFile(InputStream input, String path, String fileName) {
		File file = new File(path, fileName);
		return writeFile(input, file);
	}

	/**
	 * 写入文件
	 * 
	 * @param is
	 *            输入流
	 * @param file
	 *            写入目的地
	 * @return
	 */
	public static int writeFile(InputStream input, File file) {
		// if (!file.isFile()) {
		// return NOT_FILE;
		// }
		FileOutputStream out = null;
		byte buffer[] = new byte[PER_RECEIVE_SIZE];
		try {
			out = new FileOutputStream(file);
			int line = 0;
			while ((line = input.read(buffer)) != -1) {
				out.write(buffer, 0, line);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return NOT_FILE;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return SUCCESS;
	}

	/**
	 * 创建文件路径
	 * 
	 * @param path
	 * @return
	 */
	public static boolean creatPath(String path) {
		File file = new File(path);
		return creatPath(file);
	}

	/**
	 * 创建文件路径
	 * 
	 * @param file
	 * @return
	 */
	public static boolean creatPath(File file) {
		boolean isCreate = false;
		try {
			if (!file.exists()) {
				file.mkdirs();
			}
			isCreate = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isCreate;
	}

	/**
	 * 复制文件
	 * @param fromFile 需要复制的原文件
	 * @param toPath 复制到的目的文件夹路径
	 */

	public static boolean copyFile(File fromFile, String toPath) {

		File toFile = new File(toPath, fromFile.getName());
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(fromFile);
			fos = new FileOutputStream(toFile);
			byte[] buf = new byte[fis.available()];
			if ((fis.read(buf)) != -1) {
				fos.write(buf);
			}
			fos.flush();
			fis.close();
			fos.close();
		} catch (Exception e) {

			try {
				fis.close();
				fos.close();
				e.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}
	
	public static boolean copyFile(File fromFile, String toPath, String fileName) {

		File toFile = new File(toPath, fileName);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(fromFile);
			fos = new FileOutputStream(toFile);
			byte[] buf = new byte[fis.available()];
			if ((fis.read(buf)) != -1) {
				fos.write(buf);
			}
			fos.flush();
			fis.close();
			fos.close();
		} catch (Exception e) {

			try {
				fis.close();
				fos.close();
				e.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}
	
	
	/**
	 * 拷贝文件
	 * 
	 * @param src
	 *            源文件
	 * @param dst
	 *            目标文件
	 * @throws IOException
	 */
	public static void copy(File src, File dst) throws IOException {
		
		InputStream in = null;
		OutputStream out = null;
		try {
			if (dst.exists()) {
				out = new BufferedOutputStream(new FileOutputStream(dst, true),BUFFER_SIZE);
			} else {
				out = new BufferedOutputStream(new FileOutputStream(dst),BUFFER_SIZE);
			}
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
			out.close();
		}
	}
	
	public static boolean deleteFile(String path){
		
		boolean flag = false;
		File file = new File(path);
	    if (file.isFile() && file.exists()) {
	        file.delete();
	        flag = true;
	    }
	    return flag;
	}
	
	 /** 
	  * 删除某个文件夹
	  * @param delpath 需要删除的文件夹路径
	  * @return boolean 
	  */  
	public static boolean deleteFolder(String delpath) {
		try {
			File file = new File(delpath);
			// 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
			if (!file.isDirectory()) {
				file.delete();
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(delpath + "\\" + filelist[i]);
					if (!delfile.isDirectory()) {
						delfile.delete();
					} else if (delfile.isDirectory()) {
						deleteFolder(delpath + "\\" + filelist[i]);
					}
				}
				file.delete();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	 /**
	  * 获取文件的大小（单位：k）
	  * @param file
	  * @return
	  */
	public static long fileSize(File file) {

		long s = 0;
		FileInputStream fis = null;
		FileChannel fc = null;
		try {
			if (file.exists() && file.isFile()) {
				fis = new FileInputStream(file);
				fc = fis.getChannel();
				s = fc.size()/1024;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fc) {
				try {
					fc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return s;
	}
	
	public static String getExt(String fileName) {
		
		String[] str = fileName.split("\\.");
		if(str.length-1 > 0){
			return str[str.length-1];
		}else{
			return "-";
		}
		
	}
	
	public static void main(String[] args){
//		FileUtil.creatPath("D:\\b\\a.txt");
		String str = FileUtil.getExt("P60908.081223.jpg");
		System.out.println(str);
		
	}
}
