package com.kingtopinfo.base.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName com.kingtopinfo.base.entity.TBaseMenuEntity
 * @Description T_BASE_MENU表映射实体类
 * @author dzb@kingtopinfo.com
 * @date 2014-02-20 09:12:37
 * @version 1.0
 * @remark create by generator
 */

public class TblBaseMenuListEntity implements Serializable {
	
	private static final long		serialVersionUID	= -7909051698978301354L;
	private List<TblBaseMenuEntity>	list	= new ArrayList<TblBaseMenuEntity>();
	private TblBaseMenuEntity		e		= new TblBaseMenuEntity();
	
	public List<TblBaseMenuEntity> getList() {
		return list;
	}
	
	public void setList(List<TblBaseMenuEntity> list) {
		this.list = list;
	}
	
	public TblBaseMenuEntity getE() {
		return e;
	}
	
	public void setE(TblBaseMenuEntity e) {
		this.e = e;
	}
}