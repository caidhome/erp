package cn.itcast.erp.auth.dep.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.auth.dep.dao.dao.DepDao;
import cn.itcast.erp.auth.dep.vo.DepModel;
import cn.itcast.erp.auth.dep.vo.DepQueryModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class DepImpl extends BaseImpl<DepModel> implements DepDao {

	/*
	 * public void save(DepModel dm) { this.getHibernateTemplate().save(dm); }
	 * 
	 * public List<DepModel> getAll() { String hql = "from DepModel"; return
	 * this.getHibernateTemplate().find(hql); }
	 * 
	 * public DepModel findByUuid(Long uuid) { return
	 * this.getHibernateTemplate().get(DepModel.class, uuid); }
	 * 
	 * public void update(DepModel dm) { this.getHibernateTemplate().update(dm);
	 * }
	 * 
	 * public void delete(DepModel dm) { this.getHibernateTemplate().delete(dm);
	 * }
	 * 
	 * public List<DepModel> getAll(DepQueryModel dqm) {
	 * 
	 * DetachedCriteria dc = DetachedCriteria.forClass(DepModel.class); if
	 * (dqm.getName() != null && dqm.getName().trim().length() > 0) {
	 * dc.add(Restrictions.like("name", "%" + dqm.getName() + "%")); } if
	 * (dqm.getTele() != null && dqm.getTele().trim().length() > 0) {
	 * dc.add(Restrictions.like("tele", "%" + dqm.getTele() + "%")); } return
	 * this.getHibernateTemplate().findByCriteria(dc); }
	 * 
	 * public List<DepModel> getAll(DepQueryModel dqm, Integer pageNum, Integer
	 * pageCount) { DetachedCriteria dc =
	 * DetachedCriteria.forClass(DepModel.class);
	 * 
	 * if (dqm.getName() != null && dqm.getName().trim().length() > 0) {
	 * dc.add(Restrictions.like("name", "%" + dqm.getName() + "%")); } if
	 * (dqm.getTele() != null && dqm.getTele().trim().length() > 0) {
	 * dc.add(Restrictions.like("tele", "%" + dqm.getTele() + "%")); } return
	 * this.getHibernateTemplate().findByCriteria(dc, (pageNum - 1) * pageCount,
	 * pageCount); }
	 * 
	 * public Integer getCount(DepQueryModel dqm) { DetachedCriteria dc =
	 * DetachedCriteria.forClass(DepModel.class);
	 * dc.setProjection(Projections.rowCount()); if (dqm.getName() != null &&
	 * dqm.getName().trim().length() > 0) { dc.add(Restrictions.like("name", "%"
	 * + dqm.getName() + "%")); } if (dqm.getTele() != null &&
	 * dqm.getTele().trim().length() > 0) { dc.add(Restrictions.like("tele", "%"
	 * + dqm.getTele() + "%")); } List<Long> list =
	 * this.getHibernateTemplate().findByCriteria(dc); return
	 * list.get(0).intValue(); }
	 * 
	 * @Override public DepModel get(Serializable uuid) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * public List<DepModel> getAll(BaseQueryModel qm, Integer pageNum, Integer
	 * pageCount) { // TODO Auto-generated method stub return null; }
	 * 
	 * public Integer getCount(BaseQueryModel qm) { // TODO Auto-generated
	 * method stub return null; }
	 */
	public void doQbc(DetachedCriteria dc, BaseQueryModel qm) {
		DepQueryModel dqm = (DepQueryModel) qm;
		if (dqm.getName() != null && dqm.getName().trim().length() > 0) {
			dc.add(Restrictions.like("name", "%" + dqm.getName().trim() + "%"));
		}
		if (dqm.getTele() != null && dqm.getTele().trim().length() > 0) {
			dc.add(Restrictions.like("tele", "%" + dqm.getTele().trim() + "%"));
		}
	}
}
