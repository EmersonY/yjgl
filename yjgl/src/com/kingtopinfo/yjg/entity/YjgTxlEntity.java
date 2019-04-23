package com.kingtopinfo.yjg.entity;
	
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
	
/**
 * @ClassName entity.YjgSjdjEntity
 * @Description YJG_SJDJ表映射实体类
 * @author cyf
 * @date 2017-08-24 10:56:58
 * @version 1.0
 * @remark create by generator
 */
public class YjgTxlEntity  implements Serializable {
	private static final long	serialVersionUID	= 3542267052335795357L;
	private String bwlid;
	private String bwlnr;
	private String bwljb;
	private String bwlzt;
	private String bwlbt;
	private String bwlwz;

	private Date bwldate;
	private String bwldate1;
	private String  txlid;
	private String baseuserid;
	private String txlxm;
	private String txldh;
	private String txlbz;
	
	private int txlzt;
	private String txlemail;
	private String txlgs;
	private String txljob;
	private String txlxzqh;

	private String imgid;
	private String imgpath;
	
	private String bwltimeStart;
	private String bwltimeEnd;
	
	private int year;
	private int month;
	private int day;
	
	 
	
	public String getBwldate1() {
		return bwldate1;
	}
	public void setBwldate1(String bwldate1) {
		this.bwldate1 = bwldate1;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getBwltimeStart() {
		return bwltimeStart;
	}
	public void setBwltimeStart(String bwltimeStart) {
		this.bwltimeStart = bwltimeStart;
	}
	public String getBwltimeEnd() {
		return bwltimeEnd;
	}
	public void setBwltimeEnd(String bwltimeEnd) {
		this.bwltimeEnd = bwltimeEnd;
	}

	public String getImgid() {
		return imgid;
	}
	public void setImgid(String imgid) {
		this.imgid = imgid;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public String getBaseuserid() {
		return baseuserid;
	}
	public void setBaseuserid(String baseuserid) {
		this.baseuserid = baseuserid;
	}
	public String getTxlxm() {
		return txlxm;
	}
	public void setTxlxm(String txlxm) {
		this.txlxm = txlxm;
	}
	public String getTxldh() {
		return txldh;
	}
	public void setTxldh(String txldh) {
		this.txldh = txldh;
	}
	public String getTxlbz() {
		return txlbz;
	}
	public void setTxlbz(String txlbz) {
		this.txlbz = txlbz;
	}
	public int getTxlzt() {
		return txlzt;
	}
	public void setTxlzt(int txlzt) {
		this.txlzt = txlzt;
	}
	public String getTxlemail() {
		return txlemail;
	}
	public void setTxlemail(String txlemail) {
		this.txlemail = txlemail;
	}
	public String getTxlgs() {
		return txlgs;
	}
	public void setTxlgs(String txlgs) {
		this.txlgs = txlgs;
	}
	public String getTxljob() {
		return txljob;
	}
	public void setTxljob(String txljob) {
		this.txljob = txljob;
	}
	public String getTxlxzqh() {
		return txlxzqh;
	}
	public void setTxlxzqh(String txlxzqh) {
		this.txlxzqh = txlxzqh;
	}
	public String getTxlid() {
		return txlid;
	}
	public void setTxlid(String txlid) {
		this.txlid = txlid;
	}
	public String getBwlid() {
		return bwlid;
	}
	public void setBwlid(String bwlid) {
		this.bwlid = bwlid;
	}
	public String getBwlnr() {
		return bwlnr;
	}
	public void setBwlnr(String bwlnr) {
		this.bwlnr = bwlnr;
	}
	public String getBwljb() {
		return bwljb;
	}
	public void setBwljb(String bwljb) {
		this.bwljb = bwljb;
	}
	public String getBwlwz() {
		return bwlwz;
	}
	public void setBwlwz(String bwlwz) {
		this.bwlwz = bwlwz;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getBwldate() {
		return bwldate;
	}
	public void setBwldate(Date bwldate) {
		this.bwldate = bwldate;
	}
	public String getBwlbt() {
		return bwlbt;
	}
	public void setBwlbt(String bwlbt) {
		this.bwlbt = bwlbt;
	}
	public String getBwlzt() {
		return bwlzt;
	}
	public void setBwlzt(String bwlzt) {
		this.bwlzt = bwlzt;
	}

}