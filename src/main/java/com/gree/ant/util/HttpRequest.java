package com.gree.ant.util;


import com.gree.ant.exception.KellyException;
import org.apache.commons.codec.binary.Base64;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class HttpRequest {

	public enum Method {
		GET, POST, POST_XML, POST_JSON
	}
	public static final String CHARSET_UTF8 = "utf-8";
	public static final String CHARSET_GBK = "gbk";

	private static String authorizationToken = null;
	public static void setAuthorization(String user, String password){
		String key = user+":"+password;
		authorizationToken = new String(Base64.encodeBase64(key.getBytes()));
	}


	/**
	 * GET
	 * @param fullurl
	 * @return
	 */
	public static String get(String fullurl, Map<String, String> header, String charset) {
		return httpSend(fullurl, Method.GET, null, header, charset);
	}

	public static String get(String fullurl) {
		return httpSend(fullurl, Method.GET, null, null, CHARSET_UTF8);
	}

	/**
	 * POST
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, HashMap<String, Object> params, Map<String, String> header, String charset) {
		return httpSend(url, Method.POST, params, header, charset);
	}

	public static String post(String url, HashMap<String, Object> params) {
		return httpSend(url, Method.POST, params, null, CHARSET_UTF8);
	}

	/**
	 * POST XML
	 * @param url
	 * @param body
	 * @return
	 */
	public static String postXML(String url, String body, Map<String, String> header, String charset) {
		return httpSend(url, Method.POST_XML, body, header, charset);
	}

	public static String postXML(String url, String body) {
		return httpSend(url, Method.POST_XML, body, null, CHARSET_UTF8);
	}

	/**
	 * POST JSON
	 * @param url
	 * @param body
	 * @return
	 */
	public static String postJSON(String url, Object body, Map<String, String> header, String charset) {
		return httpSend(url, Method.POST_JSON, body, header, charset);
	}

	public static String postJSON(String url, Object body) {
		return httpSend(url, Method.POST_JSON, body, null, CHARSET_UTF8);
	}

	public static String postJSON(String url, Object body, Map<String, String> header) {
		return httpSend(url, Method.POST_JSON, body, header, CHARSET_UTF8);
	}

	@SuppressWarnings("unchecked")
	private static String httpSend(String url, Method method, Object params, Map<String, String> header, String charset){

		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();

		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "GREE_API_SYSTEM");
			connection.setRequestProperty("Accept-Charset", charset);

			connection.setDefaultUseCaches(false);

			if (method == Method.POST_XML) {
				connection.setRequestProperty("Content-Type", "text/xml; charset=" + charset);
			} else if (method == Method.POST_JSON) {
				connection.setRequestProperty("Content-Type", "application/json; charset=" + charset);
			} else if (method == Method.POST) {
				connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=" + charset);
			}

			/**
			 * 如果设置了帐号即加入
			 */
			if (authorizationToken != null) {
				connection.setRequestProperty("Authorization", "Basic " + authorizationToken);
			}

			/**
			 * 加入header
			 */
			if (header != null) {
				for (Map.Entry<String, String> entry : header.entrySet()) {
					connection.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}

			if (method == Method.GET) {

				connection.connect();

			} else {

				// 发送POST请求必须设置如下两行
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setConnectTimeout(60000*20);
				connection.setReadTimeout(60000*20);
				// 获取URLConnection对象对应的输出流


				out = new PrintWriter(connection.getOutputStream());
				// 发送请求参数

				if (method == Method.POST) {

					if (params == null) {
						//为空时什么都不做
					} else if (params instanceof String) {
						out.print(params);
					} else if (params instanceof Map) {

						StringBuffer param = new StringBuffer();
						Map _params = (Map) params;

						for (Iterator iterator = _params.keySet().iterator(); iterator.hasNext(); ) {
							String key = (String) iterator.next();
							Object obj = _params.get(key);
							if (obj == null) continue;
							if (obj.getClass().isArray()) {
								//数组的处理方式
								Object[] objs = (Object[]) obj;

								for (int i = 0; i < objs.length; i++) {
									param.append(key).append("=").append(objs[i]);
									param.append("&");
								}
							} else {
								param.append(key).append("=").append(obj);
								param.append("&");
							}
						}
						out.print(param);
					} else {
						throw new RuntimeException("不支持的Params类型[" + params.getClass() + "]");
					}

				} else if (method == Method.POST_JSON) {

					if (params == null) {
						//为空时什么都不做
					} else if (params instanceof String) {
						out.print(params);
					} else {
						out.print(Json.toJson(params, JsonFormat.compact()));
					}

				} else if (method == Method.POST_XML) {
					out.print(String.valueOf(params));
				}

				// flush输出流的缓冲
				out.flush();

			}//判断是否为POST提交 end


			// 定义BufferedReader输入流来读取URL的响应
//			in = new BufferedReader(
//					new InputStreamReader(connection.getInputStream(), charset));

			in = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), charset));

			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (java.net.ConnectException e){
			e.printStackTrace();
			throw new RuntimeException(String.format("Error for [%s] [%s]", url, "连接API网关超时或异常"));
//			System.err.println("Connect Error: " + e.getMessage());
//
//			NutMap mapResult = NutMap.NEW();
//			NutMap meta = NutMap.NEW().addv("url", url).addv("method", method).addv("header", header).addv("params", params).addv("charset", charset);
//			mapResult.addv("success", false).addv("message", e.getMessage()).addv("meta", meta);
//			result.append(Json.toJson(mapResult));

		} catch (Exception e)  {
			e.printStackTrace();
			throw new RuntimeException(String.format("Error for [%s]", e.getMessage()));
//			System.err.println("Send POST Error: " + e.getMessage());
//
//			NutMap mapResult = NutMap.NEW();
//			NutMap meta = NutMap.NEW().addv("url", url).addv("method", method).addv("header", header).addv("params", params).addv("charset", charset);
//			mapResult.addv("success", false).addv("message", e.getMessage()).addv("meta", meta);
//			result.append(Json.toJson(mapResult));

		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return result.toString();
	}

	public static KellyException detailException(Exception e){
		e.printStackTrace();
		Map<String,Object> result = packageException(e);
		return new KellyException(Integer.valueOf(result.get("code").toString()),result.get("msg").toString());
	}

	public static Map<String,Object> packageException(Exception e){
		int code = 888;
		String msg;
		if(e instanceof KellyException){
			KellyException kelly = (KellyException)e;
			code = kelly.getCode();
			msg = kelly.getMessage();
		}else{
			msg = getExceptionInformation(e);
		}
		return ResultUtil.getResult(code,msg,null);
	}

	public static String getExceptionInformation(Exception e){
		StringBuilder out = new StringBuilder();
		StackTraceElement[] trace = e.getStackTrace();
		for (StackTraceElement element:trace){
			out.append(element).append("\r\n");
		}
		return out.toString();
	}

	public static String getThrowableInformation(Throwable e){
		StringBuilder out = new StringBuilder();
		StackTraceElement[] trace = e.getStackTrace();
		for (StackTraceElement element:trace){
			out.append(element).append("\r\n");
		}
		return out.toString();
	}





}
