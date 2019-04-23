package com.kingtopinfo.yjg.service;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.runtime.ProcessInstance;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.activiti.entity.ActTaskEntity;
import com.kingtopinfo.activiti.entity.TblFlowTaskRoleMappingEntity;
import com.kingtopinfo.activiti.service.ActTaskService;
import com.kingtopinfo.activiti.service.ActWorkFlowService;
import com.kingtopinfo.base.entity.TblBaseFileEntity;
import com.kingtopinfo.base.entity.TblBaseSerialnumberEntity;
import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.entity.TblBaseUserExtEntity;
import com.kingtopinfo.base.mapper.TblBaseFileMapper;
import com.kingtopinfo.base.mapper.TblBaseSerialnumberMapper;
import com.kingtopinfo.base.mapper.TblBaseTypeMapper;
import com.kingtopinfo.base.mapper.TblBaseUserExtMapper;
import com.kingtopinfo.base.mapper.TblBaseUserMapper;
import com.kingtopinfo.base.mapper.TblBaseUserRoleMappingMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.AppPush;
import com.kingtopinfo.base.util.FilePathUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.base.util.TimeUtils;
import com.kingtopinfo.base.util.WordUtils;
import com.kingtopinfo.base.util.ZipUtil;
import com.kingtopinfo.yjg.entity.YjgJgSjCxVo;
import com.kingtopinfo.yjg.entity.YjgSjczEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjTimeEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjUserMappingEntity;
import com.kingtopinfo.yjg.entity.YjgSjqqEntity;
import com.kingtopinfo.yjg.entity.YjgXmlcEntity;
import com.kingtopinfo.yjg.mapper.YjgFyjsjdjMapper;
import com.kingtopinfo.yjg.mapper.YjgSjczMapper;
import com.kingtopinfo.yjg.mapper.YjgSjdjMapper;
import com.kingtopinfo.yjg.mapper.YjgSjdjUserMappingMapper;
import com.kingtopinfo.yjg.mapper.YjgSjqqMapper;

/**
 * @ClassName service.YjgSjdjService
 * @Description YJG_SJDJ表服务类
 * @author cyf
 * @date 2017-08-24 10:56:59
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class YjgSjdjService {
	
	@Autowired
	private YjgSjdjMapper					yjgSjdjMapper;
	@Autowired
	private YjgSjqqMapper					yjgSjqqMapper;
	@Autowired
	private YjgFyjsjdjMapper				yjgFyjsjdjMapper;
	@Autowired
	private TblBaseUserExtMapper			tblBaseUserExtMapper;
	@Autowired
	private TblBaseSerialnumberMapper		tblBaseSerialnumberMapper;
	@Autowired
	private ActWorkFlowService				workFlowService;
	@Autowired
	private YjgXmlcService					yjgXmlcService;
	@Autowired
	private ActWorkFlowService				actWorkFlowService;
	@Autowired
	private YjgSjdjUserMappingMapper		yjgSjdjUserMappingMapper;
	@Autowired
	private TblBaseUserRoleMappingMapper	tblBaseUserRoleMappingMapper;
	@Autowired
	private TblBaseFileMapper				tblBaseFileMapper;
	@Autowired
	private TblBaseUserMapper				tblBaseUserMapper;
	@Autowired
	private ActTaskService					actTaskService;
	@Autowired
	private YjgSjczMapper					yjgSjczMapper;
	@Autowired
	private TblBaseTypeMapper				tblBaseTypeMapper;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e
	 *            YjgSjdjEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	public int getCount(YjgSjdjEntity e) {
		return yjgSjdjMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件查询总条数任务
	 * @param e
	 *            YjgSjdjEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	public int getTaskCount(YjgSjdjEntity e) {
		return yjgSjdjMapper.getTaskCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e
	 *            YjgSjdjEntity实体
	 * @param rowBounds
	 *            分页实体
	 * @return YjgSjdjEntity集合
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	public List<YjgSjdjEntity> selectPagination(YjgSjdjEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgSjdjMapper.selectPagination(e, rowBounds);
	}
	
	public List<YjgJgSjCxVo> selectByJgxxAndSjxx(YjgJgSjCxVo vo) {
		return yjgSjdjMapper.selectByJgxxAndSjxx(vo);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e
	 *            YjgSjdjEntity实体
	 * @param rowBounds
	 *            分页实体
	 * @return YjgSjdjEntity集合
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	public List<YjgSjdjEntity> selectYjsjJlPagination(YjgSjdjEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgSjdjMapper.selectYjsjJlPagination(e, rowBounds);
	}
	public YjgSjdjEntity selecttaskid(String sjdjid) {		
		return yjgSjdjMapper.selecttaskid(sjdjid);
	}
	/**
	 * @Description 按条件分页查询任务
	 * @param e
	 *            YjgSjdjEntity实体
	 * @param rowBounds
	 *            分页实体
	 * @return YjgSjdjEntity集合
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	public List<YjgSjdjEntity> selectTaskPagination(YjgSjdjEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgSjdjMapper.selectTaskPagination(e, rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return YjgSjdjEntity集合
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	public List<YjgSjdjEntity> select() {
		return yjgSjdjMapper.select();
	}
	
	/**
	 * @Description 按sjdjid查询YjgSjdjEntity信息
	 * @param sjdjid
	 *            主键sjdjid
	 * @return YjgSjdjEntity实体
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	public YjgSjdjEntity getByPkey(String sjdjid) {
		return yjgSjdjMapper.getByPkey(sjdjid);
	}
	
	/**
	 * @Description 添加YjgSjdjEntity信息
	 * @param e
	 *            YjgSjdjEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @param imageIds
	 * @date 2017-08-24 10:56:59
	 */
	
	public int insert(YjgSjdjEntity e, String imageIds, String vedio, int type) {
		int rows = 0;
		if (type == 1) {
			e.setSjdjid(e.getSjdjid());
			e.setBaseuserid(e.getBaseuserid());
			e.setSbrxm(e.getSbrxm());
			e.setSbrdh(e.getSbrdh());
			String sjdjdh = hqSjdjdh(4, e.getSjdjid(), e.getSjlx(), e.getXzqh());
			e.setSjdjdh(sjdjdh);
		} else {
			String uuid = IDUtil.getId();
			if (!imageIds.equals("")) {
				imageIds = imageIds.substring(0, imageIds.length() - 1);
				String[] split = imageIds.split(",");
				for (String id : split) {
					TblBaseFileEntity tblBaseFileEntity = tblBaseFileMapper.getByPkey(id);
					tblBaseFileEntity.setOperaid(uuid);
					tblBaseFileMapper.update(tblBaseFileEntity);
				}
			}
			if (!vedio.equals("")) {
				TblBaseFileEntity tblBaseFileEntity = tblBaseFileMapper.getByPkey(vedio);
				tblBaseFileEntity.setOperaid(uuid);
				tblBaseFileMapper.update(tblBaseFileEntity);
			}
			String sjdjdh = hqSjdjdh(4, uuid, e.getSjlx(), e.getXzqh());
			e.setSjdjdh(sjdjdh);
			e.setSjdjid(uuid);
			TblBaseUserExtEntity tblBaseUserExtEntity = tblBaseUserExtMapper.getByUserId(UserInfoUtil.getBaseuserid());
			if (tblBaseUserExtEntity != null) {
				e.setSbrdh(tblBaseUserExtEntity.getTel());
			}
			e.setBaseuserid(UserInfoUtil.getBaseuserid());
			e.setSbrxm(UserInfoUtil.getUserName());
			
		}
		e.setSqzt(0);
		e.setScsj(new Date());
		e.setIsdel(1);
		e.setUpdatetime(new Date());
		e.setIsline("0");
		rows += yjgSjdjMapper.insert(e);
		// 开始流程
		String processid = actWorkFlowService.findProcessDefinition();
		TblFlowTaskRoleMappingEntity taskRoleMappingEntity = new TblFlowTaskRoleMappingEntity();
		taskRoleMappingEntity.setProcessid(processid);
		taskRoleMappingEntity.setTaskid("sjpd");
		String assignees = "";
		List<TblBaseUserEntity> list = tblBaseUserRoleMappingMapper.selectByProcessidAndTaskid(taskRoleMappingEntity);
		for (TblBaseUserEntity tblBaseUserEntity : list) {
			if (tblBaseUserEntity != null) {
				assignees += tblBaseUserEntity.getBaseuserid() + ",";
			}
		}
		ProcessInstance instance = workFlowService.startProcess(e.getDefinitionkey(), e.getVariables(), e.getTaskEntitys(), assignees.substring(0, assignees.length() - 1));
		if (instance != null) {
			// 更新主表中流程实例
			rows += yjgXmlcService.insert(new YjgXmlcEntity(), instance.getId(), e.getSjdjid(), e.getUsername());
		}
		final int _rows = rows;
		final String sjid =  e.getSjdjid();
		final List<TblBaseUserEntity> _userList = list;
		new Thread(new Runnable() {
	        @Override
	        public void run() {
	            // TODO Auto-generated method stub
	        	if(_rows > 0){
	    			YjgSjdjEntity  yen = selecttaskid(sjid);
					AppPush.pushInfo(yen,_userList,4);
				}
	        }
	    }).start();
		return rows;
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.service
	 * @Description: 查找单号缩写
	 * @author cyf
	 * @date 2017年10月26日 下午2:31:58
	 */
	public String findSzqhDhSx(String xzqh) {
		String szqh = "";
		if (xzqh == null || xzqh.equals("")) {
			szqh = "SJ";
		} else if (xzqh.equals("思明区")) {
			szqh = "SMQ";
		} else if (xzqh.equals("翔安区")) {
			szqh = "XAQ";
		} else if (xzqh.equals("湖里区")) {
			szqh = "HLQ";
		} else if (xzqh.equals("集美区")) {
			szqh = "JMQ";
		} else if (xzqh.equals("海沧区")) {
			szqh = "HCQ";
		} else if (xzqh.equals("同安区")) {
			szqh = "TAQ";
		}
		return szqh;
	}
	
	/**
	 * @Description:获取事件编号
	 * @author :caiyufei
	 * @param size
	 * @param dateStr
	 * @param id
	 * @return
	 * @date 2017年5月18日 下午2:32:00
	 */
	public String hqSjdjdh(Integer size, String id, Integer sjlx, String xzqh) {
		/**
		 * 区缩写
		 */
		String szqh = findSzqhDhSx(xzqh);
		/**
		 * 日期
		 */
		String dateStr = TimeUtils.formatyyyyMMddHHmmss(new Date());
		String dateStr1 = TimeUtils.formatyyyyMMdd(new Date());
		
		/**
		 * 随机数
		 */
		StringBuffer ranSb = new StringBuffer();
		Random rd = new Random();
		String ranStr = String.valueOf(rd.nextInt(11));
		if (ranStr.length() != 2) {
			ranSb.append("0");
			ranStr = ranSb.append(ranStr).toString();
		}
		/**
		 * 序列化最大值
		 */
		List<String> list = tblBaseSerialnumberMapper.findMaxSeqByDateStr(dateStr1);
		Integer maxSeq = 0;
		if (list.size() > 0) {
			if (list.get(0) != null) {
				maxSeq = Integer.valueOf(String.valueOf(list.get(0)));
			} else {
				maxSeq = 0000;
			}
		} else {
			maxSeq = 0000;
		}
		maxSeq = maxSeq + 1;
		String seqStr = String.valueOf(maxSeq);
		StringBuffer seqsb = new StringBuffer();
		if (seqStr.length() < size) {
			Integer len = size - seqStr.length();
			for (int i = 0; i < len; i++) {
				seqsb.append("0");
			}
		}
		/**
		 * 组合
		 */
		seqsb.append(maxSeq);
		seqStr = seqsb.toString();
		/**
		 * 保存序列化
		 */
		TblBaseSerialnumberEntity tblBaseSerialnumberEntity = new TblBaseSerialnumberEntity();
		tblBaseSerialnumberEntity.setSerialnumberid(IDUtil.getId());
		tblBaseSerialnumberEntity.setType("1");
		tblBaseSerialnumberEntity.setSeq(String.valueOf(maxSeq));
		tblBaseSerialnumberEntity.setDatestr(dateStr1);
		tblBaseSerialnumberMapper.insert(tblBaseSerialnumberEntity);
		return szqh + dateStr + ranStr + seqStr;
	}
	
	/**
	 * @Description 修改YjgSjdjEntity信息
	 * @param e
	 *            YjgSjdjEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	public int update(YjgSjdjEntity e) {
		YjgSjdjEntity yjgSjdjEntity = yjgSjdjMapper.getByPkey(e.getSjdjid());
		yjgSjdjEntity.setSbrdh(e.getSbrdh());
		yjgSjdjEntity.setSbrxm(e.getSbrxm());
		yjgSjdjEntity.setXxly(e.getXxly());
		yjgSjdjEntity.setYzjb(e.getYzjb());
		yjgSjdjEntity.setJglx(e.getJglx());
		yjgSjdjEntity.setBz(e.getBz());
		yjgSjdjEntity.setWzms(e.getWzms());
		yjgSjdjEntity.setUpdator(UserInfoUtil.getUserName());
		yjgSjdjEntity.setXzb(e.getXzb());
		yjgSjdjEntity.setYzb(e.getYzb());
		yjgSjdjEntity.setXzqh(e.getXzqh());
		yjgSjdjEntity.setWzms(e.getWzms());
		yjgSjdjEntity.setSsdl(e.getSsdl());
		yjgSjdjEntity.setJgid(e.getJgid());
		yjgSjdjEntity.setCkqx(e.getCkqx());
		String xzqh = findSzqhDhSx(e.getXzqh());
		String substring = e.getSjdjdh().substring(3, e.getSjdjdh().length());
		yjgSjdjEntity.setSjdjdh(xzqh + substring);
		return yjgSjdjMapper.update(yjgSjdjEntity);
	}
	
	public int updateSj(YjgSjdjEntity e) {
		YjgSjdjEntity yjgSjdjEntity = yjgSjdjMapper.getByPkey(e.getSjdjid());
		yjgSjdjEntity.setSbrxm(e.getSbrxm());
		

		yjgSjdjEntity.setBz(e.getBz());
		yjgSjdjEntity.setWzms(e.getWzms());
		yjgSjdjEntity.setUpdator(e.getUserName());
		yjgSjdjEntity.setUpdatetime(new Date());
		yjgSjdjEntity.setUpdateuserid(e.getBaseuserid());
		if (e.getYzb() == null) {
			yjgSjdjEntity.setYzb(yjgSjdjEntity.getYzb());
		} else {
			yjgSjdjEntity.setYzb(e.getYzb());
		}
		if (e.getXzb() == null) {
			yjgSjdjEntity.setXzb(yjgSjdjEntity.getXzb());
		} else {
			yjgSjdjEntity.setXzb(e.getXzb());
		}
		if (e.getSsdl() == null) {
			yjgSjdjEntity.setSsdl(yjgSjdjEntity.getSsdl());
		} else {
			yjgSjdjEntity.setSsdl(e.getSsdl());
		}
		if (e.getCkqx() == null) {
			yjgSjdjEntity.setCkqx(yjgSjdjEntity.getCkqx());
		} else {
			yjgSjdjEntity.setCkqx(e.getCkqx());
		}
		if (e.getJgid() == null) {
			yjgSjdjEntity.setJgid(yjgSjdjEntity.getJgid());
		} else {
			yjgSjdjEntity.setJgid(e.getJgid());
		}
		if (e.getJglx() == null) {
			yjgSjdjEntity.setJglx(yjgSjdjEntity.getJglx());
			
		} else {
			yjgSjdjEntity.setJglx(e.getJglx());
			
		}
		if (e.getYzjb() == null) {
			yjgSjdjEntity.setYzjb(yjgSjdjEntity.getYzjb());
			
		} else {
			yjgSjdjEntity.setYzjb(e.getYzjb());
			
		}
		String xzqh = "";
		if (e.getXzqh() == null) {
			yjgSjdjEntity.setXzqh(yjgSjdjEntity.getXzqh());
			xzqh = findSzqhDhSx(yjgSjdjEntity.getXzqh());
		} else {
			yjgSjdjEntity.setXzqh(e.getXzqh());
			xzqh = findSzqhDhSx(e.getXzqh());
		}
		yjgSjdjEntity.setWzms(e.getWzms());
		String substring = yjgSjdjEntity.getSjdjdh().substring(3, yjgSjdjEntity.getSjdjdh().length());
		yjgSjdjEntity.setSjdjdh(xzqh + substring);
		return yjgSjdjMapper.update(yjgSjdjEntity);
	}
	
	public int updateforapp(YjgSjdjEntity e) {
		YjgSjdjEntity yjgSjdjEntity = yjgSjdjMapper.getByPkey(e.getSjdjid());
		yjgSjdjEntity.setSbrdh(e.getSbrdh());
		yjgSjdjEntity.setSbrxm(e.getSbrxm());
		yjgSjdjEntity.setXxly(e.getXxly());
		yjgSjdjEntity.setYzjb(e.getYzjb());
		yjgSjdjEntity.setJglx(e.getJglx());
		yjgSjdjEntity.setBz(e.getBz());
		yjgSjdjEntity.setWzms(e.getWzms());
		yjgSjdjEntity.setUpdator(e.getUsername());
		yjgSjdjEntity.setUpdatetime(new Date());
		yjgSjdjEntity.setUpdateuserid(e.getBaseuserid());
		// yjgSjdjEntity.setXzb(e.getXzb());
		// yjgSjdjEntity.setYzb(e.getYzb());
		// yjgSjdjEntity.setXzqh(e.getXzqh());
		// yjgSjdjEntity.setWzms(e.getWzms());
		// yjgSjdjEntity.setSsdl(e.getSsdl());
		return yjgSjdjMapper.update(yjgSjdjEntity);
	}
	
	/**
	 * @Description 按sjdjid删除YjgSjdjEntity信息
	 * @param sjdjid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	public int delete(String sjdjid) {
		return yjgSjdjMapper.delete(sjdjid);
	}
	
	/**
	 * @Description 按sjdjid集合批量删除YjgSjdjEntity信息
	 * @param idArray
	 *            sjdjid集合
	 * @return 影响条数
	 * @author cyf
	 * @param fyjidArray
	 * @date 2017-08-24 10:56:59
	 */
	public int deleteBatch(List<YjgSjdjEntity> list) {
		int row = 0;
		for (YjgSjdjEntity yjgSjdjEntity : list) {
			if (yjgSjdjEntity.getSjlx() == 1) {
				row += delete(yjgSjdjEntity.getSjdjid());
				yjgSjqqMapper.deleteBySjdjid(yjgSjdjEntity.getSjdjid());
			} else {
				row += yjgFyjsjdjMapper.delete(yjgSjdjEntity.getFyjsjdjid());
			}
		}
		return row;
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.service
	 * @Description: 合并事件
	 * @author cyf
	 * @param yjgSjdjId
	 * @date 2017年8月29日 上午11:38:05
	 */
	public int merge(List<YjgSjdjEntity> list, String yjgSjdjId) {
		int rows = 0;
		for (YjgSjdjEntity yjgSjdjEntity : list) {
			String sjdjid = yjgSjdjEntity.getSjdjid();
			if (!yjgSjdjId.equals(sjdjid)) {
				YjgSjdjEntity e = yjgSjdjMapper.getByPkey(sjdjid);
				e.setCssjdjpid(yjgSjdjId);
				List<YjgSjdjEntity> listChildSjdj = yjgSjdjMapper.listChildSjdj(e.getSjdjid());
				if (listChildSjdj.size() > 0) {
					merge(listChildSjdj, yjgSjdjId);
				}
				rows += yjgSjdjMapper.update(e);
			}
		}
		return rows;
	}
	
	/**
	 * @Description 查询从属子事件
	 * @return YjgSjdjEntity
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	public List<YjgSjdjEntity> listChildSjdj(YjgSjdjEntity e) {
		return yjgSjdjMapper.listChildSjdj(e.getCssjdjpid());
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.service
	 * @Description:批量脱离
	 * @author cyf
	 * @date 2017年8月30日 下午5:55:15
	 */
	public int separate(String[] idArray) {
		int row = 0;
		for (String id : idArray) {
			row += yjgSjdjMapper.separate(id);
		}
		return row;
	}
	
	/**
	 * @Description 现场确认通过
	 * @author cyf
	 * @throws Exception
	 * @date 2017-08-24 10:56:59
	 */
	public int comfirmPass(YjgSjdjEntity e, int type) throws Exception {
		// 子事件流程结束
		List<YjgSjdjEntity> listChildSjdj = yjgSjdjMapper.listChildSjdj(e.getSjdjid());
		for (YjgSjdjEntity yjgSjdjEntity : listChildSjdj) {
			YjgXmlcEntity yjgXmlcEntity = yjgXmlcService.selectInstanceBySjdjid(yjgSjdjEntity.getSjdjid());
			workFlowService.deleteProcessInstance(yjgXmlcEntity.getInstanceid(), "重属子事件流程结束");
		}
		if (type == 1) {
			workFlowService.claim(e.getTaskid(), e.getBaseuserid());
		} else {
			workFlowService.claim(e.getTaskid(), UserInfoUtil.getBaseuserid());
		}
		// 权属事件达标
		YjgSjqqEntity yjgSjqqEntity = yjgSjqqMapper.findSjqqBySjdjid(e.getSjdjid());
		yjgSjqqEntity.setDbzt("1");
		yjgSjqqMapper.update(yjgSjqqEntity);
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("is_czdb", 1);
		boolean flag = workFlowService.completeTask(e.getTaskid(), variables);
		if (flag) {
			YjgSjdjEntity yjgSjdjEntity = yjgSjdjMapper.getByPkey(e.getSjdjid());
			yjgSjdjEntity.setJssj(new Date());
			yjgSjdjEntity.setSqzt(7);
			yjgSjdjEntity.setUpdatetime(new Date());
			return yjgSjdjMapper.update(yjgSjdjEntity);
		} else {
			return 0;
		}
	}
	
	/**
	 * @Description 现场确认不通过
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	public int comfirmRefuse(YjgSjdjEntity e, int type) {
		int rows = 0;
		ActTaskEntity aen = new ActTaskEntity();
		List<ActTaskEntity> taskEntityslist = new ArrayList<ActTaskEntity>();
		YjgSjqqEntity ye = yjgSjqqMapper.findSjqqBySjdjid(e.getSjdjid());
		aen.setTaskkey("czqksb");
		if (ye.getQsid() != null && !ye.getQsid().equals("")) {
			aen.setAssignee(ye.getQsid());
		}
		if (ye.getQssgid() != null && !ye.getQssgid().equals("")) {
			aen.setAssignee(ye.getQssgid());
		}
		taskEntityslist.add(aen);
		e.setTaskEntitys(taskEntityslist);
		if (type == 1) {
			workFlowService.claim(e.getTaskid(), e.getBaseuserid());
		} else {
			workFlowService.claim(e.getTaskid(), UserInfoUtil.getBaseuserid());
		}
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("is_czdb", 0);
		boolean flag = workFlowService.completeTask(e.getTaskid(), variables, e.getTaskEntitys());
		if (flag) {
			YjgSjdjEntity yjgSjdjEntity = yjgSjdjMapper.getByPkey(e.getSjdjid());
			yjgSjdjEntity.setSqzt(4);
			yjgSjdjEntity.setUpdatetime(new Date());
			rows += yjgSjdjMapper.update(yjgSjdjEntity);
			YjgSjqqEntity yjgSjqqEntity = yjgSjqqMapper.findSjqqBySjdjid(yjgSjdjEntity.getSjdjid());
			yjgSjqqEntity.setDbzt("2");
			yjgSjqqEntity.setWdbbz(e.getOpinion());
			rows += yjgSjqqMapper.update(yjgSjqqEntity);
			rows += yjgSjczMapper.updateCzzt(yjgSjdjEntity.getSjdjid());
			
			final String sjid =  e.getSjdjid();
			final int _rows = rows;
			final ActTaskEntity _aen = aen;
			new Thread(new Runnable() {
		        @Override
		        public void run() {
		            // TODO Auto-generated method stub
		        	if(_rows >0){
						YjgSjdjEntity  yen = selecttaskid(sjid);
						String[] ids = {_aen.getAssignee()};
						AppPush.pushInfo(yen, ids, 3);
					}
		        }
		    }).start();
			return rows;
		} else {
			return 0;
		}
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.service
	 * @Description: 兜底
	 * @author cyf
	 * @param i
	 * @throws Exception
	 * @date 2017年9月26日 下午4:41:43
	 */
	public int fallback(YjgSjdjEntity e, int i) throws Exception {
		int rows = 0;
		List<YjgSjdjEntity> listChildSjdj = yjgSjdjMapper.listChildSjdj(e.getSjdjid());
		for (YjgSjdjEntity yjgSjdjEntity : listChildSjdj) {
			YjgXmlcEntity yjgXmlcEntity = yjgXmlcService.selectInstanceBySjdjid(yjgSjdjEntity.getSjdjid());
			workFlowService.deleteProcessInstance(yjgXmlcEntity.getInstanceid(), "重属子事件流程结束");
		}
		YjgSjdjEntity yjgSjdjEntity = yjgSjdjMapper.getByPkey(e.getSjdjid());
		yjgSjdjEntity.setSqzt(6);
		yjgSjdjEntity.setJssj(new Date());
		yjgSjdjEntity.setUpdatetime(new Date());
		rows = yjgSjdjMapper.update(yjgSjdjEntity);
		Map<String, Object> variables = new HashMap<String, Object>();
		actWorkFlowService.claim(e.getTaskid(), yjgSjdjEntity.getBaseuserid());
		variables.put("is_dd", 1);
		if (i == 1) {
			variables.put("assignee", e.getAssignee());
		} else {
			variables.put("assignee", UserInfoUtil.getUserName());
		}
		boolean flag = actWorkFlowService.completeTask(e.getTaskid(), variables);
		if (flag) {
			return rows;
		} else {
			return 0;
		}
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.service
	 * @Description: 分页查询已办任务
	 * @author cyf
	 * @date 2017年9月30日 下午2:49:54
	 */
	public List<YjgSjdjEntity> selectTaskedPagination(YjgSjdjEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgSjdjMapper.selectTaskedPagination(e, rowBounds);
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.service
	 * @Description: 分页查询已办业务数量
	 * @author cyf
	 * @date 2017年9月30日 下午2:50:10
	 */
	public int getTaskedCount(YjgSjdjEntity e) {
		return yjgSjdjMapper.getTaskedCount(e);
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.service
	 * @Description: 分页查询办结任务
	 * @author cyf
	 * @date 2017年9月30日 下午2:49:54
	 */
	public List<YjgSjdjEntity> selectBjtaskPagination(YjgSjdjEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgSjdjMapper.selectBjtaskPagination(e, rowBounds);
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.service
	 * @Description: 分页查询办结任务数量
	 * @author cyf
	 * @date 2017年9月30日 下午2:50:10
	 */
	public int getBjtaskCount(YjgSjdjEntity e) {
		return yjgSjdjMapper.getBjtaskCount(e);
	}
	
	/**
	 * 回退任务，任务将回退到上一节点去，并回退给上一节点的提交者
	 */
	public boolean rollbackTask(YjgSjdjEntity e) {
		boolean result = workFlowService.rollbackTask(e.getTaskid(), e.getVariables(), e.getSjdjid());
		return result;
	}
	
	public Integer taskAssign(YjgSjdjEntity yjgSjdjEntity, String[] idArray, int type) {
		int rows = 0;
		String assignees = "";
		for (String userid : idArray) {
			YjgSjdjUserMappingEntity e = new YjgSjdjUserMappingEntity();
			e.setBaseuserid(userid);
			e.setSfjd(0);
			e.setSjdjusermappingid(IDUtil.getId());
			e.setType("2");
			e.setSjdjid(yjgSjdjEntity.getSjdjid());
			rows += yjgSjdjUserMappingMapper.insert(e);
			assignees += userid + ",";
		}
		YjgSjdjUserMappingEntity yjgSjdjUserMappingEntity;
		if (type == 1) {
			yjgSjdjUserMappingEntity = yjgSjdjUserMappingMapper.getByUserIdAndSjdjIdAndType(yjgSjdjEntity.getSjdjid(), yjgSjdjEntity.getBaseuserid(), "1");
		} else {
			yjgSjdjUserMappingEntity = yjgSjdjUserMappingMapper.getByUserIdAndSjdjIdAndType(yjgSjdjEntity.getSjdjid(), UserInfoUtil.getBaseuserid(), "1");
			
		}
		yjgSjdjUserMappingEntity.setSfjd(1);
		yjgSjdjUserMappingEntity.setCzsj(new Date());
		yjgSjdjUserMappingMapper.update(yjgSjdjUserMappingEntity);
		YjgSjdjEntity e = getByPkey(yjgSjdjEntity.getSjdjid());
		e.setSqzt(2);
		e.setUpdatetime(new Date());
		if (type == 1) {
			rows = updateforapp(e);
			actWorkFlowService.claim(yjgSjdjEntity.getTaskid(), yjgSjdjEntity.getBaseuserid());
		} else {
			rows = update(e);
			actWorkFlowService.claim(yjgSjdjEntity.getTaskid(), UserInfoUtil.getBaseuserid());
		}
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("assignees", assignees.substring(0, assignees.length() - 1));
		variables.put("is_qzfw", 1);
		boolean flag = actWorkFlowService.completeTask(yjgSjdjEntity.getTaskid(), variables);
		if (flag) {
			
			final String sjid =  yjgSjdjEntity.getSjdjid();
			final int _rows = rows;
			final String[] _idArray = idArray;
			new Thread(new Runnable() {
		        @Override
		        public void run() {
		            // TODO Auto-generated method stub
		        	if(_rows > 0){
						YjgSjdjEntity  yen = selecttaskid(sjid);
						AppPush.pushInfo(yen,_idArray,2);
					}
		        }
		    }).start();
			
		
			return rows;
		} else {
			return 0;
		}
	}
	public Integer sendAgain(YjgSjdjEntity yjgSjdjEntity, int type, int firstDeny) {
		int rows = 0;
		// 更新关联表
		YjgSjdjUserMappingEntity e = new YjgSjdjUserMappingEntity();
		YjgSjdjUserMappingEntity e1 = new YjgSjdjUserMappingEntity();
		TblBaseUserEntity e2 = new TblBaseUserEntity();
		String firstDenyStr = String.valueOf(firstDeny);
		if (type == 1) {
			e2 = tblBaseUserMapper.getByPkey(yjgSjdjEntity.getBaseuserid());
			e = yjgSjdjUserMappingMapper.getByUserIdAndSjdjIdAndType(yjgSjdjEntity.getSjdjid(), yjgSjdjEntity.getBaseuserid(), firstDenyStr);
			e1.setBaseuserid(yjgSjdjEntity.getBaseuserid());
		} else {
			e2 = tblBaseUserMapper.getByPkey(UserInfoUtil.getBaseuserid());
			e = yjgSjdjUserMappingMapper.getByUserIdAndSjdjIdAndType(yjgSjdjEntity.getSjdjid(), UserInfoUtil.getBaseuserid(), firstDenyStr);
			e1.setBaseuserid(UserInfoUtil.getBaseuserid());
		}
		if (e != null) {
			e.setSfjd(2);
			e.setCzsj(new Date());
			e.setJjly(yjgSjdjEntity.getOpinion());
			rows += yjgSjdjUserMappingMapper.update(e);
		}
		
		// 判断是否还有未确认确属
		e1.setSjdjid(yjgSjdjEntity.getSjdjid());
		e1.setType(firstDenyStr);
		List<YjgSjdjUserMappingEntity> list = yjgSjdjUserMappingMapper.selectWqdsj(e1);
		if (list.size() == 0) {
			String assignees = "";
			String processid = actWorkFlowService.findProcessDefinition();
			TblFlowTaskRoleMappingEntity taskRoleMappingEntity = new TblFlowTaskRoleMappingEntity();
			taskRoleMappingEntity.setProcessid(processid);
			taskRoleMappingEntity.setTaskid("cxpd");
			List<TblBaseUserEntity> userList = tblBaseUserRoleMappingMapper.selectByProcessidAndTaskid(taskRoleMappingEntity);
			for (TblBaseUserEntity tblBaseUserEntity : userList) {
				if (tblBaseUserEntity != null) {
					assignees += tblBaseUserEntity.getBaseuserid() + ",";
				}
			}
			List<YjgSjdjUserMappingEntity> qsList = yjgSjdjUserMappingMapper.getByUserIdAndSjdjId(yjgSjdjEntity.getSjdjid(), "1");
			for (YjgSjdjUserMappingEntity yjgSjdjUserMappingEntity : qsList) {
				if (yjgSjdjUserMappingEntity != null) {
					assignees += yjgSjdjUserMappingEntity.getBaseuserid() + ",";
				}
			}
			YjgSjdjEntity e4 = getByPkey(yjgSjdjEntity.getSjdjid());
			e4.setSqzt(3);
			e4.setUpdatetime(new Date());
			if (type == 1) {
				rows += updateforapp(e4);
				actWorkFlowService.claim(yjgSjdjEntity.getTaskid(), yjgSjdjEntity.getBaseuserid());
			} else {
				rows += yjgSjdjMapper.update(e4);
				actWorkFlowService.claim(yjgSjdjEntity.getTaskid(), UserInfoUtil.getBaseuserid());
			}
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("is_qzfw", 0);
			variables.put("assignees", assignees.substring(0, assignees.length() - 1));
			variables.put("option", yjgSjdjEntity.getOpinion());
			boolean flag = actWorkFlowService.completeTask(yjgSjdjEntity.getTaskid(), variables);
			if (flag) {
				final String sjid =  e.getSjdjid();
				final int _rows = rows;
				final TblBaseUserEntity _e2 = e2; 
				final List<TblBaseUserEntity> _userList = userList;
				new Thread(new Runnable() {
			        @Override
			        public void run() {
			            // TODO Auto-generated method stub
						if(_rows > 0){
							if(_e2 !=null ){
								YjgSjdjEntity  yen =selecttaskid(sjid);
//								String[] ids = {e2.getPbaseuserid()};
								AppPush.pushInfo(yen,_userList,1);
							}
						}
			        }
			    }).start();
				return rows;
			} else {
				return 0;
			}
		} else {
			if (type == 1) {
				actWorkFlowService.deleteCandidateUser(yjgSjdjEntity.getTaskid(), yjgSjdjEntity.getBaseuserid());
			} else {
				actWorkFlowService.deleteCandidateUser(yjgSjdjEntity.getTaskid(), UserInfoUtil.getBaseuserid());
			}
			rows = rows + 1;
		}

		return rows;
	}
	
	public List<ActTaskEntity> listHisFlow(YjgSjdjEntity yjgSjdjEntity) {
		List<ActTaskEntity> list = actTaskService.selectHistoryActByInstanceid(yjgSjdjEntity);
		return list;
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.service
	 * @Description: 提醒功能
	 * @author cyf
	 * @date 2017年12月19日 上午9:37:20
	 */
	public Object remindTip(Map<String, Object> resultMap) {
		YjgSjdjEntity yjgSjdjEntity = new YjgSjdjEntity();
		yjgSjdjEntity.setAssignee(UserInfoUtil.getBaseuserid());
		yjgSjdjEntity.setYzjb("0");
		resultMap.put("twentyFour", getTaskCount(yjgSjdjEntity));
		yjgSjdjEntity.setYzjb("1");
		resultMap.put("first", getTaskCount(yjgSjdjEntity));
		yjgSjdjEntity.setYzjb("2");
		resultMap.put("second", getTaskCount(yjgSjdjEntity));
		yjgSjdjEntity.setYzjb("3");
		resultMap.put("thirdly", getTaskCount(yjgSjdjEntity));
		return resultMap;
	}
	
	/**
	* @Package com.kingtopinfo.yjg.service  
	* @Description: 定时器10分钟内任务提醒
	* @author cyf    
	* @date 2018年1月10日 下午3:15:01
	 */
	public Object remindRecentTip(Map<String, Object> resultMap) {
		YjgSjdjEntity yjgSjdjEntity = new YjgSjdjEntity();
		yjgSjdjEntity.setAssignee(UserInfoUtil.getBaseuserid());
		resultMap.put("count", yjgSjdjMapper.getTaskRecentCount(yjgSjdjEntity));
		return resultMap;
	}
	
	public List<YjgSjdjEntity> exportDataBytime(YjgSjdjTimeEntity e){
		return yjgSjdjMapper.exportDataBytime(e);
	}
	
	public void exportExcelData(String[] idArray, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletOutputStream fileOutputStream = null;
		String realPath = FilePathUtil.getFilePath("disk_Path");
		BufferedImage bufferImg = null;
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("窨井信息");
			HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
			HSSFCellStyle cellStyle = wb.createCellStyle();
			cellStyle.setWrapText(true);
			
			HSSFRow row0 = sheet.createRow(0);
			HSSFCell cell0 = row0.createCell(0);
			String year = idArray[0];
			String month= idArray[1];
			String bt = year + "年" + month + "月窨井事件记录台账";
			cell0.setCellValue(bt);
			// 表头
			HSSFRow row1 = sheet.createRow(1);
			HSSFCell cell1;
			
			cell1 = row1.createCell(0);
			cell1.setCellValue("单号");
			cell1 = row1.createCell(1);
			cell1.setCellValue("时间");
			cell1 = row1.createCell(2);
			cell1.setCellValue("上报人");
			cell1 = row1.createCell(3);
			cell1.setCellValue("道路（地点）");
			cell1 = row1.createCell(4);
			cell1.setCellValue("性质");
			cell1 = row1.createCell(5);
			cell1.setCellValue("基本情况");
			cell1 = row1.createCell(6);
			cell1.setCellValue("处置过程");
			cell1 = row1.createCell(7);
			cell1.setCellValue("处置前");
			cell1 = row1.createCell(8);
			cell1.setCellValue("处置后");
			
			HSSFCellStyle cellStyle0 = cell0.getCellStyle();
			cellStyle0.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle0.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFCellStyle cellStyle1 = cell1.getCellStyle();
			cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			// 合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
			int k = 2;
			
			//内容
			YjgSjdjTimeEntity yjgSjdjTimeEntity= new YjgSjdjTimeEntity();
			if (month.length() == 1) {
				month = "0" + month;
			}
			yjgSjdjTimeEntity.setYear(year);
			yjgSjdjTimeEntity.setMonth(month);
			List<YjgSjdjEntity> YjgSjdjEntitylist = exportDataBytime(yjgSjdjTimeEntity);
			for (YjgSjdjEntity yjgSjdj : YjgSjdjEntitylist) {
				HSSFRow rowk = sheet.createRow(k);
				rowk.setHeight((short) 1500);
				HSSFCell cellk = rowk.createCell(0);
				cellk.setCellValue(yjgSjdj.getSjdjdh());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String SCSJ= format.format(yjgSjdj.getScsj());
				cellk = rowk.createCell(1);
				cellk.setCellValue(SCSJ);
				cellk = rowk.createCell(2);
				cellk.setCellValue(yjgSjdj.getSbrxm());
				cellk = rowk.createCell(3);
				cellk.setCellValue(yjgSjdj.getSsdl());
				cellk = rowk.createCell(4);
				cellk.setCellValue(yjgSjdj.getJglx());
				cellk = rowk.createCell(5);
				cellk.setCellValue(yjgSjdj.getBz());
				cellk = rowk.createCell(6);
				YjgSjczEntity yjgSjczEntity = new YjgSjczEntity();
				yjgSjczEntity.setSjdjid(yjgSjdj.getSjdjid());
				cellk.setCellValue(actTaskService.selectHistoryActBySjdjid(yjgSjdj.getSjdjid()));
				cellk.setCellStyle(cellStyle);
				yjgSjczEntity.setCzzt("0");
				List<YjgSjczEntity> selectByCzzt = yjgSjczMapper.selectByCzzt(yjgSjczEntity);
				if (selectByCzzt.size() > 0) {
					TblBaseFileEntity tblBaseFileEntity = new TblBaseFileEntity();
					tblBaseFileEntity.setOperaid(selectByCzzt.get(0).getSjczid());
					tblBaseFileEntity.setOperatype("YJG_IMG_FILE");
					List<TblBaseFileEntity> fileList = tblBaseFileMapper.getByOperaid(tblBaseFileEntity);
					try {
						if (fileList.size() > 0) {
							ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
							String filepath = fileList.get(0).getFilepath();
							bufferImg = ImageIO.read(new File(realPath + filepath));
							ImageIO.write(bufferImg, "jpg", byteArrayOut);
							HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 255, (short) 7, k, (short) 8, k);
							anchor.setAnchorType(3);
							patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
						}
					} catch (Exception e) {

					}
				}
				yjgSjczEntity.setCzzt("2");
				selectByCzzt = yjgSjczMapper.selectByCzzt(yjgSjczEntity);
				if (selectByCzzt.size() > 0) {
					TblBaseFileEntity tblBaseFileEntity = new TblBaseFileEntity();
					tblBaseFileEntity.setOperaid(selectByCzzt.get(0).getSjczid());
					tblBaseFileEntity.setOperatype("YJG_IMG_FILE");
					List<TblBaseFileEntity> fileList = tblBaseFileMapper.getByOperaid(tblBaseFileEntity);
					try {
						if (fileList.size() > 0) {
							ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
							String filepath = fileList.get(0).getFilepath();
							bufferImg = ImageIO.read(new File(realPath + filepath));
							ImageIO.write(bufferImg, "jpg", byteArrayOut);
							HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 255, (short) 8, k, (short) 9, k);
							anchor.setAnchorType(3);
							patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
						}
					} catch (Exception e) {
						
					}
				}
				k++;
			}
				
			
			// 设置每列的宽度
			setSheetColumnWidth(sheet);
			// 设置行高
			// 设置response的Header
			String filename = bt + ".xls";
			filename = new String(filename.getBytes(), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment;filename=" + filename);
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			fileOutputStream = response.getOutputStream();
			// 生成excel
			wb.write(fileOutputStream);
		} finally {
			try {
				fileOutputStream.flush();
				fileOutputStream.close();
				bufferImg.flush();
			} catch (Exception e) {

			}
			
		}
	}				
	
	private void setSheetColumnWidth(HSSFSheet sheet) {
		sheet.setColumnWidth(0, 7000);
		sheet.setColumnWidth(1, 7000);
		sheet.setColumnWidth(2, 7000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 8000);
		sheet.setColumnWidth(6, 12000);
		sheet.setColumnWidth(7, 12000);
		sheet.setColumnWidth(8, 12000);
	}
	
	public List<YjgSjdjEntity> selectJglxPagination(YjgSjdjEntity yjgSjdjEntity, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		List<YjgSjdjEntity> list = yjgSjdjMapper.selectJglxPagination(yjgSjdjEntity, rowBounds);
		return list;
	}
	
	public int getJglxCount(YjgSjdjEntity yjgSjdjEntity) {
		int count = yjgSjdjMapper.getJglxCount(yjgSjdjEntity);
		return count;
	}
	
	public void exportTransferOrder(String[] idArray, HttpServletRequest request, HttpServletResponse response) throws IOException {
		for (String sjdjid : idArray) {
			SimpleDateFormat tempDate = new SimpleDateFormat("yyyy年MM月dd日");
			Map<String, Object> map = new HashMap<String, Object>();
			YjgSjdjEntity yjgSjdjEntity = this.getByPkey(sjdjid);
			if (yjgSjdjEntity == null) {
				throw new RuntimeException();
			}
			/**
			 * 登记单号
			 */
			map.put("sjdjdh", yjgSjdjEntity.getSjdjdh());
			/**
			 * 上报来源
			 */
			if (yjgSjdjEntity.getXxly() != null) {
				map.put("xxly", tblBaseTypeMapper.selectByCodeAndValue("XXLY", yjgSjdjEntity.getXxly().toString()).get(0).getName());
			} else {
				map.put("xxly", "");
			}
			/**
			 * 上报人姓名
			 */
			if (yjgSjdjEntity.getSbrxm() != null) {
				map.put("sbrxm", yjgSjdjEntity.getSbrxm());
			} else {
				map.put("sbrxm", "");
			}
			/**
			 * 上报人电话
			 */
			if (yjgSjdjEntity.getSbrdh() != null) {
				map.put("sbrdh", yjgSjdjEntity.getSbrdh());
			} else {
				map.put("sbrdh", "");
			}
			/**
			 * 井盖编号
			 */
			if (yjgSjdjEntity.getJgid() != null) {
				map.put("jgbh", yjgSjdjEntity.getJgid());
			} else {
				map.put("jgbh", "");
			}
			/**
			 * 井盖类型
			 */
			if (yjgSjdjEntity.getJglx() != null) {
				map.put("jglx", yjgSjdjEntity.getJglx());
			} else {
				map.put("jglx", "");
			}
			/**
			 * 所属道路
			 */
			if (yjgSjdjEntity.getSsdl() != null) {
				map.put("ssdl", yjgSjdjEntity.getSsdl());
			} else {
				map.put("ssdl", "");
			}
			/**
			 * 生成时间
			 */
			if (yjgSjdjEntity.getScsj() != null) {
				map.put("scsj", TimeUtils.formatDateTime(yjgSjdjEntity.getScsj()));
			} else {
				map.put("scsj", "");
			}
			/**
			 * 窨井现状
			 */
			if (yjgSjdjEntity.getBz() != null) {
				map.put("bz", yjgSjdjEntity.getBz());
			} else {
				map.put("bz", "");
			}
			/**
			 * 位置描述
			 */
			if (yjgSjdjEntity.getWzms() != null) {
				map.put("wzms", yjgSjdjEntity.getWzms());
			} else {
				map.put("wzms", "");
			}
			/**
			 * 当前时间
			 */
			String datetime = tempDate.format(new Date());
			map.put("datetime", datetime);
			/**
			 * 接件流程
			 */
			map.put("jjLc", actTaskService.selectHistoryActBySjdjid(sjdjid));
			// 生成模板
			String title = yjgSjdjEntity.getSjdjdh() + "转办单";
			WordUtils.exportMillCertificateWord(request, response, map, title, "exportTransferOrder.ftl");
			yjgSjdjEntity.setIsline("1");
			// 更新事件状态
			yjgSjdjMapper.update(yjgSjdjEntity);
		}
		
		OutputStream outputStream = null;
		BufferedInputStream bufferedInputStream = null;
		FileInputStream fileInputStream = null;
		String diskPath = FilePathUtil.getFilePath("disk_Path") + FilePathUtil.getFilePath("temp_Path");
		try {
			ZipUtil.getInstance().doZip(diskPath, diskPath + "/temp");
			String filename = "厦门市政工程管理窨井转办单" + TimeUtils.formatyyyyMMddHHmmss(new Date()) + ".zip";
			response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
			response.setContentType("application/zip");
			outputStream = response.getOutputStream();
			// 获取ZIP文件
			File file = new File(diskPath + "/temp.zip");
			fileInputStream = new FileInputStream(file);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			byte[] b = new byte[1024];// 相当于我们的缓存
			long l = 0;// 该值用于计算当前实际下载了多少字节
			// 开始循环下载
			while (l < file.length()) {
				int j = bufferedInputStream.read(b, 0, 1024);
				l += j;
				outputStream.write(b, 0, j);
			}
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
			if (bufferedInputStream != null) {
				bufferedInputStream.close();
			}
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
			File file = new File(diskPath);
			FilePathUtil.deleteDir(file);
		}
	}
}