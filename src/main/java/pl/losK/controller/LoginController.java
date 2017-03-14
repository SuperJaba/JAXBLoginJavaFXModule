package pl.losK.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    void submitOnAction(ActionEvent event) {
        System.out.println(passwordField.getText());
        System.out.println(loginField.getText());
        UserService userService = new UserService();
        User user = new User(loginField.getText(), passwordField.getText());
        userService.authenticate(user);
        if (user.getLogin().equals("a") && user.getPassword().equals("a")) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Congratulations!");
            confirmationAlert.show();
        } else {
            Alert wrongCredentialsAlert = new Alert(Alert.AlertType.ERROR, "Wrong credentials!");
            wrongCredentialsAlert.show();
        }
    }
}
