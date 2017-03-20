package pl.losK.service;

import pl.losK.model.BillItem;
import pl.losK.xml.JsonFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by k.czechowski83@gmail.com on 2017-03-18.
 */
public class BillItemService {


    private static BillItemService instance = null;

    public BillItemService() {
        this.billItemList = new ArrayList<>();
    }

    public static BillItemService getInstance() {
        if (instance == null) {
            instance = new BillItemService();
        }
        return instance;
    }

    private List<BillItem> billItemList;

    public List<BillItem> getBillItemList() {
        return billItemList;
    }

    public void setBillItemList(List<BillItem> billItemList) {
        this.billItemList = billItemList;
    }

    public String listToJson(List<BillItem> billItemList) {
        JsonFactory jsonFactory = new JsonFactory();
        String listInString = jsonFactory.listToJSon(billItemList);
        return listInString;
    }

}