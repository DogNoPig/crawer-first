package cn.xw.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xw
 * @date 2019/6/10 11:59
 */
public class CrawlerFrist {
    public static void main(String[] args) throws IOException {
        // 1.打开浏览器，创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 2.输入网址，发送请求
        HttpGet httpGet = new HttpGet("http://wuhan.yiwuzhishu.cn/");

        // 3.发起请求返回响应 使用httpclient对象发起请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        // 4.解析响应，获取数据
        //判断状态码是否是200
        if (httpResponse.getStatusLine().getStatusCode() == 200){
            HttpEntity entity = httpResponse.getEntity();
            //InputStream content = entity.getContent();
            String content = EntityUtils.toString(entity, "utf8");
            System.out.println(content);
        }
    }
}
