package com.bupt.zq.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by Zhao Qing on 2018/7/12.
 */
public class MyCrawler extends WebCrawler{
    /**
     *
     * @param referringPage 目标页面
     * @param url 目标页面上的url
     * @return true(爬取) or false(不爬取)
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        return super.shouldVisit(referringPage, url);
    }

    /**
     * 当一个页面被提取并准备好被你的程序处理时，这个函数被调用。
     * @param page 目标页面
     */
    @Override
    public void visit(Page page) {
        if (!(page.getParseData() instanceof HtmlParseData)){
            return;
        }
        HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
        Document document = Jsoup.parse(htmlParseData.getHtml(),page.getWebURL().getURL());
        Elements elements = document.select("");
        super.visit(page);
    }
}
