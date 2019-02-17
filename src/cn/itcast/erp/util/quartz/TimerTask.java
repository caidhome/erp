package cn.itcast.erp.util.quartz;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import cn.itcast.erp.invoice.goods.business.ebi.GoodsEbi;
import cn.itcast.erp.util.format.FormatUtil;

public class TimerTask {

	private GoodsEbi goodsEbi;
	// 邮件发送器
	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}

	public void fn1() {
		System.out.println("*************************************");
		System.out.println("*****-------定时任务完成了----------****");
		System.out.println("*************************************");
	}

	// 定时更新使用次数
	public void goodsUseNumUpdate() {
		goodsEbi.goodsUseNumUpdate();
	}

	// 定时预警
	public void storeWarn() {

		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom("15294164808@126.com");
		smm.setTo("1962644476@qq.com");
		smm.setSubject("库存预警["
				+ FormatUtil.formatDate(System.currentTimeMillis()) + "]");
		smm.setText("库存预警");
		mailSender.send(smm);

	}

}
