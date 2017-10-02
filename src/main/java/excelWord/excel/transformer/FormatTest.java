package excelWord.excel.transformer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sun.media.sound.InvalidFormatException;

import net.sf.jxls.transformer.XLSTransformer;

public class FormatTest {
	public void applicationExport() throws Exception {
		String templateFileName = "uploadFiles/file/申请民政救助家庭经济状况申报竖表.xlsx";
		String destFileName = "申请民政救助家庭经济状况申报竖表.xlsx";

		Map<String, Object> beanParams = new HashMap<String, Object>();
		beanParams.put("pd", "");
		beanParams.put("ccpd", "");
		beanParams.put("fgpd", "");
		beanParams.put("fgpdsize", "");
		beanParams.put("gtpd", "");
		beanParams.put("gtpdsize", "");
		InputStream in = null;
		OutputStream out = null;

		// 设置响应
//		response.setHeader("Content-Disposition", "attachment;filename="
//				+ URLEncoder.encode(destFileName, "UTF-8"));
//		response.setContentType("application/vnd.ms-excel");
		try {
			InputStream is = new FileInputStream(templateFileName);
			XLSTransformer transformer = new XLSTransformer();
			XSSFWorkbook workBook = (XSSFWorkbook) transformer.transformXLS(is,
					beanParams);

			// 将图片以字节流的方式输入输出
			// XSSFSheet sheet = workBook.getSheetAt(0);
			// String picture = "C:/Users/Administrator/Desktop/appewm2.png";
			// ByteArrayOutputStream bos = new ByteArrayOutputStream();
			// BufferedImage BufferImg = ImageIO.read(new File(picture));
			// ImageIO.write(BufferImg, "png", bos);
			// public HSSFClientAnchor(int dx1,int dy1,int dx2,int dy2,short
			// col1,int row1,short col2,int row2)
			// XSSFClientAnchor anchor = new
			// XSSFClientAnchor(0,0,0,0,(short)10,10,(short)20,50);
			// XSSFDrawing patriarch = sheet.createDrawingPatriarch();
			// patriarch.createPicture(anchor,
			// workBook.addPicture(bos.toByteArray(),
			// workBook.PICTURE_TYPE_JPEG));
			// OutputStream os = new FileOutputStream(destPath);

			// in=new BufferedInputStream(new
			// FileInputStream(templateFileName));
			// Workbook workbook=transformer.transformXLS(in, beans);
//			out = response.getOutputStream();
			out = new FileOutputStream("");
			// 将内容写入输出流并把缓存的内容全部发出去
			workBook.write(out);
			out.flush();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
