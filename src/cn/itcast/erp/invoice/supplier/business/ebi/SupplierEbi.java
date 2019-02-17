package cn.itcast.erp.invoice.supplier.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface SupplierEbi extends BaseEbi<SupplierModel> {

	/**
	 * 联合商品类型获取供应商信息，即获取所有关联着商品类型的供应商
	 * 
	 * @return
	 */
	public List<SupplierModel> getAllUnion();

	public List<SupplierModel> getAllUnionTwo();

}