package cn.itcast.erp.invoice.order.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface OrderEbi extends BaseEbi<OrderModel> {

	/**
	 * 保存采购订单
	 * 
	 * @param om
	 *            采购订单
	 * @param goodsUuids
	 *            每个商品UUID信息
	 * @param nums
	 *            每个商品的数量
	 * @param prices
	 *            每个商品价格
	 * @param login
	 *            当前登录人信息
	 */
	public void saveBuyOrder(OrderModel om, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmpModel login);

	/**
	 * 获取所有采购订单
	 * 
	 * @param oqm
	 *            采购订单查询条件
	 * @param pageNum
	 *            当前页
	 * @param pageCount
	 *            没页显示最大数
	 * @return
	 */
	public List<OrderModel> getAllBuyOrder(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount);

	public List<OrderModel> getAllOrderType(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount);

	public Integer getCountOrderType(OrderQueryModel oqm);

	/**
	 * 采购审核通过
	 * 
	 * @param uuid
	 *            订单uuid
	 * @param login
	 *            当前登录人
	 */
	public void buyCheckPass(Long uuid, EmpModel login);

	/**
	 * 采购审核驳回
	 * 
	 * @param uuid
	 *            订单uuid
	 * @param login
	 *            当前登录人
	 */
	public void buyCheckNoPass(Long uuid, EmpModel login);

	public Integer getCountTask(OrderQueryModel oqm);

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);

	/**
	 * 给指定订单指派跟单人
	 * 
	 * @param uuid
	 *            订单uuid
	 * @param completer
	 *            跟单人信息
	 * @return
	 */
	public void assignTask(Long uuid, EmpModel completer);

	public Integer getCount(OrderQueryModel oqm, EmpModel login);

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount, EmpModel login);

	public void endTask(Long uuid);

	public Integer getCountInStore(OrderQueryModel oqm);

	public List<OrderModel> getAllInStore(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);

	public OrderDetailModel InStore(Integer num, Long storeUuid, Long odmUuid,
			EmpModel login);

}