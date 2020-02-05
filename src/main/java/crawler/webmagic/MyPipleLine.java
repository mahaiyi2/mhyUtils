package crawler.webmagic;

import java.util.List;

import crawler.webmagic.gov.MyFilter;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Selectable;

public class MyPipleLine implements Pipeline{

	@Override
	public void process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub
		System.out.println("抓取到的名称：");
		List<Selectable> aElements = resultItems.get("el_a");
		for(Selectable aElement : aElements){
			String title = aElement.xpath("//a/text()").get();
			String href = aElement.xpath("//a/@href").get();
			String date = aElement.xpath("//a/span/text()").get();
			if(MyFilter.hasKeyWord(title) && MyFilter.dateCond(date)){
				System.out.println(date+" : "+ title );
				System.out.println(href);
			}
		}
		
		
	}

}
