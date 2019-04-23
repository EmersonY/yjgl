package com.kingtopinfo.base.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;


public class FilePathUtil {
	
	// 通过配置文件获取值
	public static String getFilePath(String name) {
		Properties prop = new Properties();
		try {
			prop.load(FilePathUtil.class.getClassLoader().getResourceAsStream("document-config.properties"));
			return prop.getProperty(name);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 创建目录
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {// 判断目录是否存在
			System.out.println("创建目录失败，目标目录已存在！");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
			destDirName = destDirName + File.separator;
		}
		if (dir.mkdirs()) {// 创建目标目录
			System.out.println("创建目录成功！" + destDirName);
			return true;
		} else {
			System.out.println("创建目录失败！");
			return false;
		}
	}
	
	// 删除文件夹
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}
	
	public static String getFileRealpath(String filePath, HttpServletRequest request) {
		String yjxt = FilePathUtil.getFilePath("xmlj");
		filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + yjxt + filePath;
		return filePath;
	}
}