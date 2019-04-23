package com.kingtopinfo.base.util;

import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import oracle.sql.BLOB;

public class BlobUtil {
	/**
	 * blob对象转成二进制。
	 * @param content
	 * @return
	 */
	public static byte[] objToByte(Object content){
		System.out.println("---------------------------");
		ByteArrayOutputStream bos=null;
		ObjectOutputStream oos=null;
		try{
			bos = new ByteArrayOutputStream();
			oos =new ObjectOutputStream(bos);
			oos.writeObject(content);
			oos.flush();
			oos.close();
			byte[]  result = bos.toByteArray();
			bos.close();
			return result;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			if(bos!=null)
				bos=null;
			if(oos!=null)
				oos=null;
		}
	}
	/**
	 * blob转成String对象。	
	 * @param content
	 * @return
	 */
	public static String blobToString(Object content){
		BLOB blobContent =(BLOB) content;
		ObjectInputStream ois=null;
		try {
			ois =new ObjectInputStream(blobContent.getBinaryStream());
			Object object = ois.readObject();
			ois.close();
			System.out.println("-----------------------------");
			if (object instanceof String) {
				return (String) object;
			}else {
				return ((String[])object)[0];
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}finally{
			if(ois!=null)
				ois=null;
		}
	}
}

