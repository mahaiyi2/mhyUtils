package excelWord.excel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

 
 /**
  * @author Hongten
  * @created 2014-5-21
  */
 public class Client {
 
     public static void main(String[] args) throws IOException {
         String excel2003_2007 = Common.STUDENT_INFO_XLS_PATH;
         String excel2010 = Common.STUDENT_INFO_XLSX_PATH;
         // read the 2003-2007 excel
         List<Map> list = new ReadExcel().readExcel(excel2003_2007);
         if (list != null) {
             for (Map map : list) {
                 
             }
         }
         System.out.println("======================================");
         // read the 2010 excel
         List<Map> list1 = new ReadExcel().readExcel(excel2010);
         if (list1 != null) {
         }
     }
 }