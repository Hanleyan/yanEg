package  com.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class UtilHttp {

    @SuppressWarnings("unused")
	private RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(15000)
            .setConnectTimeout(15000)
            .setConnectionRequestTimeout(15000)
            .build();

    private static UtilHttp instance = null;
    private UtilHttp(){}
    public static UtilHttp getInstance(){
        if (instance == null) {
            instance = new UtilHttp();
        }
        return instance;
    }




    /**
     * 发送Post请求
     * @param url
     * @return
     */
    public String sendHttpPost(String url) {
        String responseContent = null;
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httppost = new HttpPost(url);
        System.out.println(httppost.getRequestLine());
        try {
            // 执行get请求
            HttpResponse httpResponse = closeableHttpClient.execute(httppost);

            // 获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            // 响应状态
            // System.out.println("status:" + httpResponse.getStatusLine());
            // 判断响应实体是否为空
            if (entity != null) {
                // System.out.println("contentEncoding:" +
                // entity.getContentEncoding());
                responseContent = EntityUtils.toString(entity);
                System.out.println("response content:"
                        + responseContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { // 关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

}
