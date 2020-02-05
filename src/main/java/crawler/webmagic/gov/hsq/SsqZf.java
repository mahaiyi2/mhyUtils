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

public class SsqZf implements PageProcessor {
	public static final String SITE_NAME="松山区政府";
//	public static final String SITE_BASE_PATH="http://www.hongshanqu.gov.cn";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(5000).setTimeOut(10000);

    @Override
    public void process(Page page) {
    	page.putField(SpiderConst.SITE_NAME_KEY,SITE_NAME);
    	List<Selectable> slList = page.getHtml().xpath("//div[@class='part-list']/ul/li").nodes();
    	List<InfoEntity> infoList = new ArrayList<InfoEntity>();
    	for(Selectable sl : slList){
    		InfoEntity info = new InfoEntity();
    		info.setSiteName(SITE_NAME);
    		info.setTitle(sl.xpath("//a/text()").get());
    		info.setDate(sl.xpath("//span/text()").get().replace("[ ","").replace(" ]", ""));//[ 2020-02-04 ]
    		info.setHref(sl.xpath("//a/@href").get());
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
    			.create(new SsqZf())
    			.addUrl("http://www.ssq.gov.cn/index.php?m=content&c=index&a=lists&catid=15")//今日松山
    			.addUrl("http://www.ssq.gov.cn/index.php?m=content&c=index&a=lists&catid=16")//综合信息
    			.thread(1);
    	spider.setDownloader(new MyHttpClientDownloader());
    	spider.addPipeline(new ExcelPipleLine());
    	spider.run();
    	
    	
    }
}
