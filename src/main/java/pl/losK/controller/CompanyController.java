package pl.losK.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import pl.losK.model.Address;
import pl.losK.model.Company;

/**
 * Created by m.losK on 2017-03-15.
 */
public class CompanyController {

    @FXML
    private TextField nipTextField;

    @FXML
    private TextField flatNumberTextField;

    @FXML
    private TextField regonTextField;

    @FXML
    private Button addCompanyButton;

    @FXML
    private TextField postalCodeTextField;

    @FXML
    private TextField cityNameTextField;

    @FXML
    private RadioButton squareRadioButton;

    @FXML
    private TextField houseNumberTextField;

    @FXML
    private RadioButton avenueRadioButton;

    @FXML
    private TextField companyNameTextField;

    @FXML
    private RadioButton streetRadioButton;

    @FXML
    private TextField streetTextField;

    private Address.StreetPrefix streetPrefix;

//    @FXML
//    void choosePrefixOnAction(ActionEvent event){
//        if(event.getSource() instanceof RadioButton){
//            RadioButton currentPrefixRadioButton = (RadioButton) event.getSource();
//            squareRadioButton.setSelected(true);
//        }
//    }

    @FXML
    void choosePrefixOnAction(ActionEvent event) {
        if (event.getSource() instanceof RadioButton) {
            RadioButton currentPrefixRadioButton = (RadioButton) event.getSource();
            String buttonName = currentPrefixRadioButton.getText();
            switch (buttonName) {
                case "ul.":
                    streetPrefix = Address.StreetPrefix.STREET;
                    break;
                case "al.":
                    streetPrefix = Address.StreetPrefix.AVENUE;
                    break;
                case "pl.":
                    streetPrefix = Address.StreetPrefix.SQUARE;

            }
        }
    }


    @FXML
    void addCompanyOnAction(ActionEvent event) {
        Company company = new Company();
        company.setName(companyNameTextField.getText());
        Address address = new Address();
        address.setStreetPrefix(Address.StreetPrefix.STREET);
        address.setStreetName(streetTextField.getText());
        address.setHouseNumber(houseNumberTextField.getText());
        address.setFlatNumber(flatNumberTextField.getText());
        address.setPostalCode(postalCodeTextField.getText());
        address.setCity(cityNameTextField.getText());
        company.setAddress(address);
        company.setNip(nipTextField.getText());
        company.setRegon(regonTextField.getText());
        //TODO
//        company.setId();
        System.out.println(company);
    }

    @FXML
    void initialize(){
        ToggleGroup group = new ToggleGroup();

        streetRadioButton.setToggleGroup(group);
        avenueRadioButton.setToggleGroup(group);
        squareRadioButton.setToggleGroup(group);
    }

}
