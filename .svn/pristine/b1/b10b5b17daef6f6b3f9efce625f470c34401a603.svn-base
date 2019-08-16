/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import com.google.common.collect.Lists;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * 定义字符串操作字符串时所用到的方法 
 * @author ThinkGem
 * @version 2013-05-22
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    private final static Log log = LogFactory.getLog(StringUtils.class);
    private static final char SEPARATOR = '_';
    private static final String CHARSET_NAME = "UTF-8";
    
    /**
     * 将字符串转换为字节数组
     * @param str
     * @return 结果数组
     * @author ThinkGem
     * @version 2013-05-22
     */
    public static byte[] getBytes(String str){
    	if (str != null){
    		try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
    	}else{
    		return null;
    	}
    }
    
    /**
     * 转换为字节数组
     * @param str
     * @return
     */
    public static String toString(byte[] bytes){
    	try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
    }
    
    /**
     * 是否包含字符串
     * @param str 验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inString(String str, String... strs){
    	if (str != null){
        	for (String s : strs){
        		if (str.equals(trim(s))){
        			return true;
        		}
        	}
    	}
    	return false;
    }
    
	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}
	
	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * @param html
	 * @return
	 */
	public static String replaceMobileHtml(String html){
		if (html == null){
			return "";
		}
		return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
	}
	

	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String abbr2(String param, int length) {
		if (param == null) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		int n = 0;
		char temp;
		boolean isCode = false; // 是不是HTML代码
		boolean isHTML = false; // 是不是HTML特殊字符,如&nbsp;
		for (int i = 0; i < param.length(); i++) {
			temp = param.charAt(i);
			if (temp == '<') {
				isCode = true;
			} else if (temp == '&') {
				isHTML = true;
			} else if (temp == '>' && isCode) {
				n = n - 1;
				isCode = false;
			} else if (temp == ';' && isHTML) {
				isHTML = false;
			}
			try {
				if (!isCode && !isHTML) {
					n += String.valueOf(temp).getBytes("GBK").length;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (n <= length - 3) {
				result.append(temp);
			} else {
				result.append("...");
				break;
			}
		}
		// 取出截取字符串中的HTML标记
		String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)",
				"$1$2");
		// 去掉不需要结素标记的HTML标记
		temp_result = temp_result
				.replaceAll(
						"</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
						"");
		// 去掉成对的HTML标记
		temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>",
				"$2");
		// 用正则表达式取出标记
		Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
		Matcher m = p.matcher(temp_result);
		List<String> endHTML = Lists.newArrayList();
		while (m.find()) {
			endHTML.add(m.group(1));
		}
		// 补全不成对的HTML标记
		for (int i = endHTML.size() - 1; i >= 0; i--) {
			result.append("</");
			result.append(endHTML.get(i));
			result.append(">");
		}
		return result.toString();
	}
	
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}
	
//	/**
//	 * 获得i18n字符串
//	 */
//	public static String getMessage(String code, Object[] args) {
//		LocaleResolver localLocaleResolver = (LocaleResolver) SpringContextHolder.getBean(LocaleResolver.class);
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
//		Locale localLocale = localLocaleResolver.resolveLocale(request);
//		return SpringContextHolder.getApplicationContext().getMessage(code, args, localLocale);
//	}
	
	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	/**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld" 
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld" 
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    
    /**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld" 
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }
 
    /**
     * 转换为JS获取对象值，生成三目运算返回结果
     * @param objectString 对象串
     *   例如：row.user.id
     *   返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
     */
    public static String jsGetVal(String objectString){
    	StringBuilder result = new StringBuilder();
    	StringBuilder val = new StringBuilder();
    	String[] vals = split(objectString, ".");
    	for (int i=0; i<vals.length; i++){
    		val.append("." + vals[i]);
    		result.append("!"+(val.substring(1))+"?'':");
    	}
    	result.append(val.substring(1));
    	return result.toString();
    }
    /**
	 * 验证字符串是否为数值格式
	 * @param str
	 * @return
	 */
	public static boolean isNumberic(String s){
		boolean bool=false;
//此正则无法验证0
//		if(null != s && !s.equals("")){
//			String regex = "^[1-9][0-9]*\\.[0-9]+$|^[1-9][0-9]*$|^0+\\.[0-9]+$";
//			Pattern pattern = Pattern.compile(regex);
//			char c = s.charAt(0);
//			if (c == '+' || c == '-') {
//				s = s.substring(1);
//			}
//			Matcher matcher = pattern.matcher(s);
//			bool = matcher.matches();
//		}
		if(null != s && !s.equals("")){
			try{
				Double dble = Double.valueOf(s);
				bool=true;
			}catch(Exception e){
				//
			}
		}
		
		return bool;
	}
	/**
	 * 获取当前weblogic服务的机器信息
	 * @return
	 */
	public static String getMatchServerInfo() {
		Properties p = System.getProperties();
		String serverInstallDir = "";//server 安装目录
		String thisMacherIpAddress = "";//当前本机ip地址
		try {
			serverInstallDir = p.getProperty("transport.root");
			//log.info("web server install directory is ["+serverInstallDir+"]");
			String consolePath = p.getProperty("weblogic.management.server");
			if (consolePath == null || consolePath.trim().equals("")) {
				//log.warn("this weblogic webserver is not config consolepath.using host name");
				thisMacherIpAddress = java.net.InetAddress.getLocalHost().getHostName();
			} else {
				//log.info("web server console url path is ["+consolePath+"]");
				URL url = new URL(consolePath);
				thisMacherIpAddress = url.getHost();
			}
		}catch(Exception e) {
			//ignore
			log.error("--获取数据库资源异常--"+e.getMessage());
		}
		return "["+thisMacherIpAddress+"]" + serverInstallDir;
	}
	
	   /**
     * 得到一指定分隔符号分隔的ArrayList
     * @param string 被分割的字符串
     * @param tchar 分割符
     * @return
     */
    public static ArrayList getArrayList(String string, String tchar) {
        // 按给定分割符分割字符串
        StringTokenizer token = new StringTokenizer(string, tchar);
        ArrayList array = new ArrayList();
        // 给定字符串为null或空则直接返回空的arraylist
        if (!string.trim().equals("")) {
            try {
                // 取得分割结果填入ArrayList
                while (token.hasMoreElements()) {
                    array.add(token.nextElement().toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return array;
    }
	
	 /**
     * 如果字符串为null,返回为空,否则返回该字符串
     * @param s 对象
     * @return String
     */
    public static String nullObject2String(Object s) {
        String str = "";
        try {
            str = s.toString();
        } catch (Exception e) {
            str = "";
        }
        str = str.trim();
        return str;
    }
    
    
    /**
     * 如果字符串为null,返回为空,否则返回该字符串,若字符串为""，返回0
     * @param s 对象
     * @return String
     */
    public static String nullObject2Zero(Object s) {
        String str = "";
        try {
            str = s.toString();
        } catch (Exception e) {
            str = "";
        }
        str = str.trim();
        if(str.equals("")){
        	str = "0";
        }
        return str;
    }

    /**
     * 如果对象为null,返回为0,否则返回该字符串表示的整数值
     * @param s 对象
     * @return int
     */
    public static int nullObject2int(Object s) {
        String str = "";
        int i = 0;
        try {
            str = s.toString();
            i = Integer.parseInt(str);
        } catch (Exception e) {
            i = 0;
        }
        return i;
    }
    /**
     * 如果对象为null,返回为0,否则返回该字符串表示的数值
     * @param s 对象
     * @return float
     */
    public static float nullObject2float(Object s) {
        String str = "";
        float f = 0;
        try {
            str = s.toString();
            f = Float.parseFloat(str) ;
        } catch (Exception e) {
            f = 0;
        }
        return f;
    }
    /**
     * 如果对象为null,返回为0,否则返回该字符串表示的数值
     * @param s 对象
     * @return float
     */
    public static long nullObject2long(Object s) {
        String str = "";
        long f = 0;
        try {
            str = s.toString();
            f = Long.parseLong(str) ;
        } catch (Exception e) {
            f = 0;
        }
        return f;
    }
    /**
     * 将输入的字符串str按照分割符dim分割成字符串数组并返回ArrayList字符串数组
     * @param str 给定字符串
     * @param dim 分割符
     * @param returndim 返回值中是否包含分割符号
     * @return
     */
    public static ArrayList<Object> TokenizerString(String str, String dim,
                                            boolean returndim) {
        str = null2String(str);
        dim = null2String(dim);
        ArrayList strlist = new ArrayList();
        // 字符串str按照分割符dim分割成字符串数组并返回ArrayList字符串数组
        StringTokenizer strtoken = new StringTokenizer(str, dim, returndim);

        while (strtoken.hasMoreElements()) {
            strlist.add(strtoken.nextElement());
        }
        return strlist;
    }
    
    public static String TokenizerList(ArrayList al,String str,String column){
    	String strlist = "";
    	if(al!=null&&al.size()>0){
    		for(int i=0;i<al.size();i++){
    			HashMap hm = (HashMap)al.get(i);
    			String t_str = nullObject2String(hm.get(column));
    			strlist = strlist+t_str+str;
    		}
    	}
    	return strlist;
    }

    
    /**
     * 此方法将传入的字符串，如ABC[,||;]DEF转化为'ABC','DEF'
     * @str_source 传入的源字符串
     * @return 转换后的字符串
     */
    public static String getQuoteString(String str_source){
        //
         String str_return="";
         str_source=str_source.replace(';',',').replace('；',',').replace(' ',',').replace('，',',');                    
            if(str_source.trim().length()>0){
                StringTokenizer st=new StringTokenizer(str_source,",");
                    while(st.hasMoreTokens()){
                        if(str_return.length()>0){
                            str_return=str_return+",'"+st.nextToken().trim()+"'";
                          }else{
                       str_return="'"+st.nextToken().trim()+"'";
                        }
                 }
            }
               
        return str_return;
     }

    
    /**
     * 如果字符串为null,返回为空,否则返回该字符串
     * @param s 字符串
     * @return String
     */
    public static String null2String(String s) {
        return s == null ? "" : s;
    }
    
    /**
     * 在字符串s左边加'%'
     * @param s 需要处理的字符串
     */
    public static String addLikeAtLeft(String s){
    	return "%"+ s;
    }
    
    /**
     * 在字符串s右边加'%'
     * @param s 需要处理的字符串
     */
    public static String addLikeAtRight(String s){
    	return  s + "%";
    }

    /**
     * 在字符串s两边边加'%'
     * @param s 需要处理的字符串
     */
    public static String addLikeAtAll(String s){
    	return  "%"+ s + "%";
    }
    
    /**
     * 在字符串s两边边加单引号
     * @param s 需要处理的字符串
     */
    public static String quoteS(String s){
    	return  "'"+ s + "'";
    }
}
