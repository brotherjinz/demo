package com.web.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecretUtil {

	public static String md5(String orign){
		if(orign == null || orign.trim().equals("")){
			return null;
		}
		try {
			orign+="gtxxs987654321";
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			byte[] b = mdInst.digest(orign.getBytes("utf-8"));
			StringBuffer buf = new StringBuffer();
			int i = -1;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if(i<0)i+=256;
				if(i<16)buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(md5("000000"));
	}
}
