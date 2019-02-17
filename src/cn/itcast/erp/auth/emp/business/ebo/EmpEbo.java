package cn.itcast.erp.auth.emp.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.itcast.erp.auth.emp.business.ebi.EmpEbi;
import cn.itcast.erp.auth.emp.dao.dao.EmpDao;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.util.MD5Utils;
import cn.itcast.erp.util.base.BaseQueryModel;

public class EmpEbo implements EmpEbi {

	private EmpDao empDao;

	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}

	public void save(EmpModel em) {
		/*
		 * // if (em.getUsername() == null || em.getUsername().trim().length()
		 * == // 0) { // throw new AppException("账户名输入有误，添加失败！"); // }
		 * em.setPwd(MD5Utils.md5(em.getPwd())); em.setLastLoginIp("-");
		 * em.setLoginTimes(0); em.setLastLoginTime(System.currentTimeMillis());
		 * empDao.save(em);
		 */
	}

	public void update(EmpModel em) {

		/*
		 * EmpModel temp = empDao.get(em.getUuid());
		 * 
		 * temp.setName(em.getName()); temp.setEmail(em.getEmail());
		 * temp.setDm(em.getDm()); temp.setTele(em.getTele());
		 * temp.setAddress(em.getAddress());
		 */
		// empDao.update(em);
	}

	public void delete(EmpModel em) {
		empDao.delete(em);
	}

	public List<EmpModel> getAll() {
		return empDao.getAll();
	}

	public EmpModel get(Serializable uuid) {
		return empDao.get(uuid);
	}

	public List<EmpModel> getAll(BaseQueryModel eqm, Integer pageNum,
			Integer pageCount) {
		return empDao.getAll(eqm, pageNum, pageCount);
	}

	public Integer getCount(BaseQueryModel eqm) {
		return empDao.getCount(eqm);
	}

	public EmpModel login(String username, String pwd, String loginIp) {
		pwd = MD5Utils.md5(pwd);
		EmpModel loginEm = empDao.getByUsernameAndPwd(username, pwd);
		loginEm.setLastLoginIp(loginIp);
		loginEm.setLastLoginTime(System.currentTimeMillis());
		loginEm.setLoginTimes(loginEm.getLoginTimes() + 1);
		return loginEm;
	}

	public boolean changePwd(String username, String pwd, String newPwd) {
		pwd = MD5Utils.md5(pwd);
		newPwd = MD5Utils.md5(newPwd);
		return empDao.changePwdByUsernameAndPwd(username, pwd, newPwd);
	}

	public void save(EmpModel em, Long[] roleUuids) {
		Set<RoleModel> roles = new HashSet<RoleModel>();

		for (Long roleUuid : roleUuids) {
			RoleModel temp = new RoleModel();
			temp.setUuid(roleUuid);
			roles.add(temp);
		}
		em.setRoles(roles);
		em.setPwd(MD5Utils.md5(em.getPwd()));
		em.setLastLoginIp("-");
		em.setLoginTimes(0);
		em.setLastLoginTime(System.currentTimeMillis());
		empDao.save(em);

	}

	public void update(EmpModel em, Long[] roleUuids) {
		EmpModel temp = empDao.get(em.getUuid());
		temp.setName(em.getName());
		temp.setEmail(em.getEmail());
		temp.setDm(em.getDm());
		temp.setTele(em.getTele());
		temp.setAddress(em.getAddress());

		Set<RoleModel> roles = new HashSet<RoleModel>();

		for (Long roleUuid : roleUuids) {
			RoleModel temp1 = new RoleModel();
			temp1.setUuid(roleUuid);
			roles.add(temp1);
		}
		temp.setRoles(roles);

	}

	public List<EmpModel> getByDep(Long uuid) {
		return empDao.getByDepUuid(uuid);
	}

}