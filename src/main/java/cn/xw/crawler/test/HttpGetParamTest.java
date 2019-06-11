package cn.xw.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author xw
 * @date 2019/6/10 14:47
 */
public class HttpGetParamTest {
    public static void main(String[] args) throws URISyntaxException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //https://www.zhihu.com/search?type=content&q=黄芬
        //构建uribuilder
        URIBuilder builder = new URIBuilder("https://www.zhihu.com/search");

        builder.setParameter("type","content").setParameter("q","黄芬");

        HttpGet httpGet = new HttpGet(builder.build());
        CloseableHttpResponse re = null;


        try {
            re = httpClient.execute(httpGet);
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
