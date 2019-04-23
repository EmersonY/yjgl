package com.kingtopinfo.ywtj.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.base.entity.TblBaseTypeEntity;
import com.kingtopinfo.base.mapper.TblBaseTypeMapper;
import com.kingtopinfo.base.util.ArithUtil;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.base.util.TimeUtils;
import com.kingtopinfo.echarts.Option;
import com.kingtopinfo.echarts.axis.CategoryAxis;
import com.kingtopinfo.echarts.series.Bar;
import com.kingtopinfo.yjg.entity.YjgBaseSjdjEntity;
import com.kingtopinfo.yjg.entity.YjgFyjsjdjEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;
import com.kingtopinfo.yjg.entity.YjgYwtjEntity;
import com.kingtopinfo.yjg.mapper.YjgFyjsjdjMapper;
import com.kingtopinfo.yjg.mapper.YjgSjdjMapper;
import com.kingtopinfo.ywtj.vo.YjlxtjVo;

/**
 * @Package com.kingtopinfo.ywtj.service
 * @Description: TODO
 * @author cyf
 * @date 2017年10月25日 上午10:49:52
 */
@Service
@Transactional
public class YjgYwtjService {
	@Autowired
	private TblBaseTypeMapper	tblBaseTypeMapper;
	@Autowired
	private YjgSjdjMapper		yjgSjdjMapper;
	@Autowired
	private YjgFyjsjdjMapper	yjgFyjsjdjMapper;
	
	public YjgYwtjEntity loadYwlyBar(YjgYwtjEntity ywtj) {
		Option option = new Option();
		
		List<TblBaseTypeEntity> baseE = tblBaseTypeMapper.selectByCode(ywtj.getCode());
		if (baseE.isEmpty()) {
			return null;
		}
		
		// 设置x轴
		CategoryAxis cate = new CategoryAxis();
		for (int i = 0; i < baseE.size(); i++) {
			cate.data(baseE.get(i).getName());
		}
		option.xAxis(cate);
		
		Bar bar = new Bar();
		int total = 0;
		
		YjgSjdjEntity e = new YjgSjdjEntity();
		e.setTimeStart(ywtj.getTimeStart());
		e.setTimeEnd(ywtj.getTimeEnd());
		
		for (int i = 0; i < baseE.size(); i++) {
			e.setXxly(Integer.valueOf(baseE.get(i).getValue()));
			int count = yjgSjdjMapper.getCountByXxly(e);
			total += count;
			bar.data(count);
		}
		
		if (total <= 0) {
			return null;
		}
		option.series(bar);
		ywtj.setOption(option);
		return ywtj;
	}
	
	public YjgYwtjEntity loadJgtjGrid(YjgYwtjEntity ywtj) {
		YjgYwtjEntity ywtjR = new YjgYwtjEntity();
		List<YjgBaseSjdjEntity> list = yjgSjdjMapper.getCountGroupByJgid(ywtj);
		ywtjR.setSjdjList(list);
		return ywtjR;
	}
	
	public YjgYwtjEntity loadSjlxBar(YjgYwtjEntity ywtj) throws ParseException {
		YjgYwtjEntity ywtjR = new YjgYwtjEntity();
		Option option = new Option();
		// 设置x轴
		List<TblBaseTypeEntity> list = tblBaseTypeMapper.selectByCode("SJXZ");
		if (list.isEmpty()) {
			return null;
		}
		
		CategoryAxis cate = new CategoryAxis();
		for (int i = 0; i < list.size(); i++) {
			cate.data(list.get(i).getName());
		}
		option.xAxis(cate);
		
		List<String> timeList = new ArrayList<String>();
		for (int i = 2; i >= 0; i--) {
			timeList.add(TimeUtils.getPastMonth(i, ywtj.getTimeStartNoDay() == null ? new Date() : ywtj.getTimeStartNoDay()));
		}
		// 设置lengend 过去三个月
		for (int i = 0; i <= 2; i++) {
			option.legend(timeList.get(i));
		}
		
		int total = 0;
		Collections.reverse(timeList);
		for (int j = 2; j >= 0; j--) {
			Date pastMonthDate = TimeUtils.getPastMonthDate(j, ywtj.getTimeStartNoDay() == null ? new Date() : ywtj.getTimeStartNoDay());
			/**
			 * 窨井
			 */
			YjgSjdjEntity yjgSjdjEntity = new YjgSjdjEntity();
			yjgSjdjEntity.setTimeStart(TimeUtils.formatyyyyMMdd(TimeUtils.getMonthFirstDay(pastMonthDate)));
			yjgSjdjEntity.setTimeEnd(TimeUtils.formatyyyyMMdd(TimeUtils.getMonthLastDay(pastMonthDate)));
			/**
			 * 非窨井
			 */
			YjgFyjsjdjEntity yjgFyjsjdjEntity = new YjgFyjsjdjEntity();
			yjgFyjsjdjEntity.setTimeStart(TimeUtils.formatyyyyMMdd(TimeUtils.getMonthFirstDay(pastMonthDate)));
			yjgFyjsjdjEntity.setTimeEnd(TimeUtils.formatyyyyMMdd(TimeUtils.getMonthLastDay(pastMonthDate)));
			
			Bar bar = new Bar(timeList.get(j));
			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					yjgSjdjEntity.setSjlx(1);
					int count = yjgSjdjMapper.getCountBySjlx(yjgSjdjEntity);
					bar.data(count);
					total += count;
				} else {
					yjgFyjsjdjEntity.setSjlx(i + 1);
					int count = yjgFyjsjdjMapper.getCountBySjlx(yjgFyjsjdjEntity);
					bar.data(count);
					total += count;
				}
			}
			option.series(bar);
		}
		
		if (total == 0) {
			return null;
		}
		
		ywtjR.setCs(total); // 共受理了 xx 件
		ywtjR.setOption(option);
		return ywtjR;
	}
	
	public YjgYwtjEntity loadSjlxlyBar(YjgYwtjEntity ywtj) throws ParseException {
		YjgYwtjEntity ywtjR = new YjgYwtjEntity();
		Option option = new Option();
		// 设置x轴
		List<String> list = new ArrayList<String>();
		list.add("110");
		list.add("非110");
		if (list.isEmpty()) {
			return null;
		}
		
		CategoryAxis cate = new CategoryAxis();
		for (int i = 0; i < list.size(); i++) {
			cate.data(list.get(i));
		}
		option.xAxis(cate);
		
		List<String> timeList = new ArrayList<String>();
		for (int i = 2; i >= 0; i--) {
			timeList.add(TimeUtils.getPastMonth(i, ywtj.getTimeStartNoDay() == null ? new Date() : ywtj.getTimeStartNoDay()));
		}
		// 设置lengend 过去三个月
		for (int i = 0; i <= 2; i++) {
			option.legend(timeList.get(i));
		}
		
		int total = 0;
		Collections.reverse(timeList);
		for (int j = 2; j >= 0; j--) {
			Date pastMonthDate = TimeUtils.getPastMonthDate(j, ywtj.getTimeStartNoDay() == null ? new Date() : ywtj.getTimeStartNoDay());
			/**
			 * 窨井
			 */
			YjgSjdjEntity yjgSjdjEntity = new YjgSjdjEntity();
			yjgSjdjEntity.setTimeStart(TimeUtils.formatyyyyMMdd(TimeUtils.getMonthFirstDay(pastMonthDate)));
			yjgSjdjEntity.setTimeEnd(TimeUtils.formatyyyyMMdd(TimeUtils.getMonthLastDay(pastMonthDate)));
			
			Bar bar = new Bar(timeList.get(j));
			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					yjgSjdjEntity.setXxly(3);
					int count = yjgSjdjMapper.getCountByXxly(yjgSjdjEntity);
					bar.data(count);
					total += count;
				} else {
					yjgSjdjEntity.setXxly(3);
					int count = yjgSjdjMapper.getCountByExceptXxly(yjgSjdjEntity);
					bar.data(count);
					total += count;
				}
			}
			option.series(bar);
		}
		
		if (total == 0) {
			return null;
		}
		
		ywtjR.setCs(total); // 共受理了 xx 件
		ywtjR.setOption(option);
		return ywtjR;
	}
	
	public YjgYwtjEntity loadDltjGrid(YjgYwtjEntity ywtj) {
		YjgYwtjEntity ywtjR = new YjgYwtjEntity();
		List<YjgBaseSjdjEntity> list = yjgSjdjMapper.getCountGroupByDlid(ywtj);
		ywtjR.setSjdjList(list);
		return ywtjR;
	}
	
	public YjgYwtjEntity loadJglxGrid(YjgYwtjEntity ywtj) {
		YjgYwtjEntity ywtjR = new YjgYwtjEntity();
		List<YjgBaseSjdjEntity> list = yjgSjdjMapper.getCountGroupByJglx(ywtj);
		List<YjlxtjVo> voList = new ArrayList<YjlxtjVo>();
		for (YjgBaseSjdjEntity yjgBaseSjdjEntity : list) {
			YjlxtjVo vo = new YjlxtjVo();
			vo.setJglx(yjgBaseSjdjEntity.getJglx());
			vo.setJjsl(yjgBaseSjdjEntity.getCount());
			// 计算闭合率
			int bhCount = yjgSjdjMapper.getBhCountByGglx(yjgBaseSjdjEntity);
			double bhDiv = ArithUtil.div(String.valueOf(bhCount), yjgBaseSjdjEntity.getCount(), 2);
			vo.setBhPercentage(String.valueOf(ArithUtil.mul(bhDiv, 100)) + "%");
			vo.setWbhCount(String.valueOf((int) ArithUtil.sub(Double.valueOf(yjgBaseSjdjEntity.getCount()), bhCount, 0)));
			vo.setBhCount(String.valueOf(bhCount));
			// 计算处理率
			int clCount = yjgSjdjMapper.getClCountByGglx(yjgBaseSjdjEntity);
			double clDiv = ArithUtil.div(String.valueOf(clCount), yjgBaseSjdjEntity.getCount(), 2);
			vo.setClPercentage(String.valueOf(ArithUtil.mul(clDiv, 100)) + "%");
			vo.setClCount(String.valueOf(clCount));
			vo.setWclCount(String.valueOf((int) ArithUtil.sub(Double.valueOf(yjgBaseSjdjEntity.getCount()), clCount, 0)));
			voList.add(vo);
		}
		ywtjR.setList(voList);
		return ywtjR;
	}
	
	public List<YjgSjdjEntity> selectYwtjPagination(YjgSjdjEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgSjdjMapper.selectYwtjPagination(e,rowBounds);
	}
	
	public Integer getJgtjDetailCount(YjgSjdjEntity e) {
		return yjgSjdjMapper.getJgtjDetailCount(e);
	}
	
}