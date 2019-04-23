package com.kingtopinfo.yjg.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.base.entity.TblBaseHolidaysEntity;
import com.kingtopinfo.base.mapper.TblBaseHolidaysMapper;
import com.kingtopinfo.base.mapper.TblBaseTypeMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.base.util.TimeUtils;
import com.kingtopinfo.yjg.entity.YjgBwlEntity;
import com.kingtopinfo.yjg.mapper.YjgBwlMapper;

/**
 * @ClassName service.YjgBwlService
 * @Description YJG_BWL表服务类
 * @author cyf
 * @date 2017-12-04 14:04:13
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class YjgBwlService {
	
	@Autowired
	private YjgBwlMapper yjgBwlMapper;
	@Autowired
	private TblBaseHolidaysMapper	tblBaseHolidaysMapper;
	@Autowired
	private TblBaseTypeMapper		tblBaseTypeMapper;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e YjgBwlEntity实体
	 * @return 条数
	 * @author cyf
	 * @param startDate 
	 * @param lastDay 
	 * @date 2017-12-04 14:04:13
	 */
	public int getCount(YjgBwlEntity e, Date startDate, Date lastDay) {
		return yjgBwlMapper.getBwlCount(e.getBwlwz(), e.getBwlnr(), startDate, lastDay, UserInfoUtil.getBaseuserid());
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e YjgBwlEntity实体
	 * @param rowBounds 分页实体
	 * @return YjgBwlEntity集合
	 * @author cyf
	 * @param startDate 
	 * @param lastDay 
	 * @date 2017-12-04 14:04:13
	 */
	public List<YjgBwlEntity> selectPagination(YjgBwlEntity e, Integer rows, Integer page, Date startDate, Date lastDay) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgBwlMapper.selectBwlPagination(e.getBwlwz(), e.getBwlnr(), startDate, lastDay, UserInfoUtil.getBaseuserid(), rowBounds);
	}
	
	/**
	 * @Description 添加YjgBwlEntity信息
	 * @param e YjgBwlEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-12-04 14:04:13
	 */	
	public int insert(YjgBwlEntity e) {
		e.setBwlid(IDUtil.getId());
		e.setBaseuserid(UserInfoUtil.getBaseuserid());
		e.setBwlzt("0");
		return yjgBwlMapper.insert(e);
	}
	
	/**
	 * @Description 修改YjgBwlEntity信息
	 * @param e YjgBwlEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-12-04 14:04:13
	 */	
	public int update(YjgBwlEntity e) {
		YjgBwlEntity yjgBwlEntity = yjgBwlMapper.getByPkey(e.getBwlid());
		yjgBwlEntity.setBwlbt(e.getBwlbt());
		yjgBwlEntity.setBwljb(e.getBwljb());
		yjgBwlEntity.setBwlnr(e.getBwlnr());
		yjgBwlEntity.setBwlwz(e.getBwlwz());
		yjgBwlEntity.setBwldate(e.getBwldate());
		yjgBwlEntity.setBwlzt(e.getBwlzt());
		return yjgBwlMapper.update(yjgBwlEntity);
	}
	
	/**
	 * @Description 按bwlid删除YjgBwlEntity信息
	 * @param bwlid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-12-04 14:04:13
	 */	
	public int delete(String bwlid) {
		return yjgBwlMapper.delete(bwlid);
	}
	
	/**
	 * @Description 按bwlid集合批量删除YjgBwlEntity信息
	 * @param idArray bwlid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-12-04 14:04:13
	 */	
	public int deleteBatch(String[] idArray){
		int row = 0;
		for(String id : idArray){
			row += delete(id);
		}
		return row;
	}
	
	public List<Map<String, Object>> selectByDate(YjgBwlEntity e, Date startDate) {
		boolean holEmpty = false;
		String holDateStr = "";
		int hosSize = 0;
		
		Date endDate = TimeUtils.add(startDate, TimeUtils.DAY, 35);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<YjgBwlEntity> bwlList = yjgBwlMapper.selectBwl(startDate, endDate, UserInfoUtil.getBaseuserid());
		List<TblBaseHolidaysEntity> holList = tblBaseHolidaysMapper.selectByStartAndEnd(startDate, endDate);
		// 法定
		if (holList == null || holList.isEmpty())
			holEmpty = true;
		else {
			holDateStr = TimeUtils.format(holList.get(0).getHolidaydate(), "yyyy-MM-dd");
			hosSize = holList.size();
		}
		int hosIndex = 0;
		for (int i = 0; i < 35; i++) {
			List<YjgBwlEntity> dateList = new ArrayList<YjgBwlEntity>();
			Map<String, Object> map = new HashMap<String, Object>();
			Date eachDate = TimeUtils.add(startDate, TimeUtils.DAY, i);
			String forDateStr = TimeUtils.format(eachDate, "yyyy-MM-dd");
			String bt = forDateStr; // 标题
			int hasholiday = 0;
			int isWeekend = 0;
			int isCurrentDate = 0;
			int isSearchMonth = 0;
			
			if (eachDate.getDay() == 0 || eachDate.getDay() == 6)
				isWeekend = 1;
			if (forDateStr.equals(TimeUtils.format(new Date(), "yyyy-MM-dd")))
				isCurrentDate = 1;
			if (e.getSearchMonth() == eachDate.getMonth() + 1)
				isSearchMonth = 1;
			// 法定节假日
			if (!holEmpty) {
				if (hosIndex < hosSize) {
					if (forDateStr.equals(holDateStr)) {
						TblBaseHolidaysEntity holiday = holList.get(hosIndex);
						if ("1".equals(holiday.getHolidaytype()))
							bt = bt + "（" + "休";
						else if ("2".equals(holiday.getHolidaytype()))
							bt = bt + "（" + "班";
						if (holiday.getIsholiday() == 1) {
							bt += "/" + tblBaseTypeMapper.selectByCodeAndValue("JJR", holiday.getHolidayname());
						}
						bt += "）";
						hasholiday = 1;
						hosIndex++;
						if (hosIndex < hosSize) {
							holDateStr = TimeUtils.format(holList.get(hosIndex).getHolidaydate(), "yyyy-MM-dd");
						}
					}
				}
			}
			for (YjgBwlEntity YjgBwlEntity : bwlList) {
				YjgBwlEntity.setBwlbt(YjgBwlEntity.getBwlbt() == null ? "" : YjgBwlEntity.getBwlbt());
				String bwldateStr = TimeUtils.format(YjgBwlEntity.getBwldate(), "yyyy-MM-dd");
				YjgBwlEntity.setBwltimeStart(TimeUtils.format(YjgBwlEntity.getBwldate(), "HH:mm:ss"));
				if (bwldateStr.equals(forDateStr)) {
					dateList.add(YjgBwlEntity);
				}
			}
			map.put("isWeekend", isWeekend);
			map.put("isCurrentDate", isCurrentDate);
			map.put("isSearchMonth", isSearchMonth);
			map.put("bt", bt);
			map.put("hasholiday", hasholiday);
			map.put("dateList", dateList);
			result.add(map);
		}
		return result;
	}

	/**
	* @Package com.kingtopinfo.yjg.service  
	* @Description: 查询备忘录
	* @author cyf    
	 * @param startDate 
	 * @throws ParseException 
	* @date 2018年1月15日 上午9:16:07
	 */
	public Map<String, Object> remindBwl() throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		java.util.Date creatDate = new java.util.Date();
		java.sql.Date startDate = new java.sql.Date(creatDate.getTime());
		Date endDate = TimeUtils.add(startDate, TimeUtils.DAY, 1);
		endDate = new java.sql.Date(endDate.getTime());
		int count = yjgBwlMapper.remindBwl(startDate, endDate, UserInfoUtil.getBaseuserid());
		map.put("count", count);
		return map;
	}
	
	/**
	* @Package com.kingtopinfo.yjg.service  
	* @Description: 已读
	* @author cyf    
	* @date 2018年1月15日 下午4:22:29
	 */
	public int read(YjgBwlEntity e) {
		YjgBwlEntity yjgBwlEntity = yjgBwlMapper.getByPkey(e.getBwlid());
		yjgBwlEntity.setBwlzt("1");
		return yjgBwlMapper.update(yjgBwlEntity);
	}
	
}