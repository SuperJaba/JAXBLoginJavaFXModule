package pl.losK.xml;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by k.czechowski83@gmail.com on 2017-03-18.
 */
public class JsonFactory {

    public String listToJSon(List list) {
        String json = new Gson().toJson(list);
        return json;
    }

    public void saveData(String jsonString) {
        File file = new File("C:\\Users\\RENT\\Desktop\\Project\\myProject\\src\\main\\resources\\listOfProducts.dat");
//        FileUtils to klasa statyczna biblioteki
//        touch - pusty plik
        try {
            FileUtils.touch(file);

            //        stringa zapisujemy do pliku za pomoca FileUtils.WriteStringToFile

            FileUtils.writeStringToFile(file, jsonString, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}