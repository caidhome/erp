package cn.itcast.erp.auth.emp.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface EmpEbi extends BaseEbi<EmpModel> {

	public EmpModel login(String username, String pwd, String loginIp);

	public boolean changePwd(String username, String pwd, String newPwd);

	public void save(EmpModel em, Long[] roleUuids);

	public void update(EmpModel em, Long[] roleUuids);

	/**
	 * ��ȡָ�����ŵ�����Ա��
	 * 
	 * @param uuid
	 *            ��ǰ��¼����Ϣ
	 * @return
	 */
	public List<EmpModel> getByDep(Long uuid);
}