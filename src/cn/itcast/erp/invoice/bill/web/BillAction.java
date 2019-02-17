package cn.itcast.erp.invoice.bill.web;

import java.util.List;

import cn.itcast.erp.invoice.bill.business.ebi.BillEbi;
import cn.itcast.erp.invoice.bill.vo.BillQueryModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.itcast.erp.invoice.supplier.business.ebi.SupplierEbi;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.util.base.BaseAction;

public class BillAction extends BaseAction {
	public BillQueryModel bqm = new BillQueryModel();

	private BillEbi billEbi;

	private SupplierEbi supplierEbi;

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setBillEbi(BillEbi billEbi) {
		this.billEbi = billEbi;
	}

	public String buyBillList() {
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList", supplierList);
		List<Object[]> billList = billEbi.getBuyBill(bqm);
		put("billList", billList);
		return "buyBillList";
	}

	// --------------ajax--------------
	public List<OrderDetailModel> odmList;

	public List<OrderDetailModel> getOdmList() {
		return odmList;
	}

	public String ajaxGetBuyBillDetail() {
		odmList = billEbi.getBuyBillDetail(bqm);
		return "ajaxGetBuyBillDetail";
	}

}