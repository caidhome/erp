package cn.itcast.erp.auth.dep.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.erp.auth.dep.business.ebi.DepEbi;
import cn.itcast.erp.auth.dep.dao.dao.DepDao;
import cn.itcast.erp.auth.dep.vo.DepModel;
import cn.itcast.erp.util.base.BaseQueryModel;

public class DepEbo implements DepEbi {

	private DepDao depDao;

	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
	}

	public void save(DepModel dm) {
		depDao.save(dm);
	}

	public void update(DepModel dm) {
		depDao.update(dm);
	}

	public void delete(DepModel dm) {
		depDao.delete(dm);
	}

	public List<DepModel> getAll() {
		return depDao.getAll();
	}

	public DepModel get(Serializable uuid) {
		return depDao.get(uuid);
	}

	/*
	 * public List<DepModel> getAll(BaseQueryModel dqm) { return
	 * depDao.getAll(dqm); }
	 */

	public List<DepModel> getAll(BaseQueryModel dqm, Integer pageNum,
			Integer pageCount) {
		return depDao.getAll(dqm, pageNum, pageCount);
	}

	public Integer getCount(BaseQueryModel dqm) {
		return depDao.getCount(dqm);
	}

}
