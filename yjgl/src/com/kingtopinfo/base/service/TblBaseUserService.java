package com.kingtopinfo.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kingtopinfo.activiti.entity.TblFlowTaskRoleMappingEntity;
import com.kingtopinfo.activiti.mapper.TblFlowTaskRoleMappingMapper;
import com.kingtopinfo.base.entity.TblBaseRoleEntity;
import com.kingtopinfo.base.entity.TblBaseRoleMenuMappingEntity;
import com.kingtopinfo.base.entity.TblBaseRoleResourceMappingEntity;
import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.entity.TblBaseUserExtEntity;
import com.kingtopinfo.base.entity.TblBaseUserRoleMappingEntity;
import com.kingtopinfo.base.mapper.TblBaseRoleMapper;
import com.kingtopinfo.base.mapper.TblBaseRoleMenuMappingMapper;
import com.kingtopinfo.base.mapper.TblBaseRoleResourceMappingMapper;
import com.kingtopinfo.base.mapper.TblBaseUserExtMapper;
import com.kingtopinfo.base.mapper.TblBaseUserMapper;
import com.kingtopinfo.base.mapper.TblBaseUserRoleMappingMapper;
import com.kingtopinfo.base.security.RefreshResourceService;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.Md5;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.yjg.service.YjgTxlService;

@Service
@Transactional
public class TblBaseUserService {
	private final static String				XLS		= "xls";
	private final static String				XLSX	= "xlsx";
	@Autowired
	private TblBaseUserMapper tblBaseUserMapper;
	@Autowired
	private TblBaseUserExtMapper	tblBaseUserExtMapper;
	@Autowired
	private TblBaseUserRoleMappingMapper	tblBaseUserRoleMappingMapper;
	@Autowired
	private TblBaseRoleService				tblBaseRoleService;
	@Autowired
	private YjgTxlService					yjgTxlService;
	@Autowired
	private TblBaseRoleMapper				tblBaseRoleMapper;
	@Autowired
	private TblBaseRoleResourceMappingMapper	tblBaseRoleResourceMappingMapper;
	@Autowired
	private TblBaseRoleMenuMappingMapper		tblBaseRoleMenuMappingMapper;
	@Autowired
	private TblFlowTaskRoleMappingMapper		tblFlowTaskRoleMappingMapper;
	@Autowired
	private RefreshResourceService				refreshResourceService;
	@Autowired
	private SqlSessionTemplate					sqlsession;
	
	public void setSqlsession(SqlSessionTemplate sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	public List<TblBaseUserEntity> selectPagination(TblBaseUserEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblBaseUserMapper.selectPagination(e, rowBounds);
	}
	
	public List<TblBaseUserEntity> selectByRoleidPagination(TblBaseUserEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblBaseUserMapper.selectByRoleidPagination(e, rowBounds);
	}
	
	public List<TblBaseUserEntity> selectUnsecondPagination(TblBaseUserEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblBaseUserMapper.selectUnsecondPagination(e, rowBounds);
	}
	
	public List<TblBaseUserEntity> selectByuserIdPagination(TblBaseUserEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblBaseUserMapper.selectByuserIdPagination(e, rowBounds);
	}
	
	public List<TblBaseUserEntity> selectByUnUserIdPagination(TblBaseUserEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		List<TblBaseUserEntity> list = tblBaseUserMapper.selectByUnUserIdPagination(e, rowBounds);
		for (TblBaseUserEntity tblBaseUserEntity : list) {
			if (tblBaseUserEntity.getPbaseuserid() != null) {
				TblBaseUserEntity e2 = tblBaseUserMapper.getByPkey(tblBaseUserEntity.getPbaseuserid());
				tblBaseUserEntity.setPbaseuserName(e2.getUsername());
			}
		}
		return list;
	}
	
	public Integer count(TblBaseUserEntity e){
		return tblBaseUserMapper.count(e);
	}
	
	public Integer unsecondCount(TblBaseUserEntity e) {
		return tblBaseUserMapper.unsecondCount(e);
	}
	
	public Integer getCountByuserId(TblBaseUserEntity e) {
		return tblBaseUserMapper.getCountByuserId(e);
	}
	
	public Integer getCountByUnUserId(TblBaseUserEntity e) {
		return tblBaseUserMapper.getCountByUnUserId(e);
	}
	
	public Integer getCountByRoleid(TblBaseUserEntity e) {
		return tblBaseUserMapper.getCountByRoleid(e);
	}
	
	/**
	 * @Description:添加用户
	 * @author:cyf
	 * @time:2017年6月1日 上午8:46:56
	 */
	public int insert(TblBaseUserEntity e) {
		String uuid = IDUtil.getId();
		int rows = 0;
		Md5 oMd5 = new Md5();
		e.setPassword(oMd5.md5Digest(e.getPassword()));
		e.setBaseuserid(uuid);
		e.setIslogin("0");
		if (!tblBaseRoleService.checkSuperUser()) {
			TblBaseUserEntity tblBaseUserEntity = getByPkey(UserInfoUtil.getBaseuserid());
			String baseuserid = tblBaseUserEntity.getBaseuserid();
			e.setPbaseuserid(baseuserid);
			insertSgd(uuid, baseuserid);
		}
		rows = sqlsession.insert("com.kingtopinfo.base.mapper.TblBaseUserMapper.insert", e);
		return rows;
	}
	
	/**
	 * @Description:查询账户是否存在
	 * @author:cyf
	 * @time:2017年6月1日 上午8:53:32
	 */
	public TblBaseUserEntity selectByAccount(String account) {
		return tblBaseUserMapper.selectByAccount(account);
	}
	public TblBaseUserEntity selectByAccountapp(String account) {
		return tblBaseUserMapper.selectByAccountapp(account);
	}
	
	/**
	 * @Description:查询账户所有信息
	 * @author:cyf
	 * @time:2017年6月1日 上午8:53:32
	 */
	public List<Map<String, Object>> selectUserMsgById(String account) {
		return tblBaseUserMapper.selectUserMsgById(account);
	}
	
	public int update(TblBaseUserEntity e, TblBaseUserEntity tBaseUserEntity) {
		tBaseUserEntity.setUsername(e.getUsername());
		tBaseUserEntity.setState(e.getState());
		tBaseUserEntity.setUpdatetime(new Date());
		tBaseUserEntity.setUpdateuserid(UserInfoUtil.getBaseuserid());
		return tblBaseUserMapper.update(tBaseUserEntity);
	}
	
	/**
	 * @Description:批量删除实体信息
	 * @author:cyf
	 * @time:2017年6月1日 下午2:41:53
	 */
	public int deleteBatch(String[] idArray) {
		int row = 0;
		for (String baseuserid : idArray) {
			TblBaseUserEntity tblBaseUserEntity = new TblBaseUserEntity();
			tblBaseUserEntity.setPbaseuserid(baseuserid);
			List<TblBaseUserEntity> list = selectByuserIdPagination(tblBaseUserEntity, 20, 1);
			for (TblBaseUserEntity e : list) {
				e.setPbaseuserid(null);
				deletePuser(e);
			}
			if (!tblBaseRoleService.checkSuperUser()) {
				deleteSgd(baseuserid);
			}
			row += delete(baseuserid);
		}
		return row;
	}
	
	/**
	 * @Description:删除用户
	 * @author:cyf
	 * @time:2017年6月1日 下午2:42:45
	 */
	public int delete(String baseuserid) {
		return tblBaseUserMapper.delete(baseuserid);
	}
	
	/**
	 * @Description:通过主键查询用户
	 * @author:cyf
	 * @time:2017年6月1日 下午2:42:45
	 */
	public TblBaseUserEntity getByPkey(String baseuserid) {
		return tblBaseUserMapper.getByPkey(baseuserid);
	}
	
	/**
	 * @Description:通过父ID查询用户
	 * @author:cyf
	 * @time:2017年6月1日 下午2:42:45
	 */
	public List<TblBaseUserEntity> getByPbaseuserid(String pbaseuserid) {
		return tblBaseUserMapper.getByPbaseuserid(pbaseuserid);
	}
	
	/**
	 * @Description:重置密码
	 * @author:cyf
	 * @time:2017年6月1日 下午2:42:45
	 */
	public int updatePassword(TblBaseUserEntity e) {
		Md5 oMd5 = new Md5();
		e.setPassword(oMd5.md5Digest(e.getPassword()));
		return tblBaseUserMapper.updatePassword(e);
	}
	
	/**
	 * @Description:更新/保存用户信息
	 * @author:cyf
	 * @time:2017年6月14日 下午4:13:24
	 */
	public int updateMsgById(String baseuserid, TblBaseUserEntity tblBaseUserEntity, TblBaseUserExtEntity tblBaseUserExtEntity) {
		TblBaseUserEntity userEntity = tblBaseUserMapper.getByPkey(baseuserid);
		userEntity.setUsername(tblBaseUserEntity.getUsername());
		tblBaseUserMapper.update(userEntity);
		TblBaseUserExtEntity userExtEntity = tblBaseUserExtMapper.getByUserId(baseuserid);
		if (userExtEntity != null) {
			userExtEntity.setTel(tblBaseUserExtEntity.getTel());
			userExtEntity.setPhone(tblBaseUserExtEntity.getPhone());
			userExtEntity.setGender(tblBaseUserExtEntity.getGender());
			userExtEntity.setUpdatetime(new Date());
			userExtEntity.setUpdateuserid(baseuserid);
			tblBaseUserExtMapper.update(userExtEntity);
		} else {
			userExtEntity = new TblBaseUserExtEntity();
			userExtEntity.setTel(tblBaseUserExtEntity.getTel());
			userExtEntity.setPhone(tblBaseUserExtEntity.getPhone());
			userExtEntity.setGender(tblBaseUserExtEntity.getGender());
			userExtEntity.setBaseuserextid(IDUtil.getId());
			userExtEntity.setBaseuserid(baseuserid);
			tblBaseUserExtMapper.insert(userExtEntity);
		}
		return 1;
	}
	
	public List<String> selectRoleIdByAccount(String account) {
		return tblBaseUserMapper.selectRoleIdByAccount(account);
	}
	
	public Integer addPuser(TblBaseUserEntity e) {
		int row = 0;
		String curUserId = UserInfoUtil.getBaseuserid();
		String[] baseuseridArray = e.getBaseuserid().split(",");
		for (String baseuserid : baseuseridArray) {
			TblBaseUserEntity tblBaseUserEntity = tblBaseUserMapper.getByPkey(baseuserid);
			tblBaseUserEntity.setPbaseuserid(curUserId);
			row += tblBaseUserMapper.update(tblBaseUserEntity);
		}
		return row;
	}
	
	public Integer deletePuser(TblBaseUserEntity e) {
		int row = 0;
		String[] baseuseridArray = e.getBaseuserid().split(",");
		for (String baseuserid : baseuseridArray) {
			TblBaseUserEntity tblBaseUserEntity = tblBaseUserMapper.getByPkey(baseuserid);
			tblBaseUserEntity.setPbaseuserid(null);
			row += tblBaseUserMapper.deletePuser(tblBaseUserEntity);
		}
		return row;
	}
	
	public int updatePuserid(String[] idArray, String pbaseuserid) {
		int rows = 0;
		for (String id : idArray) {
			if (pbaseuserid.equals("")) {
				deleteSgd(id);
			} else {
				insertSgd(id, pbaseuserid);
			}
			TblBaseUserEntity tblBaseUserEntity = getByPkey(id);
			tblBaseUserEntity.setPbaseuserid(pbaseuserid);
			rows = tblBaseUserMapper.update(tblBaseUserEntity);
		}
		return rows;
	}
	
	/**
	* @Package com.kingtopinfo.base.service  
	* @Description: 删除三级施工队关系
	* @author cyf    
	* @date 2018年1月8日 下午6:20:10
	 */
	public void deleteSgd(String baseuserid) {
		TblBaseRoleEntity tblBaseRoleEntity = tblBaseRoleService.selectRoleidByUserid(baseuserid);
		if (tblBaseRoleEntity != null) {
			TblBaseUserRoleMappingEntity e1 = new TblBaseUserRoleMappingEntity();
			e1.setBaseuserid(baseuserid);
			e1.setBaseroleid(tblBaseRoleEntity.getBaseroleid());
			tblBaseUserRoleMappingMapper.deleteByRoidAndUserid(e1);
		}
	}
	
	/**
	* @Package com.kingtopinfo.base.service  
	* @Description: 添加三级施工队关系
	* @author cyf    
	 * @param pbaseuserid 
	* @date 2018年1月8日 下午6:20:10
	 */
	public void insertSgd(String baseuserid, String pbaseuserid) {
		TblBaseRoleEntity tblBaseRoleEntity = tblBaseRoleService.selectRoleidByUserid(pbaseuserid);
		TblBaseUserRoleMappingEntity e1 = new TblBaseUserRoleMappingEntity();
		e1.setBaseuserid(baseuserid);
		e1.setBaseuserrolemid(IDUtil.getId());
		e1.setBaseroleid(tblBaseRoleEntity.getBaseroleid());
		tblBaseUserRoleMappingMapper.deleteByUserid(baseuserid);
		tblBaseUserRoleMappingMapper.insert(e1);
		
	}
	
	public void importUserExcelData(List<MultipartFile> fileList) throws Exception {
		for (MultipartFile myFile : fileList) {
			// 获得文件名
			Workbook workbook = null;
			String fileName = myFile.getOriginalFilename();
			if (fileName.endsWith(XLS)) {
				// 2003
				workbook = new HSSFWorkbook(myFile.getInputStream());
			} else if (fileName.endsWith(XLSX)) {
				// 2007
				workbook = new XSSFWorkbook(myFile.getInputStream());
			} else {
				throw new Exception("文件不是Excel文件");
			}
			
			Sheet sheet = workbook.getSheet("Sheet1");
			int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
			if (rows == 0) {
				throw new Exception("请填写数据");
			}
			String qqWhid = "";
			for (int i = 1; i <= rows + 1; i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					String accont = yjgTxlService.getCellValue(row.getCell(0), 1);
					String username = yjgTxlService.getCellValue(row.getCell(1), 1);
					String roletype = yjgTxlService.getCellValue(row.getCell(2), 1);
					/**
					 * 用户
					 */
					String userid = IDUtil.getId();
					TblBaseUserEntity tblBaseUserEntity = new TblBaseUserEntity();
					tblBaseUserEntity.setBaseuserid(userid);
					tblBaseUserEntity.setPassword("c4ca4238a0b923820dcc509a6f75849b");
					tblBaseUserEntity.setState(1);
					tblBaseUserEntity.setAccount(accont);
					tblBaseUserEntity.setUsername(username);
					tblBaseUserMapper.insert(tblBaseUserEntity);
					if (i == 1 || i == 8 || i == 15 || i == 22 || i == 29 || i == 36 || i == 43 || i == 50 || i == 57 || i == 64 || i == 71 || i == 78 || i == 85 || i == 92
							|| i == 99 || i == 106 || i == 113 || i == 120 || i == 127 || i == 134 || i == 141 || i == 148 || i == 155 || i == 162 
							|| i == 169 || i == 176 || i == 183 || i == 190 || i == 197 || i == 204 || i == 211 || i == 218 || i == 225 || i == 232 || i == 239 || i == 246
							|| i == 253 || i == 260 || i == 267 || i == 274 || i == 281 || i == 288 || i == 295 || i == 302 || i == 309 || i == 316 || i == 323
							|| i == 330 || i == 337 || i == 344 || i == 351 || i == 358 || i == 365 || i == 372 || i == 379 || i == 386 || i == 393 || i == 400 || i == 407
							|| i == 414 || i == 421 || i == 428 || i == 435 || i == 442 || i == 449 || i == 456 || i == 463 || i == 470 || i == 477 || i == 484 || i == 491
							|| i == 498 || i == 505 || i == 512 || i == 519) {
						
						/**
						 * 主角色
						 */
						String roleid = IDUtil.getId();
						TblBaseRoleEntity tblBaseRoleEntity = new TblBaseRoleEntity();
						tblBaseRoleEntity.setBaseroleid(roleid);
						tblBaseRoleEntity.setRolename(username);
						tblBaseRoleEntity.setBaserolepid("bd53557b4f3d42c7a4aa108e38dfaf11");
						tblBaseRoleEntity.setState(1);
						tblBaseRoleEntity.setBaseroletype(roletype);
						tblBaseRoleMapper.insert(tblBaseRoleEntity);
						
						/**
						 * 维护单位管理员角色
						 */
						String whid = IDUtil.getId();
						qqWhid = whid;
						TblBaseRoleEntity t1 = new TblBaseRoleEntity();
						t1.setBaseroleid(whid);
						t1.setRolename("维护单位管理员");
						t1.setBaserolepid(roleid);
						t1.setState(1);
						t1.setBaseroletype(roletype);
						tblBaseRoleMapper.insert(t1);
						
						/**
						 * 现场施工巡查员员角色
						 */
						String xcyid = IDUtil.getId();
						TblBaseRoleEntity t = new TblBaseRoleEntity();
						t.setBaseroleid(xcyid);
						t.setRolename("现场施工巡查员");
						t.setBaserolepid(roleid);
						t.setState(1);
						t.setBaseroletype(roletype);
						tblBaseRoleMapper.insert(t);
						
						addQs(whid, 1);
						addQs(xcyid, 2);
						addLc(whid, 1);
						addLc(xcyid, 2);
						addCd(whid, 1);
						addCd(xcyid, 2);
					}
					
					/**
					 * 为角色添加用户
					 */
					TblBaseUserRoleMappingEntity tblBaseUserRoleMappingEntity = new TblBaseUserRoleMappingEntity();
					tblBaseUserRoleMappingEntity.setBaseuserrolemid(IDUtil.getId());
					tblBaseUserRoleMappingEntity.setBaseuserid(userid);
					tblBaseUserRoleMappingEntity.setBaseroleid(qqWhid);
					tblBaseUserRoleMappingMapper.deleteByUserid(userid);
					tblBaseUserRoleMappingMapper.insert(tblBaseUserRoleMappingEntity);
				}
			}
		}
	}
	
	/**
	* @Package com.kingtopinfo.base.service  
	* @Description: 为角色添加按钮资源权限
	* @param type 1:维护单位管理员 2:现场施工巡查员员
	* @author cyf    
	* @date 2018年1月23日 下午3:08:19
	 */
	public void addQs(String id, int type) {
		/**
		 * 删除事件
		 */
		addRoleResouceMapping(id, "c65c54631bea45c3ad0d9d9aeea2475b");
		
		/**
		 * 确认权责
		 */
		addRoleResouceMapping(id, "4c73325cc6ee4cddaba7927822ae56fe");
		
		/**
		 * 现场处置
		 */
		addRoleResouceMapping(id, "122bafc0f7df4fefb75f132b5bbc4d37");
		
		/**
		 * 否认权责
		 */
		addRoleResouceMapping(id, "60ab651e47d24d8ab34ad5fc3bbe2a8d");
		
		if (type == 1) {
			/**
			 * 兜底
			 */
			addRoleResouceMapping(id, "02d10a12a79e4dd1a39309d68222cc88");
			/**
			 * 拒绝认领
			 */
			addRoleResouceMapping(id, "575d30962a3e442bae3536fcb0853e2f");
			/**
			 * 认领事件
			 */
			addRoleResouceMapping(id, "2b50216c6b14494eb47bec0b00a1eeaa");
		}
	}
	
	/**
	* @Package com.kingtopinfo.base.service  
	* @Description: 添加流程权限
	* @author cyf    
	* @date 2018年1月23日 下午4:01:36
	 */
	public void addLc(String id, int type) {
		
		if (type == 1) {
			addFlowTaskRoleMapping(id, "sjrl");
			addFlowTaskRoleMapping(id, "cxpd");
		}
		
		addFlowTaskRoleMapping(id, "qzqr");
		addFlowTaskRoleMapping(id, "czqksb");
		
	}
	
	/**
	* @Package com.kingtopinfo.base.service  
	* @Description: 添加菜单权限
	* @author cyf    
	* @date 2018年1月23日 下午6:08:18
	 */
	public void addCd(String id, int type) {
		if (type == 1) {
			/**
			 * 系统管理
			 */
			addRoleMenuMapping(id, "38ae10f4081147129ba8939871d0d9b1");
			/**
			 * 用户管理
			 */
			addRoleMenuMapping(id, "1f6cb2b528034a49accd4d8ceaef1181");
		}
		/**
		 * 窨井事件管理
		 */
		addRoleMenuMapping(id, "ec926f78d1174dad81a13008e56cdfaa");
		/**
		 * 事件管理
		 */
		addRoleMenuMapping(id, "4e62a2e66b2b4a03bf95f77fac92c493");
		/**
		 * 已接事件
		 */
		addRoleMenuMapping(id, "700da05752f040d39a5a03bc195704f0");
		/**
		 * 任务中心
		 */
		addRoleMenuMapping(id, "e9fe4c2924414107a51969fea3d7af89");
		/**
		 * 系统工具
		 */
		addRoleMenuMapping(id, "c7590da4624a4ed785d3e3c8cb0ab991");
		/**
		 * 通讯录
		 */
		addRoleMenuMapping(id, "878d73f78e38483dadf4c4929b19cde6");
		/**
		 * 备忘录
		 */
		addRoleMenuMapping(id, "f0e4a72f82eb4c06b476f2801a90d312");
		
	}
	
	public void addFlowTaskRoleMapping(String id, String taskid) {
		TblFlowTaskRoleMappingEntity e = new TblFlowTaskRoleMappingEntity();
		e.setFlowtaskroleid(IDUtil.getId());
		e.setDxlx("1");
		e.setRoleid(id);
		e.setTaskid(taskid);
		e.setProcessid("xmyjglxt:1:202559");
		tblFlowTaskRoleMappingMapper.insert(e);
	}
	
	public void addRoleResouceMapping(String id, String resourceid) {
		TblBaseRoleResourceMappingEntity e7 = new TblBaseRoleResourceMappingEntity();
		e7.setBaseroleresourceid(IDUtil.getId());
		e7.setBaseroleid(id);
		e7.setBaseresourceid(resourceid);
		tblBaseRoleResourceMappingMapper.insert(e7);
	}
	
	public void addRoleMenuMapping(String id, String menuid) {
		TblBaseRoleMenuMappingEntity e = new TblBaseRoleMenuMappingEntity();
		e.setBasemenuid(menuid);
		e.setBaseroleid(id);
		e.setBaserolemenumid(IDUtil.getId());
		tblBaseRoleMenuMappingMapper.insert(e);
	}
	
	public void deleteFlowTaskRoleMapping(String roleid) {
		tblFlowTaskRoleMappingMapper.deleteByRoleid(roleid);
	}
	
	public void deleteMenuMapping(String roleid) {
		tblBaseRoleMenuMappingMapper.deleteByRoleid(roleid);
	}
	
	public void checkFirstLogin() {
		TblBaseUserEntity tblBaseUserEntity = getByPkey(UserInfoUtil.getBaseuserid());
		if (tblBaseUserEntity.getIslogin().equals("0")) {
			refreshResourceService.refreshCache();
			tblBaseUserEntity.setIslogin("1");
			tblBaseUserMapper.update(tblBaseUserEntity);
		}
	}
	
	/**
	* @Package com.kingtopinfo.base.service  
	* @Description: 通过账号查询用户是否锁定
	* @author cyf    
	* @date 2018年3月7日 上午11:02:52
	 */
	public Map<String, Object> selectUserByAccount(String account) {
		Map<String, Object> map = new HashMap<String, Object>();
		TblBaseUserEntity tblBaseUserEntity = tblBaseUserMapper.selectUserByAccount(account);
		if (tblBaseUserEntity == null) {
			map.put("tip", "账号不存在！");
		} else if (tblBaseUserEntity.getState() == 1) {
			map.put("tip", "success");
		} else if (tblBaseUserEntity.getState() == 0) {
			map.put("tip", "账号已被锁定！");
		}
		return map;
	}
}
