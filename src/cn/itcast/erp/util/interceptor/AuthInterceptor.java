package cn.itcast.erp.util.interceptor;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.res.business.ebi.ResEbi;
import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.util.exception.AppException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor {

	private ResEbi resEbi;

	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getProxy().getAction().getClass()
				.getName();
		String methodName = invocation.getProxy().getMethod();
		String allName = actionName + "." + methodName;
		String allRes = ServletActionContext.getServletContext()
				.getAttribute("allRes").toString();
		if (!allRes.contains(allName)) {
			return invocation.invoke();
		}
		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession()
				.get(EmpModel.EMP_LOGIN_USERNAME_OBJECT_NAME);
		if (loginEm.getAllRes().contains(allName)) {
			return invocation.invoke();
		}
		throw new AppException("对不起，在执行本次操作中您的权限不够！");
	}

	public String intercept2(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getProxy().getAction().getClass()
				.getName();
		String methodName = invocation.getProxy().getMethod();
		String allName = actionName + "." + methodName;
		String allRes = ServletActionContext.getServletContext()
				.getAttribute("allRes").toString();
		if (!allRes.contains(allName)) {
			return invocation.invoke();
		}
		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession()
				.get(EmpModel.EMP_LOGIN_USERNAME_OBJECT_NAME);
		// if (loginEm == null) {
		// return "nologin";
		// }
		List<ResModel> resList = resEbi.getAllByEmp(loginEm.getUuid());
		for (ResModel res : resList) {
			if (allName.equals(res.getUrl()))
				return invocation.invoke();
		}
		throw new AppException("对不起，在执行本次操作中您的权限不够！");
	}

	public String intercept1(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getProxy().getAction().getClass()
				.getName();
		String methodName = invocation.getProxy().getMethod();
		String allName = actionName + "." + methodName;

		List<ResModel> resAll = resEbi.getAll();
		StringBuilder sbf = new StringBuilder();
		for (ResModel r : resAll) {
			sbf.append(r.getUrl());
			sbf.append(",");
		}
		if (sbf.indexOf(allName) < 0) {
			return invocation.invoke();
		}
		System.out.println(sbf);
		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession()
				.get(EmpModel.EMP_LOGIN_USERNAME_OBJECT_NAME);
		// if (loginEm == null) {
		// return "nologin";
		// }
		List<ResModel> resList = resEbi.getAllByEmp(loginEm.getUuid());
		for (ResModel res : resList) {
			if (allName.equals(res.getUrl()))
				return invocation.invoke();
		}
		throw new AppException("对不起，在执行本次操作中您的权限不够！");
	}
}
