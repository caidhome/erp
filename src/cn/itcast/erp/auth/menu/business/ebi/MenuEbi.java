package cn.itcast.erp.auth.menu.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface MenuEbi extends BaseEbi<MenuModel> {

	public List<MenuModel> getAllOneLevel();

	public void save(MenuModel mm, Long[] roleUuids);

	public void update(MenuModel mm, Long[] roleUuids);

	// public List<MenuModel> getAllOneLevel2();

	public List<MenuModel> getAllOneLevelByEmp(Long uuid);

	/**
	 * 通过员工的uuid和菜单的父菜单的uuid获取二级菜单
	 * 
	 * @param uuid
	 *            员工的uuid
	 * @param puuid
	 *            父菜单的uuid
	 * @return
	 */
	public List<MenuModel> getByEmpAndPuuid(Long uuid, Long puuid);

}