package crawler.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
//    	HttpClientGenerator
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-])").all());
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("names", page.getHtml().xpath("//p[@class='artical_title']/a/text()").all());
        if (page.getResultItems().get("names")==null){
            //skip this page
//            page.setSkip(true);
        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
    	Spider spider = Spider
    			.create(new GithubRepoPageProcessor())
    			.addUrl("http://xycf.chifeng.gov.cn/xydt/index.jhtml?navPage=1")
    			.thread(6);
    	spider.setDownloader(new MyHttpClientDownloader());
    	spider.addPipeline(new MyPipleLine());
    	spider.run();
    	
    	
    }

}