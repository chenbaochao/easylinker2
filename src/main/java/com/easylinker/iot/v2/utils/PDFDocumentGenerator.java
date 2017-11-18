package com.easylinker.iot.v2.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;

/**
 * Created by wwhai on 2017/11/18.
 */
@Component
public class PDFDocumentGenerator {
    private Font font;
    private BaseFont chinese;
    Document document;

    public PDFDocumentGenerator() throws Exception {
        // 设置中文字体
        chinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        document = new Document(PageSize.A4, 20, 20, 20, 20);

        font = new Font(chinese);
        font.setSize(15);
        font.setStyle(FontFactory.HELVETICA);
        font.setStyle(Font.BOLD);

    }

    public static PDFDocumentGenerator getInstance() throws Exception {
        return new PDFDocumentGenerator();
    }

    public void index(String test) throws Exception {
        Paragraph content = new Paragraph(test, font);
        content.setAlignment(Element.ALIGN_LEFT);
        PdfWriter.getInstance(document, new FileOutputStream("/createSamplePDF.pdf"));
        document.open();
        document.newPage();
        document.add(content);
        document.newPage();
        document.add(content);
        document.close();
    }

    public static void main(String[] args) throws Exception {
        PDFDocumentGenerator pdfDocumentGenerator = PDFDocumentGenerator.getInstance();
        pdfDocumentGenerator.index("哈哈哈MMP");
    }
}
