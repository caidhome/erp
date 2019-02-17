package cn.itcast.erp.invoice.order.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.order.business.ebi.OrderEbi;
import cn.itcast.erp.invoice.order.dao.dao.OrderDao;
import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.invoice.orderdetail.dao.dao.OrderDetailDao;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.itcast.erp.invoice.store.vo.StoreModel;
import cn.itcast.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import cn.itcast.erp.invoice.storedetail.vo.StoreDetailModel;
import cn.itcast.erp.invoice.storeoper.dao.dao.StoreOperDao;
import cn.itcast.erp.invoice.storeoper.vo.StoreOperModel;
import cn.itcast.erp.util.base.BaseQueryModel;
import cn.itcast.erp.util.exception.AppException;
import cn.itcast.erp.util.num.NumUtil;

public class OrderEbo implements OrderEbi {

	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void save(OrderModel om) {
		orderDao.save(om);
	}

	public void update(OrderModel om) {
		orderDao.update(om);
	}

	public void delete(OrderModel om) {
		orderDao.delete(om);
	}

	public List<OrderModel> getAll() {
		return orderDao.getAll();
	}

	public OrderModel get(Serializable uuid) {
		return orderDao.get(uuid);
	}

	public List<OrderModel> getAll(BaseQueryModel oqm, Integer pageNum,
			Integer pageCount) {
		return orderDao.getAll(oqm, pageNum, pageCount);
	}

	public Integer getCount(BaseQueryModel oqm) {
		return orderDao.getCount(oqm);
	}

	public void saveBuyOrder(OrderModel om, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmpModel loginEm) {
		String orderNum = NumUtil.generatorOrderNum();
		om.setOrderNum(orderNum);

		om.setCreateTime(System.currentTimeMillis());
		om.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		om.setType(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK);
		om.setCreater(loginEm);

		Integer totalNum = 0;
		Double totalPrice = 0d;

		Set<OrderDetailModel> odms = new HashSet<OrderDetailModel>();
		for (int i = 0; i < goodsUuids.length; i++) {
			OrderDetailModel odm = new OrderDetailModel();
			odm.setNum(nums[i]);

			odm.setPrice(prices[i]);
			odm.setSurplus(nums[i]);
			GoodsModel gm = new GoodsModel();

			gm.setUseNum(gm.getUseNum() + 1);

			gm.setUuid(goodsUuids[i]);
			odm.setGm(gm);
			odm.setOm(om);
			odms.add(odm);
			totalNum += nums[i];
			totalPrice += nums[i] * prices[i];
		}
		om.setOdms(odms);
		om.setTotalNum(totalNum);
		om.setTotalPrice(totalPrice);
		orderDao.save(om);
	}

	public List<OrderModel> getAllBuyOrder(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount) {
		oqm.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		return getAll(oqm, pageNum, pageCount);
	}

	Integer buyCheckOrderType[] = new Integer[] {
			OrderModel.ORDER_ORDERTYPE_OF_BUY,
			OrderModel.ORDER_ORDERTYPE_OF_RETURN_BUY };

	public List<OrderModel> getAllOrderType(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount) {
		return orderDao.getAllOrderType(oqm, pageNum, pageCount,
				buyCheckOrderType);
	}

	public Integer getCountOrderType(OrderQueryModel oqm) {
		return orderDao.getCountOrderType(oqm, buyCheckOrderType);
	}

	public void buyCheckPass(Long uuid, EmpModel loginer) {
		OrderModel temp = orderDao.get(uuid);

		if (!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK)) {
			throw new AppException("对不起，请不要非法操作！");
		}

		temp.setChecker(loginer);
		temp.setCheckTime(System.currentTimeMillis());
		temp.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS);
	}

	public void buyCheckNoPass(Long uuid, EmpModel loginer) {
		OrderModel temp = orderDao.get(uuid);
		if (!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK)) {
			throw new AppException("对不起，请不要非法操作！");
		}
		temp.setChecker(loginer);
		temp.setCheckTime(System.currentTimeMillis());
		temp.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_NO_PASS);
	}

	Integer taskTypes[] = new Integer[] {
			OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS,
			OrderModel.ORDER_TYPE_OF_BUY_BUYING,
			OrderModel.ORDER_TYPE_OF_BUY_IN_STORE,
			OrderModel.ORDER_TYPE_OF_BUY_COMPLETE };

	public Integer getCountTask(OrderQueryModel oqm) {
		return orderDao.getCountTask(oqm, taskTypes);
	}

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount) {
		return orderDao.getAllTask(oqm, pageNum, pageCount, taskTypes);
	}

	public void assignTask(Long uuid, EmpModel completer) {
		OrderModel temp = orderDao.get(uuid);
		if (!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS)) {
			throw new AppException("对不起，请不要非法操作！");
		}
		temp.setCompleter(completer);
		temp.setType(OrderModel.ORDER_TYPE_OF_BUY_BUYING);
	}

	public Integer getCount(OrderQueryModel oqm, EmpModel loginer) {
		oqm.setCompleter(loginer);
		return orderDao.getCount(oqm);
	}

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount, EmpModel loginer) {
		oqm.setCompleter(loginer);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}

	public void endTask(Long uuid) {
		OrderModel temp = orderDao.get(uuid);
		if (!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_BUYING)) {
			throw new AppException("对不起，请不要非法操作！");
		}
		temp.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
	}

	public Integer getCountInStore(OrderQueryModel oqm) {
		oqm.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
		return orderDao.getCount(oqm);
	}

	public List<OrderModel> getAllInStore(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount) {
		oqm.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}

	private OrderDetailDao orderDetailDao;
	private StoreDetailDao storeDetailDao;
	private StoreOperDao storeOperDao;

	public void setStoreOperDao(StoreOperDao storeOperDao) {
		this.storeOperDao = storeOperDao;
	}

	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public OrderDetailModel InStore(Integer num, Long storeUuid, Long odmUuid,
			EmpModel emlogin) {
		// 订单明细跟新
		OrderDetailModel odm = orderDetailDao.get(odmUuid);
		OrderModel om = odm.getOm();

		if (!om.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE)) {
			throw new AppException("悟空，您有调皮了！！");
		}

		if (odm.getSurplus() < num) {
			throw new AppException("悟空，您有调皮了！！");
		}

		odm.setSurplus(odm.getSurplus() - num);

		StoreModel sm = new StoreModel();
		sm.setUuid(storeUuid);

		GoodsModel gm = odm.getGm();

		// 仓库储量更新
		StoreDetailModel sdm = storeDetailDao.getByGmAndSm(gm.getUuid(),
				storeUuid);
		if (sdm != null) {
			// 存过该商品，直接更新
			sdm.setNum(sdm.getNum() + num);
		} else {
			// 没存过该商品，添加记录
			sdm = new StoreDetailModel();
			sdm.setNum(num);
			sdm.setGm(gm);
			sdm.setSm(sm);
			storeDetailDao.save(sdm);
		}
		// 记录日志
		StoreOperModel som = new StoreOperModel();
		som.setNum(num);
		som.setOperTime(System.currentTimeMillis());
		som.setType(StoreOperModel.OPER_TYPE_OF_IN);
		som.setEm(emlogin);
		som.setSm(sm);
		som.setGm(gm);
		storeOperDao.save(som);

		Integer sum = 0;
		for (OrderDetailModel temp : om.getOdms()) {
			sum += temp.getSurplus();
		}
		if (sum == 0) {
			om.setType(OrderModel.ORDER_TYPE_OF_BUY_COMPLETE);
			om.setEndTime(System.currentTimeMillis());
		}
		return odm;
	}
}