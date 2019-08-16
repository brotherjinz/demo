package com.web.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MJ on 15/9/25.
 */

public class HttpRequestUtils {

  private HttpRequestUtils() {}

  private static Logger logger = Logger.getLogger(HttpRequestUtils.class.getName());

  /**
   * 向指定URL发送GET方法的请求
   *
   * @param url 发送请求的URL
   * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
   * @return URL 所代表远程资源的响应结果
   */
  public static String sendGetRequest(String url, String param) throws Exception {
    StringBuilder stringBuilder = new StringBuilder(); // 用来接受返回值
    BufferedReader bufferedReader = null;
    try {
        if (null != url && null != param) {
            String getUrlStr = url + "?" + param;
            URL getUrl = new URL(getUrlStr);
            // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，返回不同的URLConnection子类的对象，这里返回的是HttpURLConnection
            HttpURLConnection httpURLConnection = (HttpURLConnection) getUrl.openConnection();
            httpURLConnection.setRequestProperty(" Content-Type ", " application/x-www-form-urlencoded ");
            // 建立实际的连接，但是实际上get请求要在下一句的getInputStream()函数中才会真正发到服务器
            httpURLConnection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            bufferedReader =
                    new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8")); // 设置编码，否则中文乱码
            String line;
            while (null != (line = bufferedReader.readLine())) {
                stringBuilder.append(line);
            }
            httpURLConnection.disconnect(); // 断开连接
            return stringBuilder.toString();
        }
    } catch (Exception e) {
      System.out.println("发送GET请求出现异常！" + e);
      logger.error(e);
    } finally { // 使用finally块来关闭输入流
      try {
        if (null != bufferedReader) {
          bufferedReader.close();
        }
      } catch (Exception e2) {
        logger.error(e2);
      }
    }
      return null;
  }

  /**
   * 向指定 URL 发送POST方法的请求
   *
   * @param url 发送请求的 URL
   * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
   * @return 所代表远程资源的响应结果
   */
  public static String sendPostRequest(String url, String param) throws Exception {
    DataOutputStream dataOutputStream = null;
    BufferedReader bufferedReader = null;
    StringBuilder stringBuilder = new StringBuilder(); // 用来接受返回值
    try {
        if (null != url && null != param) {
            URL postUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection httpURLConnection = (HttpURLConnection) postUrl.openConnection();
            // 设置通用的请求属性
            httpURLConnection.setRequestMethod("POST"); // 设置post方法，默认是get方法
            httpURLConnection.setUseCaches(false); // post请求不能使用缓存
            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode进行编码
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true); // 设置是否向connection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true
            httpURLConnection.setDoInput(true); // 从connection读入，默认是true
            // 连接，从openConnection()至此的配置必须要在connect之前完成，要注意的是getOutputStream会隐含的进行connect。
            // 建立实际的连接
            httpURLConnection.connect();
            // 获取URLConnection对象对应的输出流
            dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            // 发送请求参数，writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
            dataOutputStream.writeBytes(param);
            //  刷新对象输出流，将任何字节都写入潜在的流中
            dataOutputStream.flush();
            // 定义BufferedReader输入流来读取URL的响应
            bufferedReader =
                    new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8")); // 设置编码，否则中文乱码
            String line;
            while (null != (line = bufferedReader.readLine())) {
                stringBuilder.append(line);
            }
            httpURLConnection.disconnect(); // 断开连接
            return stringBuilder.toString();
        }
    } catch (Exception e) {
      System.out.println("发送 POST 请求出现异常！" + e);
      logger.error(e);
    } finally { // 使用finally块来关闭输出流、输入流
      try {
        if (null != dataOutputStream) {
          dataOutputStream.close();
        }
        if (null != bufferedReader) {
          bufferedReader.close();
        }
      } catch (Exception e2) {
        logger.error(e2);
      }
    }
    return null;
  }
}
