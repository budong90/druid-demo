
package cc.eslink.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * HTTP请求处理
 *
 * @author zhengc
 *
 */
public class HttpRequest {

    /**
     * http post请求处理
     *
     * @param url
     * @param params
     * @return
     */
    public static String postMethodURL(String url, Map<String, String> params) throws IOException {
        String bodyAsString = null;
        CloseableHttpClient client = HttpClientBuilder.create().build();
        try {
            if (url != null) {
                HttpPost httpPost = new HttpPost(url);
                List<BasicNameValuePair> parmLists = new ArrayList<BasicNameValuePair>();
                if (params != null) {
                    for (Entry<String, String> entry : params.entrySet()) {
                        parmLists.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                    }
                }
                httpPost.setEntity(new UrlEncodedFormEntity(parmLists, "utf-8"));
                RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build(); // 设置请求和传输超时时间
                httpPost.setConfig(requestConfig);
                CloseableHttpResponse response = client.execute(httpPost);
                bodyAsString = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return bodyAsString;
    }
}
