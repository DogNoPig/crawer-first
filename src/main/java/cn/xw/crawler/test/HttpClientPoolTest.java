package cn.xw.crawler.test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author xw
 * @date 2019/6/10 17:21
 */
public class HttpClientPoolTest {
    public static void main(String[] args) {
        //创建连接池
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        //设置连接池最大连接数
        cm.setMaxTotal(100);
        //设置每个主机的最大连接数
        cm.setDefaultMaxPerRoute(10);
        doGet(cm);
        doGet(cm);
//        doPost(cm);
    }

    private static void doGet(PoolingHttpClientConnectionManager cm) {
        //加入连接池管理
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        //请求方式
        HttpGet httpGet = new HttpGet("http://wuhan.yiwuzhishu.cn");
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(),"utf8");
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
                //在此不能关闭httpClient
                //由连接池管理
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private static void doPost(PoolingHttpClientConnectionManager cm) {
    }
}
