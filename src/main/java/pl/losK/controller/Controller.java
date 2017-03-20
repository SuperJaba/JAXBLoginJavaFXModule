package pl.losK.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by m.losK on 2017-03-16.
 */
public class Controller {

    protected MainController mainController;

    protected MenuController menuController;

    protected MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    protected void showErrorAlert(String message) {
        showAlert(Alert.AlertType.ERROR, message);
    }

    protected void showConfirmationAlert(String message) {
        showAlert(Alert.AlertType.CONFIRMATION, message);
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        new Alert(alertType, message).show();
    }

    protected boolean validatePostalCode(TextField postalCodeTextField) {
        boolean flag = false;
        Pattern zipPattern = Pattern.compile("(^\\d{2}-\\d{3}$)");
        Matcher zipMatcher = zipPattern.matcher(postalCodeTextField.getText());
        if (zipMatcher.find()) {
            String zip = zipMatcher.group(1);
            showConfirmationAlert("Everything alright!");
            flag = true;
        } else {
            showErrorAlert("Sorry! Wrong postal code");
        }
        return flag;
    }

    protected boolean validateStringTextField(TextField givenStringTextField) {
        boolean flag = false;
        if (givenStringTextField.getText() == null || givenStringTextField.getText().equals("")) {
            flag = true;
        } else if(!isAlphaNumericWithSpaces(givenStringTextField.getText())){
            flag = true;
        }
        return flag;
    }

    public boolean isAlphaNumericWithSpaces(String s){
        String pattern= "^[a-zA-Z0-9 ]*$";
        return s.matches(pattern);
    }

    protected boolean validateIntegerTextField(TextField givenIntegerTextField) {
        boolean flag = false;
        if (givenIntegerTextField.getText() == null || givenIntegerTextField.getText().equals("")) {
            flag = true;
        }
        String properString = givenIntegerTextField.getText();
        if(properString.equals("0") || !Pattern.matches("^\\d{1,9}$", properString)){
            flag = true;
        }
        return flag;
    }

    protected boolean validateDoubleTextField(TextField givenDoubleTextField) {
        boolean flag = false;
        if (givenDoubleTextField.getText() == null || givenDoubleTextField.getText().equals("")) {
            flag = true;
        }
        String properString = givenDoubleTextField.getText().replaceAll(",", ".");
        if(!Pattern.matches("^[+]?\\d{1,10}+(\\.?(\\d{0,2}))?$", properString)){
            flag = true;
        }
        return flag;
    }
}
