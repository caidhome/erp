package cn.itcast.erp.util.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.erp.auth.res.business.ebi.ResEbi;
import cn.itcast.erp.auth.res.vo.ResModel;

public class AllResLoadListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils
				.getWebApplicationContext(sc);
		ResEbi resEbi = (ResEbi) wac.getBean("resEbi");
		List<ResModel> allRes = resEbi.getAll();
		StringBuilder sbf = new StringBuilder();
		for (ResModel r : allRes) {
			sbf.append(r.getUrl());
			sbf.append(",");
		}
		sc.setAttribute("allRes", sbf.toString());
	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
