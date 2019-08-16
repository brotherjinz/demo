package com.web.util;

/**
 * Created by MJ on 15/9/25.
 *
 * @use 该HttpParam类用于封装HTTP请求参数
 */
public class HttpParam {

  private String key; // 请求参数
  private String value; // 参数值

  public HttpParam() {}

  public HttpParam(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
