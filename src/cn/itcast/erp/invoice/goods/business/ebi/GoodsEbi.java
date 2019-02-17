package cn.itcast.erp.invoice.goods.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface GoodsEbi extends BaseEbi<GoodsModel> {

	/**
	 * 根据商品类别信息获取所有商品信息
	 * 
	 * @param uuid
	 *            商品类别ID
	 * @return
	 */
	public List<GoodsModel> getByGtm(Long uuid);

	public void goodsUseNumUpdate();

}