package cn.itcast.erp.invoice.order.dao.dao;

import java.util.List;

import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.util.base.BaseDao;

public interface OrderDao extends BaseDao<OrderModel> {

	public List<OrderModel> getAllOrderType(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount, Integer[] buyCheckOrderType);

	public int getCountOrderType(OrderQueryModel oqm,
			Integer[] buyCheckOrderType);

	public Integer getCountTask(OrderQueryModel oqm, Integer[] taskTypes);

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount, Integer[] taskTypes);

}
