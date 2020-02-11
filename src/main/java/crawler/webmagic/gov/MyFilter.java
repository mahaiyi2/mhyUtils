package crawler.webmagic.gov;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MyFilter {

	private static Set<String> keyWord;
	private static Calendar clCond = Calendar.getInstance();
	static{
		keyWord = new HashSet<>();
		keyWord.add("冠状");
		keyWord.add("疫");
		keyWord.add("肺炎");
		keyWord.add("武汉");
		keyWord.add("湖北");
		keyWord.add("安全");
		keyWord.add("防");
		keyWord.add("病毒");
		keyWord.add("应急");
		keyWord.add("救援");
		
		//注意月份从0开始算，第三个参数为当月的第几天，如果超过当月天数，则月份增加
		clCond.set(2020,1, 9);
		
	}
	
	public static boolean hasKeyWord(String title){
		for(String s:keyWord){
			if(title.contains(s))return true;
		}
		return false;
	}
	public static boolean dateCond(String str){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse(str);
			Calendar cl = Calendar.getInstance();
			cl.setTime(d);
			return cl.after(clCond);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(clCond.get(Calendar.YEAR));
		System.out.println(clCond.get(Calendar.MONTH));
		System.out.println(clCond.get(Calendar.DATE));
	}
}
