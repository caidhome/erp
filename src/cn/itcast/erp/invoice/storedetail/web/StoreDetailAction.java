package cn.itcast.erp.invoice.storedetail.web;

import java.util.List;

import cn.itcast.erp.invoice.storedetail.business.ebi.StoreDetailEbi;
import cn.itcast.erp.invoice.storedetail.vo.StoreDetailModel;
import cn.itcast.erp.invoice.storedetail.vo.StoreDetailQueryModel;
import cn.itcast.erp.util.base.BaseAction;

public class StoreDetailAction extends BaseAction {
	public StoreDetailModel sm = new StoreDetailModel();
	public StoreDetailQueryModel sqm = new StoreDetailQueryModel();

	private StoreDetailEbi storeDetailEbi;

	public void setStoreDetailEbi(StoreDetailEbi storeDetailEbi) {
		this.storeDetailEbi = storeDetailEbi;
	}

	// ��ת���б�ҳ��
	public String list() {
		setDataTotal(storeDetailEbi.getCount(sqm));
		List<StoreDetailModel> storeDetailList = storeDetailEbi.getAll(sqm, pageNum, pageCount);
		put("storeDetailList", storeDetailList);
		return LIST;
	}

	// ��ת�����ҳ��
	public String input() {
		if (sm.getUuid() != null) {
			sm = storeDetailEbi.get(sm.getUuid());
		}
		return INPUT;
	}

	// ��ӹ���
	public String save() {
		if (sm.getUuid() == null) {
			storeDetailEbi.save(sm);
		} else {
			storeDetailEbi.update(sm);
		}
		return TO_LIST;
	}

	// ɾ��
	public String delete() {
		storeDetailEbi.delete(sm);
		return TO_LIST;
	}

}