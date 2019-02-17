package cn.itcast.erp.invoice.goodstype.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface GoodsTypeEbi extends BaseEbi<GoodsTypeModel> {

	/**
	 * ���ݹ�Ӧ����Ϣ��ѯ��Ʒ�����Ϣ
	 * 
	 * @param uuid
	 *            ��Ӧ��ID
	 * @return
	 */
	public List<GoodsTypeModel> getBySmUuid(Long uuid);

	public List<GoodsTypeModel> getBySmUuidUnionTwo(Long supplierUuid);

	public List<GoodsTypeModel> getUnionBySmUuid(Long uuid);

}