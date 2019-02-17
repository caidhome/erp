package cn.itcast.erp.auth.res.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.erp.auth.res.dao.dao.ResDao;
import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.auth.res.vo.ResQueryModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class ResImpl extends BaseImpl<ResModel> implements ResDao {

	public void doQbc(DetachedCriteria dc, BaseQueryModel qm) {
		ResQueryModel rqm = (ResQueryModel) qm;
		// TODO ��д�Զ����ѯ����

	}

	public List<ResModel> getAll(Long uuid) {
		String hql = "select distinct res from EmpModel em join em.roles rm join rm.reses res where em.uuid=?";
		return this.getHibernateTemplate().find(hql, uuid);
	}
}