package cn.itcast.erp.invoice.goodstype.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeQueryModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class GoodsTypeImpl extends BaseImpl<GoodsTypeModel> implements
		GoodsTypeDao {

	public void doQbc(DetachedCriteria dc, BaseQueryModel qm) {
		GoodsTypeQueryModel gqm = (GoodsTypeQueryModel) qm;
		// TODO ��д�Զ����ѯ����

	}

	public List<GoodsTypeModel> getBySmUuid(Long uuid) {
		String hql = "from GoodsTypeModel where sm.uuid=?";
		return this.getHibernateTemplate().find(hql, uuid);
	}

	public List<GoodsTypeModel> getAllUnionTwo(Long supplierUuid) {
		String hql = "select distinct s from GoodsModel gm join gm.gtm gt join gt.sm s";
		return this.getHibernateTemplate().find(hql, supplierUuid);
	}

	public List<GoodsTypeModel> getUnionBySmUuid(Long uuid) {
		String hql = "select distinct gt from GoodsModel gm join gm.gtm gt where gt.sm.uuid=?";
		return this.getHibernateTemplate().find(hql, uuid);
	}
}