package pl.losK.pdf;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import pl.losK.model.Company;
import pl.losK.service.DataService;

import java.io.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by m.losK on 2017-03-16.
 */
public class PDFFactory {
    public void createPDF() {
        Properties properties = DataService.loadProperties();
        String pdfPath = properties.getProperty("pdfPath");
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath + File.separator + "file.pdf"));
            document.open();
            document.add(new Paragraph("Hello world!"));
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void pdfFromCompany(Company company) {
        DataService dataService = new DataService();
        List<String> listCompanyInfo = dataService.printCompanyInfo(company);
        Properties properties = DataService.loadProperties();
        String pdfPath = properties.getProperty("pdfPath");
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath + File.separator + "file.pdf"));
            document.setPageSize(new Rectangle(180, 240));
            document.open();
            for (String e : listCompanyInfo) {
                document.add(new Paragraph(e));
            }
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
