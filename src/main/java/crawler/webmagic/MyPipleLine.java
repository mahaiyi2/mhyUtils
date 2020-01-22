package crawler.webmagic;

import java.util.List;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class MyPipleLine implements Pipeline{

	@Override
	public void process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub
		System.out.println("抓取到的名称：");
		List<String> names = resultItems.get("names");
		for(String name : names){
			System.out.println(name);
		}
		
		
	}

}
