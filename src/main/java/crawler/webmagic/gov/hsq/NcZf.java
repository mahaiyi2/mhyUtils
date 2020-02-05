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

public class NcZf implements PageProcessor {
	public static final String SITE_NAME="宁城县政府";
	public static final String SITE_BASE_PATH="http://www.ningchengxian.gov.cn";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(5000).setTimeOut(10000);

    @Override
    public void process(Page page) {
    	page.putField(SpiderConst.SITE_NAME_KEY,SITE_NAME);
    	List<Selectable> slList = page.getHtml().xpath("//div[@class='News_PagingList']/div/ul/li").nodes();
    	
    	List<InfoEntity> infoList = new ArrayList<InfoEntity>();
    	for(Selectable sl : slList){
    		InfoEntity info = new InfoEntity();
    		info.setSiteName(SITE_NAME);
    		info.setTitle(sl.xpath("//a/text()").get());
    		info.setDate(sl.xpath("//span/text()").get().replace("[","").replace("]", ""));//[2020-02-03]
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
    			.create(new NcZf())
    			.addUrl("http://www.ningchengxian.gov.cn/zwgk/jggk/jrnc/")//今日宁城
    			.addUrl("http://www.ningchengxian.gov.cn/zwgk/jggk/bmdt/")//部门动态
    			.addUrl("http://www.ningchengxian.gov.cn/zwgk/jggk/xzdt/")//乡镇动态
    			.thread(1);
    	spider.setDownloader(new MyHttpClientDownloader());
    	spider.addPipeline(new ExcelPipleLine());
    	spider.run();
    	
    	
    }
    public static void main(String[] args) {
    	spriderStart();
	}
}
