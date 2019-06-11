package cn.xw.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xw
 * @date 2019/6/10 14:47
 */
public class HttpPostParamTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://www.zhihu.com/search");
        CloseableHttpResponse re = null;
        //https://www.zhihu.com/search?type=content&q=黄芬
        // 构建参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type","content"));
        params.add(new BasicNameValuePair("q","黄芬"));
        // 构建实体
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params,"utf8");
        // 将实体 写入 post
        httpPost.setEntity(formEntity);
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
