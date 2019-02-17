package cn.itcast.erp.auth.emp.dao.dao;

import java.util.List;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.util.base.BaseDao;

public interface EmpDao extends BaseDao<EmpModel> {
	public EmpModel getByUsernameAndPwd(String username, String pwd);

	public boolean changePwdByUsernameAndPwd(String username, String pwd,
			String newPwd);

	public List<EmpModel> getByDepUuid(Long uuid);
}
