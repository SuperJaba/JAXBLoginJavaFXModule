package pl.losK.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.losK.model.Address;
import pl.losK.model.Company;
import pl.losK.pdf.PDFFactory;
import pl.losK.service.DataService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by m.losK on 2017-03-15.
 */
public class CompanyController extends Controller {

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
                    break;
                default:
                    streetPrefix = Address.StreetPrefix.STREET;

            }
        }
    }


    @FXML
    Company addCompanyOnAction() {
        //TODO
        return bindToModelCompany();
    }

    private Company bindToModelCompany() {
        Company company = new Company();
        company.setName(companyNameTextField.getText());
        Address address = new Address();
        address.setStreetPrefix(streetPrefix);
        address.setStreetName(streetTextField.getText());
        address.setHouseNumber(houseNumberTextField.getText());
        address.setFlatNumber(flatNumberTextField.getText());
        address.setPostalCode(postalCodeTextField.getText());
        address.setCity(cityNameTextField.getText());
        company.setAddress(address);
        company.setNip(nipTextField.getText());
        company.setRegon(regonTextField.getText());
        DataService dataService = new DataService();
        dataService.printOutCompanyInfo(company);
        validatePostalCode();
        return company;
    }

    @FXML
    void initialize() {
        ToggleGroup group = new ToggleGroup();

        streetRadioButton.setToggleGroup(group);
        avenueRadioButton.setToggleGroup(group);
        squareRadioButton.setToggleGroup(group);
    }

    private void validatePostalCode() {
        Pattern zipPattern = Pattern.compile("(^\\d{2}-\\d{3}$)");
        Matcher zipMatcher = zipPattern.matcher(postalCodeTextField.getText());
        if (zipMatcher.find()) {
            String zip = zipMatcher.group(1);
            showConfirmationAlert("Everything alright!");
        } else {
            showErrorAlert("Sorry! Wrong postal code");
        }
    }

    @FXML
    void createPDFOnAction(ActionEvent event) {
        PDFFactory pdfFactory = new PDFFactory();
        pdfFactory.pdfFromCompany(addCompanyOnAction());
    }

    @FXML
    void validateOnAction(ActionEvent event) {
        validatePostalCode();
    }
}
