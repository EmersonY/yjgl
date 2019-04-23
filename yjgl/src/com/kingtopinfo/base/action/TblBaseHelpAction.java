package com.kingtopinfo.base.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@RequestMapping("/TblBaseHelpAction")
public class TblBaseHelpAction extends ActionSupport {
	private static final long	serialVersionUID	= 2005759873496837827L;
	
	@RequestMapping(value = "/down", method = RequestMethod.GET)
	@ResponseBody
	public void down(HttpServletRequest request, HttpServletResponse response, String filepath) {
		try {
			// path是指欲下载的文件的路径。
			String dstPath = request.getSession().getServletContext().getRealPath("/lodop");
			String filePath = dstPath + "\\" + filepath;
			File file = new File(filePath);
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			System.out.println("dd"+response);
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(filepath.getBytes("gb2312"), "ISO8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close(); 
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}