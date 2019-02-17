package cn.itcast.erp.auth.role.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.auth.role.business.ebi.RoleEbi;
import cn.itcast.erp.auth.role.dao.dao.RoleDao;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.util.base.BaseQueryModel;

public class RoleEbo implements RoleEbi {

	private RoleDao roleDao;

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	// 废弃
	public void save(RoleModel rm) {
		// roleDao.save(rm);
	}

	// 废弃
	public void update(RoleModel rm) {
		// roleDao.update(rm);
	}

	public void delete(RoleModel rm) {
		roleDao.delete(rm);
	}

	public List<RoleModel> getAll() {
		return roleDao.getAll();
	}

	public RoleModel get(Serializable uuid) {
		return roleDao.get(uuid);
	}

	public List<RoleModel> getAll(BaseQueryModel rqm, Integer pageNum,
			Integer pageCount) {
		return roleDao.getAll(rqm, pageNum, pageCount);
	}

	public Integer getCount(BaseQueryModel rqm) {
		return roleDao.getCount(rqm);
	}

	/*
	 * public void save(RoleModel rm, Long[] resUuids) { Set<ResModel> reses =
	 * new HashSet<ResModel>(); for (Long resUuid : resUuids) { ResModel temp =
	 * new ResModel(); temp.setUuid(resUuid); reses.add(temp); }
	 * rm.setReses(reses); roleDao.save(rm); }
	 * 
	 * public void update(RoleModel rm, Long[] resUuids) { Set<ResModel> reses =
	 * new HashSet<ResModel>(); for (Long resUuid : resUuids) { ResModel temp =
	 * new ResModel(); temp.setUuid(resUuid); reses.add(temp); }
	 * rm.setReses(reses); roleDao.update(rm); }
	 */

	public void save(RoleModel rm, Long[] resUuids, Long[] menuUuids) {
		Set<ResModel> reses = new HashSet<ResModel>();
		for (Long resUuid : resUuids) {
			ResModel temp = new ResModel();
			temp.setUuid(resUuid);
			reses.add(temp);
		}
		rm.setReses(reses);

		Set<MenuModel> menus = new HashSet<MenuModel>();
		for (Long mi : menuUuids) {
			MenuModel temp = new MenuModel();
			temp.setUuid(mi);
			menus.add(temp);
		}
		rm.setMenus(menus);
		roleDao.save(rm);

	}

	public void update(RoleModel rm, Long[] resUuids, Long[] menuUuids) {
		// 给用户添加角色信息
		Set<ResModel> reses = new HashSet<ResModel>();
		for (Long resUuid : resUuids) {
			ResModel temp = new ResModel();
			temp.setUuid(resUuid);
			reses.add(temp);
		}
		rm.setReses(reses);
		// 给用户添加菜单信息
		Set<MenuModel> menus = new HashSet<MenuModel>();
		for (Long mi : menuUuids) {
			MenuModel temp = new MenuModel();
			temp.setUuid(mi);
			menus.add(temp);
		}
		rm.setMenus(menus);
		roleDao.update(rm);

	}
}