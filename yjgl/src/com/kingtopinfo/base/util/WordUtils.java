package com.kingtopinfo.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class WordUtils {
	// 配置信息,代码本身写的还是很可读的,就不过多注解了
	private static Configuration	configuration	= null;
	// 这里注意的是利用WordUtils的类加载器动态获得模板文件的位置
	private static final String		templateFolder	= WordUtils.class.getClassLoader().getResource("/com/kingtopinfo/template/").getPath();
	static {
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		try {
			configuration.setDirectoryForTemplateLoading(new File(templateFolder));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private WordUtils() {
		throw new AssertionError();
	}
	
	public static void exportMillCertificateWord(HttpServletRequest request, HttpServletResponse response, Map map, String title, String ftlFile) throws IOException {
		String fileName = title + ".doc";
		String diskPath = FilePathUtil.getFilePath("disk_Path") + FilePathUtil.getFilePath("temp_Path");
		String filePath = diskPath + File.separator + fileName;
		FileUtil.creatPath(diskPath);
		Template freemarkerTemplate = configuration.getTemplate(ftlFile);
		try {
			File f = new File(filePath);
			Template t = freemarkerTemplate;
			Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
			t.process(map, w);
			w.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
}
