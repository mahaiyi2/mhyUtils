package utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class IdUtils {
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
//	public static void main(String[] args) throws IOException {
//		File f = new File("C:\\Users\\Administrator\\Desktop\\UUID.txt");
//		for(int i =0;i<117786;i++){
//			FileUtil.appendToFilePerLine(f, get32UUID());
//		}
//	}
}
