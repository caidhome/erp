package cn.itcast.erp.util.quartz;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import cn.itcast.erp.invoice.goods.business.ebi.GoodsEbi;
import cn.itcast.erp.util.format.FormatUtil;

public class TimerTask {

	private GoodsEbi goodsEbi;
	// �ʼ�������
	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}

	public void fn1() {
		System.out.println("*************************************");
		System.out.println("*****-------��ʱ���������----------****");
		System.out.println("*************************************");
	}

	// ��ʱ����ʹ�ô���
	public void goodsUseNumUpdate() {
		goodsEbi.goodsUseNumUpdate();
	}

	// ��ʱԤ��
	public void storeWarn() {

		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom("15294164808@126.com");
		smm.setTo("1962644476@qq.com");
		smm.setSubject("���Ԥ��["
				+ FormatUtil.formatDate(System.currentTimeMillis()) + "]");
		smm.setText("���Ԥ��");
		mailSender.send(smm);

	}

}
