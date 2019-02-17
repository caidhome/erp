package cn.itcast.erp.invoice.bill.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.erp.invoice.bill.dao.dao.BillDao;
import cn.itcast.erp.invoice.bill.vo.BillQueryModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;

public class BillImpl extends HibernateDaoSupport implements BillDao {

	public List<Object[]> getBuyBill(BillQueryModel bqm) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderDetailModel.class);
		ProjectionList pjList = Projections.projectionList();
		pjList.add(Projections.groupProperty("gm"));
		pjList.add(Projections.sum("num"));
		dc.setProjection(pjList);
		// Ìõ¼þ
		dc.createAlias("om", "o");
		if (bqm.getType() != null && bqm.getType() != -1) {
			dc.add(Restrictions.eq("o.type", bqm.getType()));
		}
		if (bqm.getSupplierUuid() != null && bqm.getSupplierUuid() != -1) {
			dc.createAlias("o.sm", "s");
			dc.add(Restrictions.eq("s.uuid", bqm.getSupplierUuid()));
		}

		return this.getHibernateTemplate().findByCriteria(dc);
	}

	public List<OrderDetailModel> getBuyBillDetail(BillQueryModel bqm) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderDetailModel.class);
		dc.add(Restrictions.eq("gm.uuid", bqm.getGoodsUuid()));

		dc.createAlias("om", "o");
		if (bqm.getType() != null && bqm.getType() != -1) {
			dc.add(Restrictions.eq("o.type", bqm.getType()));
		}
		if (bqm.getSupplierUuid() != null && bqm.getSupplierUuid() != -1) {
			dc.createAlias("o.sm", "s");
			dc.add(Restrictions.eq("s.uuid", bqm.getSupplierUuid()));
		}

		return this.getHibernateTemplate().findByCriteria(dc);
	}
}