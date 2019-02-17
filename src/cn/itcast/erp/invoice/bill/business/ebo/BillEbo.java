package cn.itcast.erp.invoice.bill.business.ebo;

import java.util.List;

import cn.itcast.erp.invoice.bill.business.ebi.BillEbi;
import cn.itcast.erp.invoice.bill.dao.dao.BillDao;
import cn.itcast.erp.invoice.bill.vo.BillQueryModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;

public class BillEbo implements BillEbi {

	private BillDao billDao;

	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	public List<Object[]> getBuyBill(BillQueryModel bqm) {
		return billDao.getBuyBill(bqm);
	}

	public List<OrderDetailModel> getBuyBillDetail(BillQueryModel bqm) {
		return billDao.getBuyBillDetail(bqm);
	}
}