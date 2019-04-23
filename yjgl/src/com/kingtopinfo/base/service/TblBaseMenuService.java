package com.kingtopinfo.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.base.entity.TblBaseMenuEntity;
import com.kingtopinfo.base.entity.TblBaseMenuListEntity;
import com.kingtopinfo.base.entity.TblBaseResourceEntity;
import com.kingtopinfo.base.mapper.TblBaseMenuMapper;
import com.kingtopinfo.base.mapper.TblBaseResourceMapper;
import com.kingtopinfo.base.mapper.TblBaseRoleMenuMappingMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;

/**
 * @ClassName com.kingtopinfo.base.service.TBaseMenuService
 * @Description T_BASE_MENU表服务类
 * @author dzb@kingtopinfo.com
 * @date 2014-02-20 09:12:37
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblBaseMenuService {
	
	@Autowired
	private TblBaseMenuMapper tblBaseMenuMapper;
	@Autowired
	private TblBaseResourceMapper			tblBaseResourceMapper;
	
	@Autowired
	private TblBaseRoleMenuMappingMapper tblBaseRoleMenuMappingMapper;
	
	public List<TblBaseMenuListEntity> select(){
		return tblBaseMenuMapper.select();
	}
	
	public TblBaseMenuEntity getByKey(String basemenuid) {
		return tblBaseMenuMapper.getByKey(basemenuid);
	}
	
	public List<TblBaseMenuListEntity> menuList(String baseuserid, HttpSession httpSession) {
		Map<String, TblBaseResourceEntity> resourceNoMap = new HashMap<String, TblBaseResourceEntity>();
		List<String> roleIdList = tblBaseMenuMapper.selectRoleByUserId(baseuserid);
		List<TblBaseMenuListEntity> menuEntityList = new ArrayList<TblBaseMenuListEntity>();
		if (roleIdList.size() > 0) {
			List<TblBaseMenuEntity> list = tblBaseMenuMapper.selectMenuByRoids(roleIdList);
			for (TblBaseMenuEntity e : list) {
				TblBaseMenuListEntity oMenuEntity = new TblBaseMenuListEntity();
				oMenuEntity.setE(e);
				List<TblBaseMenuEntity> l = tblBaseMenuMapper.selectByRole(roleIdList, e.getBasemenuid());
				// List<TblBaseMenuEntity> l = tblBaseMenuMapper.selectMenuByPid(e.getBasemenuid());
				List<TblBaseMenuEntity> TBaseMenuEntityList = new ArrayList<TblBaseMenuEntity>();
				for (TblBaseMenuEntity o : l) {
					TBaseMenuEntityList.add(o);
				}
				oMenuEntity.setList(TBaseMenuEntityList);
				menuEntityList.add(oMenuEntity);
			}
			List<TblBaseResourceEntity> resourceList = tblBaseResourceMapper.selectInRoleId(roleIdList);
			for (TblBaseResourceEntity tblBaseResourceEntity : resourceList) {
				if (tblBaseResourceEntity.getResourceno() != null) {
					resourceNoMap.put(tblBaseResourceEntity.getResourceno(), tblBaseResourceEntity);
				}
			}
		}
		httpSession.setAttribute("resourceNoMap", resourceNoMap);// 保存资源项
		return menuEntityList;
	}
	
	/**
	 * @Description:新增菜单
	 * @author:cyf
	 * @time:2017年6月8日 上午8:58:23
	 */
	public int insert(TblBaseMenuEntity e) {
		e.setBasemenuid(IDUtil.getId());
		int maxSequ = tblBaseMenuMapper.selectMaxSequ();
		e.setSequ(maxSequ + 1);
		if (e.getBasemenupid().equals("")) {
			e.setBasemenupid("-");
		}
		return tblBaseMenuMapper.insert(e);
	}
	
	/**
	 * @Description:编辑菜单
	 * @author:cyf
	 * @time:2017年6月8日 上午8:58:23
	 */
	public int update(TblBaseMenuEntity e) {
		TblBaseMenuEntity tblBaseMenuEntity = tblBaseMenuMapper.getByKey(e.getBasemenuid());
		tblBaseMenuEntity.setMenuname(e.getMenuname());
		tblBaseMenuEntity.setState(e.getState());
		tblBaseMenuEntity.setSrc(e.getSrc());
		tblBaseMenuEntity.setUpdateuserid(UserInfoUtil.getBaseuserid());
		tblBaseMenuEntity.setUpdatetime(new Date());
		int row = tblBaseMenuMapper.update(tblBaseMenuEntity);
		return row;
	}
	
	/**
	 * @Description:删除菜单
	 * @author:cyf
	 * @time:2017年6月8日 上午9:57:06
	 */
	public int delete(String basemenuid) {
		int rows = 0;
		List<TblBaseMenuEntity> list = tblBaseMenuMapper.selectByPid(basemenuid);
		if (list != null && !list.isEmpty()) {
			for (TblBaseMenuEntity each : list) {
				delete(each.getBasemenuid());
			}
		}
		rows += tblBaseMenuMapper.delete(basemenuid);
		tblBaseRoleMenuMappingMapper.deleteByMenuid(basemenuid);
		return rows;
	}
	
	/**
	 * @Description:更改菜单节点
	 * @author:cyf
	 * @time:2017年6月8日 上午11:14:15
	 */
	public int move(TblBaseMenuEntity e) {
		return tblBaseMenuMapper.move(e);
	}
	
	/**
	 * @Description:更改菜单子节点
	 * @author:cyf
	 * @time:2017年6月8日 上午11:14:15
	 */
	public int moveUpOrDown(String currentBasemenuid, int currentSequ, String npBasemenuid, int npSequ) {
		TblBaseMenuEntity current = new TblBaseMenuEntity();
		TblBaseMenuEntity np = new TblBaseMenuEntity();
		current.setBasemenuid(currentBasemenuid);
		current.setSequ(npSequ);
		int rows = tblBaseMenuMapper.updateSequ(current);
		np.setBasemenuid(npBasemenuid);
		np.setSequ(currentSequ);
		rows += tblBaseMenuMapper.updateSequ(np);
		return rows;
	}
	
	/**
	 * @Description:更改菜单顶级节点
	 * @author:cyf
	 * @param state
	 *            1:上移 0:下移
	 * @time:2017年6月8日 上午11:14:15
	 */
	public Map<String, Object> moveTopUpOrDown(String currentBasemenuid, Integer currentSequ, Integer state) {
		Map<String, Object> map = new HashMap<String, Object>();
		TblBaseMenuEntity current = new TblBaseMenuEntity();
		if (state == 0) {
			TblBaseMenuEntity np = tblBaseMenuMapper.secTopMinMsg(currentBasemenuid);
			
			if (np == null) {
				map.put("success", false);
				map.put("msg", "该节点已为最底层节点，无法移动。");
				return map;
			}
			current.setBasemenuid(currentBasemenuid);
			current.setSequ(np.getSequ());
			tblBaseMenuMapper.updateSequ(current);
			np.setSequ(currentSequ);
			tblBaseMenuMapper.updateSequ(np);
		}else{
			TblBaseMenuEntity np = tblBaseMenuMapper.secTopMaxMsg(currentBasemenuid);
			if (np == null) {
				map.put("success", false);
				map.put("msg", "该节点已为最顶层节点，无法移动。");
				return map;
			}
			current.setBasemenuid(currentBasemenuid);
			current.setSequ(np.getSequ());
			tblBaseMenuMapper.updateSequ(current);
			np.setSequ(currentSequ);
			tblBaseMenuMapper.updateSequ(np);
		}
		map.put("success", true);
		return map;
	}
	
	public List<String> getResUrl(String roleid){
		
		return tblBaseMenuMapper.selectUrlByRoleId(roleid);
	}
	
	public List<TblBaseMenuEntity> selectAllUrl(){
		return tblBaseMenuMapper.selectAllUrl();
	}
	
	public List<TblBaseMenuEntity> selectByUrl(String url){
		return tblBaseMenuMapper.selectByUrl(url);
	}
	
	public TblBaseMenuEntity selectByKey(String id){
		return tblBaseMenuMapper.selectByKey(id);
	}
	
	public List<String> selectRoleByUrl(String url){
		return tblBaseMenuMapper.selectRoleByUrl(url);
	}
	
	/**
	 * @Description 按条件查询总条数
	 * @param e
	 *            TblBaseMenuEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-07-28 09:39:57
	 */
	public int getCount(TblBaseMenuEntity e) {
		return tblBaseMenuMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e
	 *            TblBaseMenuEntity实体
	 * @param rowBounds
	 *            分页实体
	 * @return TblBaseMenuEntity集合
	 * @author cyf
	 * @date 2017-07-28 09:39:57
	 */
	public List<TblBaseMenuEntity> selectPagination(TblBaseMenuEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblBaseMenuMapper.selectPagination(e, rowBounds);
	}
	
	public List<TblBaseMenuEntity> selectMenuTreeByRoleId() {
		List<String> roleIdList = tblBaseMenuMapper.selectRoleByUserId(UserInfoUtil.getBaseuserid());
		List<TblBaseMenuEntity> list = tblBaseMenuMapper.selectMenuByRoids(roleIdList);
		return list;
	}
	
	public List<TblBaseMenuEntity> selectroleOfMenu(String roleid) {
		return tblBaseMenuMapper.selectroleOfMenu(roleid);
	}
	
}