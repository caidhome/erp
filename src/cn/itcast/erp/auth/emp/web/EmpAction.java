package cn.itcast.erp.auth.emp.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.itcast.erp.auth.dep.business.ebi.DepEbi;
import cn.itcast.erp.auth.dep.vo.DepModel;
import cn.itcast.erp.auth.emp.business.ebi.EmpEbi;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.emp.vo.EmpQueryModel;
import cn.itcast.erp.auth.res.business.ebi.ResEbi;
import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.auth.role.business.ebi.RoleEbi;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.util.base.BaseAction;

public class EmpAction extends BaseAction {
	public EmpModel em = new EmpModel();
	public EmpQueryModel eqm = new EmpQueryModel();

	private EmpEbi empEbi;

	private DepEbi depEbi;

	private RoleEbi roleEbi;

	private ResEbi resEbi;

	public Long[] roleUuids;

	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}

	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}

	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}

	// 跳转到列表页面
	public String list() {
		List<DepModel> depList = depEbi.getAll();
		put("depList", depList);
		setDataTotal(empEbi.getCount(eqm));
		List<EmpModel> empList = empEbi.getAll(eqm, pageNum, pageCount);
		put("empList", empList);
		return LIST;
	}

	// 跳转到添加页面
	public String input() {
		List<RoleModel> roleList = roleEbi.getAll();
		put("roleList", roleList);
		List<DepModel> depList = depEbi.getAll();
		put("depList", depList);
		if (em.getUuid() != null) {
			em = empEbi.get(em.getUuid());
			roleUuids = new Long[em.getRoles().size()];
			int i = 0;
			for (RoleModel temp : em.getRoles()) {
				roleUuids[i++] = temp.getUuid();
			}
		}
		return INPUT;
	}

	// 添加功能
	public String save() {
		if (em.getUuid() == null) {
			empEbi.save(em, roleUuids);
		} else {
			empEbi.update(em, roleUuids);
		}
		return TO_LIST;
	}

	// 删除
	public String delete() {
		empEbi.delete(em);
		return TO_LIST;
	}

	/**
	 * 登录方法
	 * 
	 * @return
	 */
	public String login() {
		HttpServletRequest request = getRequest();
		String loginIp = request.getHeader("x-forwarded-for");
		if (loginIp == null || loginIp.length() == 0
				|| "unknown".equalsIgnoreCase(loginIp)) {
			loginIp = request.getHeader("Proxy-Client-IP");
		}
		if (loginIp == null || loginIp.length() == 0
				|| "unknown".equalsIgnoreCase(loginIp)) {
			loginIp = request.getHeader("WL-Proxy-Client-IP");
		}
		if (loginIp == null || loginIp.length() == 0
				|| "unknown".equalsIgnoreCase(loginIp)) {
			loginIp = request.getRemoteAddr();
		}

		EmpModel loginEm = empEbi.login(em.getUsername(), em.getPwd(), loginIp);
		if (loginEm == null) {
			this.addActionError("用户名或密码输入错误！");
			return "loginFail";
		} else {
			List<ResModel> resList = resEbi.getAllByEmp(em.getUuid());
			StringBuilder sbf = new StringBuilder();
			for (ResModel r : resList) {
				sbf.append(r.getUrl());
				sbf.append(",");
			}
			em.setAllRes(sbf.toString());
			putSession(EmpModel.EMP_LOGIN_USERNAME_OBJECT_NAME, loginEm);
			return "loginSuccess";
		}
	}

	public String loginOut() {
		putSession(EmpModel.EMP_LOGIN_USERNAME_OBJECT_NAME, null);
		return "nologin";

	}

	public String toChangPwd() {
		return "toChangPwd";
	}

	public String newPwd;

	public String changePwd() {
		System.out.println(newPwd + "    " + em.getPwd());
		boolean flag = empEbi.changePwd(getLogin().getUsername(), em.getPwd(),
				newPwd);
		if (flag) {
			putSession(EmpModel.EMP_LOGIN_USERNAME_OBJECT_NAME, null);
			return "nologin";
		} else {
			return "toChangPwd";
		}
	}
}