package excelWord.excel.jxls2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.util.IOUtils;

public class Demo {
    static byte[] imgBytes;
//    static{
//        try {
//            InputStream ins = new FileInputStream(Demo.class.getClassLoader().getResource("img.jpg").getFile());
//            imgBytes = IOUtils.toByteArray(ins);
//            ins.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void main(String[] args) throws FileNotFoundException, IOException {
//        HashMap<String, Object> beans = new HashMap<String, Object>();
//        beans.put("title", "这是标题");
//        beans.put("list", getList());
//        beans.put("logoImg", imgBytes);
//        beans.put("startTime", new Date());
//        beans.put("endTime", new Date());
//        beans.put("exportTime", new Date());
//        beans.put("url", "http://www.baidu.com");
//        beans.put("company", "LK_King");
//        File template = JxlsUtil.getTemplate("demo.xlsx");
//        File out = new File("D:/out.xlsx");
//        JxlsUtil.exportExcel(template, out, beans);
//        System.out.println("导出成功");
//    }

    static List<User> getList(){
        List<User> list = new ArrayList<User>();
        User zs = new User("张三");
        User ls = new User("李四");
        User ww = new User("王五");
        for (int i = 1; i <= 3; i++) {
            zs.getClients().add(new User("客户"+i, true, imgBytes));
        }
        for (int i = 1; i <= 2; i++) {
            ww.getClients().add(new User("客户"+i, false, null));
        }
        list.add(zs);
        list.add(ls);
        list.add(ww);
        return list;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        HashMap<String, Object> beans = new HashMap<String, Object>();
        beans.put("name", "这是标题");
       
//        File template = JxlsUtil.getTemplate("jx2.xlsx");
        File template = new File("C:\\Users\\Administrator\\Desktop\\jx2.xlsx");
        File out = new File("C:\\Users\\Administrator\\Desktop\\jx2Out.xlsx");
        JxlsUtil.exportExcel(template, out, beans);
        System.out.println("导出成功");
    }
}