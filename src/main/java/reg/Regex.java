package reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	 public static void main(String[] args) {
		  String content = "good(hello)";
		  //String regex = "\\((.*?)\\)";
		  String regex = "\\((.*)\\)";
		  Pattern p = Pattern.compile(regex);
		  Matcher m = p.matcher(content);
		  while (m.find()) {
		
		   System.out.println("得到(123)".replaceAll(regex,""));
		  }
		 }
}
