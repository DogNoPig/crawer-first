package cn.xw.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author xw
 * @date 2019/6/10 14:47
 */
public class HttpConfigTest {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://wuhan.yiwuzhishu.cn/");

        //配置请求信息
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)  // 创建连接的最长时间，单位是毫秒
                .setConnectionRequestTimeout(500)  // 设置获取连接的最长时间 毫秒
                .setSocketTimeout(10*1000)   // 设置数据传输的最长时间
                .build();

        // 给请求设置请求信息
        httpGet.setConfig(config);

        CloseableHttpResponse re = null;
        //https://www.zhihu.com/search?type=content&q=%E9%BB%84%E8%8A%AC
        try {
            re = httpClient.execute(httpGet);
            HttpEntity entity = re.getEntity();
            if (re.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(entity, "utf8");
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                re.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
