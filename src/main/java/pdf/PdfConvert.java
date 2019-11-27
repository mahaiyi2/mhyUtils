package pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfConvert {

	/*** @param picturePath 图片地址*/
    private static void createPic(Document document,String picturePath) {
        try {
            Image image = Image.getInstance(picturePath);
            float imgWidth = image.getWidth();
            float imgHeight = image.getHeight();
            
            
            
            float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()+70;
            
            document.setMargins(0,0,0,0);
            
            float imgWC = imgWidth /documentWidth;
            
            
            float documentHeight = imgHeight  / imgWC;
            
//            float documentHeight = documentWidth / 580 * 320;//重新设置宽高
//            float documentHeight = document.getPageSize().getHeight()-document.bottomMargin()-document.topMargin();//重新设置宽高
            
//            image.setPaddingTop(0);
            
            image.scaleAbsolute(documentWidth, documentHeight);//重新设置宽高
            document.add(image);
            Paragraph paragraph = new Paragraph();
            paragraph.add(image);
            document.add(paragraph);
        } catch (Exception ex) {
        }
    }
    public static void image2pdf(String imgPath, String pdfPath) throws DocumentException, IOException {
        Document document = new Document();
        OutputStream os = new FileOutputStream(new File(pdfPath));

        PdfWriter writer = PdfWriter.getInstance(document,os);
        writer.setStrictImageSequence(true);
        document.open();
        createPic(document,imgPath);
        document.close();
    }
    public static void main(String[] args) throws IOException, DocumentException {
        image2pdf("C:\\Users\\ab048704\\Desktop\\pdftest\\扫描 002.jpg","C:\\Users\\ab048704\\Desktop\\pdftest\\完成.pdf");
    }
}
