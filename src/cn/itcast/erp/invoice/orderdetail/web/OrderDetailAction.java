package cn.itcast.erp.invoice.orderdetail.web;

import java.util.List;

import cn.itcast.erp.invoice.orderdetail.business.ebi.OrderDetailEbi;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailQueryModel;
import cn.itcast.erp.util.base.BaseAction;

public class OrderDetailAction extends BaseAction {
	public OrderDetailModel om = new OrderDetailModel();
	public OrderDetailQueryModel oqm = new OrderDetailQueryModel();

	private OrderDetailEbi orderDetailEbi;

	public void setOrderDetailEbi(OrderDetailEbi orderDetailEbi) {
		this.orderDetailEbi = orderDetailEbi;
	}

	// ��ת���б�ҳ��
	public String list() {
		setDataTotal(orderDetailEbi.getCount(oqm));
		List<OrderDetailModel> orderDetailList = orderDetailEbi.getAll(oqm,
				pageNum, pageCount);
		put("orderDetailList", orderDetailList);
		return LIST;
	}

	// ��ת�����ҳ��
	public String input() {
		if (om.getUuid() != null) {
			om = orderDetailEbi.get(om.getUuid());
		}
		return INPUT;
	}

	// ��ӹ���
	public String save() {
		if (om.getUuid() == null) {
			orderDetailEbi.save(om);
		} else {
			orderDetailEbi.update(om);
		}
		return TO_LIST;
	}

	// ɾ��
	public String delete() {
		orderDetailEbi.delete(om);
		return TO_LIST;
	}

	// ------------------ajax----------------

	public void setOm(OrderDetailModel om) {
		this.om = om;
	}

	public String ajaxGetSurplus() {
		om = orderDetailEbi.get(om.getUuid());
		return "ajaxGetSurplus";
	}

}