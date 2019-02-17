package cn.itcast.erp.auth.menu.dao.dao;

import java.util.List;

import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.util.base.BaseDao;

public interface MenuDao extends BaseDao<MenuModel> {

	public List<MenuModel> getByPuuidIsOneOrZero();

	// public List<MenuModel> getAllOneLevel2();

	public List<MenuModel> getAllOneLevelByEmpUuid(Long uuid);

	public List<MenuModel> getByEmpAndPuuid(Long uuid, Long puuid);

}
