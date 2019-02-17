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
	 * ����ɹ�����
	 * 
	 * @param om
	 *            �ɹ�����
	 * @param goodsUuids
	 *            ÿ����ƷUUID��Ϣ
	 * @param nums
	 *            ÿ����Ʒ������
	 * @param prices
	 *            ÿ����Ʒ�۸�
	 * @param login
	 *            ��ǰ��¼����Ϣ
	 */
	public void saveBuyOrder(OrderModel om, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmpModel login);

	/**
	 * ��ȡ���вɹ�����
	 * 
	 * @param oqm
	 *            �ɹ�������ѯ����
	 * @param pageNum
	 *            ��ǰҳ
	 * @param pageCount
	 *            ûҳ��ʾ�����
	 * @return
	 */
	public List<OrderModel> getAllBuyOrder(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount);

	public List<OrderModel> getAllOrderType(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount);

	public Integer getCountOrderType(OrderQueryModel oqm);

	/**
	 * �ɹ����ͨ��
	 * 
	 * @param uuid
	 *            ����uuid
	 * @param login
	 *            ��ǰ��¼��
	 */
	public void buyCheckPass(Long uuid, EmpModel login);

	/**
	 * �ɹ���˲���
	 * 
	 * @param uuid
	 *            ����uuid
	 * @param login
	 *            ��ǰ��¼��
	 */
	public void buyCheckNoPass(Long uuid, EmpModel login);

	public Integer getCountTask(OrderQueryModel oqm);

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);

	/**
	 * ��ָ������ָ�ɸ�����
	 * 
	 * @param uuid
	 *            ����uuid
	 * @param completer
	 *            ��������Ϣ
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