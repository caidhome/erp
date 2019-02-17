package cn.itcast.erp.auth.res.web;

import java.util.List;

import cn.itcast.erp.auth.res.business.ebi.ResEbi;
import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.auth.res.vo.ResQueryModel;
import cn.itcast.erp.util.base.BaseAction;

public class ResAction extends BaseAction {
	public ResModel rm = new ResModel();
	public ResQueryModel rqm = new ResQueryModel();

	private ResEbi resEbi;

	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	// ��ת���б�ҳ��
	public String list() {
		setDataTotal(resEbi.getCount(rqm));
		List<ResModel> resList = resEbi.getAll(rqm, pageNum, pageCount);
		put("resList", resList);
		return LIST;
	}

	// ��ת�����ҳ��
	public String input() {
		if (rm.getUuid() != null) {
			rm = resEbi.get(rm.getUuid());
		}
		return INPUT;
	}

	// ��ӹ���
	public String save() {
		if (rm.getUuid() == null) {
			resEbi.save(rm);
		} else {
			resEbi.update(rm);
		}
		return TO_LIST;
	}

	// ɾ��
	public String delete() {
		resEbi.delete(rm);
		return TO_LIST;
	}

}