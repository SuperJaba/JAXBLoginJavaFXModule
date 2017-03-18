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

    protected BillItemService() {
        this.billItemList = new ArrayList<>();
        this.billItemList = createList();
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

    public List<BillItem> createList() {
        List<BillItem> billitemList = new ArrayList<>();
        BillItem paczek = new BillItem();
        paczek.setItemName("Paczek");
        paczek.setAmount(1);
        paczek.setPrice(2.70);
        paczek.setDescription("Pączek z marmoladą");
        paczek.setTax(0.08);
        paczek.setCode("72235");

        billitemList.add(paczek);

        BillItem herbata = new BillItem();
        herbata.setItemName("Herbata");
        herbata.setAmount(1);
        herbata.setPrice(1.70);
        herbata.setDescription("Malinowa");
        herbata.setTax(0.22);
        herbata.setCode("62435");

        billitemList.add(herbata);

        BillItem ciachoZeSzpinakiem = new BillItem();
        ciachoZeSzpinakiem.setItemName("Ciastko ze szpinakiem");
        ciachoZeSzpinakiem.setAmount(1);
        ciachoZeSzpinakiem.setPrice(3.70);
        ciachoZeSzpinakiem.setDescription("Ciasto francuskie nadziewane szpinakiem");
        ciachoZeSzpinakiem.setTax(0.08);
        ciachoZeSzpinakiem.setCode("623445");

        billitemList.add(ciachoZeSzpinakiem);

        BillItem kawa = new BillItem();
        kawa.setItemName("Kawa");
        kawa.setAmount(1);
        kawa.setPrice(5.0);
        kawa.setDescription("Swiezo mielona kawa JAVA prosto z plantacji");
        kawa.setTax(0.22);
        kawa.setCode("97034567");

        billitemList.add(kawa);
        return billitemList;
    }

    public String listToJson(List<BillItem> billItemList) {
        JsonFactory jsonFactory = new JsonFactory();
        String listInString = jsonFactory.listToJSon(billItemList);
        return listInString;
    }

}