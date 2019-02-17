package cn.itcast.erp.auth.menu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import cn.itcast.erp.auth.menu.business.ebi.MenuEbi;
import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.menu.vo.MenuQueryModel;
import cn.itcast.erp.auth.role.business.ebi.RoleEbi;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.util.base.BaseAction;

public class MenuAction extends BaseAction {
	public MenuModel mm = new MenuModel();
	public MenuQueryModel mqm = new MenuQueryModel();

	private MenuEbi menuEbi;

	private RoleEbi roleEbi;

	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}

	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}

	// 跳转到列表页面
	public String list() {
		List<MenuModel> parentList = menuEbi.getAllOneLevel();
		put("parentList", parentList);
		setDataTotal(menuEbi.getCount(mqm));
		List<MenuModel> menuList = menuEbi.getAll(mqm, pageNum, pageCount);
		put("menuList", menuList);
		return LIST;
	}

	// 跳转到添加页面
	public String input() {
		List<RoleModel> roleList = roleEbi.getAll();
		put("roleList", roleList);
		// List<MenuModel> menuList = menuEbi.getAll();
		List<MenuModel> menuList = menuEbi.getAllOneLevel();
		put("menuList", menuList);
		if (mm.getUuid() != null) {
			mm = menuEbi.get(mm.getUuid());
			roleUuids = new Long[mm.getRoles().size()];
			int i = 0;
			for (RoleModel rm : mm.getRoles()) {
				roleUuids[i++] = rm.getUuid();
			}
		}
		return INPUT;
	}

	public Long[] roleUuids;

	// 添加功能
	public String save() {
		if (mm.getUuid() == null) {
			menuEbi.save(mm, roleUuids);
		} else {
			menuEbi.update(mm, roleUuids);
		}
		return TO_LIST;
	}

	// 删除
	public String delete() {
		menuEbi.delete(mm);
		return TO_LIST;
	}

	// 显示菜单
	public void showMenu() throws IOException {
		String root = getRequest().getParameter("root");
		HttpServletResponse response = getResponse();
		response.setContentType("text/html;charset=utf-8");
		StringBuilder json = new StringBuilder();
		if ("source".equals(root)) {
			// 获取一级菜单
			List<MenuModel> menus = menuEbi.getAllOneLevelByEmp(getLogin()
					.getUuid());
			json.append("[");
			for (MenuModel temp : menus) {
				json.append("{\"text\":\"");
				json.append(temp.getName());
				json.append("\",\"hasChildren\":true,\"classes\":\"folder\",\"id\":\"");
				json.append(temp.getUuid());
				json.append("\"},");
			}
		} else {
			Long puuid = new Long(root);
			// 获取二级菜单
			List<MenuModel> menus = menuEbi.getByEmpAndPuuid(getLogin()
					.getUuid(), puuid);
			json.append("[");
			for (MenuModel temp : menus) {
				json.append("{\"text\":\"<a class='hei' target='main' href='");
				json.append(temp.getUrl());
				json.append("'>");
				json.append(temp.getName());
				json.append("</a>\",\"hasChildren\":false,\"classes\":\"file\",\"id\":\"");
				json.append(temp.getUuid());
				json.append("\"},");
			}
		}
		json.deleteCharAt(json.length() - 1);
		json.append("]");
		PrintWriter fw = response.getWriter();
		fw.write(json.toString());
		fw.flush();
	}
}