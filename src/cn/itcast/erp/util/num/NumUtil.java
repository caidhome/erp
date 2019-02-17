package cn.itcast.erp.util.num;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NumUtil {

	public static int sernum = 1;
	public static int len = 5;
	public static final byte[] zeros = { 48, 48, 48, 48, 48 };

	public static void main(String[] args) {
		System.out.println(generatorOrderNum());
	}

	public static final String generatorOrderNum() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String fir = sdf.format(d);
		int num = sernum++;
		int len2 = (num + "").length();
		String sec = new String(zeros, 0, len - len2);
		return Long.toHexString(new Long(fir + sec + num)).toUpperCase();
	}

}
