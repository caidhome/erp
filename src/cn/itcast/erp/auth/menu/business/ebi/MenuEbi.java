package cn.itcast.erp.auth.menu.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface MenuEbi extends BaseEbi<MenuModel> {

	public List<MenuModel> getAllOneLevel();

	public void save(MenuModel mm, Long[] roleUuids);

	public void update(MenuModel mm, Long[] roleUuids);

	// public List<MenuModel> getAllOneLevel2();

	public List<MenuModel> getAllOneLevelByEmp(Long uuid);

	/**
	 * ͨ��Ա����uuid�Ͳ˵��ĸ��˵���uuid��ȡ�����˵�
	 * 
	 * @param uuid
	 *            Ա����uuid
	 * @param puuid
	 *            ���˵���uuid
	 * @return
	 */
	public List<MenuModel> getByEmpAndPuuid(Long uuid, Long puuid);

}