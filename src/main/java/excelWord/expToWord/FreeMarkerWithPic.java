package excelWord.expToWord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import sun.misc.BASE64Encoder;

public class FreeMarkerWithPic {
	
	public static void main(String[] args) throws Exception{
        /** 初始化配置文件 **/
        Configuration configuration = new Configuration();
        /** 设置编码 **/
        configuration.setDefaultEncoding("utf-8");
        /** 我的ftl文件是放在D盘的**/
        String fileDirectory = "C:\\Users\\Administrator\\Desktop\\";
        /** 加载文件 **/
        configuration.setDirectoryForTemplateLoading(new File(fileDirectory));
        /** 加载模板 **/
        Template template = configuration.getTemplate("gbrmspb.ftl");
        /** 准备数据 **/
        Map<String,String> dataMap = new HashMap<>();

        /** 图片路径 **/
        String imagePath = "C:\\Users\\Administrator\\Desktop\\c.jpeg";
        /** 将图片转化为**/
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imagePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                in.close();
            }
        }
        /** 进行base64位编码 **/
       BASE64Encoder encoder = new BASE64Encoder();
       
        /** 在ftl文件中有${textDeal}这个标签**/
        dataMap.put("XM","一下省略一万字");
        /** 图片数据**/
        dataMap.put("photo",encoder.encode(data));

        /** 指定输出word文件的路径 **/
        String outFilePath = "C:\\Users\\Administrator\\Desktop\\a.doc";
        File docFile = new File(outFilePath);
        FileOutputStream fos = new FileOutputStream(docFile);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);

        if(out != null){
            out.close();
        }
    }

}
