package crawler.webmagic.gov;

import crawler.webmagic.MyHttpClientDownloader;
import crawler.webmagic.MyPipleLine;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class HomePage implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000).setTimeOut(10000);

    @Override
    public void process(Page page) {
//    	HttpClientGenerator
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
//        page.addTargetRequests(page.getHtml().links().regex("(http://www\\.chifeng\\.gov\\.cn/channels/4_\\d+\\.html)").all());
        page.addTargetRequests(page.getHtml().links().regex("(http://www\\.chifeng\\.gov\\.cn/channels/4_[2,3]\\.html)").all());
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-])").all());
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("titles", page.getHtml().xpath("//p[@class='artical_title']/a/text()").all());
        page.putField("el_a", page.getHtml().xpath("///div[@class='zy_list']/ul/li").nodes());
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
    	Spider spider = Spider
    			.create(new HomePage())
    			.addUrl("http://www.chifeng.gov.cn/channels/4.html")
    			.thread(6);
    	spider.setDownloader(new MyHttpClientDownloader());
    	spider.addPipeline(new MyPipleLine());
    	spider.run();
    	
    	
    }

}
