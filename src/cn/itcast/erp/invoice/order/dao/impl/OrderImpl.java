package cn.itcast.erp.invoice.order.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.invoice.order.dao.dao.OrderDao;
import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class OrderImpl extends BaseImpl<OrderModel> implements OrderDao {

	public void doQbc(DetachedCriteria dc, BaseQueryModel qm) {
		OrderQueryModel oqm = (OrderQueryModel) qm;
		dc.createAlias("sm", "s");
		// TODO 编写自定义查询条件
		if (oqm.getOrderType() != null && oqm.getOrderType() != -1) {
			dc.add(Restrictions.eq("orderType", oqm.getOrderType()));
		}
		if (oqm.getCreater() != null && oqm.getCreater().getName() != null
				&& oqm.getCreater().getName().trim().length() > 0) {
			dc.createAlias("creater", "c1");
			dc.add(Restrictions.like("c1.name", "%"
					+ oqm.getCreater().getName().trim() + "%"));
		}

		if (oqm.getType() != null && oqm.getType() != -1) {
			dc.add(Restrictions.eq("type", oqm.getType()));
		}

		if (oqm.getChecker() != null && oqm.getChecker().getName() != null
				&& oqm.getChecker().getName().trim().length() > 0) {
			dc.createAlias("checker", "c2");
			dc.add(Restrictions.like("c2.name", "%"
					+ oqm.getChecker().getName().trim() + "%"));
		}

		if (oqm.getCompleter() != null && oqm.getCompleter().getName() != null
				&& oqm.getCompleter().getName().trim().length() > 0) {
			dc.createAlias("completer", "c3");
			dc.add(Restrictions.like("c3.name", "%"
					+ oqm.getCompleter().getName().trim() + "%"));
		}

		if (oqm.getCompleter() != null && oqm.getCompleter().getUuid() != null
				&& oqm.getCompleter().getUuid() != -1) {
			dc.add(Restrictions.eq("completer", oqm.getCompleter()));
		}
		if (oqm.getSm() != null && oqm.getSm().getUuid() != -1) {
			dc.add(Restrictions.eq("s.uuid", oqm.getSm().getUuid()));
		}
		if (oqm.getSm() != null && oqm.getSm().getNeeds() != null
				&& oqm.getSm().getNeeds() != -1) {
			dc.add(Restrictions.eq("s.needs", oqm.getSm().getNeeds()));
		}
	}

	public void doQbc2(DetachedCriteria dc, BaseQueryModel qm,
			Integer orderType[]) {

		dc.add(Restrictions.in("orderType", orderType));
		doQbc(dc, qm);
	}

	public void doQbc3(DetachedCriteria dc, BaseQueryModel qm,
			Integer orderType[]) {

		dc.add(Restrictions.in("type", orderType));
		doQbc(dc, qm);
	}

	public List<OrderModel> getAllOrderType(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount, Integer[] buyCheckOrderTypes) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		doQbc2(dc, oqm, buyCheckOrderTypes);
		return this.getHibernateTemplate().findByCriteria(dc,
				(pageNum - 1) * pageCount, pageCount);
	}

	public int getCountOrderType(OrderQueryModel oqm,
			Integer[] buyCheckOrderTypes) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		dc.setProjection(Projections.rowCount());
		doQbc2(dc, oqm, buyCheckOrderTypes);
		List<Long> list = this.getHibernateTemplate().findByCriteria(dc);
		return list.get(0).intValue();
	}

	public Integer getCountTask(OrderQueryModel oqm, Integer[] taskTypes) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		dc.setProjection(Projections.rowCount());
		doQbc3(dc, oqm, taskTypes);
		List<Long> list = this.getHibernateTemplate().findByCriteria(dc);
		return list.get(0).intValue();
	}

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount, Integer[] taskTypes) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		doQbc3(dc, oqm, taskTypes);
		return this.getHibernateTemplate().findByCriteria(dc,
				(pageNum - 1) * pageCount, pageCount);
	}
}