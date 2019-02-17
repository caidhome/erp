package cn.itcast.erp.auth.res.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.erp.auth.res.business.ebi.ResEbi;
import cn.itcast.erp.auth.res.dao.dao.ResDao;
import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.util.base.BaseQueryModel;

public class ResEbo implements ResEbi {

	private ResDao resDao;

	public void setResDao(ResDao resDao) {
		this.resDao = resDao;
	}

	public void save(ResModel dm) {
		resDao.save(dm);
	}

	public void update(ResModel rm) {
		resDao.update(rm);
	}

	public void delete(ResModel rm) {
		resDao.delete(rm);
	}

	public List<ResModel> getAll() {
		return resDao.getAll();
	}

	public ResModel get(Serializable uuid) {
		return resDao.get(uuid);
	}

	public List<ResModel> getAll(BaseQueryModel rqm, Integer pageNum,
			Integer pageCount) {
		return resDao.getAll(rqm, pageNum, pageCount);
	}

	public Integer getCount(BaseQueryModel rqm) {
		return resDao.getCount(rqm);
	}

	public List<ResModel> getAllByEmp(Long uuid) {
		return resDao.getAll(uuid);
	}
}