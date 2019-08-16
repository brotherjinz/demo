package com.web.util;

import java.security.MessageDigest;

/**
 * md5工具类
 * 
 * @author 李亚洲
 * 
 */
public class MD5Util {

	/**
	 * 对字符串进行MD5加密
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes());
			byte b[] = md5.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	public static void main(String[] args) {
		System.out.println(md5("admin"));
	}
}
