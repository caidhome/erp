package cn.itcast.erp.auth.role.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface RoleEbi extends BaseEbi<RoleModel> {

	/**
	 * 保存员工信息
	 * 
	 * @param rm
	 *            员工基本信息
	 * @param resUuids
	 *            员工的角色信息
	 * @param menuUuids
	 *            员工的菜单信息
	 */
	public void save(RoleModel rm, Long[] resUuids, Long[] menuUuids);

	/**
	 * 修改员工信息
	 * 
	 * @param rm
	 *            员工基本信息
	 * @param resUuids
	 *            员工的角色信息
	 * @param menuUuids
	 *            员工的菜单信息
	 */
	public void update(RoleModel rm, Long[] resUuids, Long[] menuUuids);

}