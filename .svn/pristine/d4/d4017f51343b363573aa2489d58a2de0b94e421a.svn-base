package com.web.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by MJ on 15/9/25.
 */
public class HttpParamOperation {

  private HttpParamOperation() {}

  private static Logger logger = Logger.getLogger(HttpParamOperation.class.getName());

  /**
   * Created by MJ on 15/9/25.
   *
   * @use 将map转换成url参数格式: name1=value1&name2=value2
   *
   * @param map 参数map
   * @return String 返回参数格式: name1=value1&name2=value2
   */
  public static String getUrlParamsFromMap(Map<String, String> map) throws Exception {
    try {
      if (null != map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
          stringBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=")
              .append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
        }
        String content = stringBuilder.toString();
        if (content.endsWith("&")) {
          content = StringUtils.substringBeforeLast(content, "&");
        }
        return content;
      }
    } catch (Exception e) {
      logger.error(e);
    }
    return null;
  }

  /**
   * Created by MJ on 15/9/25.
   *
   * @use 将HttpParam列表转换成url参数格式: name1=value1&name2=value2
   *
   * @param httpParamList HttpParam列表
   * @return String 返回参数格式: name1=value1&name2=value2
   */
  public static String getUrlParamsFromList(List<HttpParam> httpParamList) throws Exception {
    try {
      if (null != httpParamList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (httpParamList.size() > 0) {
          for (HttpParam httpParam : httpParamList) {
            stringBuilder.append(URLEncoder.encode(httpParam.getKey(), "UTF-8")).append("=")
                .append(URLEncoder.encode(httpParam.getValue(), "UTF-8")).append("&");
          }
          String content = stringBuilder.toString();
          if (content.endsWith("&")) {
            content = StringUtils.substringBeforeLast(content, "&");
          }
          return content;
        }
      }
    } catch (Exception e) {
      logger.error(e);
    }
    return null;
  }
}
