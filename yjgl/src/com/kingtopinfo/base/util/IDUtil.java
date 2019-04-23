package com.kingtopinfo.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class IDUtil {

	public static String getId(){
		
		String  str = UUID.randomUUID().toString();
		return str.substring(0,8)+str.substring(9,13)+str.substring(14,18)+str.substring(19,23)+str.substring(24,36);
	}
	
	/**
	 * 产生一个唯一的字符串年月日时分秒毫秒+5位随机数
	 * @return
	 */
	public static String getUnique() {
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String str = sf.format(new Date()) + nextInt(10000,99999);
		return str;
	}
	
	private static int nextInt(final int min, final int max){
		
		Random rand= new Random();
		int tmp = Math.abs(rand.nextInt());
		return tmp % (max - min + 1) + min;
	}
}
