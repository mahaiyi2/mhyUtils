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

public class HsqZf implements PageProcessor {
	public static final String SITE_NAME="红山区政府";
	public static final String SITE_BASE_PATH="http://www.hongshanqu.gov.cn";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(5000).setTimeOut(10000);

    @Override
    public void process(Page page) {
    	page.putField(SpiderConst.SITE_NAME_KEY,SITE_NAME);
    	List<Selectable> slList = page.getHtml().xpath("//div[@class='listtext_listpage']/ul/li").nodes();
    	List<InfoEntity> infoList = new ArrayList<InfoEntity>();
    	for(Selectable sl : slList){
    		InfoEntity info = new InfoEntity();
    		info.setSiteName(SITE_NAME);
    		info.setTitle(sl.xpath("//a/text()").get());
    		info.setDate(sl.xpath("//span/text()").get());
    		info.setHref(SITE_BASE_PATH + sl.xpath("//a/@href").get());
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
    			.create(new HsqZf())
    			.addUrl(SITE_BASE_PATH + "/news/jrhs/index.html")//今日红山
    			.addUrl(SITE_BASE_PATH + "/news/bmdt/index.html")//部门动态
    			.addUrl(SITE_BASE_PATH + "/news/zjdt1/index.html")//镇街动态
    			.addUrl(SITE_BASE_PATH + "/news/jrtt/index.html")//今日头条
    			.thread(1);
    	spider.setDownloader(new MyHttpClientDownloader());
    	spider.addPipeline(new ExcelPipleLine()); 
    	spider.run();
    	
    	
    }
}
