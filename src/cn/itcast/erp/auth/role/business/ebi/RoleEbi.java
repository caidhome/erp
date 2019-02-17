package cn.itcast.erp.auth.role.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface RoleEbi extends BaseEbi<RoleModel> {

	/**
	 * ����Ա����Ϣ
	 * 
	 * @param rm
	 *            Ա��������Ϣ
	 * @param resUuids
	 *            Ա���Ľ�ɫ��Ϣ
	 * @param menuUuids
	 *            Ա���Ĳ˵���Ϣ
	 */
	public void save(RoleModel rm, Long[] resUuids, Long[] menuUuids);

	/**
	 * �޸�Ա����Ϣ
	 * 
	 * @param rm
	 *            Ա��������Ϣ
	 * @param resUuids
	 *            Ա���Ľ�ɫ��Ϣ
	 * @param menuUuids
	 *            Ա���Ĳ˵���Ϣ
	 */
	public void update(RoleModel rm, Long[] resUuids, Long[] menuUuids);

}