package cn.itcast.erp.invoice.goods.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface GoodsEbi extends BaseEbi<GoodsModel> {

	/**
	 * ������Ʒ�����Ϣ��ȡ������Ʒ��Ϣ
	 * 
	 * @param uuid
	 *            ��Ʒ���ID
	 * @return
	 */
	public List<GoodsModel> getByGtm(Long uuid);

	public void goodsUseNumUpdate();

}