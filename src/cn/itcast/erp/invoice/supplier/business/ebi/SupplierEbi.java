package cn.itcast.erp.invoice.supplier.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface SupplierEbi extends BaseEbi<SupplierModel> {

	/**
	 * ������Ʒ���ͻ�ȡ��Ӧ����Ϣ������ȡ���й�������Ʒ���͵Ĺ�Ӧ��
	 * 
	 * @return
	 */
	public List<SupplierModel> getAllUnion();

	public List<SupplierModel> getAllUnionTwo();

}