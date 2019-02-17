package cn.itcast.erp.invoice.storedetail.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import cn.itcast.erp.invoice.storedetail.vo.StoreDetailModel;
import cn.itcast.erp.invoice.storedetail.vo.StoreDetailQueryModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class StoreDetailImpl extends BaseImpl<StoreDetailModel> implements
		StoreDetailDao {

	public void doQbc(DetachedCriteria dc, BaseQueryModel qm) {
		StoreDetailQueryModel sqm = (StoreDetailQueryModel) qm;
		// TODO 编写自定义查询条件

	}

	public StoreDetailModel getByGmAndSm(Long uuid, Long storeUuid) {
		String hql = "from StoreDetailModel where gm.uuid=? and sm.uuid=?";
		List<StoreDetailModel> sdmList = this.getHibernateTemplate().find(hql,
				uuid, storeUuid);
		return sdmList.size() > 0 ? sdmList.get(0) : null;
	}
}