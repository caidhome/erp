package cn.itcast.erp.invoice.storeoper.web;

import java.util.List;

import cn.itcast.erp.invoice.storeoper.business.ebi.StoreOperEbi;
import cn.itcast.erp.invoice.storeoper.vo.StoreOperModel;
import cn.itcast.erp.invoice.storeoper.vo.StoreOperQueryModel;
import cn.itcast.erp.util.base.BaseAction;

public class StoreOperAction extends BaseAction {
	public StoreOperModel sm = new StoreOperModel();
	public StoreOperQueryModel sqm = new StoreOperQueryModel();

	private StoreOperEbi storeOperEbi;

	public void setStoreOperEbi(StoreOperEbi storeOperEbi) {
		this.storeOperEbi = storeOperEbi;
	}

	// ��ת���б�ҳ��
	public String list() {
		setDataTotal(storeOperEbi.getCount(sqm));
		List<StoreOperModel> storeOperList = storeOperEbi.getAll(sqm, pageNum, pageCount);
		put("storeOperList", storeOperList);
		return LIST;
	}

	// ��ת�����ҳ��
	public String input() {
		if (sm.getUuid() != null) {
			sm = storeOperEbi.get(sm.getUuid());
		}
		return INPUT;
	}

	// ��ӹ���
	public String save() {
		if (sm.getUuid() == null) {
			storeOperEbi.save(sm);
		} else {
			storeOperEbi.update(sm);
		}
		return TO_LIST;
	}

	// ɾ��
	public String delete() {
		storeOperEbi.delete(sm);
		return TO_LIST;
	}

}