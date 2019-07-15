package com.gzw.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class HttpClientUtil {
	public String httpPostMethod(String url,Map<String,String> params) throws IOException {
		int socketTimeout = 30000; //请求超时时间
		int connectTimeout = 30000;// 传输超时时间
		String result = null;
		//判断是否有参数
		try {
			if (params != null) {
				// List<NameValuePair> parameters = new ArrayList<NameValuePair>();
				URIBuilder builder = new URIBuilder(url);
				for (Map.Entry<String, String> entry : params.entrySet()) {
					// parameters.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
					builder.addParameter(entry.getKey(), entry.getValue());
				}
				url = builder.build().toString();
				// httpPost.setEntity(new UrlEncodedFormEntity(parameters));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpPost httpPost = new HttpPost(url);
    	// 设置请求和传输超时时间
    	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
    	httpPost.setConfig(requestConfig);
    	httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		try {
			if(httpResponse.getStatusLine().getStatusCode()==200) {
				result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			if(httpResponse!=null) {
				httpResponse.close();;
			}
			if(httpClient!=null) {
				httpClient.close();
			}
		}
		return result;
	}
}
