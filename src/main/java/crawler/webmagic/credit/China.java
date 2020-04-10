package crawler.webmagic.credit;

import crawler.webmagic.MyHttpClientDownloader;
import crawler.webmagic.gov.SpiderConst;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
//import us.codecraft.webmagic.selector.Selectable;

public class China implements PageProcessor {
	public static final String SITE_NAME="信用中国";
    private Site site = Site.me().setRetryTimes(1).setSleepTime(5000).setTimeOut(10000);

    @Override
    public void process(Page page) {
    	page.putField(SpiderConst.SITE_NAME_KEY,SITE_NAME);
    	System.out.println(page.getHtml());
    	Selectable copName = page.getHtml().xpath("//ul[@id='companylists']/li/p[@class='company-name']/span/text()");
//    	List<InfoEntity> infoList = new ArrayList<InfoEntity>();
//    	for(Selectable sl : slList){
//    		InfoEntity info = new InfoEntity();
//    		info.setSiteName(SITE_NAME);
//    		info.setTitle(sl.xpath("//a/text()").get());
//    		info.setDate(sl.xpath("//span/text()").get().replace("[","").replace("]", ""));//[2020-02-03]
//    		info.setHref(SITE_BASE_PATH + sl.xpath("//a/@href").get());
//    		if(MyFilter.hasKeyWord(info.getTitle())&&MyFilter.dateCond(info.getDate())){
//    			infoList.add(info);
//    		}
//    	}
    	page.putField(SpiderConst.TITLE_KEY, copName);
        
        
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void spriderStart() {
    	Spider spider = Spider
    			.create(new China())
    			.addUrl("https://www.creditchina.gov.cn/xinyongxinxi/index.html?index=0&scenes=defaultScenario&tableName=credit_xyzx_tyshxydm&searchState=2&entityType=1,2,4,5,6,7,8&keyword=%E8%B5%A4%E5%B3%B0%E4%B9%9D%E5%B7%9E%E9%80%9A%E8%BE%BE%E5%8C%BB%E7%96%97%E5%99%A8%E6%A2%B0%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8")//要闻动态
//    			.addUrl("http://fgw.nmg.gov.cn/xynmg/essearch-L.jspx?index=0&keywords=%E8%B5%A4%E5%B3%B0%E4%B9%9D%E5%B7%9E%E9%80%9A%E8%BE%BE%E5%8C%BB%E7%96%97%E5%99%A8%E6%A2%B0%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8")
    			.thread(1);
    	System.setProperty("selenuim_config","F:\\biancheng\\workSpaces\\mhyUtils\\mhyUtils\\src\\main\\resources\\config.ini");
//    	spider.setDownloader(new MyHttpClientDownloader());
    	spider.setDownloader(new SeleniumDownloader("C:\\Users\\Administrator\\Desktop\\chromedriver.exe"));//模拟浏览器
    	spider.addPipeline(new ConsolePipeline());
    	spider.run();
    }
    public static void main(String[] args) {
    	spriderStart();
	}
}
