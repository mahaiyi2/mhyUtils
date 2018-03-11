package excelWord.excel.jxls2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;

public class JxlsUtilsBAK{
    
    public static void exportExcel(InputStream is, OutputStream os, Map<String, Object> model) throws IOException{
//        Context context = PoiTransformer.createInitialContext();
        Context context = new Context();
        if (model != null) {
            for (String key : model.keySet()) {
                context.putVar(key, model.get(key));
            }
        }
        JxlsHelper jxlsHelper = JxlsHelper.getInstance();
        Transformer transformer  = jxlsHelper.createTransformer(is, os);
//        获得配置
        JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator)transformer.getTransformationConfig().getExpressionEvaluator();
//        设置静默模式，不报警告
        evaluator.getJexlEngine().setSilent(true);
//        函数强制，自定义功能
        Map<String, Object> funcs = new HashMap<String, Object>();
        funcs.put("utils", new JxlsUtilsBAK());    //添加自定义功能
        evaluator.getJexlEngine().setFunctions(funcs);
//        必须要这个，否者表格函数统计会错乱
        jxlsHelper.setUseFastFormulaProcessor(false).processTemplate(context, transformer);
//        jxlsHelper.processTemplate(is, os, context);
    }

    public static void exportExcel(File xls, File out, Map<String, Object> model) throws FileNotFoundException, IOException {
            exportExcel(new FileInputStream(xls), new FileOutputStream(out), model);
    }
    
    public static void exportExcel(String templatePath, OutputStream os, Map<String, Object> model) throws Exception {
        File template = getTemplate(templatePath);
        if(template != null){
            exportExcel(new FileInputStream(template), os, model);    
        } else {
            throw new Exception("Excel 模板未找到。");
        }
    }
    
    //获取jxls模版文件
    public static File getTemplate(String path){
        File template = new File(path);
        if(template.exists()){
            return template;
        }
        return null;
    }    
    
    // 日期格式化
    public String dateFmt(Date date, String fmt) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat dateFmt = new SimpleDateFormat(fmt);
            return dateFmt.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    // if判断
    public Object ifelse(boolean b, Object o1, Object o2) {
        return b ? o1 : o2;
    }
    
    
    
    
    public static void main(String[] args) throws Exception {
        // 模板路径和输出流
        String templatePath = "C:\\Users\\Administrator\\Desktop\\jx2.xlsx";
        OutputStream os = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\jx2Out.xlsx");
//
//    	String templatePath = "C:\\Users\\Administrator\\Desktop\\object_collection_template - 副本.xls";
//        OutputStream os = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\jx2Out.xls");
        // 定义一个Map，往里面放入要在模板中显示数据
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", "001");
        model.put("name", "张三");
        model.put("age", 18);
        //调用之前写的工具类，传入模板路径，输出流，和装有数据Map
        JxlsUtilsBAK.exportExcel(templatePath, os, model);
        os.close();
        System.out.println("完成");
    }
}