package cn.itcast.erp.util.interceptor;

import cn.itcast.erp.util.exception.AppException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (AppException e) {
			ActionSupport action = (ActionSupport) invocation.getAction();
			action.addActionError(e.getMessage());
			return "error";
		} catch (Exception e) {
			// ActionSupport action = (ActionSupport) invocation.getAction();
			// action.addActionError("�Բ��𣬷������ѹرգ�����ϵ����Ա��");
			// return "error";
			e.printStackTrace();
			return invocation.invoke();

		}
	}

}
