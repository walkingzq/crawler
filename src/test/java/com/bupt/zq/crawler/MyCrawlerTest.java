package com.bupt.zq.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;

import static org.junit.Assert.*;

/**
 * Created by Zhao Qing on 2018/7/12.
 */
public class MyCrawlerTest {

    /**
     * 解析企业大类url规则
     * @throws Exception
     */
    @Test
    public void baseCrawler() throws Exception{
        File file = new File("F:\\个人\\学校\\毕业论文\\CDR数据\\商家号码爬取\\概览页面爬取\\电话邦 - 电话号码生态圈构建者.html");
        FileReader reader = new FileReader(file);
        char[] chars = new char[(int) file.length()];
        reader.read(chars);
        String str = new String(chars);
//        System.out.println(str);
        Document document = Jsoup.parse(str,"http://www.dianhua.cn/yp");
        Elements elements = document.select("#query_com_nav").select("dl > dt > ul").select("li > a");
        for (Element element : elements){
            System.out.println(element.ownText() + ": " + element.attr("href"));
        }
        System.out.println(elements.size());
    }

    /**
     * 解析店铺超大类url规则
     * @throws Exception
     */
    @Test
    public void shopBaseCrawler() throws Exception{
        File file = new File("F:\\个人\\学校\\毕业论文\\CDR数据\\商家号码爬取\\概览页面爬取\\天津电话号码大全,天津生活电话,-电话邦.html");
        FileReader reader = new FileReader(file);
        char[] chars = new char[(int) file.length()];
        reader.read(chars);
        String str = new String(chars);
//        System.out.println(str);
        Document document = Jsoup.parse(str,"http://www.dianhua.cn/tianjin/life");
        Elements elements = document.select("#main > div > div.main_z > div").select(".m_z2").select(".m_z2_zt").not(".dd_last1").select("a");
        for (Element element : elements){
            System.out.println(element.ownText() + ":" + element.attr("href"));
        }
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void shopBase2Crawler() throws Exception {
        File file = new File("F:\\个人\\学校\\毕业论文\\CDR数据\\商家号码爬取\\概览页面爬取\\商户二级分类页面\\热线电话-电话邦.html");
        FileReader reader = new FileReader(file);
        char[] chars = new char[(int) file.length()];
        reader.read(chars);
        String str = new String(chars);
        Document document = Jsoup.parse(str, "http://www.dianhua.cn/tianjin/c4");
        Elements elements = document.select("#main > div > div.col_left_right > div.c_left > dl > dl").select("dd").not(".active").select("a");
        for (Element element : elements){
            System.out.println(element.text() + ":" + element.attr("href"));
        }
    }

    @Test
    public void detailCrawler()throws Exception{
        File file = new File("F:\\个人\\学校\\毕业论文\\CDR数据\\商家号码爬取\\概览页面爬取\\小吃快餐电话，地址 - 天津-电话邦.html");
        FileReader reader = new FileReader(file);
        char[] chars = new char[(int) file.length()];
        reader.read(chars);
        String str = new String(chars);
        Document document = Jsoup.parse(str,"http://www.dianhua.cn/tianjin/c160");
        Elements elements = document.select("#main > div > div.col_left_right > div.c_right > div.c_right_body").select(".c_right_list > dl");
//        Elements elements = document.select("#main > div > div.col_left_right > div.c_right > div.c_right_body > div:nth-child(2) > dl > dt:nth-child(2)");
        String name , phone , addr , city = "";
        Element base = null;
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.print("              企业名称              企业电话                企业地址                企业城市              ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for (Element element : elements){
            base = element.select("dt").first();
            name = base.select("a[href]").first().ownText();
            phone = base.select("div.tel_list > p").first().ownText();
            addr = base.select("._c_addr").first().ownText();
            city = element.select("dt").last().select("._right > p").last().ownText();
            System.out.println(name + "," + phone + "," + addr + "," + city);
        }
        System.out.println("总匹配数：" + elements.size());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Elements newUrls = document.select("#main > div > div.col_left_right > div.c_right > div.c_right_foot > div").select("a").not(".active").not("._next");
        System.out.println("新增url个数:" + newUrls.size());
        for (Element element : newUrls){
            System.out.println(element.attr("href"));
        }


    }

}