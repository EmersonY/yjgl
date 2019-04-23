package com.kingtopinfo.video;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class VideoThumbTaker {
	protected String ffmpegApp;
	
	public VideoThumbTaker(String ffmpegApp) {
		this.ffmpegApp = ffmpegApp;
	}
	
	@SuppressWarnings("unused")
	/****
	 * 获取指定时间内的图片
	 * 
	 * @param videoFilename:视频路径
	 * @param thumbFilename:图片保存路径
	 * @param width:图片长
	 * @param height:图片宽
	 * @param hour:指定时
	 * @param min:指定分
	 * @param sec:指定秒
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void getThumb(String videoFilename, String thumbFilename, int width, int height, int hour, int min, float sec) throws IOException, InterruptedException {
		ProcessBuilder processBuilder = new ProcessBuilder(ffmpegApp, "-y", "-i", videoFilename, "-vframes", "1", "-ss", hour + ":" + min + ":" + sec, "-f", "mjpeg", "-s",
				width + "*" + height, "-an", thumbFilename);
		
		Process process = processBuilder.start();
		
		InputStream stderr = process.getErrorStream();
		InputStreamReader isr = new InputStreamReader(stderr);
		BufferedReader br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null)
			;
		process.waitFor();
		
		if (br != null)
			br.close();
		if (isr != null)
			isr.close();
		if (stderr != null)
			stderr.close();
	}
	
	// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
	public void processMp4(String oldfilepath, String ffmpegPath, String newfilepath) throws IOException, InterruptedException {
		
		// 文件命名
		List<String> commend = new ArrayList<String>();
		commend.add(ffmpegPath);
		commend.add("-i");
		commend.add(oldfilepath);
		commend.add("-ab");
		commend.add("56");
		commend.add("-ar");
		commend.add("22050");
		commend.add("-qscale");
		commend.add("8");
		commend.add("-r");
		commend.add("15");
		commend.add("-s");
		commend.add("600x500");
		commend.add(newfilepath);
		
		ProcessBuilder builder = new ProcessBuilder(commend);
		Process process = builder.start();
		InputStream stderr = process.getErrorStream();
		InputStreamReader isr = new InputStreamReader(stderr);
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		while ((line = br.readLine()) != null)
			System.out.println(line);
		process.destroy();
			
	}
	
	public static void main(String[] args) {
		VideoThumbTaker videoThumbTaker = new VideoThumbTaker("D:\\ffmpeg\\bin\\ffmpeg.exe");
		try {
//			videoThumbTaker.getThumb("f:/sun/Diana_Vickers_-_The_Boy_Who_Murdered_Love.mkv", "C:\\thumbTest.png", 800, 600, 0, 0, 9);
//			System.out.println("over");
			// videoThumbTaker.processMp4("C:\\Users\\john\\Desktop\\sp\\xxfire.avi");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}