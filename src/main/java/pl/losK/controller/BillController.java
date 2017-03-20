package pl.losK.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import pl.losK.model.Bill;
import pl.losK.model.BillItem;
import pl.losK.service.BillItemService;
import pl.losK.xml.JsonFactory;

import java.util.List;


/**
 * Created by m.losK on 2017-03-18.
 */
public class BillController extends Controller {
    @FXML
    private TextField houseNumberField;

    @FXML
    private Button submit;

    @FXML
    private TextField cityField;

    @FXML
    private Button validateButton;

    @FXML
    private TextField countryField;

    @FXML
    private RadioButton cardRadio;

    @FXML
    private TextField postCodeField;

    @FXML
    private Button makePdfButton;

    @FXML
    private TextField streetNameField;

    @FXML
    private RadioButton cashRadio;

    @FXML
    private TextField flatNumberField;

    @FXML
    private GridPane listGrid;

    @FXML
    private TextField nipField;

    @FXML
    void addCompanyOnAction(ActionEvent event) {

    }

    @FXML
    private Label priceAll;


    @FXML
    void makePdfOnAction(ActionEvent event) {

    }

    @FXML
    void validateOnAction(ActionEvent event) {

    }

    @FXML
    void changeAmount() {

    }

//    @FXML
//    void choosePaymentOnAction(ActionEvent event) {
//        if (event.getSource() instanceof RadioButton){
//            RadioButton currentPrefixRadioButton = (RadioButton) event.getSource();
////            currentPrefixRadioButton.get
//            String buttonName = currentRadioButton.getText();
//            switch (buttonName){
//                case "Card":
//                    cardPrefix = CardPrefix.Card;
//                    break;
//                case "Cash":
//                    cashPrefix = CashPrefix.CASH;
//                    break;         }
//        }
//    }

    //TODO unchecked loadList
    @FXML
    void initialize() {
        ToggleGroup group = new ToggleGroup();
        cashRadio.setToggleGroup(group);
        cardRadio.setToggleGroup(group);
        //for
        Bill bill = new Bill();
        BillItemService billItemService = new BillItemService();
        List<BillItem> list = new JsonFactory().loadListDataFromJsonFile();

        int col = 0;
        int row = 0;
        for (BillItem line : list) {

            listGrid.add(new Label(line.getCode()), col, row + 1);
            listGrid.add(new Label(line.getItemName()), col + 1, row + 1);
            TextField textField = new TextField();
            textField.setId("amountField");
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
//                System.out.println(line.getPrice()  + " to " + newValue);
//                System.out.println(line);
                bill.updateItem(line, Integer.valueOf(newValue));
                System.out.println(bill.getPrice());
            });
            listGrid.add(textField, col + 2, row + 1);
//            listgrid.add(new TextField();)
            row++;
            priceAll.setText(bill.getPrice().toString());
        }
    }
}
