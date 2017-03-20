package pl.losK.xml;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.FileUtils;
import pl.losK.model.Bill;
import pl.losK.model.BillItem;
import pl.losK.service.DataService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.losK on 2017-03-14.
 */
public class JsonFactory {

    private String filePath = DataService.loadProperties().getProperty("resourcesPath");
    private File file = new File(filePath + File.separator + "listOfProducts.dat");

    public String listToJSon(List list) {
        String json = new Gson().toJson(list);
        return json;
    }

    public void saveData(String jsonString) {
        try {
            FileUtils.touch(file);
            FileUtils.writeStringToFile(file, jsonString, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO think if its better way to validate empty file
    public List<BillItem> loadListDataFromJsonFile() {
        final Type REVIEW_TYPE = new TypeToken<List<BillItem>>() {
        }.getType();
        JsonReader reader = null;
        List<BillItem> billItemList = null;
        try {
            reader = new JsonReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (file.length() <= 1) {
            billItemList = new ArrayList<>();
        } else {
            billItemList = new Gson().fromJson(reader, REVIEW_TYPE);
        }
        return billItemList;
    }
}