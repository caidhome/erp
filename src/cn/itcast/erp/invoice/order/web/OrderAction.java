package cn.itcast.erp.invoice.order.web;

import java.util.List;

import cn.itcast.erp.auth.emp.business.ebi.EmpEbi;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.invoice.goods.business.ebi.GoodsEbi;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.invoice.order.business.ebi.OrderEbi;
import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.itcast.erp.invoice.store.business.ebi.StoreEbi;
import cn.itcast.erp.invoice.store.vo.StoreModel;
import cn.itcast.erp.invoice.supplier.business.ebi.SupplierEbi;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.util.base.BaseAction;

public class OrderAction extends BaseAction {
	public OrderModel om = new OrderModel();
	public OrderQueryModel oqm = new OrderQueryModel();

	private OrderEbi orderEbi;

	private SupplierEbi supplierEbi;

	private GoodsTypeEbi goodsTypeEbi;

	public EmpEbi empEbi;

	private StoreEbi storeEbi;

	private GoodsEbi goodsEbi;

	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}

	public void setStoreEbi(StoreEbi storeEbi) {
		this.storeEbi = storeEbi;
	}

	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}

	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setOrderEbi(OrderEbi orderEbi) {
		this.orderEbi = orderEbi;
	}

	// 跳转到列表页面
	public String list() {
		setDataTotal(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAll(oqm, pageNum, pageCount);
		put("orderList", orderList);
		return LIST;
	}

	// 跳转到添加页面
	public String input() {
		if (om.getUuid() != null) {
			om = orderEbi.get(om.getUuid());
		}
		return INPUT;
	}

	// 添加功能
	public String save() {
		if (om.getUuid() == null) {
			orderEbi.save(om);
		} else {
			orderEbi.update(om);
		}
		return TO_LIST;
	}

	// 删除
	public String delete() {
		orderEbi.delete(om);
		return TO_LIST;
	}

	// -----------采购管理-------------------
	public String buyList() {
		setDataTotal(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAllBuyOrder(oqm, pageNum,
				pageCount);
		put("orderList", orderList);
		System.out.println("------------" + orderList.get(0).getUuid());
		return "buyList";
	}

	public String buyInput() {
		List<SupplierModel> supplierList = supplierEbi.getAllUnionTwo();
		List<GoodsTypeModel> gtmList = goodsTypeEbi
				.getUnionBySmUuid(supplierList.get(0).getUuid());
		List<GoodsModel> goodsList = goodsEbi
				.getByGtm(gtmList.get(0).getUuid());
		put("supplierList", supplierList);
		put("gtmList", gtmList);
		put("goodsList", goodsList);
		return "buyInput";
	}

	public Long[] goodsUuids;
	public Integer[] nums;
	public Double[] prices;

	// 保存购买订单
	public String buySave() {

		orderEbi.saveBuyOrder(om, goodsUuids, nums, prices, getLogin());
		return "toBuyList";
	}

	public String buyDetail() {
		om = orderEbi.get(om.getUuid());
		return "buyDetail";
	}

	// ---------------------------------------
	// ----------采购审核模块--------------------
	// ---------------------------------------

	public String buyCheckList() {
		setDataTotal(orderEbi.getCountOrderType(oqm));
		List<OrderModel> orderList = orderEbi.getAllOrderType(oqm, pageNum,
				pageCount);
		put("orderList", orderList);
		return "buyCheckList";
	}

	public String buyCheckDetail() {
		om = orderEbi.get(om.getUuid());
		return "buyCheckDetail";
	}

	// 采购审核通过
	public String buyCheckPass() {
		orderEbi.buyCheckPass(om.getUuid(), getLogin());
		return "toBuyCheckPass";
	}

	// 采购审核驳回
	public String buyCheckNoPass() {
		orderEbi.buyCheckNoPass(om.getUuid(), getLogin());
		return "toBuyCheckPass";
	}

	// ---------------------------------------
	// ----------货物运输模块--------------------
	// ---------------------------------------
	public String taskList() {
		setDataTotal(orderEbi.getCountTask(oqm));
		List<OrderModel> orderList = orderEbi.getAllTask(oqm, pageNum,
				pageCount);
		put("orderList", orderList);
		return "taskList";
	}

	public String taskDetail() {
		List<EmpModel> empList = empEbi.getByDep(getLogin().getDm().getUuid());
		put("empList", empList);
		om = orderEbi.get(om.getUuid());
		return "taskDetail";
	}

	// 指派跟单人
	public String assignTask() {
		orderEbi.assignTask(om.getUuid(), om.getCompleter());
		return "toTaskList";
	}

	// 查询任务
	public String tasks() {
		setDataTotal(orderEbi.getCount(oqm, getLogin()));
		List<OrderModel> orderList = orderEbi.getAllTask(oqm, pageNum,
				pageCount, getLogin());
		put("orderList", orderList);
		return "tasks";
	}

	public String task() {
		om = orderEbi.get(om.getUuid());
		return "toTasks";
	}

	// 结单
	public String endTask() {
		orderEbi.endTask(om.getUuid());
		return "endTask";
	}

	// --------------入库-------------
	public String inStoreList() {
		setDataTotal(orderEbi.getCountInStore(oqm));
		List<OrderModel> orderList = orderEbi.getAllInStore(oqm, pageNum,
				pageCount);
		put("orderList", orderList);
		return "inStoreList";
	}

	// 进入入库明细
	public String inStoreDetail() {
		List<StoreModel> storeList = storeEbi.getAll();
		put("storeList", storeList);
		om = orderEbi.get(om.getUuid());
		return "inStoreDetail";
	}

	// -------------AJAX--------------
	// -------------AJAX--------------
	// -------------AJAX--------------

	public Long supplierUuid;
	public Long gtmUuid;
	public Long goodsUuid;

	private List<GoodsTypeModel> gtmList;
	private List<GoodsModel> gmList;
	private GoodsModel gm;

	public GoodsModel getGm() {
		return gm;
	}

	public List<GoodsTypeModel> getGtmList() {
		return gtmList;
	}

	public List<GoodsModel> getGmList() {
		return gmList;
	}

	public String ajaxGetGtmAndGm() {
		gtmList = goodsTypeEbi.getUnionBySmUuid(supplierUuid);
		gmList = goodsEbi.getByGtm(gtmList.get(0).getUuid());
		gm = gmList.get(0);
		return "ajaxGetGtmAndGm";
	}

	// 有数据筛选功能
	public String used;

	public String ajaxGetGtmAndGm2() {
		gtmList = goodsTypeEbi.getUnionBySmUuid(supplierUuid);
		Goods: for (int i = gtmList.size() - 1; i >= 0; i--) {
			List<GoodsModel> tempGm = goodsEbi.getByGtm(gtmList.get(i)
					.getUuid());
			for (int j = 0; j < tempGm.size(); j++) {
				if (!used.contains("'" + tempGm.get(j).getUuid() + "'")) {
					continue Goods;
				}
			}
			gtmList.remove(i);
		}

		gmList = goodsEbi.getByGtm(gtmList.get(0).getUuid());
		for (int i = gmList.size() - 1; i >= 0; i--) {
			Long uuid = gmList.get(i).getUuid();
			if (used.contains("'" + uuid + "'")) {
				gmList.remove(i);
			}
		}
		gm = gmList.get(0);
		return "ajaxGetGtmAndGm";
	}

	public String ajaxGetGm() {
		gmList = goodsEbi.getByGtm(gtmUuid);
		for (int i = gmList.size() - 1; i >= 0; i--) {
			Long uuid = gmList.get(i).getUuid();
			if (used.contains("'" + uuid + "'")) {
				gmList.remove(i);
			}
		}
		gm = gmList.get(0);
		return "ajaxGetGm";
	}

	public String ajaxGetPrice() {
		gm = goodsEbi.get(goodsUuid);
		return "ajaxGetPrice";
	}

	// 入库
	public Integer num;
	public Long storeUuid;
	public Long odmUuid;

	public OrderDetailModel odm;

	public OrderDetailModel getOdm() {
		return odm;
	}

	public String ajaxInStore() {
		odm = orderEbi.InStore(num, storeUuid, odmUuid, getLogin());
		return "ajaxInStore";
	}
}