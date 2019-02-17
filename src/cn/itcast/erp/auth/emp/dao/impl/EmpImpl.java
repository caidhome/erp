package cn.itcast.erp.auth.emp.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.auth.emp.dao.dao.EmpDao;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.emp.vo.EmpQueryModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class EmpImpl extends BaseImpl<EmpModel> implements EmpDao {

	public void doQbc(DetachedCriteria dc, BaseQueryModel qm) {
		EmpQueryModel dqm = (EmpQueryModel) qm;
		// TODO 编写自定义查询条件
		if (dqm.getUsername() != null && dqm.getUsername().trim().length() > 0) {
			dc.add(Restrictions.eq("username", dqm.getUsername()));
		}
		if (dqm.getName() != null && dqm.getName().trim().length() > 0) {
			dc.add(Restrictions.like("name", "%" + dqm.getName() + "%"));
		}

		if (dqm.getTele() != null && dqm.getTele().trim().length() > 0) {
			dc.add(Restrictions.like("tele", "%" + dqm.getTele() + "%"));
		}
		if (dqm.getGender() != null && dqm.getGender() != -1) {
			System.out.println(dqm.getGender());
			dc.add(Restrictions.eq("gender", dqm.getGender()));
		}
		if (dqm.getEmail() != null && dqm.getEmail().trim().length() > 0) {
			dc.add(Restrictions.like("email", "%" + dqm.getEmail() + "%"));
		}
		if (dqm.getDm() != null && dqm.getDm().getUuid() != null
				&& dqm.getDm().getUuid() != -1) {
			dc.add(Restrictions.like("dm", dqm.getDm()));
		}

		if (dqm.getBirthday() != null) {
			dc.add(Restrictions.ge("birthday", dqm.getBirthday()));
		}
		if (dqm.getBirthday2() != null) {
			dc.add(Restrictions.le("birthday",
					dqm.getBirthday2() + 86400000 - 1));
		}
	}

	public EmpModel getByUsernameAndPwd(String username, String pwd) {
		String hql = "from EmpModel where username = ? and pwd = ?";
		List<EmpModel> list = this.getHibernateTemplate().find(hql, username,
				pwd);
		return list.size() > 0 ? list.get(0) : null;
	}

	public boolean changePwdByUsernameAndPwd(String username, String pwd,
			String newPwd) {
		String hql = "update EmpModel set pwd=? where pwd=? and username=?";
		int row = this.getHibernateTemplate().bulkUpdate(hql, newPwd, pwd,
				username);
		return row > 0;
	}

	public List<EmpModel> getByDepUuid(Long uuid) {
		String hql = "from EmpModel where dm.uuid=?";
		return this.getHibernateTemplate().find(hql, uuid);
	}
}