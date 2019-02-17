package cn.itcast.erp.util.interceptor;

import cn.itcast.erp.auth.emp.vo.EmpModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getProxy().getAction().getClass()
				.getName();
		String methodName = invocation.getProxy().getMethod();
		String allName = actionName + "." + methodName;
		if ("cn.itcast.erp.auth.emp.web.EmpAction.login".equals(allName)) {
			return invocation.invoke();
		}

		String operName = invocation.getProxy().getActionName();// page_login
		if ("page_login".equals(operName)) {
			return invocation.invoke();
		}

		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession()
				.get(EmpModel.EMP_LOGIN_USERNAME_OBJECT_NAME);
		if (loginEm == null) {
			return "nologin";
		}
		return invocation.invoke();
	}
}
