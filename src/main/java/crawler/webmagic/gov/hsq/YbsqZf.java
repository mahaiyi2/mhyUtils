package crawler.webmagic.gov.hsq;

import java.util.ArrayList;
import java.util.List;

import crawler.webmagic.ExcelPipleLine;
import crawler.webmagic.InfoEntity;
import crawler.webmagic.MyHttpClientDownloader;
import crawler.webmagic.gov.MyFilter;
import crawler.webmagic.gov.SpiderConst;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class YbsqZf implements PageProcessor {
	public static final String SITE_NAME="元宝山区政府";
	public static final String SITE_BASE_PATH="http://www.ybs.gov.cn";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(5000).setTimeOut(10000);

    @Override
    public void process(Page page) {
    	page.putField(SpiderConst.SITE_NAME_KEY,SITE_NAME);
    	List<Selectable> slList = page.getHtml().xpath("//div[@class='lbnews']/ul/li").nodes();
    	List<InfoEntity> infoList = new ArrayList<InfoEntity>();
    	for(Selectable sl : slList){
    		InfoEntity info = new InfoEntity();
    		info.setSiteName(SITE_NAME);
    		info.setTitle(sl.xpath("//span[@class='fl']/a/text()").get());
    		info.setDate(sl.xpath("//span[@class='fr font07']/text()").get());
    		info.setHref(SITE_BASE_PATH + sl.xpath("//span[@class='fl']/a/@href").get());
    		if(MyFilter.hasKeyWord(info.getTitle())&&MyFilter.dateCond(info.getDate())){
    			infoList.add(info);
    		}
    	}
    	page.putField(SpiderConst.INFO_LIST_KEY, infoList);
        
        
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void spriderStart() {
    	Spider spider = Spider
    			.create(new YbsqZf())
    			.addUrl(SITE_BASE_PATH + "/ybsqzf/438647/439204/index.html")//宝山新闻
    			.addUrl(SITE_BASE_PATH + "/ybsqzf/438647/439483/index.html")//部门动态
    			.addUrl(SITE_BASE_PATH + "/ybsqzf/438647/440208/index.html")//民生实事
    			.addUrl(SITE_BASE_PATH + "/ybsqzf/438647/439475/index.html")//媒体聚焦
    			.thread(1);
    	spider.setDownloader(new MyHttpClientDownloader());
    	spider.addPipeline(new ExcelPipleLine());
    	spider.run();
    	
    	
    }

}
