package com.kingtopinfo.base.util;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Md5 {
	
	public  static String getMd5Time(String pw,String un) {
		String res="";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date =null;
		try {
			date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
		    long ts = date.getTime();
	        res = String.valueOf(ts);
	   
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = md5Digest(un+pw+res);
	     return str;
		
	}

	public static String md5Digest(String src){  
        // 定义数字签名方法, 可用：MD5, SHA-1  
        MessageDigest md;
        byte[] b = null;
		try {
			md = MessageDigest.getInstance("MD5");
			b = md.digest(src.getBytes("utf-8"));  
		} catch (Exception e) {
			e.printStackTrace();
		}  

        return byte2HexStr(b).toLowerCase();  
   }
   

    private static String byte2HexStr(byte[] b) {  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < b.length; i++) {  
            String s = Integer.toHexString(b[i] & 0xFF);  
            if (s.length() == 1) {  
                 sb.append("0");  
            }  
                  
            sb.append(s.toUpperCase());  
         }  
              
         return sb.toString();  
    }
	
}
