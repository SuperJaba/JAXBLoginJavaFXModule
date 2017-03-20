package pl.losK.controller;

/**
 * Created by k.czechowski83@gmail.com on 2017-03-18.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pl.losK.model.BillItem;
import pl.losK.service.BillItemService;
import pl.losK.validation.Validation;
import pl.losK.xml.JsonFactory;

import java.util.List;

public class BillItemController extends Controller {

    @FXML
    private TextField productNameTextField;

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
    private TextArea descriptionTextArea;

    @FXML
    void addBillItemOnAction(ActionEvent event) {
        addNewProductToDatabase();
    }

    @FXML
    void submitOnEnter(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            addNewProductToDatabase();
        }
    }

    private void addNewProductToDatabase() {
        if (validationOnAction()) {
            JsonFactory jsonFactory = new JsonFactory();
            BillItemService billItemService = BillItemService.getInstance();
            List<BillItem> billItemList = jsonFactory.loadListDataFromJsonFile();
            BillItem billItem = new BillItem();
            billItem.setItemName(productNameTextField.getText());
            billItem.setDescription(descriptionTextArea.getText());
            billItem.setAmount(Integer.parseInt(amountTextField.getText()));
            billItem.setPrice(Double.parseDouble(priceTextField.getText().replaceAll(",", ".")));
            billItem.setTax(Double.parseDouble(taxTextField.getText().replaceAll(",", ".")));
            billItem.setCode(codeTextField.getText());
            billItemList.add(billItem);
            String listInJSon = jsonFactory.listToJSon(billItemList);
            jsonFactory.saveData(listInJSon);
        }
    }

    private boolean validationOnAction() {
        boolean flag = false;
        if (validation.validate(Validation.FieldType.STRING, productNameTextField.getText())) {
            showErrorAlert("Sorry! Entered name is incorrect. It has to be alphanumeric only");
        } else if (descriptionTextArea == null) {
            showErrorAlert("Sorry! Entered description is incorrect");
        } else if (validateIntegerTextField(amountTextField)) {
            showErrorAlert("Sorry! Entered amount is incorrect");
        } else if (validateDoubleTextField(priceTextField)) {
            showErrorAlert("Sorry! Entered price is incorrect");
        } else if (validateDoubleTextField(taxTextField)) {
            showErrorAlert("Sorry! Entered tax is incorrect");
        } else if (validation.validate(Validation.FieldType.STRING, codeTextField.getText())) {
            showErrorAlert("Sorry! Entered code is incorrect");
        } else {
            flag = true;
        }
        return flag;
    }
}