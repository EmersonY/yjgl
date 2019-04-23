package com.kingtopinfo.base.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 压缩解压工具类，支持中文并修复了0K内容解压为文件夹得BUG。 使用ant.zip辅助
 * 
 * @author 陈海彬
 */
@SuppressWarnings("unused")
public class ZipUtil {
	
	private ZipFile			zipFile;
	
	private ZipOutputStream	zipOut;						// 压缩Zip
	
	private ZipEntry		zipEntry;
	
	private static int		bufSize;					// size of bytes
	
	private byte[]			buf;
	
	private int				readedBytes;
	
	// 用于压缩中。要去除的绝对父路路径，目的是将绝对路径变成相对路径。
	
	private String			deleteAbsoluteParent;
	
	private static ZipUtil	instance	= new ZipUtil();
	
	public static ZipUtil getInstance() {
		return instance;
	}
	
	/**
	 * 构造方法。默认缓冲区大小为512字节。
	 */
	private ZipUtil() {
		this(512);
	}
	
	/**
	 * 构造方法。
	 * 
	 * @param bufSize
	 *            指定压缩或解压时的缓冲区大小
	 */
	@SuppressWarnings("static-access")
	private ZipUtil(int bufSize) {
		this.bufSize = bufSize;
		this.buf = new byte[this.bufSize];
		deleteAbsoluteParent = null;
	}
	
	/**
	 * 压缩文件夹内的所有文件和目录。包括父级目录
	 * 
	 * @param zipDirectory
	 *            需要压缩的文件夹名
	 */
	public void doZipWithDirectory(String zipDirectory, String zipToPath) {
		File zipDir = new File(zipDirectory);
		doZip(new File[] { zipDir }, zipToPath);
	}
	
	public void doZipForSingleFile(String zipFile, String zipToPath) {
		doZip(new File[] { new File(zipFile) }, zipToPath);
	}
	
	/**
	 * 压缩文件夹内的所有文件和目录。包括父级目录
	 * 
	 * @param zipDirectory
	 *            需要压缩的文件夹名
	 */
	public void doZip(String zipDirectory, String zipToPath) {
		File zipDir = new File(zipDirectory);
		if (zipDir.isDirectory()) {
			doZip(zipDir.listFiles(), zipToPath);
		} else if (zipDir.isFile()) {
			File[] files = new File[1];
			files[0] = zipDir;
			doZip(files, zipToPath);
		}
	}
	
	/**
	 * 压缩多个文件或目录。可以指定多个单独的文件或目录。而 doZipWithDirectory(String zipDirectory)则直接压缩整个文件夹。
	 * 
	 * @param files
	 *            要压缩的文件或目录组成的File数组。
	 * @param zipFileName
	 *            压缩后的zip文件名，如果后缀不是".zip"， 自动添加后缀".zip"。
	 */
	public void doZip(File[] files, String zipToPath) {
		// 未指定压缩文件名，默认为"ZipFile"
		if (zipToPath == null || zipToPath.equals(""))
			zipToPath = "ZipFile";
		
		// 添加".zip"后缀
		if (!zipToPath.endsWith(".zip"))
			zipToPath += ".zip";
		
		try {
			this.zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipToPath)));
			compressFiles(files, this.zipOut, true);
			this.zipOut.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * 压缩文件和目录。由doZip()调用
	 * 
	 * @param files
	 *            要压缩的文件
	 * @param zipOut
	 *            zip输出流
	 * @param isAbsolute
	 *            是否是要去除的绝对路径的根路径。因为compressFiles() 会递归地被调用，所以只用deleteAbsoluteParent不行。必须用isAbsolute来指明 compressFiles()是第一次调用，而不是后续的递归调用。即如果要压缩的路径是
	 *            E:\temp，那么第一次调用时，isAbsolute=true，则deleteAbsoluteParent会记录 要删除的路径就是E:\ ，当压缩子目录E:\temp\folder时，isAbsolute=false， 再递归调用compressFiles()时，deleteAbsoluteParent仍然是E:\
	 *            。从而保证了 将E:\temp及其子目录均正确地转化为相对目录。这样压缩才不会出错。不然绝对 路径E:\也会被写入到压缩文件中去。
	 */
	private void compressFiles(File[] files, ZipOutputStream zipOut, boolean isAbsolute) throws IOException {
		
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file == null)
				continue; // 空的文件对象
				
			// 删除绝对父路径
			
			if (file.isAbsolute()) {
				if (isAbsolute) {
					deleteAbsoluteParent = file.getParentFile().getAbsolutePath();
					deleteAbsoluteParent = appendSeparator(deleteAbsoluteParent);
				}
			} else
				deleteAbsoluteParent = "";
			
			if (file.isDirectory()) {// 是目录
				
				compressFolder(file, zipOut);
			} else {// 是文件
				
				compressFile(file, zipOut);
			}
		}
	}
	
	/**
	 * 压缩文件或空目录。由compressFiles()调用。
	 * 
	 * @param file
	 *            需要压缩的文件
	 * @param zipOut
	 *            zip输出流
	 */
	public void compressFile(File file, ZipOutputStream zipOut) throws IOException {
		
		String fileName = file.toString();
		
		/* 去除绝对父路径。 */
		if (file.isAbsolute())
			fileName = fileName.substring(deleteAbsoluteParent.length());
		if (fileName == null || fileName == "")
			return;
		
		/*
		 * 因为是空目录，所以要在结尾加一个"/"。 不然就会被当作是空文件。 ZipEntry的isDirectory()方法中,目录以"/"结尾. org.apache.tools.zip.ZipEntry : public boolean isDirectory() { return getName().endsWith("/"); }
		 */
		if (file.isDirectory())
			fileName = fileName + "/";// 此处不能用"\\"
			
		zipOut.putNextEntry(new ZipEntry(fileName));
		
		// 如果是文件则需读;如果是空目录则无需读，直接转到zipOut.closeEntry()。
		
		if (file.isFile()) {
			FileInputStream fileIn = new FileInputStream(file);
			while ((this.readedBytes = fileIn.read(this.buf)) > 0) {
				zipOut.write(this.buf, 0, this.readedBytes);
			}
			fileIn.close();
		}
		
		zipOut.closeEntry();
	}
	
	/**
	 * 递归完成目录文件读取。由compressFiles()调用。
	 * 
	 * @param dir
	 *            需要处理的文件对象
	 * @param zipOut
	 *            zip输出流
	 */
	private void compressFolder(File dir, ZipOutputStream zipOut) throws IOException {
		
		File[] files = dir.listFiles();
		
		if (files.length == 0)// 如果目录为空，则单独压缩空目录。
			
			compressFile(dir, zipOut);
		else
			// 如果目录不为空,则分别处理目录和文件.
			compressFiles(files, zipOut, false);
	}
	
	/**
	 * 解压缩zip包
	 * 
	 * @param zipFilePath
	 *            zip文件路径
	 * @param targetPath
	 *            解压缩到的位置，如果为null或空字符串则默认解压缩到跟zip包同目录跟zip包同名的文件夹下
	 * @return 解压后解压文件夹路径
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static String unZip(String zipFilePath, String targetPath) throws IOException {
		
		OutputStream os = null;
		InputStream is = null;
		ZipFile zipFile = null;
		String resultPath = "";
		try {
			
			zipFile = new ZipFile(zipFilePath);
			String directoryPath = "";
			
			if (null == targetPath || "".equals(targetPath)) {
				// 以zip包名命名文件夹名
				directoryPath = zipFilePath.substring(0, zipFilePath.lastIndexOf("."));
			} else {
				directoryPath = targetPath;
			}
			
			// zip引用
			Enumeration entryEnum = zipFile.entries();
			if (null != entryEnum) {
				ZipEntry zipEntry = null;
				// zip包内是否有下一个对象
				while (entryEnum.hasMoreElements()) {
					// zip包内一个对象
					directoryPath = targetPath;
					zipEntry = (ZipEntry) entryEnum.nextElement();
					// 如果为文件夹
					if (zipEntry.isDirectory()) {
						directoryPath = directoryPath + File.separator + zipEntry.getName();
						continue;
					}
					if (zipEntry.getSize() > 0) {
						// 指定文件
						File targetFile = ZipUtil.buildFile(directoryPath + File.separator + zipEntry.getName());
						
						os = new BufferedOutputStream(new FileOutputStream(targetFile));
						// 读取zip
						is = zipFile.getInputStream(zipEntry);
						byte[] buffer = new byte[4096];
						int readLen = 0;
						while ((readLen = is.read(buffer, 0, 4096)) >= 0) {
							os.write(buffer, 0, readLen);
						}
						os.flush();
						os.close();
					} else {
						// 空目录
						ZipUtil.buildFile(directoryPath + File.separator + zipEntry.getName());
					}
				}
			}
			
			resultPath = targetPath + "/";
			return resultPath;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		} finally {
			if (null != zipFile) {
				zipFile.close();
				zipFile = null;
			}
			if (null != is) {
				is.close();
			}
			if (null != os) {
				os.close();
			}
		}
	}
	
	/**
	 * 如果文件所在路径不存在则生成路径
	 * 
	 * @param fileName
	 *            文件名 带路径
	 * @param isDirectory
	 *            是否为路径
	 * @return
	 * @throws IOException
	 */
	public static File buildFile(String fileName) throws IOException {
		File target = new File(fileName);
		if (target.isDirectory()) {
			target.mkdirs();
		} else {
			if (!target.getParentFile().exists()) {
				target.getParentFile().mkdirs();
			}
			target = new File(target.getAbsolutePath());
			target.createNewFile();
		}
		return target;
	}
	
	/**
	 * 给文件路径或目录结尾添加File.separator
	 * 
	 * @param fileName
	 *            需要添加路径分割符的路径
	 * @return 如果路径已经有分割符，则原样返回，否则添加分割符后返回。
	 */
	private String appendSeparator(String path) {
		if (!path.endsWith(File.separator))
			path += File.separator;
		return path;
	}
	
	/**
	 * 解压指定zip文件。
	 * 
	 * @param unZipFile
	 *            需要解压的zip文件对象
	 * @throws IOException
	 */
	public String unZip(File unZipFile, String toUnZipPath) throws IOException {
		return unZip(unZipFile.toString(), toUnZipPath);
	}
	
	/**
	 * 设置压缩或解压时缓冲区大小。
	 * 
	 * @param bufSize
	 *            缓冲区大小
	 */
	@SuppressWarnings("static-access")
	public void setBufSize(int bufSize) {
		this.bufSize = bufSize;
	}
	
	// public static void main(String[] args) throws Exception {
	// ZipUtil zip = ZipUtil.getInstance() ;
	
	// doZipExample
	// zip.doZip("E:\\test1", "E:\\test1.zip") ;
	// doZipWithDirectory
	// zip.doZipWithDirectory("F:\\m3read文档", "F:\\m3read文档.zip") ;
	// doUnZipExample
	// zip.unZip("F:\\m3read文档.zip", "F:\\m3read文档1") ;
	// ZipUtil.getInstance().doZip("\\\\192.168.10.24\\content\\temp\\ebook_tmp\\txt_tmp\\4004644m271E","\\\\192.168.10.24\\content\\temp\\ebook_tmp\\txt_tmp\\4004644m271E");
	// }
	public static void main(String[] args) {
		// String a="dddasdafdsafdsa";
		// boolean b = a.contains("dd");
		// System.out.println(b);
		try {
			System.out.println("解压后文件夹路径===" + ZipUtil.unZip("D:\\xx\\xx.zip", "D:\\xx"));
			
			// File file = new File("G:\\1\\数据包.zip");
			// System.out.println(file.getName());
			// System.out.println(file.delete());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
