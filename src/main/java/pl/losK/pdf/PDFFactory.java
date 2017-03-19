package pl.losK.pdf;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import pl.losK.model.Bill;
import pl.losK.model.Company;
import pl.losK.service.DataService;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by m.losK on 2017-03-16.
 */
public class PDFFactory {

    public void pdfFromCompany(Company company) {
        DataService dataService = new DataService();
        List<String> listCompanyInfo = dataService.printCompanyInfo(company);
        Properties properties = DataService.loadProperties();
        String pdfPath = properties.getProperty("resourcesPath");
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath + File.separator + "file.pdf"));

            Font font = new Font(Font.TIMES_ROMAN, 18.0f, Font.NORMAL, Color.black);

            document.setPageSize(new Rectangle(180,240));
            document.setMargins(15,15,15,15);
            document.open();
            for(String e: listCompanyInfo)
                document.add(new Paragraph(e,font));
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createPdfBill(Bill bill) {
        DataService dataService = new DataService();
        List<String> companyInfoList = dataService.printBillInfo(bill);

        Properties properties = DataService.loadProperties();
        String pdfPath = properties.getProperty("resourcesPath");

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath + File.separator + "bill.pdf"));

            Font font = new Font(Font.TIMES_ROMAN, 8.0f, Font.NORMAL, Color.black);

            document.setPageSize(new Rectangle(200, 300));
            document.setMargins(15, 15, 15, 15);
            document.open();
            for (String e : companyInfoList)
                document.add(new Paragraph(e, font));
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
