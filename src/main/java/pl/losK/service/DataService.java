package pl.losK.service;

import org.apache.commons.io.FileUtils;
import pl.losK.model.User;
import pl.losK.xml.XMLFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by m.losK on 2017-03-13.
 */
public class DataService {

    private File file;

    //Requires correct file path
    public DataService() {
        this.file = new File("C:\\Users\\Vitalij\\Desktop\\sdacademy\\data.dat");
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
}
