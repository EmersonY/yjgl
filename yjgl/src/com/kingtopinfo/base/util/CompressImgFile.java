package com.kingtopinfo.base.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CompressImgFile {
	
	public static void reduceImg(File file, HttpServletRequest request) throws Exception {
		FileOutputStream out = null;
		int widthdist = 1024; 
		int heightdist = 1024;
		try {
			String sjcjImgPath = FilePathUtil.getFilePath("sjwh_Img_Path");
			String dstPath = FilePathUtil.getFilePath("disk_Path") + sjcjImgPath + "/";
			File dirfile = new File(dstPath);
			if (!dirfile.exists()) {
				dirfile.mkdirs();
			}
			Image src = null;
			src = javax.imageio.ImageIO.read(file);
			if (src != null) {
				heightdist = resizeFix(widthdist, src.getWidth(null), src.getHeight(null));
				BufferedImage tag = new BufferedImage((int) widthdist, (int) heightdist, BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);
				out = new FileOutputStream(dstPath + file.getName());
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				
			}
		} finally {
			out.close();
		}
		
	}
	
	public static int resizeFix(int width, int w, int h) throws IOException {
		int height = Math.round(width * h / w);
		return height;
	}
	
}
