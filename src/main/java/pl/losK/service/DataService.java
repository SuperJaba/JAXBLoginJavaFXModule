package pl.losK.service;

import org.apache.commons.io.FileUtils;
import pl.losK.model.*;
import pl.losK.xml.XMLFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by m.losK on 2017-03-13.
 */
public class DataService {

    private File file;

    //Requires correct file path
    public DataService() {
        String filePath = loadProperties().getProperty("resourcesPath");
        this.file = new File(filePath + File.separator + "data.dat");
    }


    public void saveData(String xmlString) {
        try {
            FileUtils.touch(file);
            FileUtils.writeStringToFile(file, xmlString, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User loadData() {
        User user = null;
        XMLFactory<User> xmlFactory = new XMLFactory<>(User.class);
        String xml = null;
        try {
            xml = FileUtils.readFileToString(file, "UTF-8");
            user = xmlFactory.xmlToObject(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<String> printCompanyInfo(Company company) {
        List<String> result = new ArrayList<>();
        result.add(company.getName());
        result.add(company.getAddress().toString());
        result.add(company.getNip());
        result.add(company.getRegon());
        return result;
    }

    public void printOutCompanyInfo(Company company) {
        List<String> listInfo = printCompanyInfo(company);
        for (String line : listInfo) {
            System.out.println(line);
        }
    }

    public static Properties loadProperties() {
        Properties result = new Properties();
        InputStream inputStream = DataService.class.getClassLoader().getResourceAsStream("data.properties");
        try {
            result.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public Bill loadBillData() {

        Bill bill = null;
        XMLFactory<Bill> xmlFactory = new XMLFactory<Bill>(Bill.class);
        String xml;
        try {
            xml = FileUtils.readFileToString(file, "UTF-8");
            bill = xmlFactory.xmlToObject(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bill;
    }

    public List<String> printBillInfo(Bill bill) {
        List<String> result = new ArrayList();
        DecimalFormat df = new DecimalFormat("#.##");

        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        result.add(dt1.format(bill.getDate()).toString());

        result.add("___________________________________");
        for (BillItem e : bill.getListOfItems()) {
            result.add(e.getAmount() + " x " + e.getItemName() + " "
                    + df.format(e.getPrice()) + " zl = " + " " +
                    df.format(e.getAmount() * e.getPrice()) + " zl");
        }
        result.add("___________________________________");
        result.add("\t Price: \t" + df.format(bill.getPrice()).toString());
        result.add("Paid:\t" + bill.getPayment().toString());

        return result;
    }
}
