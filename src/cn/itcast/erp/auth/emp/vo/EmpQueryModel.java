package cn.itcast.erp.auth.emp.vo;

import cn.itcast.erp.util.base.BaseQueryModel;
import cn.itcast.erp.util.format.FormatUtil;

public class EmpQueryModel extends EmpModel implements BaseQueryModel {
	// TODO ��д�Զ����ѯ����
	private Long birthday2;
	private String birthday2View;

	public Long getBirthday2() {
		return birthday2;
	}

	public void setBirthday2(Long birthday2) {
		this.birthday2 = birthday2;
		this.birthday2View = FormatUtil.formatDate(birthday2);
	}

	public String getBirthday2View() {
		return birthday2View;
	}

}