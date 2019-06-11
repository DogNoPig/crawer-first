package jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * Jsoup测试
 * @author xw
 * @date 2019/6/11 11:01
 */
public class JsoupFristTest {
    /**
     * 测试jsoup解析URL
     * @throws Exception
     */
    @Test
    public void testUrl() throws Exception {
        // 解析url地址
        Document doc = Jsoup.parse(new URL("http://wuhan.yiwuzhishu.cn"), 10000);

        //通过标签获取值 标签选择器
        String title = doc.getElementsByTag("title").first().text();

        System.out.println(title);
    }

    /**
     * 测试jsoup解析字符串
     * @throws Exception
     */
    @Test
    public void testString() throws Exception {
        // 使用工具类 读取文件内容转换为字符串
        String content = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\test.html"), "utf8");

        //解析字符串
        Document doc = Jsoup.parse(content);

        //标签选择器解析
        String title = doc.getElementsByTag("title").first().text();

        //打印
        System.out.println(title);
    }

    /**
     * 测试jsoup解析文件
     * @throws Exception
     */
    @Test
    public void testFile() throws Exception{
        Document doc = Jsoup.parse(new File("C:\\Users\\Administrator\\Desktop\\test.html"), "utf8");

        String title = doc.getElementsByTag("title").first().text();

        System.out.println(title);

    }

    /**
     * 测试jsoup通过DOM解析文件
     * @throws Exception
     */
    @Test
    public void testDOM() throws Exception{
        Document doc = Jsoup.parse(new File("C:\\Users\\Administrator\\Desktop\\test.html"), "utf8");

        // 1.通过id获取内容
        Element idElement = doc.getElementById("hf");
        // 2.通过标签获取内容
        Element tagElement = doc.getElementsByTag("span").first();
        // 3.通过class文件获取内容
        Element classElement = doc.getElementsByClass("hf hf1").first();
        // 4.通过属性&&||值获取属性
        Element kElement = doc.getElementsByAttribute("abc").first();
        Element kvElement = doc.getElementsByAttributeValue("href", "www.baidu.com").first();

        System.out.println("通过id获取内容："+idElement.text());
        System.out.println("通过标签获取内容："+tagElement.text());
        System.out.println("通过class文件获取内容："+classElement.text());
        System.out.println("通过属性获取内容："+kElement.text());
        System.out.println("通过属性-值获取属性："+kvElement.text());
    }

    /**
     *  测试jsoup通过元素获取数据
     * @throws Exception
     */
    @Test
    public void testData() throws Exception{
        Document doc = Jsoup.parse(new File("C:\\Users\\Administrator\\Desktop\\test.html"), "utf8");

        Element hf = doc.getElementById("hf");
        Element classElement = doc.getElementsByClass("hf hf1").first();
        //1.从元素中获取id
        String str = hf.id();
        System.out.println("从元素中获取id："+str);

        //2.从元素中获取className
        String cla = classElement.className();
        System.out.println("从元素中获取className："+cla);

        //3.从元素中获取属性的值
        Element kElement = doc.getElementsByAttribute("abc").first();
        String text = kElement.attr("abc");
        System.out.println("从元素中获取属性的值："+text);

        //4.获取所有属性值
        Attributes attributes = kElement.attributes();
        Iterator<Attribute> attrs = attributes.iterator();
        while (attrs.hasNext()){
            System.out.println("获取所有属性值："+attrs.next());
        }

        //5.从元素中获取文本内容
        String s = kElement.text();
        System.out.println("从元素中获取文本内容："+s);

    }

    /**
     * 使用选择器获取元素
     * @throws Exception
     */
    @Test
    public void testSelector() throws Exception{
        Document doc = Jsoup.parse(new File("C:\\Users\\Administrator\\Desktop\\test.html"), "utf8");

        //1.通过标签查找元素
        Element element = doc.select("h1").first();
        System.out.println("单标签选择器获取元素："+element.text());
        Elements elements = doc.select("a");
        for (Element e:elements){
            System.out.println("多标签选择器获取元素："+e.text());
        }
        //2.通过id查找元素
        Element idEle = doc.select("#hf").first();
        System.out.println("id选择器获取元素："+idEle.text());

        //3.通过class名称查找元素
        Element classElement = doc.select(".hf2").first();
        System.out.println("class选择器获取元素："+classElement.text());

        //4.利用属性查找元素
        Element first = doc.select("[abc]").first();
        System.out.println("属性选择器获取元素："+first.text());

        //5.利用属性值来查找元素
        Element aEle = doc.select("[abc=123]").first();
        System.out.println("属性值选择器获取元素："+first.text());

    }

    /**
     * 组合选择器
     * @throws Exception
     */
    @Test
    public void testSelector2() throws Exception{
        Document doc = Jsoup.parse(new File("C:\\Users\\Administrator\\Desktop\\test.html"), "utf8");

        //1.元素+id
        Element element = doc.select("h1#hf").first();
        System.out.println("组合选择器：元素+id："+element.text());

        //2.元素+class
        Element element1 = doc.select("span.hf2").first();
        System.out.println("组合选择器：元素+class："+element1.text());

        //3.元素+属性名
        Element element2 = doc.select("div[abc]").first();
        System.out.println("组合选择器：元素+属性名："+element2.text());

        //4.任意组合 div[abc].hf2
        Element element3 = doc.select("[ab] > div").first();
        System.out.println("组合选择器：任意组合 父 -》 子："+element3.text());

        //5.查找某个元素下子元素
        Elements elements = doc.select(".aa div");
        System.out.println("组合选择器：元素下子元素："+elements.text());
    }

}
