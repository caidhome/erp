package cn.itcast.erp.invoice.goodstype.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import cn.itcast.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.util.base.BaseQueryModel;

public class GoodsTypeEbo implements GoodsTypeEbi {

	private GoodsTypeDao goodsTypeDao;

	public void setGoodsTypeDao(GoodsTypeDao goodsTypeDao) {
		this.goodsTypeDao = goodsTypeDao;
	}

	public void save(GoodsTypeModel gm) {
		goodsTypeDao.save(gm);
	}

	public void update(GoodsTypeModel gm) {
		goodsTypeDao.update(gm);
	}

	public void delete(GoodsTypeModel gm) {
		goodsTypeDao.delete(gm);
	}

	public List<GoodsTypeModel> getAll() {
		return goodsTypeDao.getAll();
	}

	public GoodsTypeModel get(Serializable uuid) {
		return goodsTypeDao.get(uuid);
	}

	public List<GoodsTypeModel> getAll(BaseQueryModel gqm, Integer pageNum,
			Integer pageCount) {
		return goodsTypeDao.getAll(gqm, pageNum, pageCount);
	}

	public Integer getCount(BaseQueryModel gqm) {
		return goodsTypeDao.getCount(gqm);
	}

	public List<GoodsTypeModel> getBySmUuid(Long uuid) {
		return goodsTypeDao.getBySmUuid(uuid);
	}

	public List<GoodsTypeModel> getBySmUuidUnionTwo(Long supplierUuid) {
		return goodsTypeDao.getAllUnionTwo(supplierUuid);
	}

	public List<GoodsTypeModel> getUnionBySmUuid(Long uuid) {
		return goodsTypeDao.getUnionBySmUuid(uuid);
	}
}