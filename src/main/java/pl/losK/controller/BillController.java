package pl.losK.controller;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import pl.losK.model.Bill;
import pl.losK.model.BillItem;
import pl.losK.model.Payment;
import pl.losK.pdf.PDFFactory;
import pl.losK.service.BillItemService;
import pl.losK.service.DataService;
import pl.losK.xml.JsonFactory;
import pl.losK.xml.XMLFactory;

import java.util.List;


/**
 * Created by m.losK on 2017-03-18.
 */
public class BillController extends Controller {
    @FXML
    private Label priceAll;
    @FXML
    private Button printToPdf;
    @FXML
    private Button saveToFile;
    @FXML
    private RadioButton cashRadio;
    @FXML
    private GridPane listgrid;
    @FXML
    private RadioButton cardRadio;

    @FXML
    void cashRadioClick(ActionEvent event) {
        bill.setPayment(Payment.CASH);
//        System.out.println("CASH");
    }

    @FXML
    void cardRadioClick(ActionEvent event) {
        bill.setPayment(Payment.CARD);
//        System.out.println("CARD");
    }

    @FXML
    void saveToFileClick(ActionEvent event) {
        DataService dataService = new DataService();
        XMLFactory<Bill> xmlFactory = new XMLFactory<>(Bill.class);
        String xmlBill = xmlFactory.objectToXml(bill);
        dataService.saveData(xmlBill);
    }

    @FXML
    void printToPdfClick(ActionEvent event) {
        PDFFactory pdfFactory = new PDFFactory();
        pdfFactory.createPdfBill(bill);
    }

    Bill bill;

    @FXML
    void initialize() {
        bill = new Bill();
        //ustawianie radio buttonow peymentu
        ToggleGroup group = new ToggleGroup();
        cashRadio.setToggleGroup(group);
        cardRadio.setToggleGroup(group);
        if (bill.getPayment() == Payment.CARD) {
            cardRadio.setSelected(true);
        }
        if (bill.getPayment() == Payment.CASH) {
            cashRadio.setSelected(true);
        }
        //ustawianie listy produktow
        BillItemService billItemService = BillItemService.getInstance();
        JsonFactory jsonFactory = new JsonFactory();
        List<BillItem> list = jsonFactory.loadListDataFromJsonFile();
        int col = 0;
        int row = 0;
        Integer number = 1;
        for (BillItem line : list) {
            //Kolumna 1: numeracja
            listgrid.add(new Label(number.toString()), col, row + 1);
            number++;
            //Koluna 2: nazwa item'u
            listgrid.add(new Label(line.getItemName()), col + 1, row + 1);
            //Kolumna 3: Cena item'u
            listgrid.add(new Label(line.getPrice() + " PLN"), col + 2, row + 1);
            //Kolumna 4: Ilość zakupiona
            Label itemPriceTotalLabel = new Label();//Label dla kolumny 5
            TextField textField = new TextField();
            textField.setId("amountfield");
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                bill.updateItem(line, newValue);
//                System.out.println(bill.getPrice());
                priceAll.setText(bill.getPrice().toString());
                if (line.getAmount() > 0) {
                    itemPriceTotalLabel.setText(line.getPrice() * line.getAmount() + " PLN");
                } else {
                    itemPriceTotalLabel.setText("");
                }
            });
            textField.setPromptText("0");
            textField.setAlignment(Pos.BASELINE_CENTER);
            listgrid.add(textField, col + 3, row + 1);
            //Kolumna 5: Cena zakpu
            listgrid.add(itemPriceTotalLabel, col + 4, row + 1);
            listgrid.setHalignment(itemPriceTotalLabel, HPos.CENTER);
            //Zmiana wersu
            row++;
        }
    }
}