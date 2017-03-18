package pl.losK.controller;

/**
 * Created by k.czechowski83@gmail.com on 2017-03-18.
 */

import com.sun.xml.internal.bind.v2.TODO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pl.losK.model.BillItem;
import pl.losK.service.BillItemService;
import pl.losK.xml.JsonFactory;

import java.util.List;

public class BillItemController extends Controller {

    @FXML
    private TextField productTextField;

    @FXML
    private TextField codeTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField amountTextField;

    @FXML
    private Button addProductButton;

    @FXML
    private TextField taxTextField;

    @FXML
    private TextArea descriptionTextField;

    @FXML
    void addProductController(ActionEvent event) {
        addNewProductToDatabase();
    }


    private void addNewProductToDatabase() {
        JsonFactory jsonFactory = new JsonFactory();
        BillItemService billItemService = BillItemService.getInstance();
        List<BillItem> billItemList = billItemService.getBillItemList();
        BillItem billItem = new BillItem();
        billItem.setAmount(amountTextField.getPrefColumnCount());
        billItem.setPrice(priceTextField.getPrefColumnCount());
        billItem.setTax(taxTextField.getPrefColumnCount());
        billItem.setCode(codeTextField.getText());
        billItem.setItemName(productTextField.getText());
        billItem.setDescription(descriptionTextField.getText());
        billItemList.add(billItem);
        String listInJSon = jsonFactory.listToJSon(billItemList);
        jsonFactory.saveData(listInJSon);
        //TODO
        // validation
    }


}