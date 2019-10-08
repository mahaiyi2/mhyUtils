package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	/**
	 * 下划线明明转驼峰命名
	 * @author mhy
	 * @date:   2019年3月17日 下午6:18:40
	 * @param str 字符串
	 * @param fistUpper 首字母是否大写
	 * 
	 * @return
	 */
	public static String _2hump(String str,boolean fistUpper) {
		 str = str.toLowerCase();
		 Pattern linePattern = Pattern.compile("_(\\w)");
	     Matcher matcher = linePattern.matcher(str);
	      StringBuffer sb = new StringBuffer();
	      while (matcher.find()) {
	          matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
	     }
	       matcher.appendTail(sb);
	       str = sb.toString();
	     return fistUpper?str.substring(0, 1).toUpperCase() + str.substring(1):str;
	}
	
	public static void main(String[] args) {
		System.out.println(_2hump("aa_bb_cc",true));
	}

//public String upperCase(String str) {
//    char[] ch = str.toCharArray();
//    if (ch[0] >= 'a' && ch[0] <= 'z') {
//        ch[0] = (char) (ch[0] - 32);
//    }
//    return new String(ch);
//
//}
	  /**
	   * 驼峰转下划线写法
	   * @author mhy
	   * @date:   2019年3月17日 下午6:31:33
	   * @param str
	   * @return
	   */
	  public static String hump2_(String str) {
		  Pattern humpPattern = Pattern.compile("[A-Z]");
	      Matcher matcher = humpPattern.matcher(str);
	     StringBuffer sb = new StringBuffer();
	     while (matcher.find()) {
	          matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
	      }
	       matcher.appendTail(sb);
	     return sb.toString();
	 }
}
