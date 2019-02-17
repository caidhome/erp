package cn.itcast.erp.auth.menu.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.itcast.erp.auth.menu.business.ebi.MenuEbi;
import cn.itcast.erp.auth.menu.dao.dao.MenuDao;
import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.util.base.BaseQueryModel;

public class MenuEbo implements MenuEbi {

	private MenuDao menuDao;

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void save(MenuModel mm) {
		menuDao.save(mm);
	}

	public void update(MenuModel mm) {
		MenuModel temp = menuDao.get(mm.getUuid());
		temp.setUrl(mm.getUrl());
		temp.setName(mm.getName());
		// menuDao.update(mm);
	}

	public void delete(MenuModel mm) {
		MenuModel temp = menuDao.get(mm.getUuid());
		menuDao.delete(temp);
	}

	public List<MenuModel> getAll() {
		return menuDao.getAll();
	}

	public MenuModel get(Serializable uuid) {
		return menuDao.get(uuid);
	}

	public List<MenuModel> getAll(BaseQueryModel mqm, Integer pageNum,
			Integer pageCount) {
		return menuDao.getAll(mqm, pageNum, pageCount);
	}

	public Integer getCount(BaseQueryModel mqm) {
		return menuDao.getCount(mqm);
	}

	public List<MenuModel> getAllOneLevel() {
		return menuDao.getByPuuidIsOneOrZero();
	}

	public void save(MenuModel mm, Long[] roleUuids) {
		Set<RoleModel> roles = new HashSet<RoleModel>();
		for (Long ri : roleUuids) {
			RoleModel temp = new RoleModel();
			temp.setUuid(ri);
			roles.add(temp);
		}
		mm.setRoles(roles);
		menuDao.save(mm);
	}

	public void update(MenuModel mm, Long[] roleUuids) {
		MenuModel temp = menuDao.get(mm.getUuid());
		temp.setUrl(mm.getUrl());
		temp.setName(mm.getName());
		Set<RoleModel> roles = new HashSet<RoleModel>();
		for (Long ri : roleUuids) {
			RoleModel temp1 = new RoleModel();
			temp1.setUuid(ri);
			roles.add(temp1);
		}
		temp.setRoles(roles);
	}

	/*
	 * public List<MenuModel> getAllOneLevel2() { return
	 * menuDao.getAllOneLevel2(); }
	 */

	public List<MenuModel> getAllOneLevelByEmp(Long uuid) {
		return menuDao.getAllOneLevelByEmpUuid(uuid);
	}

	public List<MenuModel> getByEmpAndPuuid(Long uuid, Long puuid) {
		return menuDao.getByEmpAndPuuid(uuid, puuid);
	}
}