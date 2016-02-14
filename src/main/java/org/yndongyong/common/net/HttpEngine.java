package org.yndongyong.common.net;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.yndongyong.common.utils.L;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * http请求类，单例
 */
public class HttpEngine {

	private static final String TAG = "HttpEngine";

	private final static String REQUEST_MOTHOD = "POST";
	private final static String ENCODE_TYPE = "UTF-8";
	private final static int TIME_OUT = 15*1000;

	private static HttpEngine instance = null;

	private HttpEngine() {
	}

	public static HttpEngine getInstance() {
		if (instance == null) {
			instance = new HttpEngine();
		}
		return instance;
	}

	/**
	 * 
	 * @param url 访问的地址
	 * @param paramsMap map 参数集合
	 * @param typeOfT 要返回对象的类型
	 * @return T
	 * @throws IOException
	 */
	public <T> T postHandle(String url ,Map<String, String> paramsMap, Type typeOfT)
			throws IOException ,JsonParseException{
		L.d(TAG, " postHandle() ");
		HttpURLConnection connection = getConnection(url);

		String data = joinParams(paramsMap);
		connection.setRequestProperty("Content-Length",String.valueOf(data.getBytes().length));
		connection.connect();
		L.d(TAG, " postHandle() connection is null ?:" + connection);
		OutputStream os = connection.getOutputStream();
		os.write(data.getBytes());
		os.flush();

		if (connection.getResponseCode() == 200) {
			// 获取响应的输入流对象
			InputStream is = connection.getInputStream();
			// 创建字节输出流对象
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// 定义读取的长度
			int len = 0;
			// 定义缓冲区
			byte buffer[] = new byte[1024];
			// 按照缓冲区的大小，循环读取
			while ((len = is.read(buffer)) != -1) {
				// 根据读取的长度写入到os对象中
				baos.write(buffer, 0, len);
			}
			// 释放资源
			is.close();
			baos.close();
			connection.disconnect();
			// 返回字符串
			final String result = new String(baos.toByteArray());
            L.d(TAG, "http result:" + result);
            Gson gson = new Gson();
                return gson.fromJson(result, typeOfT);
        } else {
			connection.disconnect();
			return null;
		}
	}

	/**
	 * 根据 url获取HttpURLConnection 对象
	 * @param SERVER_URL API的地址
	 * @return
	 */
	private HttpURLConnection getConnection(String SERVER_URL) {
		HttpURLConnection connection = null;
		// 初始化connection
		try {
			// 根据地址创建URL对象
			URL url = new URL(SERVER_URL);
			// 根据URL对象打开链接
			connection = (HttpURLConnection) url.openConnection();
			// 设置请求的方式
			connection.setRequestMethod(REQUEST_MOTHOD);
			// 发送POST请求必须设置允许输入，默认为true
			connection.setDoInput(true);
			// 发送POST请求必须设置允许输出
			connection.setDoOutput(true);
			// 设置不使用缓存
			connection.setUseCaches(false);
			// 设置请求的超时时间
			connection.setReadTimeout(TIME_OUT);
			connection.setConnectTimeout(TIME_OUT);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("Response-Type", "json");
			connection.setChunkedStreamingMode(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}

	private String joinParams(Map<String, String> paramsMap) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : paramsMap.keySet()) {
			stringBuilder.append(key);
			stringBuilder.append("=");
			stringBuilder.append(URLEncoder.encode(paramsMap.get(key),ENCODE_TYPE));
			stringBuilder.append("&");
		}
		return stringBuilder.substring(0, stringBuilder.length() - 1);
	}
}
