package crawler.webmagic.credit;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import crawler.webmagic.InfoEntity;
import crawler.webmagic.gov.SpiderConst;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import utils.FileUtil;

public class ConsolePipleLine implements Pipeline{
	@Override
	public void process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub
		String siteName = resultItems.get(SpiderConst.SITE_NAME_KEY);
		System.out.println("网站: " + siteName);
		List<InfoEntity> infoList = resultItems.get(SpiderConst.INFO_LIST_KEY);
		List<String> strList = new ArrayList<String>();
		for(InfoEntity info:infoList){
			strList.add(info.getTitle()+"	"+info.getDate()+"	"+info.getHref()+"	"+siteName);
		}
		try {
			FileUtil.list2file(strList, "C:\\Users\\Administrator\\Desktop\\临时.txt",true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(siteName+" 发生错误");
			e.printStackTrace();
		}
	}
}
