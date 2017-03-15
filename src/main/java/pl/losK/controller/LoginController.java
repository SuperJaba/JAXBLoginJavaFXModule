package pl.losK.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pl.losK.model.User;
import pl.losK.service.UserService;


/**
 * Created by m.losK on 2017-03-14.
 */
public class LoginController {

    @FXML
    private Button submit;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;

    @FXML
    void submitOnAction() {
        UserService userService = new UserService();
        User user = new User(loginField.getText(), passwordField.getText());

        if (userService.authenticate(user)) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Congratulations! You are logged in.");
            confirmationAlert.show();
        } else {
            Alert wrongCredentialsAlert = new Alert(Alert.AlertType.ERROR, "Sorry! Wrong data");
            wrongCredentialsAlert.show();
        }
    }

    @FXML
    void submitOnEnter(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            submitOnAction();
        }
    }
}
