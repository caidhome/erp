package cn.itcast.erp.invoice.storeoper.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.erp.invoice.storeoper.business.ebi.StoreOperEbi;
import cn.itcast.erp.invoice.storeoper.dao.dao.StoreOperDao;
import cn.itcast.erp.invoice.storeoper.vo.StoreOperModel;
import cn.itcast.erp.util.base.BaseQueryModel;

public class StoreOperEbo implements StoreOperEbi {

	private StoreOperDao storeOperDao;
	public void setStoreOperDao(StoreOperDao storeOperDao) {
		this.storeOperDao = storeOperDao;
	}

	public void save(StoreOperModel sm) {
		storeOperDao.save(sm);
	}

	public void update(StoreOperModel sm) {
		storeOperDao.update(sm);
	}

	public void delete(StoreOperModel sm) {
		storeOperDao.delete(sm);
	}

	public List<StoreOperModel> getAll() {
		return storeOperDao.getAll();
	}

	public StoreOperModel get(Serializable uuid) {
		return storeOperDao.get(uuid);
	}

	public List<StoreOperModel> getAll(BaseQueryModel sqm, Integer pageNum, Integer pageCount) {
		return storeOperDao.getAll(sqm, pageNum, pageCount);
	}

	public Integer getCount(BaseQueryModel sqm) {
		return storeOperDao.getCount(sqm);
	}
}