package cn.xw.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author xw
 * @date 2019/6/10 14:47
 */
public class HttpPostTest {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://wuhan.yiwuzhishu.cn/");
        CloseableHttpResponse re = null;
        //https://www.zhihu.com/search?type=content&q=%E9%BB%84%E8%8A%AC
        try {
            re = httpClient.execute(httpPost);
            HttpEntity entity = re.getEntity();
            String content = EntityUtils.toString(entity, "utf8");
//            long length = entity.getContentLength();
            System.out.println(content.length());
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
