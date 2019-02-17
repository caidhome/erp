package cn.itcast.erp.invoice.goods.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.erp.invoice.goods.business.ebi.GoodsEbi;
import cn.itcast.erp.invoice.goods.dao.dao.GoodsDao;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.util.base.BaseQueryModel;

public class GoodsEbo implements GoodsEbi {

	private GoodsDao goodsDao;

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void save(GoodsModel gm) {
		gm.setUseNum(0);
		goodsDao.save(gm);
	}

	public void update(GoodsModel gm) {
		gm.setUseNum(gm.getUseNum());
		// goodsDao.update(gm);
	}

	public void delete(GoodsModel gm) {
		goodsDao.delete(gm);
	}

	public List<GoodsModel> getAll() {
		return goodsDao.getAll();
	}

	public GoodsModel get(Serializable uuid) {
		return goodsDao.get(uuid);
	}

	public List<GoodsModel> getAll(BaseQueryModel gqm, Integer pageNum,
			Integer pageCount) {
		return goodsDao.getAll(gqm, pageNum, pageCount);
	}

	public Integer getCount(BaseQueryModel gqm) {
		return goodsDao.getCount(gqm);
	}

	public List<GoodsModel> getByGtm(Long uuid) {
		return goodsDao.getByGtmUuid(uuid);
	}

	public void goodsUseNumUpdate() {
		goodsDao.goodsUseNumUpdate();
	}

}